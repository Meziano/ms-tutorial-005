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
We want our `Feign` iterface to get the *employees* for a give *department* so we have add a method to our interface:
```
package de.meziane.ms.controller;
..
@FeignClient(name = "employee-service", url = "http://localhost:8082")
public interface EmployeeServiceClient {
  
}
```
 to **employee-service** has three endpoi 
<!--stackedit_data:
eyJoaXN0b3J5IjpbMTk1MDM2NzUyMSwtOTM5MTgxNjE3LDg3Mz
Q4NjcxNywxMDIwNzg3Mjc1LC0zNDAxODk0NzEsMTE2MzIyNjIz
NSwxODQ2NDkyMzIxLC0yMDc4NDY0NDY3LC0yMDIwNjMzNTI2LC
0xMjc1MTMxOTE1LDgwMDg2MjcyNCwtMzQ4Njk5NzVdfQ==
-->