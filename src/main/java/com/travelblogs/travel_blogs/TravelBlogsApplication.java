package com.travelblogs.travel_blogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.travelblogs.travel_blogs.controller","com.travelblogs.travel_blogs.service","com.travelblogs.travel_blogs.model","com.travelblogs.travel_blogs.repository"})
@EntityScan("com.travelblogs.travel_blogs.model")
@EnableJpaRepositories("com.travelblogs.travel_blogs.repository")
public class TravelBlogsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelBlogsApplication.class, args);
	}

}
