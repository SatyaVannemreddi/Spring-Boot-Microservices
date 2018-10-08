package com.satya.jpa.databasejpademo.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;


@Entity
public class PartTimeEmployee extends Employee{
	protected PartTimeEmployee(){}
	
	public PartTimeEmployee(String name, BigDecimal hourlyWages) {
		super(name);
		this.hourlyWages = hourlyWages;
	}

	
	private BigDecimal hourlyWages;
	
	
}
