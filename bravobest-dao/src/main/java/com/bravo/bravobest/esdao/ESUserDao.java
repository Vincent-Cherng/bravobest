package com.bravo.bravobest.esdao;

import com.bravo.bravobest.api.entity.ESUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * ES BOOt的dao接口
 */
public interface ESUserDao extends ElasticsearchRepository<ESUser,String> {



}
