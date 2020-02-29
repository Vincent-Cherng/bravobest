package com.bravo.bravobest.service;

import com.bravo.bravobest.api.entity.ResultData;
import com.bravo.bravobest.binterface.UserService;
import com.bravo.bravobest.common.page.Pager;
import com.bravo.bravobest.common.util.ResultUtils;
import com.bravo.bravobest.dao.UserDao;
import com.bravo.bravobest.api.entity.User;
import org.apache.ibatis.annotations.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public ResultData queryList(Map<String, Object> pmap) throws Exception {
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
    public ResultData queryOneByLoginName(String loginName) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("loginName",loginName);
        try {
            User user = userDao.queryOneWithPassword(map);
            return ResultUtils.success(user);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResultUtils.fail();
        }
    }
}
