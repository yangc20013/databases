package com.symbio.demo.domain.mongodb.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection="Taaa")
public class Taaa {
    @Id
    private String id;
    @Field("name")
    private String name;
    

    
    
}
