package com.satya.jpa.databasejpademo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.satya.jpa.databasejpademo.entity.Course;

@RepositoryRestResource(path="/courses") // this helps to expose it as a rest service
public interface CourseSpringDataRepository extends JpaRepository<Course, Long>{
	
	List<Course> findByName(String name);
	List<Course> findByNameAndId(String name, Long id);
	List<Course> findByNameOrderByIdDesc(String name);
	int countByName(String name);
	int deleteByName(String name);
	
	@Query("select c from Course c where name like '%Spring%'")
	List<Course> coursesByQuery(); 
	
	@Query(value="select * from Course where name like '%Spring%'", nativeQuery=true)
	List<Course> coursesByNativeQuery(); 
	

}
