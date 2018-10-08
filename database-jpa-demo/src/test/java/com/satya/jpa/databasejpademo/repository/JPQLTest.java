package com.satya.jpa.databasejpademo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
public class JPQLTest {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	
	/*	@Test
	@DirtiesContext   // this is to reset the data after completion of test
	public void jpql_basic() {
		Query query = em.createQuery("select c from Course c"); //query using entity
		List list = query.getResultList();
		logger.info("select c from Course c -> {}", list);
	}
	
	@Test
	@DirtiesContext   // this is to reset the data after completion of test
	public void typedJpql_basic() {
		TypedQuery<Course> tpQuery = em.createQuery("select c from Course c", Course.class); //typed query entity
		List<Course> list = tpQuery.getResultList();
		logger.info("select c from Course c -> {}", list);
	}
	
	@Test
	@DirtiesContext   // this is to reset the data after completion of test
	public void typedJpql_namedquery_basic() {
		TypedQuery<Course> tpQuery = em.createNamedQuery("query_all_courses", Course.class);
		List<Course> list = tpQuery.getResultList();
		logger.info("select c from Course c -> {}", list);
	}
	
	@Test
	@DirtiesContext   // this is to reset the data after completion of test
	public void jpql_join_queries() {
		TypedQuery<Course> tpQuery = em.createQuery("select c from Course c where c.students is empty", Course.class);
		List<Course> list = tpQuery.getResultList();
		logger.info("select c from Course c -> {}", list);
	}
	
	@Test
	@DirtiesContext   // this is to reset the data after completion of test
	public void jpql_join_queries_g2() {
		TypedQuery<Course> tpQuery = em.createQuery("select c from Course c where size(c.students) >= 2", Course.class);
		List<Course> list = tpQuery.getResultList();
		logger.info("select c from Course c -> {}", list);
	}
	
	@Test
	@DirtiesContext   // this is to reset the data after completion of test
	public void jpql_join_queries_orderby() {
		TypedQuery<Course> tpQuery = em.createQuery("select c from Course c order by size(c.students)", Course.class);
		List<Course> list = tpQuery.getResultList();
		logger.info("select c from Course c -> {}", list);
	}
	
	@Test
	@DirtiesContext   // this is to reset the data after completion of test
	public void jpql_join_queries() {
		Query tpQuery = em.createQuery("select c,s from Course c JOIN c.students s");
		List<Object[]> list = tpQuery.getResultList();
		logger.info("list size -> {}", list.size());
		
		for(Object[] result: list){
			logger.info("course {} student {}", result[0], result[1]);
		}
	}
	
	
	@Test
	@DirtiesContext   // this is to reset the data after completion of test
	public void jpql_left_join_queries() {
		Query tpQuery = em.createQuery("select c,s from Course c LEFT JOIN c.students s");
		List<Object[]> list = tpQuery.getResultList();
		logger.info("list size -> {}", list.size());
		
		for(Object[] result: list){
			logger.info("course {} student {}", result[0], result[1]);
		}
	}*/
	
	@Test
	@DirtiesContext   // this is to reset the data after completion of test
	public void jpql_cross_join_queries() {
		Query tpQuery = em.createQuery("select c,s from Course c, Student s");
		List<Object[]> list = tpQuery.getResultList();
		logger.info("list size -> {}", list.size());
		
		for(Object[] result: list){
			logger.info("course {} student {}", result[0], result[1]);
		}
	}


}
