package com.satya.jpa.databasejpademo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.satya.jpa.databasejpademo.entity.Employee;

@Repository
@Transactional // handles transactions automatically
public class InheritanceRepository {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	public List<Employee> retreiveAllEmployees(){
		TypedQuery<Employee> empQuery = em.createQuery("select e from Employee e", Employee.class);
		return empQuery.getResultList();
	}
	
	public void insert(Employee emp){
		em.persist(emp);
	}
	
}
