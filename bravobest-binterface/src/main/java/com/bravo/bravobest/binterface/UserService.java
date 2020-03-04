package com.bravo.bravobest.binterface;

import com.bravo.bravobest.api.entity.ResultData;
import com.bravo.bravobest.api.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * 查询人员列表
     * @param pmap
     * @return
     * @throws Exception
     */
    ResultData queryList(Map<String, Object> pmap) throws Exception;

    /**
     * 根据登录名查询单个人员
     * @param loginName
     * @return
     * @throws Exception
     */
    ResultData queryOneByLoginName(String loginName) throws Exception;



}
