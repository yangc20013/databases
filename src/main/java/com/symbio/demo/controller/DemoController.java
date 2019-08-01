package com.symbio.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.symbio.demo.domain.business.repositories.StudentRepo;
import com.symbio.demo.domain.mongodb.repositories.SiteDataRepo;
import com.symbio.demo.services.UserService;

@RequestMapping(value = "/demo")
@RestController
public class DemoController {

    @Autowired
    private UserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private SiteDataRepo aRepo;
    
    @Autowired
    private StudentRepo studentRepo;

    @RequestMapping(value = "/test5", method = RequestMethod.GET)
    public ResponseEntity<Object> test5() {
        return new ResponseEntity<>(studentRepo.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public ResponseEntity<Object> test1() {
        return new ResponseEntity<>(userService.findById(1l), HttpStatus.OK);
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public ResponseEntity<Object> test2() {
        return new ResponseEntity<>(userService.findDefaultById(1l), HttpStatus.OK);
    }

    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public ResponseEntity<Object> test3() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/mongodb", method = RequestMethod.GET)
    public ResponseEntity<Object> mongodb() {

        return new ResponseEntity<>(aRepo.findAll(), HttpStatus.OK);
        // return new ResponseEntity<>(mongoTemplate.findAll(Taaa.class), HttpStatus.OK);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<Object> test() {
        
        Aggregation aggregate = Aggregation.newAggregation(
                Aggregation.unwind("$team_list"),
                Aggregation.match(Criteria.where("site").is("site2")),
                Aggregation.project().and("team_list").size().as("count")); 
        
        AggregationResults<JSONObject> result =  mongoTemplate.aggregate(aggregate, "aaa", JSONObject.class);

        Integer a = result.getUniqueMappedResult().getInteger("count");


        return new ResponseEntity<>(a, HttpStatus.OK);
    }

}
