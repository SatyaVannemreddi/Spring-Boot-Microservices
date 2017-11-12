package com.satya.sc.fc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satya.sc.fc.dao.FirstNameClient;
import com.satya.sc.fc.dao.LastNameClient;

@Service
public class FullNameServiceImpl implements FullNameService{
	FirstNameClient fn;
	LastNameClient ln;
	
	@Autowired
	public void setFn(FirstNameClient fn) {
		this.fn = fn;
	}
	
	@Autowired
	public void setLn(LastNameClient ln) {
		this.ln = ln;
	}
	
	@Override
	public String buildFullName() {
		String fullName = fn.getFirstName()+", "+ln.getLastName();
		return fullName;
	}

}
