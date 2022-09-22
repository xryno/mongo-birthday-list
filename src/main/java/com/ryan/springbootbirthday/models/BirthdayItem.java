package com.ryan.springbootbirthday.models;


import java.time.Instant;


import javax.persistence.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Document
@Entity
public class BirthdayItem {

    @Id
    @Getter
    @Setter
    private String id;

    @Field
    @Getter
    @Setter
    private String name;

    @Field
    @Getter
    @Setter
    private String dob;

    @Field
    @Getter
    @Setter
    private Integer currentAge;

    @Field
    @Getter
    @Setter
    private Integer newAge;

    @Field
    @Getter
    @Setter
    private Long daysLeft;

    @Field
    @Getter
    @Setter
    private Instant dateCreated = Instant.now();

    @Field
    @Getter
    @Setter
    private Instant dateModified = Instant.now();

    public BirthdayItem(){}

    public BirthdayItem(String name, String dob, Integer currentAge, Integer newAge, Long daysLeft, Instant dateCreated, Instant dateModified) {
        this.name = name;
        this.dob = dob;
        this.currentAge = currentAge;
        this.newAge = newAge;
        this.daysLeft = daysLeft;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    }

