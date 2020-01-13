package com.bravo.bravobest.web;


import com.bravo.bravobest.binterface.OrgService;
import com.bravo.bravobest.api.entity.Org;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/org")
@RestController
public class OrgController {

    @Autowired
    private OrgService orgService;


    @RequestMapping("/test")
    public String testOrg(String s) throws Exception {
        Map<String, Object> map = new HashMap<>();
//        List<Org> orgs = orgService.queryList(map);
        Org orgs = orgService.queryOne(map);
        System.out.println(orgs);
        return "hello,Org";
    }
}
