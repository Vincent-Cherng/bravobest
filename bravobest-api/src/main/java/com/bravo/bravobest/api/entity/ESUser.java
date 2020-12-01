package com.bravo.bravobest.api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;


@Document(indexName = "user",type = "_doc")
public class ESUser implements Serializable {


    private static final long serialVersionUID = 1010002795181281449L;

    @Id()
    private String id;

    /*这里ik_max_word是按最细粒度分词，ik_smart是按最粗粒度分词。
    在搜索的时候用粗粒度，在写入的时候用细粒度。*/
    @Field(type = FieldType.Text , searchAnalyzer = "ik_smart" , analyzer = "ik_max_word")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "ESUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
