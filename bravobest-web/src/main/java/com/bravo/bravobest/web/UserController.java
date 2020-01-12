package com.bravo.bravobest.web;

import com.bravo.bravobest.binterface.UserService;
import com.bravo.bravobet.api.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String test1() throws Exception {
        Map<String,Object> map = new HashMap<>();
        List<User> users = userService.queryList(map);
        return "hello bravo";
    }
}
