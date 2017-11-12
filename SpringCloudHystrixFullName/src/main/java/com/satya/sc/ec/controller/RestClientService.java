package com.satya.sc.ec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class RestClientService {
	@Autowired
	RestTemplate client;
	
	@HystrixCommand(fallbackMethod = "alternativeFName")
	public String getFirstName(){
			return client.getForObject("http://" + "SPRINGFIRSTNAME" +"/getFirstName", String.class);
	}
	
	public String alternativeFName(){
		System.out.println("*****fallbackmethod1****");
		return "Satya";
	}
	
	@HystrixCommand(fallbackMethod = "alternativeLName")
	public String getLastName(){
			return client.getForObject("http://" + "SPRINGLASTNAME" +"/getLastName", String.class);
	}
	
	public String alternativeLName(){
		System.out.println("*****fallbackmethod2****");
		return "Vannem";
	}
}
