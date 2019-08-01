package com.symbio.demo.domain.mongodb.models;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;


@Data
@Document(collection = "site_data")
public class SiteData {
    @Id
    private String id;
    @Field("name")
    private String name;
    @Field("time")
    @DateTimeFormat(iso=ISO.DATE_TIME)
    private Date time;
    @Field("created_time")
    @DateTimeFormat(iso=ISO.DATE_TIME)
    private Date createdTime;
    
    @Field("updated_time")
//    @DateTimeFormat(iso=ISO.DATE_TIME)
    private LocalDateTime updatedTime;
}
