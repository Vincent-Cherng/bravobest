package com.bravo.bravobest.service;

import com.bravo.bravobest.api.entity.ESUser;
import com.bravo.bravobest.binterface.ESUserService;
import com.bravo.bravobest.esdao.ESUserDao;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ESUserServiceImpl implements ESUserService<ESUser> {


    /*//普通查询使用这个
    @Autowired
    private ESUserDao esUserDao;

    //分页查询与滚动查询使用elasticsearchRestTemplate
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public String query() {
        Optional<ESUser> esUser = esUserDao.findById("1");
        ESUser esUser1 = esUser.get();
        System.out.println(esUser1.toString());

        return esUser1.toString();
    }

    @Override
    public String save() {
        ESUser esUser = new ESUser();
        esUser.setId("4");
        esUser.setName("我不是美国人，不厉害厉害");
        ESUser save = esUserDao.index(esUser);
        String s = save.toString();
        return s;
    }

    @Override
    public String update() {
        ESUser esUser = new ESUser();
        esUser.setId("6");
        esUser.setName("萝卜青菜，各有所爱for update");
        ESUser save = esUserDao.index(esUser);
        String s = save.toString();
        return s;
    }

    @Override
    public String delete() {
//        ESUser esUser = new ESUser();
//        esUser.setId("1");
//        esUserDao.delete(esUser);
        esUserDao.deleteById("1");
        return "delete";
    }

    @Override
    public String bulk() {
        ESUser esUser = new ESUser();
        esUser.setId("7");
        esUser.setName("诸葛亮");
        ESUser esUser1 = new ESUser();
        esUser1.setId("8");
        esUser1.setName("刘备");
        List<ESUser> list = new ArrayList<>();
        list.add(esUser);
        list.add(esUser1);
        Iterable<ESUser> esUsers = esUserDao.saveAll(list);
        esUsers.forEach(x-> System.out.println(x));
        return "success";
    }


    @Override
    public String queryWithPage(){
        NativeSearchQuery query = new NativeSearchQuery(new BoolQueryBuilder());
        query.setPageable(PageRequest.of(1, 2));
        AggregatedPage<ESUser> esUsers = elasticsearchTemplate.queryForPage(query, ESUser.class);

        List<ESUser> content = esUsers.getContent();

        for (ESUser user : content) {
            System.out.println(user.toString());
        }
        return "";
    }

    @Override
    public String search(){
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "中国");
        Iterable<ESUser> search = esUserDao.search(matchQueryBuilder);
        search.forEach(x -> System.out.println(x.getName()));
        ArrayList array = null;
        Object o = array.get(0);
        return "";
    }


    //综合搜索
    public Page<ESUser> sortSearch(String keyword, Integer pageNum, Integer pageSize, Integer sort) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //分页
        nativeSearchQueryBuilder.withPageable(pageable);

        //搜索
        if (StringUtils.isEmpty(keyword)) {
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
        } else {
            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("goodsName", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(10)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("goodsIntro", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(5)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("tag", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);
            nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);
        }
        //排序
        if(sort==1){
            //按新品从新到旧
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("goodsId").order(SortOrder.DESC));
        }else if(sort==2){
            //按库存从高到低
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("stockNum").order(SortOrder.DESC));
        }else if(sort==3){
            //按价格从低到高
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("sellingPrice").order(SortOrder.ASC));
        }else if(sort==4){
            //按价格从高到低
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("sellingPrice").order(SortOrder.DESC));
        }else{
            //按相关度
            nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        }
        nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        System.out.println("DSL:{}" + searchQuery.getQuery().toString());
        return esUserDao.search(searchQuery);
    }*/

    //相关商品推荐
    /*
    public Page<EsProduct> recommend(Long id, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<EsProduct> esProductList = productDao.getAllEsProductList(id);
        if (esProductList.size() > 0) {
            EsProduct esProduct = esProductList.get(0);
            String keyword = esProduct.getGoodsName();
            Long goodsCategoryId = esProduct.getGoodsCategoryId();
            //根据商品名称、商品简介，商品标签，分类进行搜索
            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("goodsName", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(8)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("goodsIntro", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("tag", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("goodsCategoryId", goodsCategoryId),
                    ScoreFunctionBuilders.weightFactorFunction(6)));
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);
            NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
            builder.withQuery(functionScoreQueryBuilder);
            builder.withPageable(pageable);
            NativeSearchQuery searchQuery = builder.build();
            log.info("DSL:{}", searchQuery.getQuery().toString());
            return productRepository.search(searchQuery);
        }
        return new PageImpl<>(null);
    }*/

}
