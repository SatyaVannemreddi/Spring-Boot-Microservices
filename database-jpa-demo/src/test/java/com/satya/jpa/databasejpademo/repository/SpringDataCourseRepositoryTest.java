package com.satya.jpa.databasejpademo.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.satya.jpa.databasejpademo.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class SpringDataCourseRepositoryTest {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseSpringDataRepository repository;
	
//	@Test
//	@DirtiesContext   // this is to reset the data after completion of test
//	public void findById_exists() {
//		Optional<Course> optional = repository.findById(10001L);
//		assertTrue(optional.isPresent());
//		logger.info("Course exists->{}", optional.isPresent());
//	}
	
//	@Test
//	@DirtiesContext   // this is to reset the data after completion of test
//	public void findById_Notexists() {
//		Optional<Course> optional = repository.findById(20001L);
//		assertFalse(optional.isPresent());
//		logger.info("Course exists->{}", optional.isPresent());
//	}
	
//	@Test
//	@DirtiesContext   // this is to reset the data after completion of test
//	public void sortByName() {
//		Sort sort = new Sort(Sort.Direction.DESC, "name");
//		
//		logger.info("All Course exists->{}", repository.findAll(sort));
//	}
	
	@Test
	@DirtiesContext   // this is to reset the data after completion of test
	public void pagenation() {
		PageRequest pr = PageRequest.of(0, 3);
		Page<Course> page = repository.findAll(pr);
		
		logger.info("first page courses->{}", page.getContent());
		
		Pageable secondPageable = page.nextPageable();
		
		Page<Course> secondPage = repository.findAll(secondPageable);
		
		logger.info("second page courses->{}", secondPage.getContent());
		
	}


}
