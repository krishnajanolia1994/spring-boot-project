package com.example.demoRest.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.example.demoRest.entiy.Employee;
import com.example.demoRest.entiy.Employee2;
import com.example.demoRest.projection.MyProjction;
import com.example.demoRest.repository.MyRepository2;

@Component
public class Employee2Bo {
	@Autowired
	MyRepository2 repository;

	@Autowired
	EntityManager entityManager;
	
	@SuppressWarnings("serial")
	public static Specification<Employee2> salaryLessThan(Long salary)
	{
		return new Specification<Employee2>() {

			@Override
			public Predicate toPredicate(Root<Employee2> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				return criteriaBuilder.lessThan(root.get("salary"), salary);
			}
		};
	}
	 @SuppressWarnings("serial")
	public static Specification<Employee2> nameEqual(String name)
	 {
		 return new Specification<Employee2>() {
			
			@Override
			public Predicate toPredicate(Root<Employee2> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("name"), name);
			}
		};
	 }
	public Employee2 saveEmployee(Employee2 employee) {
		Employee2 employee2 = null;
		if (employee != null) {
			employee2 = new Employee2();
			BeanUtils.copyProperties(employee, employee2);
			repository.save(employee2);
		}
		return employee2;
	}

	public Employee2 getById(Long id) {
		ArrayList<Long> list = new ArrayList<>();
		list.add(id);
		return (Employee2) repository.findAllById(list);
	}

	public List<Employee2> getAll() {
		return (List<Employee2>) repository.findAll();
	}

	public List<Employee2> getByName(String name) {
		return repository.findByName(name );
	}

	public List<Employee2> getByNameLName(String name, String lName) {
 		return repository.findByNameAndSurName(name, lName);
	}

	public List<Employee2> getByNameOrLName(String name, String lName) {
		return repository.findByNameOrSurName(name, lName);
	}

	public List<Employee2> getBySalaryBetween(Long salary1, Long salary2) {
		return repository.findBySalaryBetween(salary1, salary2);
	}

	public List<Employee2> getBySalaryLessThanEqual(Long salary1) {
		// TODO Auto-generated method stub
		return repository.findBySalaryLessThanEqual(salary1);
	}

	public List<Employee2> getBySalaryIsNull() {
		// TODO Auto-generated method stub
		return repository.findBySalaryIsNull();
	}

	public List<Employee2> getByFirstNameIsLike(String firstName) {
		// TODO Auto-generated method stub
		return repository.findByNameLike(firstName);
	}

	public List<Employee2> getByFirstNameStartWith(String firstName) {
		// TODO Auto-generated method stub
		return repository.findByNameStartingWith(firstName);
	}

	public List<Employee2> getByFirstNameStartingWithOrderBySalary(String firstName) {
		// TODO Auto-generated method stub
		return repository.findByNameStartingWithOrderBySalary(firstName);
	}

	public List<Employee2> getNamedQuery(Long salary) {
		// TODO Auto-generated method stub
		return repository.findBySalary(salary);
	}

//	public List<Employee2> findBySurName(String lName) {
//		// TODO Auto-generated method stub
//		return repository.findBySurName(lName);
//	}

	public List<MyProjction> findByProjection(String lName, Long salary) {
		List<MyProjction> list = repository.findByNameOrSalary(lName, salary);
		return list;
	}

	public List<Object> findByCriteria() {
		List<Object> employees=null;
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery=builder.createQuery();
		Root<Employee2> root=criteriaQuery.from(Employee2.class);
		CriteriaQuery<Object> select=criteriaQuery.select(root);
		
		TypedQuery<Object> query=entityManager.createQuery(select);
		
		employees=query.getResultList();
		return employees;
	}

	public Page<Employee2> getAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

//	public List<Employee> getByPradicate(String name, String lName) {
//		Employee employee=new Employee();
////		Predicate predicate =employee.getName().equals(name).and(employee.getSurName().equals(lName));
//		return employee;
//	}

	public List<Employee2> findBySpecification(String name, Long salary) {
		return repository.findAll(salaryLessThan(salary).or(nameEqual(name)));
	}
	public List<Employee2> findBySpecificationAndNameAndSalary(String name, Long salary) {
		return repository.findAll(salaryLessThan(salary).and(nameEqual(name)));
	}
	public List<Employee2> findByCriteriaNameNull() {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee2> cq=cb.createQuery(Employee2.class);
		Root<Employee2> root=cq.from(Employee2.class);
		cq.select(root);
		cq.where(root.get("name").isNull());
 		return entityManager.createQuery(cq).getResultList();
	}
	public List<Employee2> getByNameLNameByCriteria(String name, String lName) {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee2> cq=cb.createQuery(Employee2.class);
		Root<Employee2> root=cq.from(Employee2.class);
		cq.select(root);
		cq.where(cb.and(cb.equal(root.get("name"), name)));
		cq.where(cb.and(cb.equal(root.get("surName"), lName)));
 		return entityManager.createQuery(cq).getResultList();
	}
	public List<Employee2> getBySalaryLessThan(Long salary) {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee2> cq=cb.createQuery(Employee2.class);
		Root<Employee2> root=cq.from(Employee2.class);
		cq.select(root);
		cq.where(cb.lt(root.get("salary"), salary));
 		return entityManager.createQuery(cq).getResultList();
	}
	public List<Employee2> getBySalaryBetweenCriteria(Long salary, Long salary1) {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee2> cq=cb.createQuery(Employee2.class);
		Root<Employee2> root=cq.from(Employee2.class);
		cq.select(root);
		cq.where(cb.between(root.get("salary"), salary, salary1));
 		return entityManager.createQuery(cq).getResultList();
	}
	public List<Employee2> getByNameLikeCriteria(String name) {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee2> cq=cb.createQuery(Employee2.class);
		Root<Employee2> root=cq.from(Employee2.class);
		cq.select(root);
		cq.where(cb.like(root.get("name"), name));
 		return entityManager.createQuery(cq).getResultList();
	}
	public List<Employee2> getByInCriteria() {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee2> cq=cb.createQuery(Employee2.class);
		Root<Employee2> root=cq.from(Employee2.class);
		cq.select(root);
		List<String> names=new ArrayList<>();
		cq.where(root.get("name").in("krishna" ,"Snndeep"));
 		return entityManager.createQuery(cq).getResultList();
	}
	public Map<String, Object> getJoinResult() {
		Map<String, Object> map= new HashMap<>();
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee2> cq=cb.createQuery(Employee2.class);
		Root<Employee2> root=cq.from(Employee2.class);
//		Join<Employee2, Employee> join=root.join("employee");
		TypedQuery<Employee2> tq=entityManager.createQuery(cq);
		map.put("result", tq.getResultList());
		return map;
	}
	

}
