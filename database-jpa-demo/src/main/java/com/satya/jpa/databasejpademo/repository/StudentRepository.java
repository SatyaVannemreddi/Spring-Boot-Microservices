package com.satya.jpa.databasejpademo.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.satya.jpa.databasejpademo.entity.Passport;
import com.satya.jpa.databasejpademo.entity.Student;

@Repository
@Transactional // handles transactions automatically
public class StudentRepository {
	
	@Autowired
	EntityManager em;
	
	public Student findById(Long id){
		Student crs = em.find(Student.class, id); // method to find the record by primary key
		return crs;
	}
	
	public void save(){
		
		Passport passport = new Passport("srihan1234");
		em.persist(passport);
		
		Student student = new Student("Srihan");
		student.setPassport(passport);
		
		em.persist(student);
	}
	
	public void deleteById(Long id){
		Student crs = findById(id);
		em.remove(crs); //method to delete the record
	}

}
