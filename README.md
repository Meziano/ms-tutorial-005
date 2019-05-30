# Microservices Tutorial 005
## Introducing Feign

[Feign](https://github.com/Netflix/feign) is a declarative web service client, that makes writing web service clients easier.
In this tutorial we will use `Feign` for the communication between our **department-service** and **employee-service**.

It's important to understand that `Feign` can be used independently from other `Spring Cloud Components`.
### Strategy
We will use our 3 services **department-service**, **employee-service** and  **config-service** from [last tutorial](https://github.com/Meziano/ms-tutorial-004).

As we want  **department-service** to get employee data from the **employee-service** we will have to introduce `Feign` in the **department-service**. The both other services will be used as they are.
### The dependency
To use `Feign` we just have to add the `spring-cloud-starter-openfeign` to the `pom` of  **department-service**:
```
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```
### The EnableFeignClients annotation
The `DepartmentApplication` must be annotated with *EnableFeignClients*. 
```
package de.meziane.ms;
..
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DepartmentApplication {
    public static void main( String[] args ) {
    	SpringApplication.run(DepartmentApplication.class, args);
    }
}
```
### The EmployeeServiceClient Interface

Feign allows building REST clients declaring and annotating an interface, the actual implementation is provisioned at runtime. 
In our case we introduce a new interface `EmployeeServiceClient` that we annotate with `@FeignClient`:
```
package de.meziane.ms.controller;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "employee-service", url = "http://localhost:8082")
public interface EmployeeServiceClient {
}
```  
We want our `Feign` interface to get the *employees* for a given *department* so we have to add a method to our interface:
```
package de.meziane.ms.controller;
..
@FeignClient(name = "employee-service", url = "http://localhost:8082")
public interface EmployeeServiceClient {

  @GetMapping("/{deptId}/employees")
  public List<Employee> getEmployeesForDeptId(@PathVariable Long deptId);
}
```
### The DepartmentController
In the `DepartmentController` we don't more need the <del>RestTemplate</del> as we will use the `EmployeeServiceClient` to fetch employee data fro **employee-service**:

```
package de.meziane.ms.controller;
..
@RestController
public class DepartmentController {
  @Autowired
  DepartmentRepository departmentRepository;

  @Autowired
  EmployeeServiceClient employeeServiceClient;

  ..
  @GetMapping("/departments/with-employees/{id}")
  public Department findByIdWithEmployees(@PathVariable Long id) {
    Department dept = departmentRepository.getOne(id);
    List<Employee> employees = employeeServiceClient.getEmployeesForDeptId(id);
    dept.setEmployees(employees);
    return dept;
  }
}
```
### Starting our microservices
Let's now start the **config-service**, the **employee-service** and the **department-service** with the same `configuration files` as in the (last tutorial)[https://github.com/Meziano/ms-tutorial-004] of course as `Spring Boot Application`

If we request http://localhost:8081/departments/with-employees/2

we get the IT-department with its employees: 
![The IT-Department with its employees using Feign](images/ITDeptWithItsEmployeesUsingFeign.png?raw=true)
### Summary
We introduced `Feign` that 
<!--stackedit_data:
eyJoaXN0b3J5IjpbLTI2MjA0MzY1NywtODI5ODI1ODE5LC0xNj
k1NTQ1MjgyLDE5NTAzNjc1MjEsLTkzOTE4MTYxNyw4NzM0ODY3
MTcsMTAyMDc4NzI3NSwtMzQwMTg5NDcxLDExNjMyMjYyMzUsMT
g0NjQ5MjMyMSwtMjA3ODQ2NDQ2NywtMjAyMDYzMzUyNiwtMTI3
NTEzMTkxNSw4MDA4NjI3MjQsLTM0ODY5OTc1XX0=
-->