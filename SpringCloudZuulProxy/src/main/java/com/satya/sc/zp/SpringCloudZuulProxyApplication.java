package com.satya.sc.zp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class SpringCloudZuulProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudZuulProxyApplication.class, args);
	}
}
