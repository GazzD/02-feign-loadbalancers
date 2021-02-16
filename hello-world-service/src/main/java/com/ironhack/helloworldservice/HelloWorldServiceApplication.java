package com.ironhack.helloworldservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class HelloWorldServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldServiceApplication.class, args);
	}

}
