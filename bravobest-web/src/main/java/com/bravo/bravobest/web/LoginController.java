package com.bravo.bravobest.web;

import com.bravo.bravobest.api.entity.ResultData;
import com.bravo.bravobest.common.util.ResultUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/")
@RestController
public class LoginController {


//    @CrossOrigin
    @RequestMapping("login")
    public ResultData login(String userName, String password, String checkCode) {
        if (StringUtils.isBlank(userName)) {
            return ResultUtils.fail("用户名必填！");
        }
        if (StringUtils.isBlank(password)) {
            return ResultUtils.fail("密码必填！");
        }
        /*if (StringUtils.isBlank(checkCode)) {
            return ResultUtils.fail("验证码必填！");
        }*/
        if ("admin".equals(userName) && "admin".equals(password)) {
            return ResultUtils.success("登录成功！");
        }
        return ResultUtils.fail();
    }

}
