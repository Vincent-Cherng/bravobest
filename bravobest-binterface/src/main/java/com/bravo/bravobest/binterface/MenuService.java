package com.bravo.bravobest.binterface;

import com.bravo.bravobest.api.entity.ResultData;

public interface MenuService {

    /**
     * 查询菜单栏
     * @param userNo
     * @return
     * @throws Exception
     */
    ResultData queryMenu(String userNo) throws Exception;
}
