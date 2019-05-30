package de.meziane.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import de.meziane.ms.domain.*;
import de.meziane.ms.repository.DepartmentRepository;

@RestController
public class DepartmentController {

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	EmployeeServiceClient employeeServiceClient;

	@GetMapping("/departments")
	public List<Department> findAll() {
		List<Department> depts = departmentRepository.findAll();
		return depts;
	}

	@GetMapping("/departments/{id}")
	public Department findById(@PathVariable Long id) {
		Department dept = departmentRepository.getOne(id);
		return dept;
	}

	@GetMapping("/departments/with-employees/{id}")
	public Department findByIdWithEmployees(@PathVariable Long id) {
		Department dept = departmentRepository.getOne(id);
		List<Employee> employees = employeeServiceClient.getEmployeesForDeptId(id);
		dept.setEmployees(employees);
		return dept;
	}

}
