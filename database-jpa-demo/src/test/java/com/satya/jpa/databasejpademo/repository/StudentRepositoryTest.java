package com.satya.jpa.databasejpademo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.satya.jpa.databasejpademo.entity.Course;
import com.satya.jpa.databasejpademo.entity.Passport;
import com.satya.jpa.databasejpademo.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest() // launches entire spring context
public class StudentRepositoryTest {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	StudentRepository repository;
	
	@Autowired
	EntityManager em;
	
//	@Test
//	@DirtiesContext   // this is to reset the data after completion of test
//	@Transactional // without this annotation, it fails in getting passport details if we select fetch option as LAZY
//	public void getStudentWithPassport() {
//		Student student = em.find(Student.class, 20001L);
//		Passport passport= student.getPassport();
//		
//		logger.info("Student->{}",student);
//		
//		logger.info("Passport->{}",passport);
//	}
	
//	@Test
//	@DirtiesContext   // this is to reset the data after completion of test
//	@Transactional // without this annotation, it fails in getting passport details if we select fetch option as LAZY
//	public void getPassportWithStudent() {
//		Passport passport = em.find(Passport.class, 30001L);
//		Student student= passport.getStudent();
//		
//		logger.info("Student->{}",student);
//		
//		logger.info("Passport->{}",passport);
//	}
	
//	@Test
//	@Transactional // without this annotation, it fails in getting passport details if we select fetch option as LAZY
//	public void retrieveStudentAndCourses() {
//		Student student = em.find(Student.class, 20001L);
//		List<Course> courses= student.getCourses();
//		
//		logger.info("Student->{}",student);
//		
//		logger.info("courses->{}",courses);
//	}
	
	@Test
	@Transactional // without this annotation, it fails in getting passport details if we select fetch option as LAZY
	public void retrieveCourseAndStudents() {
		Course course = em.find(Course.class, 10001L);
		List<Student> students= course.getStudents();
		
		logger.info("Course->{}",course);
		
		logger.info("Students->{}",students);
	}

}
