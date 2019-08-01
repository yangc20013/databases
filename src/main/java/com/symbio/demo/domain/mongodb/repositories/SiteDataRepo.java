package com.symbio.demo.domain.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.symbio.demo.domain.mongodb.models.SiteData;

public interface SiteDataRepo extends MongoRepository<SiteData, String> {

}
