package com.bravo.bravobest.service;

import com.bravo.bravobest.binterface.UserService;
import com.bravo.bravobest.common.page.Pager;
import com.bravo.bravobest.dao.UserDao;
import com.bravo.bravobest.api.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> queryList(Map<String, Object> pmap) throws Exception {
        User user = new User();
        user.setUserNo("1234");
        user.setUserName("老铁");
        user.setPassword("123");
        user.setLoginName("234");
        List<User> users = userDao.queryList(new Pager());
        System.out.println(users);
        return null;
    }

    @Override
    public User queryOne(Map<String, Object> pmap) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("userNo","admin");
        User user = userDao.queryOne(map);
        return user;
    }
}
