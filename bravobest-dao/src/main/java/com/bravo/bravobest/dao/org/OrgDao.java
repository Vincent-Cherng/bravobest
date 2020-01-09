package com.bravo.bravobest.dao.org;

import com.bravo.bravobet.api.entity.Org;
import java.util.List;
import java.util.Map;

public interface OrgDao {

    /**
     * 查询list
     * @param pmap
     * @return
     * @throws Exception
     */
    List<Org> queryList(Map<String,Object> pmap) throws Exception;

    /**
     * 查询
     * @param pmap
     * @return
     * @throws Exception
     */
    Org queryOne(Map<String,Object> pmap) throws Exception;
}
