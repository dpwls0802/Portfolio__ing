package com.record;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TravelRecordApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelRecordApplication.class, args);
	}

}
