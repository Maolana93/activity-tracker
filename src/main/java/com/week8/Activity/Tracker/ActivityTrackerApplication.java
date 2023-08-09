package com.week8.Activity.Tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ActivityTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivityTrackerApplication.class, args);
	}

}
