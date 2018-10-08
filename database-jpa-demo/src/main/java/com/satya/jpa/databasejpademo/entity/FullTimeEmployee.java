package com.satya.jpa.databasejpademo.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;


@Entity
public class FullTimeEmployee extends Employee{
	
	protected FullTimeEmployee(){}
	
	public FullTimeEmployee(String name, BigDecimal salary) {
		super(name);
		this.salary = salary;
	}
	private BigDecimal salary;
	
}
