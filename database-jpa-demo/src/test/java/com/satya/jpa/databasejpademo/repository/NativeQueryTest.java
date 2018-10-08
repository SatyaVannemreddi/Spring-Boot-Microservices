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
import org.springframework.transaction.annotation.Transactional;

import com.satya.jpa.databasejpademo.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest() // launches entire spring context
public class NativeQueryTest {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	@DirtiesContext   // this is to reset the data after completion of test
	public void native_query_basic() {
		Query query = em.createNativeQuery("select * from course", Course.class); //Native query execution
		List list = query.getResultList();
		logger.info("select * from Course -> {}", list);
	}
	
	@Test
	@DirtiesContext   // this is to reset the data after completion of test
	public void native_query_where_indexParam_basic() {
		Query query = em.createNativeQuery("select * from course where id =:1", Course.class); //Native query with where indexed parameter execution
		query.setParameter(1, 1001L);
		List list = query.getResultList();
		logger.info("select * from Course -> {}", list);
	}
	
	@Test
	@DirtiesContext   // this is to reset the data after completion of test
	public void native_query_where_namedParam_basic() {
		Query query = em.createNativeQuery("select * from course where id =:id", Course.class); //Native query with where indexed parameter execution
		query.setParameter("id", 1001L);
		List list = query.getResultList();
		logger.info("select * from Course -> {}", list);
	}
	
	@Test
	@DirtiesContext   // this is to reset the data after completion of test
	@Transactional
	public void native_updateStmt_basic() {
		Query query = em.createNativeQuery("update course set name=:name where id =:id"); //Native update statement with where parameter execution
		query.setParameter("name", "some name");
		query.setParameter("id", 1001L);
		int rows = query.executeUpdate();
		logger.info("Number of rows updated->{}", rows);
	}

}
