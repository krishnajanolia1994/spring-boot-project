package com.example.demoRest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.example.demoRest.entiy.Employee;
import com.example.demoRest.projection.MyProjction;
@Component
public interface MyRepository extends CrudRepository<Employee, Long> , PagingAndSortingRepository<Employee, Long> ,JpaSpecificationExecutor<Employee>{
	List<Employee> findByName(String name);

	List<Employee> findByNameAndSurName(String name, String lName);

	List<Employee> findByNameOrSurName(String name, String lName);

	List<Employee> findBySalaryBetween(Long salary1, Long salary2);

	List<Employee> findBySalaryLessThanEqual(Long salary1);

	List<Employee> findBySalaryIsNull();

	List<Employee> findByNameLike(String firstName);

	List<Employee> findByNameStartingWith(String firstName);

	List<Employee> findByNameStartingWithOrderBySalary(String firstName);

	List<Employee> findBySalary(Long salary);

	

	@Query(value="select e from Employee e where surName=?1")
	List<Employee> findBySurName(String lName);

	List<MyProjction> findByNameOrSalary(String lName, Long salary);





}
