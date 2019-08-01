package com.symbio.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.symbio.demo.domain.business.models.Student;
import com.symbio.demo.domain.business.repositories.StudentRepo;
import com.symbio.demo.modeles.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate2;
    
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private StudentRepo studentRepo;

    @Before
    public void setUp() {
        jdbcTemplate1.update("DELETE  FROM  user ");
        jdbcTemplate2.update("DELETE  FROM  user ");
    }
    
    @Test
    public void testJpa() {
//        Student s = new Student();
//        s.setName("aaa");
//        s.setUpdatedTime(new Date());
//        studentRepo.save(s);
        
        List<Student> list = studentRepo.findAll();
        list.stream().forEach(st -> {
            System.out.println(JSONObject.toJSONString(st));
        });
    }

    @Test
    public void test() throws Exception {

        // 往第一个数据源中插入两条数据
        jdbcTemplate1.update("insert into user(id,name,age) values(?, ?, ?)", 1, "aaa", 20);
        jdbcTemplate1.update("insert into user(id,name,age) values(?, ?, ?)", 2, "bbb", 30);

        // 往第二个数据源中插入一条数据，若插入的是第一个数据源，则会主键冲突报错
        jdbcTemplate2.update("insert into user(id,name,age) values(?, ?, ?)", 1, "aaa", 20);

        // 查一下第一个数据源中是否有两条数据，验证插入是否成功
        Assert.assertEquals("2", jdbcTemplate1.queryForObject("select count(1) from user", String.class));

        // 查一下第一个数据源中是否有两条数据，验证插入是否成功
        Assert.assertEquals("1", jdbcTemplate2.queryForObject("select count(1) from user", String.class));

    }
    
    
    @Test
    public void mongoDate() {
        User u = new User();    
        u.setName("symbio");
        u.setBirth(new Date());
        mongoTemplate.save(u);
        
        System.out.println(mongoTemplate.findAll(User.class));
    }
    
    @Test
    public void mongoTest() {
        String objStr = "{'name':'symbio','emp':{'leader':'TL1','hr_id':'123456'}}";
        
        mongoTemplate.save(JSONObject.parse(objStr),"test");
        
        List<AggregationOperation> operations = new ArrayList<>();
        operations.add(Aggregation.project("name","emp.leader").andExclude("_id"));
        Aggregation aggregation = Aggregation.newAggregation(operations);
        AggregationResults<Map> result = mongoTemplate.aggregate(aggregation, "test", Map.class);
        
        List<Map> s = result.getMappedResults().stream().map (item -> (Map<String,Object>) item).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(s));
    }
}
