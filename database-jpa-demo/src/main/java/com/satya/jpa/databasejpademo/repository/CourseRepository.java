package com.satya.jpa.databasejpademo.repository;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.satya.jpa.databasejpademo.entity.Course;
import com.satya.jpa.databasejpademo.entity.Review;
import com.satya.jpa.databasejpademo.entity.ReviewRating;
import com.satya.jpa.databasejpademo.entity.Student;

@Repository
@Transactional // handles transactions automatically
public class CourseRepository {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	public Course findById(Long id){
		Course crs = em.find(Course.class, id); // method to find the record by primary key
		return crs;
	}
	
	public Course save(Course crs){
		if(crs.getId() == null){
			em.persist(crs);  // mehtod to insert into table
		}else{
			em.merge(crs); // method to update the record
		}
		return crs;
	}
	
	public void deleteById(Long id){
		Course crs = findById(id);
		em.remove(crs); //method to delete the record
	}
	
	public void addReviewsForCourse(){
		Course crs = em.find(Course.class, 10001L);
		
		Review rev1 = new Review("worst course", ReviewRating.FIVE);
//		crs.addReview(rev1);
		Review rev2 = new Review("not good", ReviewRating.FOUR);
//		crs.addReview(rev2);
		
		rev1.setCourse(crs);
		rev2.setCourse(crs);
		
		em.persist(rev1);
		em.persist(rev2);
		
		logger.info("Reveiws for course 10001->{}", crs.getReviews());
	}
	
	public void addCourseAndStudentAndRelation(){
		Course course = new Course("Mastering SQL");
		em.persist(course);
		Student student = new Student("Raghav");
		em.persist(student);
		
		course.addStudent(student);
		student.addCourse(course);
		
		em.persist(student);
		
	}

}
