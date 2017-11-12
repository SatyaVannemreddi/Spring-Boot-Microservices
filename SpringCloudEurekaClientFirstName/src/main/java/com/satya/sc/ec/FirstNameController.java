package com.satya.sc.ec;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstNameController {
	
	@GetMapping("/getFirstName")
	public @ResponseBody String getFirstName(){
		return "Satyanarayana";
	}
}
