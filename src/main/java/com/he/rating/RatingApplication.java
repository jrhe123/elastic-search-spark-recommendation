package com.he.rating;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {"com.he.rating"}
)
@MapperScan("com.he.rating.dao")
public class RatingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingApplication.class, args);
	}

}
