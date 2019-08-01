package com.symbio.demo;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableCaching
@RestController
@EnableAsync
@EnableScheduling
@EnableAutoConfiguration
public class DemoApplication {
    
    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
    }

	public static void main(String[] args) {
	    TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
		SpringApplication.run(DemoApplication.class, args);
	}

}
