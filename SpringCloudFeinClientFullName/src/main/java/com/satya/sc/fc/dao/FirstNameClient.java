package com.satya.sc.fc.dao;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("springFirstName")
public interface FirstNameClient {
	@GetMapping("/getFirstName")
	public String getFirstName();
}
