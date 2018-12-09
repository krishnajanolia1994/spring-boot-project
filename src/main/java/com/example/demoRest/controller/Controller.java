package com.example.demoRest.controller;

import java.util.List;

//import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.QueryParam;

//import org.apache.logging.log4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoRest.bo.EmployeeBo;
import com.example.demoRest.entiy.Employee;
import com.example.demoRest.projection.MyProjction;


@RestController
@RequestMapping("/employees")
public class Controller {
//	Logger logger=(Logger) LoggerFactory.getLogger(Controller.class);
	@Autowired
	EmployeeBo employeeBo;
	@RequestMapping("/a")
	public String show()
	{
		return "hello world";
	}
	@PostMapping
	public Employee create(@RequestBody Employee employee)
	{
		System.out.println(employee.getEmployer()+"  "+employee.getManager()
		+"  "+employee.getName()+"   "+employee.getSalary());
		return employeeBo.saveEmployee(employee);
	}
	
	@RequestMapping()
	@GetMapping
	public List<Employee> getAll()
	{
		List<Employee> listEmployee=null;
	    listEmployee=employeeBo.getAll();
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
	
	@RequestMapping("/id")
	@GetMapping
	public Employee getById(@PathVariable  Long  id)
	{
		Employee employee=employeeBo.getById(id);
		return employee;
	}
	
	@RequestMapping("get_by_name")
	@GetMapping
	public List<Employee> getByName(@QueryParam("name") String name)
	{
		List<Employee> employees=null;
		employees=employeeBo.getByName(name);
		return employees;
	}
	
	@RequestMapping("get_by_name_and_last_name")
	@GetMapping
	public List<Employee> getByNameLName(@QueryParam("name") String name,@QueryParam("LName") String LName)
	{
		List<Employee> list=null;
		list=employeeBo.getByNameLName(name,LName);
		return list;
	}
	
	@RequestMapping("get_by_name_or_last_name")
	@GetMapping
	public List<Employee> getByNameOrLName(@QueryParam("name") String name,@QueryParam("LName") String LName)
	{
		List<Employee> employee=employeeBo.getByNameOrLName(name,LName);
		return employee;
	}
	
	@RequestMapping("get_by_sallary_between")
	@GetMapping
	public List<Employee> getBySallaryBetween(@QueryParam("salary1") Long salary1,
			@QueryParam("salary2") Long salary2)
	{
		List<Employee> employee=employeeBo.getBySalaryBetween(salary1,salary2);
		return employee;
	}
	
	@RequestMapping("get_by_sallary_is_null")
	@GetMapping
	public List<Employee> getBySallaryLessThan()
	{
		List<Employee> employee=employeeBo.getBySalaryIsNull();
		return employee;
	}
	
	@RequestMapping("get_by_first-name_like")
	@GetMapping
	public List<Employee> getByFirstName(@QueryParam("firstName") String firstName)
	{
		String pattern=firstName+"%";
		List<Employee> employee=employeeBo.getByFirstNameIsLike(pattern);
		return employee;
	}
	
	@RequestMapping("get_by_first-name_Strat-with")
	@GetMapping
	public List<Employee> getByFirstNameStartWith(@QueryParam("firstName") String firstName)
	{
		List<Employee> employee=employeeBo.getByFirstNameStartWith(firstName);
		return employee;
	}
	
	@RequestMapping("get_by_first-name_stating-with_order-by_salary")
	@GetMapping
	public List<Employee> getByFirstNameStartWithOrdrBy(@QueryParam("firstName") String firstName)
	{
		List<Employee> employee=employeeBo.getByFirstNameStartingWithOrderBySalary(firstName);
		return employee;
	}
	
	@RequestMapping("get_by_named-query")
	@GetMapping
	public List<Employee> getByByNamedQuery(@QueryParam("salary") Long salary)
	{
		List<Employee> employee=employeeBo.getNamedQuery(salary);
		return employee;
	}
	
	@RequestMapping("get_@Query")
	@GetMapping
	public List<Employee> getByLastNmae(@QueryParam("lName") String lName)
	{
		List<Employee> employee=employeeBo.findBySurName(lName);
		return employee;
	}
	
	@RequestMapping("projection")
	@GetMapping
	public List<MyProjction> getByProjection(@QueryParam("lName") String lName,
			@QueryParam("salary") Long salary)
	{
		List<MyProjction> projection=employeeBo.findByProjection(lName,salary);
		return projection;
	}
	
	@RequestMapping("criteria")
	@GetMapping
	public List<Object> getByCriteria()
	{
		List<Object> projection=employeeBo.findByCriteria();
		return projection;
	}
	
	
	
	



}
