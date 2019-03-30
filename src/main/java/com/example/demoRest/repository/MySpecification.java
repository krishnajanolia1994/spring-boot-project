package com.example.demoRest.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.example.demoRest.entiy.Employee;
@SuppressWarnings("rawtypes")
public class MySpecification {


	public static Specification setName(String fName)
	{
		return new Specification<Employee>() {
			private static final long serialVersionUID = -5372311995144994506L;

			@Override
			public Predicate toPredicate(Root<Employee> root,
					CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.like(root.get("name"), fName);
			}
		};
	}
	public static Specification setLName(String lName)
	{
		return new Specification<Employee>() {
			private static final long serialVersionUID = -5372311995144994506L;
			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.like(root.get("surName"), lName);
			}
		};
	}
	
	public static Specification setSalasryLimit(Long salary) {
		return new  Specification<Employee>() {
			private static final long serialVersionUID = -5372311995144994506L;
			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.le(root.get("salary"), salary);
			}
		};
	}
}
