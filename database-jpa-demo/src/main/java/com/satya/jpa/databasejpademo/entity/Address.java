package com.satya.jpa.databasejpademo.entity;

import javax.persistence.Embeddable;

@Embeddable // to make the class embeddable
public class Address {
	private String street;
	private String city;
	private String country;
	private String zipCode;
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	public Address(){}
	
	public Address(String street, String city, String country, String zipCode) {
		super();
		this.street = street;
		this.city = city;
		this.country = country;
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
