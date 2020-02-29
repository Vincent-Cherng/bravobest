package com.bravo.bravobest.web;

import com.bravo.bravobest.api.entity.ResultData;
import com.bravo.bravobest.binterface.MenuService;
import com.bravo.bravobest.common.base.BaseController;
import com.bravo.bravobest.common.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/queryMenu")
    public ResultData queryMenu(HttpServletRequest req) throws Exception {
        String curUserNo = getCurUserNo(req);
        ResultData resultData = menuService.queryMenu(curUserNo);
        return resultData;
    }

}
