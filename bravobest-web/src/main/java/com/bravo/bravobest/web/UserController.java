package com.bravo.bravobest.web;

import com.bravo.bravobest.binterface.UserService;
import com.bravo.bravobest.api.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@RestController
//@Slf4j
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String test1() throws Exception {
        logger.error("hello slf4j");
        logger.error("hello slf4j");
        Map<String,Object> map = new HashMap<>();
//        List<User> users = userService.queryList(map);
        return "hello bravo";
    }
}
