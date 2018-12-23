package com.example.demoRest.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demoRest.entiy.Employee;
import com.example.demoRest.projection.MyProjction;
import com.example.demoRest.repository.MyRepository;

@Component
public class EmployeeBo {
	@Autowired
	MyRepository repository;

	@Autowired
	EntityManager entityManager;

	public Employee saveEmployee(Employee employee) {
		Employee employee2 = null;
		if (employee != null) {
			employee2 = new Employee();
			System.out.println("hfkjk  ");

			BeanUtils.copyProperties(employee, employee2);
			repository.save(employee2);
		}
		return employee2;
	}

	public Employee getById(Long id) {
		ArrayList<Long> list = new ArrayList<>();
		list.add(id);
		return (Employee) repository.findAllById(list);
	}

	public List<Employee> getAll() {
		return (List<Employee>) repository.findAll();
	}

	public List<Employee> getByName(String name) {
		return repository.findByName(name );
	}

	public List<Employee> getByNameLName(String name, String lName) {
		// TODO Auto-generated method stub
		return repository.findByNameAndSurName(name, lName);
	}

	public List<Employee> getByNameOrLName(String name, String lName) {
		return repository.findByNameOrSurName(name, lName);
	}

	public List<Employee> getBySalaryBetween(Long salary1, Long salary2) {
		return repository.findBySalaryBetween(salary1, salary2);
	}

	public List<Employee> getBySalaryLessThanEqual(Long salary1) {
		// TODO Auto-generated method stub
		return repository.findBySalaryLessThanEqual(salary1);
	}

	public List<Employee> getBySalaryIsNull() {
		// TODO Auto-generated method stub
		return repository.findBySalaryIsNull();
	}

	public List<Employee> getByFirstNameIsLike(String firstName) {
		// TODO Auto-generated method stub
		return repository.findByNameLike(firstName);
	}

	public List<Employee> getByFirstNameStartWith(String firstName) {
		// TODO Auto-generated method stub
		return repository.findByNameStartingWith(firstName);
	}

	public List<Employee> getByFirstNameStartingWithOrderBySalary(String firstName) {
		// TODO Auto-generated method stub
		return repository.findByNameStartingWithOrderBySalary(firstName);
	}

	public List<Employee> getNamedQuery(Long salary) {
		// TODO Auto-generated method stub
		return repository.findBySalary(salary);
	}

	public List<Employee> findBySurName(String lName) {
		// TODO Auto-generated method stub
		return repository.findBySurName(lName);
	}

	public List<MyProjction> findByProjection(String lName, Long salary) {
		List<MyProjction> list = repository.findByNameOrSalary(lName, salary);
		return list;
	}

	public List<Object> findByCriteria() {
		List<Object> employees=null;
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery=builder.createQuery();
		Root<Employee> root=criteriaQuery.from(Employee.class);
		CriteriaQuery<Object> select=criteriaQuery.select(root);
		
		TypedQuery<Object> query=entityManager.createQuery(select);
		
		employees=query.getResultList();
		return employees;
	}

}
