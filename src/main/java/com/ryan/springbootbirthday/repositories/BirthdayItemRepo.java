package com.ryan.springbootbirthday.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ryan.springbootbirthday.models.BirthdayItem;


@Repository
public interface BirthdayItemRepo extends MongoRepository<BirthdayItem, Long> {
    
}
