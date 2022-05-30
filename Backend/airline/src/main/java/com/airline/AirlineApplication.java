package com.airline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AirlineApplication { 

	public static void main(String[] args) {
		SpringApplication.run(AirlineApplication.class, args);
	}

}
