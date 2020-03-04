package com.bravo.bravobest.common.page;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 分页拦截器
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class , Integer.class}) })
@Slf4j
public class PaginationInterceptor implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取拦截的对象
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        // 待执行的sql对象
        BoundSql boundSql = statementHandler.getBoundSql();
        // 判断是否查询语句
        if (!"".equals(boundSql.getSql()) && boundSql.getSql().toUpperCase().trim().startsWith("SELECT")) {
            // 获取参数
            Object params = boundSql.getParameterObject();
            if (params instanceof Map) { // 请求为多个参数，参数采用Map封装
                return complexParamsHandler((Map<?, ?>) params,boundSql, invocation,statementHandler);
            } else if (params instanceof Pager) { // 单个参数且为Page，则表示该操作需要进行分页处理
                return pageHandlerExecutor(params,boundSql, invocation,statementHandler);
            }
        }
        return invocation.proceed();
    }


    private Object pageHandlerExecutor(Object params,BoundSql boundSql, Invocation invocation,StatementHandler statementHandler) throws Exception{
        Pager pager = (Pager) params;
        Connection connection = (Connection) invocation.getArgs()[0];

        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        // 查询总条数
        int count = count(connection,
                (ParameterHandler) metaObject.getValue("delegate.parameterHandler"), boundSql);
        pager.setTotalRecord(count);
        // 拼接分页语句
        String pageSql = boundSql.getSql() + " limit " +
                ((pager.getPage() - 1) * pager.getRows()) +
                ", " + pager.getRows();
        metaObject.setValue("delegate.boundSql.sql", pageSql);
        return invocation.proceed();
    }


    private Object complexParamsHandler(Map params,BoundSql boundSql, Invocation invocation,StatementHandler statementHandler) throws Throwable {
        //判断参数中是否指定分页
        if (containsPage(params)) {
            return pageHandlerExecutor((Pager) params.get("pager"),boundSql, invocation,statementHandler);
        } else {
            return invocation.proceed();
        }
    }

    private boolean containsPage(Map<?, ?> params) {
        if(params == null){
            return false;
        }else if(!params.containsKey("pager")){
            return false;
        }
        Object page = params.get("pager");
        if(page==null){
            return false;
        }else if(page instanceof Pager){
            return true;
        }
        return false;
    }


    public int count(Connection connection, ParameterHandler parameterHandler, BoundSql boundSql) {
        String countSql = "select count(0) from (" + boundSql.getSql() + ") as total";
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {
            countStmt = connection.prepareStatement(countSql);
            parameterHandler.setParameters(countStmt);
            rs = countStmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != countStmt) {
                    countStmt.close();
                }
                if (null != rs) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public Object plugin(Object o) {
        if (o instanceof StatementHandler) {
            return Plugin.wrap(o, this);
        } else {
            return o;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
