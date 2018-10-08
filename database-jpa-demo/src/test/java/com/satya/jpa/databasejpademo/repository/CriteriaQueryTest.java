package com.satya.jpa.databasejpademo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.satya.jpa.databasejpademo.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest() // launches entire spring context
public class CriteriaQueryTest {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	/*@Test // test case using criteria queries
	@DirtiesContext   // this is to reset the data after completion of test
	public void all_criteria_query() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> rootCourse = cq.from(Course.class);
		
		TypedQuery<Course> tq =  em.createQuery(cq.select(rootCourse));
		List<Course> list = tq.getResultList();
		
		logger.info("Course list -> {}", list);
	} 
	
	@Test // test case using criteria queries with where condition
	@DirtiesContext   // this is to reset the data after completion of test
	public void criteria_query_with_where() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> rootCourse = cq.from(Course.class);
		
		Predicate cblike = cb.like(rootCourse.get("name"), "%Spring%");
		cq.where(cblike);
		
		TypedQuery<Course> tq =  em.createQuery(cq.select(rootCourse));
		List<Course> list = tq.getResultList();
		
		logger.info("Course list -> {}", list);
	}
	
	@Test // test case using criteria queries with joins
	@DirtiesContext   // this is to reset the data after completion of test
	public void criteria_query_with_joins() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> rootCourse = cq.from(Course.class);
		
		rootCourse.join("students");
		
		TypedQuery<Course> tq =  em.createQuery(cq.select(rootCourse));
		List<Course> list = tq.getResultList();
		
		logger.info("Course list -> {}", list);
	}*/
	
	@Test // test case using criteria queries with Left joins
	@DirtiesContext   // this is to reset the data after completion of test
	public void criteria_query_with__letf_joins() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> rootCourse = cq.from(Course.class);
		
		rootCourse.join("students", JoinType.LEFT);
		
		TypedQuery<Course> tq =  em.createQuery(cq.select(rootCourse));
		List<Course> list = tq.getResultList();
		
		logger.info("Course list -> {}", list);
	}


}
