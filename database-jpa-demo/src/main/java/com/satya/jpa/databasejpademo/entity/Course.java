package com.satya.jpa.databasejpademo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQuery(name="query_all_courses", query="select c from Course c")
@Cacheable  // this is to enable second level cache
@SQLDelete(sql="update course set is_deleted=true where id = ?")  // this is to update a column value when try to delete it
@Where(clause="is_deleted = false") // this is to append extra condition to query
public class Course {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@UpdateTimestamp // update this column with current date and time whenever update happens
	private LocalDateTime lastUpdatedDate;
	@CreationTimestamp // update this column with current date and time whenever insert happens
	private LocalDateTime createdDate;
	
	private boolean isDeleted;
	
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@PreRemove // this will be executed when a row is being deleted
	private void preRemove(){
		this.isDeleted = true;
	}
	@OneToMany(mappedBy="course")
	private List<Review> reviews = new ArrayList<Review>();
	
	@ManyToMany(mappedBy="courses")
	@JsonIgnore
	private List<Student> students = new ArrayList<Student>();
	
	public List<Student> getStudents() {
		return students;
	}
	public void addStudent(Student student) {
		this.students.add(student);
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
	public void removeReview(Review review) {
		this.reviews.remove(review);
	}
	
	public Course(){}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public Course(String name) {
		super();
		this.name = name;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}

}
