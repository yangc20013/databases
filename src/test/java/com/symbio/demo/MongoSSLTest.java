package com.symbio.demo;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.symbio.demo.domain.mongodb.models.SiteData;
import com.symbio.demo.domain.mongodb.repositories.SiteDataRepo;
import com.symbio.demo.utils.DateUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class MongoSSLTest {

    @Autowired
    private SiteDataRepo siteDataRepo;

    @Test
    public void testMongoDate() {
//        siteDataRepo.deleteAll();
//
//        SiteData a = new SiteData();
//        a.setName("aa");
//        a.setTime(DateUtils.formatToDate("2019-07-08 20:59:59", DateUtils.DATE_TIME_PATTERN));
//        a.setCreatedTime(new Date());
//        a.setUpdatedTime(LocalDateTime.now());
//        siteDataRepo.save(a);
        List<SiteData> datas = siteDataRepo.findAll();
        for (SiteData o : datas) {
            System.out.println(JSON.toJSONString(o));
            String ts = DateUtils.formatToDateStr(o.getTime(), DateUtils.DATE_TIME_PATTERN);
            System.out.println("Time:         : " + ts);
            String aa = DateUtils.formatToDateStr(o.getCreatedTime(), DateUtils.DATE_TIME_PATTERN);
            System.out.println("Date          : " + aa);
            System.out.println("LocalDateTime : " + o.getUpdatedTime());
        }
    }
}
