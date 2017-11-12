package com.satya.sc.fc.dao;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("springLastName")
public interface LastNameClient {
	@GetMapping("/getLastName")
	public String getLastName();

}
