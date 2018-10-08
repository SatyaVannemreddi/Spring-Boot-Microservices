package com.satya.jpa.databasejpademo.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {
	@Id
	@GeneratedValue
	private Long id;
	private String description;
	
	@Enumerated(EnumType.STRING)    // to indicate enumerated value
	private ReviewRating rating;
	
	@ManyToOne
	private Course course;
	
	
	public Review(){}
	public Review(String description, ReviewRating rating) {
		super();
		this.description = description;
		this.rating = rating;
	}

	
	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ReviewRating getRating() {
		return rating;
	}

	public void setRating(ReviewRating rating) {
		this.rating = rating;
	}
	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	@Override
	public String toString() {
		return "Review [id=" + id + ", description=" + description + ", rating=" + rating + "]";
	}
	
	

}
