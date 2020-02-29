package com.bravo.bravobest.service;

import com.bravo.bravobest.api.entity.Menu;
import com.bravo.bravobest.api.entity.ResultData;
import com.bravo.bravobest.binterface.MenuService;
import com.bravo.bravobest.common.util.ResultUtils;
import com.bravo.bravobest.dao.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public ResultData queryMenu(String userNo) throws Exception {
        try {
            List<Menu> menuList = menuDao.queryMenu(userNo);
            return ResultUtils.success(menuList);
        } catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail();
        }
    }
}
