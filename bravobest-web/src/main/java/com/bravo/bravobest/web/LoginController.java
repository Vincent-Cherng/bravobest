package com.bravo.bravobest.web;

import com.bravo.bravobest.api.entity.ResultData;
import com.bravo.bravobest.api.entity.User;
import com.bravo.bravobest.binterface.UserService;
import com.bravo.bravobest.common.util.ResultUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;


@RequestMapping("/")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

//    @CrossOrigin
    @RequestMapping("login")
    public ResultData login(HttpServletRequest req,String loginName, String password, String checkCode) throws Exception {
        if (StringUtils.isBlank(loginName)) {
            return ResultUtils.fail(-2,"用户名必填！");
        }
        if (StringUtils.isBlank(password)) {
            return ResultUtils.fail(-2,"密码必填！");
        }
        /*if (StringUtils.isBlank(checkCode)) {
            return ResultUtils.fail(-2,"验证码必填！");
        }*/
        ResultData resultData = userService.queryOneByLoginName(loginName);
        if (resultData.getCode() != ResultData.DEFAULT_SUCCESS_CODE){
            return ResultUtils.fail(-1,"登录失败！");
        }
        User user = (User)resultData.getData();
        if (user == null) {
            return ResultUtils.fail(-3,"用户登录名不存在！");
        }
        if (!password.equals(user.getPassword())) {
            return ResultUtils.fail(-4,"用户密码输入错误！");
        }
        req.getSession().setAttribute("user",user);
        return ResultUtils.success(user);
    }


    @RequestMapping("logOut")
    public ResultData logout(HttpServletRequest req){
//        req.getSession().removeAttribute("user");
        return ResultUtils.success();
    }
}
