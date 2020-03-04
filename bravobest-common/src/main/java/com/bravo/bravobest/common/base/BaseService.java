package com.bravo.bravobest.common.base;

import com.bravo.bravobest.common.page.Pager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseService {


    /**
     * 将数据分页面返回
     * @param pmap
     * @param data
     * @return
     */
    public Map<String,Object> buildPagination(Map<String,Object> pmap, List<? extends Object> data) {
        Map<String, Object> rmap = new HashMap<>();
        if(pmap.containsKey("pager")){
            Pager pager = (Pager) pmap.get("pager");
            rmap.put("total",pager.getTotalRecord());
        }
        rmap.put("data",data);
        return rmap;
    }
}
