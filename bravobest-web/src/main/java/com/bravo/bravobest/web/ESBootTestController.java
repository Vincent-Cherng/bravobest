package com.bravo.bravobest.web;


import com.bravo.bravobest.binterface.ESUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Elasticsearch 与springboot一体整合包测试
 */
@RequestMapping("/esBoot")
@RestController
public class ESBootTestController {

    @Autowired
    private ESUserService eSUserService;

    @RequestMapping("/query")
    public String query(){
        String query = eSUserService.query();
        return query;
    }

    @RequestMapping("/save")
    public String save(){
        String save = eSUserService.save();
        return save;
    }

    @RequestMapping("/update")
    public String update(){
        String update = eSUserService.update();
        return update;
    }

    @RequestMapping("/delete")
    public String delete(){
        String delete = eSUserService.delete();
        return delete;
    }

    @RequestMapping("/bulk")
    public String bulk(){
        String bulk = eSUserService.bulk();
        return bulk;
    }

    @RequestMapping("/queryWithPage")
    public String queryWithPage(){
        String page = eSUserService.queryWithPage();
        return page;
    }
}
