package com.satya.jpa.databasejpademo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Passport {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false)
	private String passNumber;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy="passport") // mappedBy specifies the owning side
	private Student student;
	
	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public Passport(){}
	
	
	public Passport(String passNumber) {
		super();
		this.passNumber = passNumber;
	}


	public Long getId() {
		return id;
	}

	public String getPassNumber() {
		return passNumber;
	}

	public void setPassNumber(String passNumber) {
		this.passNumber = passNumber;
	}

	@Override
	public String toString() {
		return "Passport [id=" + id + ", passNumber=" + passNumber + "]";
	}
	
}
