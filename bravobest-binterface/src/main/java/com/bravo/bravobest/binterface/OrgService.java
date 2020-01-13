package com.bravo.bravobest.binterface;

import com.bravo.bravobest.api.entity.Org;

import java.util.List;
import java.util.Map;

public interface OrgService {

    /**
     * 查询部门列表
     * @param pmap
     * @return
     * @throws Exception
     */
    List<Org> queryList(Map<String, Object> pmap) throws Exception;

    /**
     * 查询单个部门
     * @param pmap
     * @return
     * @throws Exception
     */
    Org queryOne(Map<String, Object> pmap) throws Exception;



}
