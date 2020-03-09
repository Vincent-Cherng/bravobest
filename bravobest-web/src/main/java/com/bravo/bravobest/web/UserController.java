package com.bravo.bravobest.web;

import com.bravo.bravobest.api.entity.ResultData;
import com.bravo.bravobest.api.entity.User;
import com.bravo.bravobest.binterface.UserService;
import com.bravo.bravobest.common.base.BaseController;
import com.bravo.bravobest.common.util.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.Map;

@RequestMapping("/user")
@RestController
//@Validated
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

    @RequestMapping("/test")
    public ResultData test(HttpServletRequest req, @NotBlank(message = "用户编号不能为空") String userNo,
                           @NotBlank(message = "部门编号不能为空") String orgNo) throws Exception {
        System.out.println(userNo);
        return ResultUtils.success();
    }


}
