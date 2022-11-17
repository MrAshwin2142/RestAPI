# Employee Assigment Spring Boot Rest API

### PURPOSE
Learn about 3-tier mapping and diffrent types relations between Employee, Project and Department enity. Create Rest API for CRUD opration on this enities. 

### Used TECH

1) Spring Boot Framework
2) Maven plugin
3) Postman
4) MySql
5) VS Code

### Maven Dependencies

    * spring-boot-jpa
    * spring-boot-web
    * spring-boot-myaql-connector
    * spring-boot-Junit
    * spring-boot-mockito
    * spring-boot-devtools

### Entities


* Employee 
    * Id (Auto generated)
    * Name
    * Email
    * Department
    * AssignedProject

* Project
    * Id
    * Name
    * Technology
    * Department
    * Employees

* Department
    * Id
    * Name
    * Location


### Mapping Between Entities


      1) one-to-many
        Department -> Employee 
A single department can have multiple employees.
      
       2)  Many-to-one 
        Employee -> Department
multiple employee can have same department 
 
       3) Many-to-Many
        Employee -> Project
This relationship between employee to project and project to employee because a single employee can have multiple project and a single project can be assigned to multiple employees.

