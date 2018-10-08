package com.satya.jpa.databasejpademo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Student {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false)
	private String name;
	
	@Embedded  // this is embedded class
	private Address address;
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@OneToOne(fetch=FetchType.LAZY)  // default fetch option is EAGER
	private Passport passport;
	
	@ManyToMany
	@JoinTable(name="STUDENT_COURSE", joinColumns=@JoinColumn(name="STUDENT_ID"), 
	inverseJoinColumns=@JoinColumn(name="Course_id"))
	private List<Course> courses = new ArrayList<Course>();
	
	public List<Course> getCourses() {
		return courses;
	}
	public void addCourse(Course course) {
		this.courses.add(course);
	}
	public Passport getPassport() {
		return passport;
	}
	public void setPassport(Passport passport) {
		this.passport = passport;
	}
	public Student(){}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public Student(String name) {
		super();
		this.name = name;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}

}
