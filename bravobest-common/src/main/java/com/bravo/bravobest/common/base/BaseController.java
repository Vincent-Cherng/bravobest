package com.bravo.bravobest.common.base;

import com.bravo.bravobest.api.entity.User;

import javax.servlet.http.HttpServletRequest;

public class BaseController {


    /**
     * 获取当前登录人
     * @param req
     * @return
     * @throws Exception
     */
    public User getCurUser(HttpServletRequest req) throws Exception {
        User user = (User)req.getSession().getAttribute("user");
        return user;
    }

    /**
     * 获取当前登录人编号
     * @param req
     * @return
     * @throws Exception
     */
    public String getCurUserNo(HttpServletRequest req) throws Exception {
        String userNo = this.getCurUser(req).getUserNo();
        return userNo;
    }


}
