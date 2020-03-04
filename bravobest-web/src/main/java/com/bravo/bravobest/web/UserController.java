package com.bravo.bravobest.web;

import com.bravo.bravobest.api.entity.ResultData;
import com.bravo.bravobest.api.entity.User;
import com.bravo.bravobest.binterface.UserService;
import com.bravo.bravobest.common.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@RestController
//@Slf4j
public class UserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 查询同一部门下还没考评过的用户
     * @return
     * @throws Exception
     */
    @RequestMapping("/queryUsersInOrg")
    public ResultData queryUsersInOrg(HttpServletRequest req,String evaluationPeriod) throws Exception {
        Map<String, Object> map = super.requestToMap(req, new String[]{"currentPage", "pageSize"});
        User curUser = super.getCurUser(req);
        map.put("orgNo",curUser.getOrgNo());
        map.put("userNo",curUser.getUserNo());
        map.put("evaluationPeriod",evaluationPeriod);
        ResultData resultData = userService.queryUnEvaluatePerson(map);
        return resultData;
    }
}
