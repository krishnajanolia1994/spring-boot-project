package com.example.demoRest.controller;

import java.util.List;
import java.util.Map;

//import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.QueryParam;

//import org.apache.logging.log4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoRest.bo.Employee2Bo;
import com.example.demoRest.entiy.Employee2;
import com.example.demoRest.projection.MyProjction;


@RestController
@RequestMapping("/employees2")
public class ControllerEmplolyee2 {
//	Logger logger=(Logger) LoggerFactory.getLogger(Controller.class);
	@Autowired
	Employee2Bo employee2Bo;
	@RequestMapping("/a")
	public String show()
	{
		return "hello world";
	}
	@PostMapping
	public Employee2 create(@RequestBody Employee2 employee)
	{
		System.out.println(employee.getEmployer()+"  "+employee.getManager()
		+"  "+employee.getName()+"   "+employee.getSalary());
		return employee2Bo.saveEmployee(employee);
	}
	@RequestMapping("/join")
	@GetMapping
	public Map<String, Object> getJoinResult(){
		return employee2Bo.getJoinResult();
	}
	
	@RequestMapping()
	@GetMapping
	public List<Employee2> getAll()
	{
		List<Employee2> listEmployee=null;
	    listEmployee=employee2Bo.getAll();
//	    if(listEmployee==null)
//	    {
//	    	logger.warn(" empty result is return");
//	    }
//	    else
//	    {
//	    	logger.warn("a list is return");
//	    }
		return  listEmployee;
	}
	
	@RequestMapping("/pageble")
	@GetMapping
	public Page<Employee2> getAll(Pageable  Pageable)
	{

		return  employee2Bo.getAll(Pageable);
	}
	
	@RequestMapping("/id")
	@GetMapping
	public Employee2 getById(@PathVariable  Long  id)
	{
		Employee2 employee=employee2Bo.getById(id);
		return employee;
	}
	
	@RequestMapping("get_by_name")
	@GetMapping
	public List<Employee2> getByName(@QueryParam("name") String name)
	{
		List<Employee2> employees=null;
		employees=employee2Bo.getByName(name);
		return employees;
	}
	
	@RequestMapping("get_by_name_and_last_name")
	@GetMapping
	public List<Employee2> getByNameLName(@QueryParam("name") String name,@QueryParam("LName") String LName)
	{
		List<Employee2> list=null;
		list=employee2Bo.getByNameLName(name,LName);
		return list;
	}
	@RequestMapping("get_by_name_and_last_name_by_criteria")
	@GetMapping
	public List<Employee2> getByNameLNameByCriteria(@QueryParam("name") String name,@QueryParam("LName") String LName)
	{
		return employee2Bo.getByNameLNameByCriteria(name,LName);
	}
	@RequestMapping("get_by_salary_less_than_by_criteria")
	@GetMapping
	public List<Employee2> getBySalaryLessThan(@QueryParam("salary") Long salary)
	{
		return employee2Bo.getBySalaryLessThan(salary);
	}
	@RequestMapping("get_by_salary_between_by_criteria")
	@GetMapping
	public List<Employee2> getByBetween(@QueryParam("salary") Long salary,@QueryParam("salary1") Long salary1)
	{
		return employee2Bo.getBySalaryBetweenCriteria(salary,salary1);
	}
	@RequestMapping("get_by_name_like_criteria")
	@GetMapping
	public List<Employee2> getByBetween(@QueryParam("salary") Long salary,@QueryParam("name") String name)
	{
		return employee2Bo.getByNameLikeCriteria(name+"%");
	}
	@RequestMapping("get_by_name_in_criteria")
	@GetMapping
	public List<Employee2> getByBetween()
	{
		return employee2Bo.getByInCriteria();
	}
	
//	@RequestMapping("pradicate")
//	@GetMapping
//	public List<Employee> getPadicate(@QueryParam("name") String name,@QueryParam("LName") String LName)
//	{
//		List<Employee> list=null;
//		list=employee2Bo.getByPradicate(name,LName);
//		return list;
//	}
	@RequestMapping("get_by_name_or_last_name")
	@GetMapping
	public List<Employee2> getByNameOrLName(@QueryParam("name") String name,@QueryParam("LName") String LName)
	{
		List<Employee2> employee=employee2Bo.getByNameOrLName(name,LName);
		return employee;
	}
	
	@RequestMapping("get_by_sallary_between")
	@GetMapping
	public List<Employee2> getBySallaryBetween(@QueryParam("salary1") Long salary1,
			@QueryParam("salary2") Long salary2)
	{
		List<Employee2> employee=employee2Bo.getBySalaryBetween(salary1,salary2);
		return employee;
	}
	
	@RequestMapping("get_by_sallary_is_null")
	@GetMapping
	public List<Employee2> getBySallaryLessThan()
	{
		List<Employee2> employee=employee2Bo.getBySalaryIsNull();
		return employee;
	}
	
	@RequestMapping("get_by_first-name_like")
	@GetMapping
	public List<Employee2> getByFirstName(@QueryParam("firstName") String firstName)
	{
		String pattern=firstName+"%";
		List<Employee2> employee=employee2Bo.getByFirstNameIsLike(pattern);
		return employee;
	}
	
	@RequestMapping("get_by_first-name_Strat-with")
	@GetMapping
	public List<Employee2> getByFirstNameStartWith(@QueryParam("firstName") String firstName)
	{
		List<Employee2> employee=employee2Bo.getByFirstNameStartWith(firstName);
		return employee;
	}
	
	@RequestMapping("get_by_first-name_stating-with_order-by_salary")
	@GetMapping
	public List<Employee2> getByFirstNameStartWithOrdrBy(@QueryParam("firstName") String firstName)
	{
		List<Employee2> employee=employee2Bo.getByFirstNameStartingWithOrderBySalary(firstName);
		return employee;
	}
	
	@RequestMapping("get_by_named-query")
	@GetMapping
	public List<Employee2> getByByNamedQuery(@QueryParam("salary") Long salary)
	{
		List<Employee2> employee=employee2Bo.getNamedQuery(salary);
		return employee;
	}
	
//	@RequestMapping("get_@Query")
//	@GetMapping
//	public List<Employee2> getByLastNmae(@QueryParam("lName") String lName)
//	{
//		List<Employee2> employee=employee2Bo.findBySurName(lName);
//		return employee;
//	}
	
	@RequestMapping("projection")
	@GetMapping
	public List<MyProjction> getByProjection(@QueryParam("lName") String lName,
			@QueryParam("salary") Long salary)
	{
		List<MyProjction> projection=employee2Bo.findByProjection(lName,salary);
		return projection;
	}
	
	@RequestMapping("criteria")
	@GetMapping
	public List<Object> getByCriteria()
	{
		List<Object> projection=employee2Bo.findByCriteria();
		return projection;
	}
	
	@RequestMapping("criteria_name_is_null")
	@GetMapping
	public List<Employee2> getByCriteriaNameNull(@QueryParam("name") String name)
	{
		List<Employee2> projection=employee2Bo.findByCriteriaNameNull();
		return projection;
	}
	@RequestMapping("specification")
	@GetMapping
	public List<Employee2> getBySpecification(@QueryParam("name") String name,
			@QueryParam("salary") Long salary)
	{
		return employee2Bo.findBySpecification(name, salary);
	}
	
	@RequestMapping("specification/and")
	@GetMapping
	public List<Employee2> getBySpecificationNameAndSalary(@QueryParam("name") String name,
			@QueryParam("salary") Long salary)
	{
		List<Employee2> employees=employee2Bo.findBySpecificationAndNameAndSalary(name, salary);
		return employees;
	}
	
	
}
