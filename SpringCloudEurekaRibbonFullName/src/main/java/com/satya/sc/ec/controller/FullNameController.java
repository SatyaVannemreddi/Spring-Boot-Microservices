package com.satya.sc.ec.controller;

//import java.net.URI;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class FullNameController {
	
	@Autowired
	RestTemplate client;
	
	@GetMapping("/getFullName")
	public @ResponseBody String getFullName(){
		return getNamePart("SPRINGFIRSTNAME") +" "+getNamePart("SPRINGLASTNAME");
	}
	
	public String getNamePart(String service) {
		System.out.println("Service Name:"+service);		
		if("SPRINGFIRSTNAME".equals(service))
			return client.getForObject("http://" + service+"/getFirstName", String.class);
		else if("SPRINGLASTNAME".equals(service))
			return client.getForObject("http://" + service+"/getLastName", String.class);
	    return null;
	  }
}
