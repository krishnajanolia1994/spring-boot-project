package com.example.demoRest.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

//import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demoRest.bo.EmployeeBo;
import com.example.demoRest.entiy.Employee;
import com.example.demoRest.projection.MyProjction;


@RestController
@RequestMapping("/employees")
public class Controller {
	Logger logger= LoggerFactory.getLogger(Controller.class);
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
	
	@RequestMapping("/pageble")
	@GetMapping
	public Page<Employee> getAll(Pageable  pageable)
	{
		return  employeeBo.getAll(pageable);
	}
	 
	@RequestMapping("/async" )
	@GetMapping
	public List<Employee> getAllSyn()
	{

		return  (List<Employee>) employeeBo.getAllAsync();
	}
	
	@RequestMapping("advanced/search")
	@GetMapping
	public List<Employee> getAllBySpecification(@QueryParam("fName") String fName,
			@QueryParam("lName") String lName,
			@QueryParam("salary") Long salary)
	{

		return  employeeBo.getAll(fName,lName,salary);
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
	@RequestMapping("get_by_name_and_last_name_by_criteria")
	@GetMapping
	public List<Employee> getByNameLNameByCriteria(@QueryParam("name") String name,@QueryParam("LName") String LName)
	{
		return employeeBo.getByNameLNameByCriteria(name,LName);
	}
	@RequestMapping("get_by_salary_less_than_by_criteria")
	@GetMapping
	public List<Employee> getBySalaryLessThan(@QueryParam("salary") Long salary)
	{
		return employeeBo.getBySalaryLessThan(salary);
	}
	@RequestMapping("get_by_salary_between_by_criteria")
	@GetMapping
	public List<Employee> getByBetween(@QueryParam("salary") Long salary,@QueryParam("salary1") Long salary1)
	{
		return employeeBo.getBySalaryBetweenCriteria(salary,salary1);
	}
	@RequestMapping("get_by_name_like_criteria")
	@GetMapping
	public List<Employee> getByBetween(@QueryParam("salary") Long salary,@QueryParam("name") String name)
	{
		return employeeBo.getByNameLikeCriteria(name+"%");
	}
	@RequestMapping("get_by_name_in_criteria")
	@GetMapping
	public List<Employee> getByBetween()
	{
		return employeeBo.getByInCriteria();
	}
	
//	@RequestMapping("pradicate")
//	@GetMapping
//	public List<Employee> getPadicate(@QueryParam("name") String name,@QueryParam("LName") String LName)
//	{
//		List<Employee> list=null;
//		list=employeeBo.getByPradicate(name,LName);
//		return list;
//	}
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
	
	@RequestMapping("criteria_name_is_null")
	@GetMapping
	public List<Employee> getByCriteriaNameNull(@QueryParam("name") String name)
	{
		List<Employee> projection=employeeBo.findByCriteriaNameNull();
		return projection;
	}
	@RequestMapping("specification")
	@GetMapping
	public List<Employee> getBySpecification(@QueryParam("name") String name,
			@QueryParam("salary") Long salary)
	{
		return employeeBo.findBySpecification(name, salary);
	}
	
	@RequestMapping("getCriteriaAndPradicate")
	@GetMapping
	public Page<Object> getCriteriaAndPradicate(@QueryParam("name") String name,
			@QueryParam("salary") Long salary, Pageable pageable)
	{
		return employeeBo.getCriteriaAndPradicate(name, salary,pageable);
	}
	
	@RequestMapping("specification/and")
	@GetMapping
	public List<Employee> getBySpecificationNameAndSalary(@QueryParam("name") String name,
			@QueryParam("salary") Long salary)
	{
		List<Employee> employees=employeeBo.findBySpecificationAndNameAndSalary(name, salary);
		return employees;
	}
	
	@RequestMapping(value="/upload" , consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	@GetMapping
	public void uploadFile(@RequestParam("file") MultipartFile file)
	{
		String path = "D:\\my-folder\\";
		File convertFile = new File(path+file.getOriginalFilename());
		createNewFile(convertFile);
		FileOutputStream fileOutputStream = getoutPutStream(convertFile);
		wrietFileIntoNewFile(fileOutputStream,file);
	}

	private void wrietFileIntoNewFile(FileOutputStream fileOutputStream, MultipartFile file) {
		try {
			fileOutputStream.write(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	private void createNewFile(File convertFile) {
		try {
			convertFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private FileOutputStream getoutPutStream(File convertFile) {
		FileOutputStream fileOutputStream =null;
		try {
			fileOutputStream=new FileOutputStream(convertFile);
		} catch (FileNotFoundException e) {
			logger.error("could not create Object of outputStrea in uploadind file");
		}
		return fileOutputStream;
	}
	
	@RequestMapping(value="/schedule")
	@GetMapping
	public void shcheduling()
	{
		employeeBo.schedule();
	}
}
