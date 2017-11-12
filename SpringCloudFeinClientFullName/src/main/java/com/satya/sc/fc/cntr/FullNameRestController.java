package com.satya.sc.fc.cntr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satya.sc.fc.service.FullNameService;

@RestController
public class FullNameRestController {
	@Autowired
	FullNameService fns;
	
	@GetMapping("/getFullName")
	public String getFullName(){
		System.out.println("******Feign Client Service******");
		return fns.buildFullName();
	}

}
