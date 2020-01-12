package com.bravo.bravobest.binterface;

import com.bravo.bravobet.api.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {


    /**
     * 查询人员列表
     * @param pmap
     * @return
     * @throws Exception
     */
    List<User> queryList(Map<String, Object> pmap) throws Exception;

    /**
     * 查询单个人员
     * @param pmap
     * @return
     * @throws Exception
     */
    User queryOne(Map<String, Object> pmap) throws Exception;
}
