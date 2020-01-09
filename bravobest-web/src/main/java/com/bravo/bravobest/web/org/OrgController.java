package com.bravo.bravobest.web.org;


import com.bravo.bravobest.binterface.org.OrgService;
import com.bravo.bravobet.api.entity.Org;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/org")
@RestController
public class OrgController {

    @Autowired
    private OrgService orgService;


    @RequestMapping("/test")
    public String testOrg(String s) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("name","lilili");
        List<Org> orgs = orgService.queryList(map);
        System.out.println(orgs);
        return "hello,Org";
    }
}
