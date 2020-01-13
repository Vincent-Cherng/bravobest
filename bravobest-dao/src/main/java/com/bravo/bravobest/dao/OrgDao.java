package com.bravo.bravobest.dao;

import com.bravo.bravobest.api.entity.Org;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrgDao {

    /**
     * 查询list
     * @param pmap
     * @return
     * @throws Exception
     */
    List<Org> queryList(Map<String, Object> pmap) throws Exception;

    /**
     * 查询
     * @param pmap
     * @return
     * @throws Exception
     */
    Org queryOne(Map<String, Object> pmap) throws Exception;
}
