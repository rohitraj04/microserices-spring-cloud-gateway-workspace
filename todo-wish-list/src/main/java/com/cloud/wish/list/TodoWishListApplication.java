package com.cloud.wish.list;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

	
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class TodoWishListApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoWishListApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
