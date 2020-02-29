package com.bravo.bravobest.dao;

import com.bravo.bravobest.api.entity.Menu;
import com.bravo.bravobest.api.entity.ResultData;

import java.util.List;

public interface MenuDao {

    /**
     * 查询菜单栏
     * @param userNo
     * @return
     * @throws Exception
     */
    List<Menu> queryMenu(String userNo) throws Exception;
}
