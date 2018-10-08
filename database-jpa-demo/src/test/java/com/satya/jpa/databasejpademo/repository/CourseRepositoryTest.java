package com.satya.jpa.databasejpademo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
import javax.transaction.Transactional;

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
public class CourseRepositoryTest {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;
	
	@Autowired
	EntityManager em;
	

//	@Test
//	public void findById_basic() {
//		Course crs = repository.findById(1001L);
//		assertEquals("Spring boot", crs.getName());
//		
//	}
//	
//	@Test
//	@DirtiesContext   // this is to reset the data after completion of test
//	public void deleteById_basic() {
//		repository.deleteById(10002L);
//		Course crs = repository.findById(10002L);
//		assertNull(crs);
//	}
	
//	@Test
//	@DirtiesContext   // this is to reset the data after completion of test
//	public void save_basic() {
//		Course crs = repository.findById(1001L);
//		assertEquals("Spring boot", crs.getName());
//		
//		crs.setName("Spring boot-updated");
//		repository.save(crs);
//		
//		crs = repository.findById(1001L);
//		assertEquals("Spring boot-updated", crs.getName());
//	}
	
	@Test
	@DirtiesContext   // n+1 problem
	@Transactional
	public void findAllCourses() {
		EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
		Subgraph<Object> subGraph = entityGraph.addSubgraph("students");
		List<Course> courses = em.createNamedQuery("query_all_courses", Course.class).setHint("javax.persistence.loadgraph", entityGraph).
				getResultList();
		for(Course crs: courses){
			logger.info("Course-> {} and student->{}",crs, crs.getStudents());
		}
	}

}
