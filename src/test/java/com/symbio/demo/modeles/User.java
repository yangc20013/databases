package com.symbio.demo.modeles;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Transient;

import org.assertj.core.util.DateUtil;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "user")
public class User {
    private String name;
    private Date birth;
    @Transient
    private Calendar birthday;
    
    public Calendar getBirthDay() {
        return DateUtil.toCalendar(this.birth);
    }

}
