# Microservices Tutorial 005
## Introducing Feign

[Feign](https://github.com/Netflix/feign) is a declarative web service client, that makes writing web service clients easier.
In this tutorial we will use `Feign` for the communication between our **department-service** and **employee-service**.

It's important to understand that `Feign` can be used independently from other `Spring Cloud Components`.
### Strategy
We will use our 3 services **department-service**, **employee-service** and  **config-service** from [last tutorial](https://github.com/Meziano/ms-tutorial-004).
As we want  **department-service** to get employee data from the **employee-service** we will have to introduce `Feign` in the **department-service**. The both other services will be used as they are.
### The dependency
To use `Feign` we just have to add the `spring-cloud-starter-openfeign` to the `pom` of  **department-service**.
### The EnableFeignClients annotation
The `DepartmentApplication` must be annotated with *EnableFeignClients*

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
 
<!--stackedit_data:
eyJoaXN0b3J5IjpbLTM0MDE4OTQ3MSwxMTYzMjI2MjM1LDE4ND
Y0OTIzMjEsLTIwNzg0NjQ0NjcsLTIwMjA2MzM1MjYsLTEyNzUx
MzE5MTUsODAwODYyNzI0LC0zNDg2OTk3NV19
-->