package com.bravo.bravobest.service;

import com.bravo.bravobest.binterface.OrgService;
import com.bravo.bravobest.dao.OrgDao;
import com.bravo.bravobest.api.entity.Org;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Transactional
public class OrgServiceImpl implements OrgService {

    @Autowired
    private OrgDao orgDao;

    @Override
    public List<Org> queryList(Map<String, Object> pmap) throws Exception {
        System.out.println(pmap);
        List<Org> orgs = new ArrayList<>();
        Org org = new Org();
        org.setOrgNo("111");
        org.setOrgName("222");
        orgs.add(org);
        return orgs;
    }

    @Override
    public Org queryOne(Map<String, Object> pmap) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("orgNo","1");
        Org org = orgDao.queryOne(map);



        return org;
    }
}