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
