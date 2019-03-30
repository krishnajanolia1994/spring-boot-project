package com.example.demoRest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.example.demoRest.entiy.Employee2;
import com.example.demoRest.projection.MyProjction;
@Component
public interface MyRepository2 extends CrudRepository<Employee2, Long> , PagingAndSortingRepository<Employee2, Long> ,JpaSpecificationExecutor<Employee2>{
	List<Employee2> findByName(String name);

	List<Employee2> findByNameAndSurName(String name, String lName);

	List<Employee2> findByNameOrSurName(String name, String lName);

	List<Employee2> findBySalaryBetween(Long salary1, Long salary2);

	List<Employee2> findBySalaryLessThanEqual(Long salary1);

	List<Employee2> findBySalaryIsNull();

	List<Employee2> findByNameLike(String firstName);

	List<Employee2> findByNameStartingWith(String firstName);

	List<Employee2> findByNameStartingWithOrderBySalary(String firstName);

	List<Employee2> findBySalary(Long salary);

	

//	@Query(value="select e from Employee2 e where surName=?1")
//	List<Employee2> findBySurName(String lName);

	List<MyProjction> findByNameOrSalary(String lName, Long salary);





}
