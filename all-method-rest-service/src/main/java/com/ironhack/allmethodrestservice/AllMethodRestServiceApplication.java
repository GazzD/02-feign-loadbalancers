package com.ironhack.allmethodrestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AllMethodRestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllMethodRestServiceApplication.class, args);
	}

}
