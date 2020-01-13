package com.bravo.bravobest.dao;

import com.bravo.bravobest.api.entity.User;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

    /**
     * 查询list
     * @param pmap
     * @return
     * @throws Exception
     */
    List<User> queryList(Map<String, Object> pmap) throws Exception;

    /**
     * 查询
     * @param pmap
     * @return
     * @throws Exception
     */
    User queryOne(Map<String, Object> pmap) throws Exception;

    /**
     * 新增人员
     * @param user
     * @return
     * @throws Exception
     */
    int doSave(User user) throws Exception;
}
