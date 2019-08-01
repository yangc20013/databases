package com.symbio.demo.domain.business.models;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Student implements Serializable {
    private static final long serialVersionUID = 8339954254113825535L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    private String name;
    
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy/MM/dd",timezone="America/New_York")
    private Calendar updatedTime;
}
