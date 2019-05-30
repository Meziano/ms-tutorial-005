package de.meziane.ms.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import de.meziane.ms.domain.Employee;

@FeignClient(name = "employee-service", url = "http://localhost:8082")
public interface EmployeeServiceClient {
	
	@GetMapping("/{deptId}/employees")
	public List<Employee> getEmployeesForDeptId(@PathVariable Long deptId); 

}
