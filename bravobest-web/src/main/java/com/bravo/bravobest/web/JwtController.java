package com.bravo.bravobest.web;

import com.bravo.bravobest.api.entity.ResultData;
import com.bravo.bravobest.api.entity.User;
import com.bravo.bravobest.api.jwt.JwtKit;
import com.bravo.bravobest.api.jwt.JwtProperties;
import com.bravo.bravobest.binterface.UserService;
import com.bravo.bravobest.common.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JwtController
 * @Description
 * @Author chengfeng
 * @Date 2/5/21 4:05 PM
 **/
@RestController
@RequestMapping("/jwt")
public class JwtController {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtKit jwtKit;

    @Autowired
    private UserService userService;


    @RequestMapping("/jwtLogin")
    public ResultData jwtLogin(String loginName,String password) throws Exception {
        ResultData resultData = userService.queryOneByLoginName(loginName);
        User user = (User) resultData.getData();
        if (password.equals(user.getPassword())){
            Map<String,Object> map = new HashMap<>();
            String token = jwtKit.generateToken(user);
            map.put("tokenHead",jwtProperties.getTokenHead());
            map.put("token",token);
            return ResultUtils.success(map);
        }
        return ResultUtils.fail("用户不存在！");
    }

}
