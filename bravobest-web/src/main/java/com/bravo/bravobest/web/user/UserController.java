package com.bravo.bravobest.web.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

    @RequestMapping("/hello")
    public String test1(){
        return "hello bravo";
    }
}
