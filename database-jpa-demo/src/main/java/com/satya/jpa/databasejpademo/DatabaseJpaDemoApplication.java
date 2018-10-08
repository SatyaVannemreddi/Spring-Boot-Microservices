package com.satya.jpa.databasejpademo;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.satya.jpa.databasejpademo.entity.Course;
import com.satya.jpa.databasejpademo.entity.Employee;
import com.satya.jpa.databasejpademo.entity.FullTimeEmployee;
import com.satya.jpa.databasejpademo.entity.PartTimeEmployee;
import com.satya.jpa.databasejpademo.repository.CourseRepository;
import com.satya.jpa.databasejpademo.repository.InheritanceRepository;
import com.satya.jpa.databasejpademo.repository.StudentRepository;

@SpringBootApplication
public class DatabaseJpaDemoApplication implements CommandLineRunner{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CourseRepository cRepo;
	
	@Autowired
	private StudentRepository sRepo;
	
	@Autowired
	private InheritanceRepository inhRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(DatabaseJpaDemoApplication.class, args);
	}
	
	public void run(String... args) throws Exception {
//		Course crs = cRepo.findById(1001L);
//		
//		logger.info("Course-> {}", crs);
//		
//		Course newCrs = new Course("Spring Sleuth");
//		cRepo.save(newCrs);
//		
//		cRepo.deleteById(1001L);
		
//		sRepo.save();
//		cRepo.addReviewsForCourse();
		
//		cRepo.addCourseAndStudentAndRelation();
		
//		 Employee ftEmployee = new FullTimeEmployee("Satya", new BigDecimal("10000"));
//		
//		inhRepo.insert(ftEmployee);
		
		
		
//		inhRepo.insert(new PartTimeEmployee("Tanuj", new BigDecimal("90")));	 
//		inhRepo.insert(new FullTimeEmployee("Satya", new BigDecimal("10000")));
//		
//		logger.info("employees -> {}", inhRepo.retreiveAllEmployees()); 
		
	}
}
