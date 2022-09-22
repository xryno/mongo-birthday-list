package com.ryan.springbootbirthday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.ryan.springbootbirthday.models.BirthdayItem;
import com.ryan.springbootbirthday.repositories.BirthdayItemRepo;



// @SpringBootApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableMongoRepositories(basePackageClasses = BirthdayItemRepo.class)
public class SpringBootBirthdayApplication implements CommandLineRunner {

	private final BirthdayItemRepo birthdayItemRepo;

	@Autowired
	public SpringBootBirthdayApplication(BirthdayItemRepo birthdayItemRepo) {
		this.birthdayItemRepo = birthdayItemRepo;

	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBirthdayApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		for (BirthdayItem birthdayItem: birthdayItemRepo.findAll()) {
			System.out.println(birthdayItem);
		}


	}
	

}
