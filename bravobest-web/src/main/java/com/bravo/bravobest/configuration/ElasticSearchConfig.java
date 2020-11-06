package com.bravo.bravobest.configuration;


import org.apache.http.HttpHost;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;


/**
 * Elasticsearch rest-high-level-client的配置
 */
@Configuration
public class ElasticSearchConfig {

    @Value("${spring.elasticsearch.address}")
    private String address;

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        String[] hostList = address.split(",");
        HttpHost[] httpHosts = new HttpHost[hostList.length];
        for (int i = 0 ; i < hostList.length ; i++){
            String host = hostList[i];
            httpHosts[i] = new HttpHost(host.split(":")[0],Integer.parseInt(host.split(":")[1]),"http");
        }
        return new RestHighLevelClient(RestClient.builder(httpHosts));
    }

}
