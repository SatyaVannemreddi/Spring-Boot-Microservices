package com.satya.sc.ec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication

public class SpringCloudEurekaClientFullNameApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaClientFullNameApplication.class, args);
	}
	
	@Bean 
	@LoadBalanced
	RestTemplate restTemplate() {
	    return new RestTemplate();
	} 
	
}
