package com.satya.sc.ec.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class FullNameController {
	
	@Autowired
	DiscoveryClient client;
	
	@GetMapping("/getFullName")
	public @ResponseBody String getFullName(){
		return getNamePart("SPRINGFIRSTNAME") +" "+getNamePart("SPRINGLASTNAME");
	}
	
	public String getNamePart(String service) {
		System.out.println("Service Name:"+service);
	    List<ServiceInstance> list = client.getInstances(service);
	    System.out.println("service list:"+list.size());
	    if (list != null && list.size() > 0 ) {
	      URI uri = list.get(0).getUri();
	      
		  if (uri !=null ) {
			if("SPRINGFIRSTNAME".equals(service))
				return (new RestTemplate()).getForObject(uri.resolve("/getFirstName"),String.class);
			else if("SPRINGLASTNAME".equals(service))
				return (new RestTemplate()).getForObject(uri.resolve("/getLastName"),String.class);
		  }
	    }
	    return null;
	  }
}
