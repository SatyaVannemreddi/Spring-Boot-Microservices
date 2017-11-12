package com.satya.sc.ec.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LastNameController {
	
	@GetMapping("/getLastName")
	public @ResponseBody String getLastName(){
		return "Vannemreddi";
	}

}
