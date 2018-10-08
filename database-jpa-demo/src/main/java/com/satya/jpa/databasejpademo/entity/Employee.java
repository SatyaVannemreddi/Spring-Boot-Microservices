package com.satya.jpa.databasejpademo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
//@Entity
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE) // Single table for storing all inherited entities
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS) // separate table for each concrete calls
//@Inheritance(strategy=InheritanceType.JOINED) // separate tables will be created for concrete and base classes and joined together
//@DiscriminatorColumn(name="EmployeeType") // this is to specify the column name to distinguish subclass types
public abstract class Employee {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false)
	private String name;
	
	protected Employee(){}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public Employee(String name) {
		super();
		this.name = name;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}

}
