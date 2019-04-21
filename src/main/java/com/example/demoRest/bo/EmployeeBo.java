package com.example.demoRest.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.glassfish.jersey.server.BackgroundScheduler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.example.demoRest.entiy.Employee;
import com.example.demoRest.projection.MyProjction;
import com.example.demoRest.repository.MyRepository;
import com.example.demoRest.repository.MySpecification;

@Component
public class EmployeeBo{
	@Autowired
	MyRepository repository;

	@Autowired
	EntityManager entityManager;
	
	
	@SuppressWarnings("serial")
	public static Specification<Employee> salaryLessThan(Long salary)
	{
		return new Specification<Employee>() {

			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				return criteriaBuilder.lessThan(root.get("salary"), salary);
			}
		};
	}
	 @SuppressWarnings("serial")
	public static Specification<Employee> nameEqual(String name)
	 {
		 return new Specification<Employee>() {
			
			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("name"), name);
			}
		};
	 }
	public Employee saveEmployee(Employee employee) {
		Employee employee2 = null;
		if (employee != null) {
			employee2 = new Employee();
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

	public Page<Employee> getAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

//	public List<Employee> getByPradicate(String name, String lName) {
//		Employee employee=new Employee();
////		Predicate predicate =employee.getName().equals(name).and(employee.getSurName().equals(lName));
//		return employee;
//	}

	public List<Employee> findBySpecification(String name, Long salary) {
		return repository.findAll(salaryLessThan(salary).or(nameEqual(name)));
	}
	public List<Employee> findBySpecificationAndNameAndSalary(String name, Long salary) {
		return repository.findAll(salaryLessThan(salary).and(nameEqual(name)));
	}
	public List<Employee> findByCriteriaNameNull() {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> cq=cb.createQuery(Employee.class);
		Root<Employee> root=cq.from(Employee.class);
		cq.select(root);
		cq.where(root.get("name").isNull());
 		return entityManager.createQuery(cq).getResultList();
	}
	public List<Employee> getByNameLNameByCriteria(String name, String lName) {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> cq=cb.createQuery(Employee.class);
		Root<Employee> root=cq.from(Employee.class);
		cq.select(root);
		cq.where(cb.and(cb.equal(root.get("name"), name)));
		cq.where(cb.and(cb.equal(root.get("surName"), lName)));
 		return entityManager.createQuery(cq).getResultList();
	}
	public List<Employee> getBySalaryLessThan(Long salary) {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> cq=cb.createQuery(Employee.class);
		Root<Employee> root=cq.from(Employee.class);
		cq.select(root);
		cq.where(cb.lt(root.get("salary"), salary));
 		return entityManager.createQuery(cq).getResultList();
	}
	public List<Employee> getBySalaryBetweenCriteria(Long salary, Long salary1) {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> cq=cb.createQuery(Employee.class);
		Root<Employee> root=cq.from(Employee.class);
		cq.select(root);
		cq.where(cb.between(root.get("salary"), salary, salary1));
 		return entityManager.createQuery(cq).getResultList();
	}
	public List<Employee> getByNameLikeCriteria(String name) {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> cq=cb.createQuery(Employee.class);
		Root<Employee> root=cq.from(Employee.class);
		cq.select(root);
		cq.where(cb.like(root.get("name"), name));
 		return entityManager.createQuery(cq).getResultList();
	}
	public List<Employee> getByInCriteria() {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> cq=cb.createQuery(Employee.class);
		Root<Employee> root=cq.from(Employee.class);
		cq.select(root);
		List<String> names=new ArrayList<>();
		cq.where(root.get("name").in("krishna" ,"Snndeep"));
 		return entityManager.createQuery(cq).getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Employee> getAll(String fName, String lName, Long salary) {
		List<Employee> list =null;
		if(Objects.nonNull(fName)&&Objects.nonNull(lName)&&Objects.nonNull(salary)) {
			return repository.findAll((MySpecification.setSalasryLimit(salary).
					and(MySpecification.setName(fName).
					and(MySpecification.setLName(lName)))));
		}
		return list;
	}
	public List<Employee> getAllAsync() {
		ThreadPoolExecutor ScheduledThreadPoolExecutor=new ScheduledThreadPoolExecutor(10);
		ScheduledThreadPoolExecutor.execute(new Runnable() {
			
			@Override
			public void run() {
				getSum();
			}
		});
		return (List<Employee>) repository.findAll();
	}
	
	@Async
	private void getSum() {
		int sum=0;
		int i=0;
		if(i==1500) {
			System.out.println(repository.findAll());
		}
		for(i=0;i<100;i++) {
			sum+=i;
			try {
				Thread.sleep(1000);
				System.out.println(sum);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(sum);
	}
	@SuppressWarnings("unchecked")
	public Page<Object> getCriteriaAndPradicate(String name, Long salary, Pageable pageable) {
		CriteriaBuilder cb= entityManager.getCriteriaBuilder();
		@SuppressWarnings("rawtypes")
		CriteriaQuery criteriaQuery = cb.createQuery();
		@SuppressWarnings("unchecked")
		Root<Employee> root=criteriaQuery.from(Employee.class);
		criteriaQuery.select(root);
		Predicate predicate = cb.equal(root.get("name"), name);
		Predicate predicate1 = cb.equal(root.get("salary"), salary);
		cb.and(predicate,predicate1);
		Iterator<Order> itr =pageable.getSort().iterator(); 
		Order order = itr.next();
		String propery =order.getProperty();
		if(order.isAscending()) {
			criteriaQuery.orderBy(cb.asc(root.get(propery)));
		}
		else if(order.isDescending()){
			criteriaQuery.orderBy(cb.desc(root.get(propery)));

		}
		TypedQuery<Object> tq = entityManager.createQuery(criteriaQuery.select(root));
		int totalrRecords = tq.getResultList().size();
		tq.setFirstResult(pageable.getPageNumber()*pageable.getPageSize());
		tq.setMaxResults(pageable.getPageSize());
		return new PageImpl<>(tq.getResultList(), pageable, totalrRecords);
	}
	
	

}
