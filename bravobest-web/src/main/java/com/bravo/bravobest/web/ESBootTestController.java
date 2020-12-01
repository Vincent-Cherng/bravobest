package com.bravo.bravobest.web;


import com.bravo.bravobest.api.entity.ESUser;
import com.bravo.bravobest.binterface.ESUserService;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Elasticsearch 与springboot一体整合包测试
 */
@RequestMapping("/esBoot")
@RestController
public class ESBootTestController {

    private static final Logger logger = LoggerFactory.getLogger(ESBootTestController.class);

    @Autowired
    private ESUserService eSUserService;

    @RequestMapping("/query")
    public String query(){
        logger.info("query info");
        String query = eSUserService.query();
        return query;
    }

    @RequestMapping("/save")
    public String save(){
        logger.info("save info");
        String save = eSUserService.save();
        return save;
    }

    @RequestMapping("/update")
    public String update(){
        logger.info("update info");
        String update = eSUserService.update();
        return update;
    }

    @RequestMapping("/delete")
    public String delete(){
        logger.info("delete info");
        String delete = eSUserService.delete();
        return delete;
    }

    @RequestMapping("/bulk")
    public String bulk(){
        logger.info("bulk info");
        String bulk = eSUserService.bulk();
        return bulk;
    }

    @RequestMapping("/queryWithPage")
    public String queryWithPage(){
        logger.info("queryWithPage info");
        String page = eSUserService.queryWithPage();
        return page;
    }

    @RequestMapping("/search")
    public String search(){
        logger.info("search info");
        String search = eSUserService.search();
        return search;
    }
}
