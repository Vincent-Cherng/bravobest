package com.bravo.bravobest.dao.user;

import com.bravo.bravobet.api.entity.User;
import java.util.List;
import java.util.Map;

public interface UserDao {

    /**
     * 查询list
     * @param pmap
     * @return
     * @throws Exception
     */
    List<User> queryList(Map<String,Object> pmap) throws Exception;

    /**
     * 查询
     * @param pmap
     * @return
     * @throws Exception
     */
    User queryOne(Map<String,Object> pmap) throws Exception;
}
