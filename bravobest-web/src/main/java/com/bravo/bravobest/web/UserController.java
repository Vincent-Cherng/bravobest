package com.bravo.bravobest.web;

import com.bravo.bravobest.api.entity.ResultData;
import com.bravo.bravobest.binterface.UserService;
import com.bravo.bravobest.api.entity.User;
import com.bravo.bravobest.common.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@RestController
//@Slf4j
public class UserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 查询同一部门下的用户
     * @return
     * @throws Exception
     */
    @RequestMapping("/queryUsersInOrg")
    public ResultData queryUsersInOrg(HttpServletRequest req) throws Exception {
        Map<String,Object> map = new HashMap<>();
        User curUser = super.getCurUser(req);
        map.put("orgNo",curUser.getOrgNo());
        map.put("userNo",curUser.getUserNo());
        map.put("excludeOneSelf","Y");
        ResultData resultData = userService.queryList(map);
        return resultData;
    }
}
