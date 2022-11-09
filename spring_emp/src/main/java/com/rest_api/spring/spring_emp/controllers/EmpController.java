package com.rest_api.spring.spring_emp.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rest_api.spring.spring_emp.entity.Employee;
import com.rest_api.spring.spring_emp.sevices.EmployeeService;

@RestController
@RequestMapping
public class EmpController {
    
    private EmployeeService employeeService = new EmployeeService();

    //get All Employee
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployee(){
        List<Employee> list=employeeService.getAllEmployees();

        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    // get Employee by Id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id){
        Employee employee=employeeService.getEmployeeById(id);
        if(employee==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(employee));
    }
    
    // Create Employee
    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee emp=null;
        try{
            System.out.println(emp);
            emp=this.employeeService.addEmployee(employee);
            System.out.println(emp);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch(Exception e){
            System.out.println(emp);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }

    // delete employee
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deletEmployee(@PathVariable("id") int id){
        try {
            this.employeeService.deletEmployee(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //update employee
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,@PathVariable("id") int id){
        try {
            this.employeeService.updateEmployee(employee,id);
            return ResponseEntity.ok().body(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }

}
