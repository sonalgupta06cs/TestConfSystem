package com.conf.bookingsystem.ConferenceBookingSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.conf.bookingsystem")
@EnableJpaRepositories("com.conf.bookingsystem.repository")
@EntityScan("com.conf.bookingsystem.entity")
public class ConferenceBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConferenceBookingSystemApplication.class, args);
	}

}
