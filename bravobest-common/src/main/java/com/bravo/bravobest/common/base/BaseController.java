package com.bravo.bravobest.common.base;

import com.bravo.bravobest.api.entity.User;
import com.bravo.bravobest.common.page.Pager;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {


    /**
     * 获取当前登录人
     * @param req
     * @return
     * @throws Exception
     */
    public User getCurUser(HttpServletRequest req) throws Exception {
        User user = (User)req.getSession().getAttribute("user");
        return user;
    }

    /**
     * 获取当前登录人编号
     * @param req
     * @return
     * @throws Exception
     */
    public String getCurUserNo(HttpServletRequest req) throws Exception {
        String userNo = this.getCurUser(req).getUserNo();
        return userNo;
    }

    /**
     * 把request请求的参数放在map中
     * @return
     */
    public Map<String,Object> requestToMap(HttpServletRequest req,String[] paramStr){
        Map<String, Object> map = new HashMap<>();
        for (String param : paramStr) {
            if (StringUtils.isNotBlank(param)){
                if("currentPage".equals(param)) {
                    Pager pager = null;
                    if(map.containsKey("pager")){
                        pager = (Pager) map.get("pager");
                    } else {
                        pager = new Pager();
                    }
                    pager.setCurrentPage(Integer.parseInt(req.getParameter(param)));
                    map.put("pager",pager);
                } else if("pageSize".equals(param)) {
                    Pager pager = null;
                    if(map.containsKey("pager")){
                        pager = (Pager) map.get("pager");
                    } else {
                        pager = new Pager();
                    }
                    pager.setPageSize(Integer.parseInt(req.getParameter(param)));
                    map.put("pager",pager);
                } else {
                    String paramValue = req.getParameter(param);
                    map.put(param,paramValue);
                }
            }
        }
        return map;
    }

}
