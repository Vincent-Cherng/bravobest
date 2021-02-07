package com.bravo.bravobest.web;


import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Elasticsearch rest-high-level-client的实现方法
 */
@RestController
@RequestMapping("/es")
public class ESTestController {

    /*private static final Logger logger = LoggerFactory.getLogger(ESTestController.class);

    @Autowired
    private RestHighLevelClient client;


    @RequestMapping("/queryOne")
    public String queryOne() throws IOException {
        logger.info("ESTestController queryOne start");
        //查询单个
        GetRequest getRequest = new GetRequest("user", "_doc", "1");

        //可选参数 start
        String[] includes = {"id","name"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true,includes,excludes);
        getRequest.fetchSourceContext(fetchSourceContext);
        //可选参数 end

        GetResponse response = client.get(getRequest);
        if (response.isExists()){
            String sourceAsString = response.getSourceAsString();
            Map<String, Object> sourceAsMap = response.getSourceAsMap();
            System.out.println(sourceAsMap);
            System.out.println(sourceAsString);
            return sourceAsString;
        }
        logger.info("ESTestController queryOne end");
        return "";
    }


    @RequestMapping("/queryOneAsync")
    public String queryOneAsync() throws IOException {
        logger.info("ESTestController queryOneAsnc start");
        //异步查询单个
        GetRequest getRequest = new GetRequest("user", "_doc", "1");

        //异步监听器
        ActionListener listener = new ActionListener<GetResponse>(){
            //成功时回调
            @Override
            public void onResponse(GetResponse documentFields) {
                System.out.println("进入回调");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String sourceAsString = documentFields.getSourceAsString();
                System.out.println(sourceAsString);
            }

            //失败时回调
            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        };
        //开始执行
        client.getAsync(getRequest,listener);
        //结束执行

        logger.info("ESTestController queryOneAsnc end");
        return "";
    }


    @RequestMapping("/save")
    public String save() throws IOException {
        logger.info("ESTestController save start");
        //执行新增
        IndexRequest indexRequest = new IndexRequest("user");
        indexRequest.id("4");
        indexRequest.type("_doc");

        Map<String, String> map = new HashMap<>();
        map.put("id","4");
        map.put("name","萝卜");
        indexRequest.source(map);

        //开始执行
        client.index(indexRequest);
        //结束执行

        logger.info("ESTestController save end");
        return "";
    }

    @RequestMapping("/saveAsync")
    public String saveAsync() throws IOException {
        logger.info("ESTestController saveAsync start");
        //执行异步新增
        IndexRequest indexRequest = new IndexRequest("user");
        indexRequest.id("5");
        indexRequest.type("_doc");

        Map<String, String> map = new HashMap<>();
        map.put("id","5");
        map.put("name","青菜我的最爱");
        indexRequest.source(map);

        ActionListener listener = new ActionListener<IndexResponse>(){
            @Override
            public void onResponse(IndexResponse indexResponse) {
                System.out.println("异步开始执行");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("异步结束执行");
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("异步执行出错");
                e.printStackTrace();
            }
        };

        //开始执行
        client.indexAsync(indexRequest,listener);
        //结束执行

        logger.info("ESTestController saveAsync end");
        return "";
    }


    @RequestMapping("/update")
    public String update() throws IOException {
        logger.info("ESTestController update start");
        //执行修改
        UpdateRequest updateRequest = new UpdateRequest("user", "_doc", "5");
        Map<String, String> map = new HashMap<>();
        map.put("name","青菜也行");
        updateRequest.doc(map);

        //设置超时时间
        updateRequest.timeout("1s");
        //设置重试次数
        updateRequest.retryOnConflict(3);

        //开始执行
        UpdateResponse update = client.update(updateRequest);
        //结束执行

        logger.info("ESTestController update end");
        return "";
    }



    @RequestMapping("/updateAsync")
    public String updateAsync() throws IOException {
        logger.info("ESTestController updateAsync start");
        //执行修改
        UpdateRequest updateRequest = new UpdateRequest("user", "_doc", "5");
        Map<String, String> map = new HashMap<>();
        map.put("name","青菜也行updateAsync");
        updateRequest.doc(map);

        //设置超时时间
        updateRequest.timeout("1s");
        //设置重试次数
        updateRequest.retryOnConflict(3);

        ActionListener listener = new ActionListener<UpdateResponse>(){
            @Override
            public void onResponse(UpdateResponse updateResponse) {
                System.out.println("修改执行开始");
                System.out.println("修改执行结束");
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        };

        //开始执行
        client.updateAsync(updateRequest,listener);
        //结束执行
        logger.info("ESTestController updateAsync end");
        return "";
    }


    @RequestMapping("/delete")
    public String delete() throws IOException {
        logger.info("ESTestController delete start");
        //执行删除 异步删除类似修改
        DeleteRequest deleteRequest = new DeleteRequest("user","_doc","5");
        //开始执行
        DeleteResponse delete = client.delete(deleteRequest);
        //结束执行
        System.out.println(delete.getId());
        System.out.println(delete.getResult());
        logger.info("ESTestController delete end");
        return "";
    }



    @RequestMapping("/bulk")
    public String bulk() throws IOException {
        logger.info("ESTestController bulk start");
        //执行批量  执行异步批量参考上面
        Map<String, String> map = new HashMap<>();
        map.put("id","5");
        map.put("name","一一");
        Map<String, String> umap = new HashMap<>();
        umap.put("id","1");
        umap.put("name","咕咕");
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(new IndexRequest("user","_doc","5").source(map));
        bulkRequest.add(new UpdateRequest("user","_doc","1").doc(umap));
        bulkRequest.add(new DeleteRequest("user","_doc","3"));

        BulkResponse bulk = client.bulk(bulkRequest);
        BulkItemResponse[] items = bulk.getItems();
        for (BulkItemResponse item : items){
            DocWriteRequest.OpType opType = item.getOpType();
            System.out.println(opType);
        }

        logger.info("ESTestController bulk end");
        return "";
    }*/


}



