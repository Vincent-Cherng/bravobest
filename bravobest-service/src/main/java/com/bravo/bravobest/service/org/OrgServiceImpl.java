package com.bravo.bravobest.service.org;

import com.bravo.bravobest.binterface.org.OrgService;
import com.bravo.bravobet.api.entity.Org;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrgServiceImpl implements OrgService {


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
        return null;
    }
}
