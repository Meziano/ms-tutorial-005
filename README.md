# Microservices Tutorial 005
## Introducing Feign

[Feign](https://github.com/Netflix/feign) is a declarative web service client, that makes writing web service clients easier.
In this tutorial we will use `Feign` for the communication between our **department-service** and **employee-service**.

It's important to understand that `Feign` can be used independently from other `Spring Cloud Components`.
## Strategy
We will use our 3 services **department-service**, **employee-service** and  **config-service** from [last tutorial](https://github.com/Meziano/ms-tutorial-004).
As we want  **department-service** to get employee data from the **employee-service** we will have to introduce `Feign` in the **department-service**. The both other services will be used as they are.

<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-openfeign</artifactId>
		</dependency>


 
<!--stackedit_data:
eyJoaXN0b3J5IjpbNzQzMDYwNDcsMTg0NjQ5MjMyMSwtMjA3OD
Q2NDQ2NywtMjAyMDYzMzUyNiwtMTI3NTEzMTkxNSw4MDA4NjI3
MjQsLTM0ODY5OTc1XX0=
-->