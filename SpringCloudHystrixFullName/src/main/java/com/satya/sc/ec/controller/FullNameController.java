package com.satya.sc.ec.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FullNameController {
	
	@Autowired
	RestClientService client;
	
	@GetMapping("/getFullName")
	public @ResponseBody String getFullName(){
		return client.getFirstName() +" "+client.getLastName();
	}
}
