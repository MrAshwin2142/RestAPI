package com.sprint_boot.emp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint_boot.emp.entities.Employee;
import com.sprint_boot.emp.service.EmployeeService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // Create employee..
    @PostMapping()
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp) {
        Employee e=employeeService.addEmployee(emp);
        return new ResponseEntity<>(e,HttpStatus.CREATED);
    }

    // get all employee..
    @GetMapping()
    public ResponseEntity<List<Employee>> getAllEmployee(){
        return new ResponseEntity<>(employeeService.getAllEmployees(),HttpStatus.OK);
    }

    // get employee by id..
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id){
        Employee temp_emp=employeeService.getEmployeeById(id);
        if(temp_emp==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(id),HttpStatus.OK);
    }

    // get employee by department id
    @GetMapping("/dept/{did}")
    public ResponseEntity<List<Employee>> getEmployeeByDeptId(@PathVariable("did") Long id){
        List<Employee> emp_list= employeeService.getEmployeeByDeptId(id);
        if(emp_list==null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.of(Optional.of(emp_list));
        }
    }

    // get employee by project id
    @GetMapping("/prj/{pid}")
    public ResponseEntity<List<Employee>> getEmployeeByPrjId(@PathVariable("pid") Long id){
        List<Employee> emp_list= employeeService.getEmployeeByPrjId(id);
        if(emp_list==null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.of(Optional.of(emp_list));
        }
    }

    //update employee
    @PutMapping("{eid}/department/{did}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee e, @PathVariable("eid") Long id, @PathVariable("did") Long dept_id){
        Employee emp=employeeService.updateEmployee(e, id, dept_id);
        if(emp!=null){
            return ResponseEntity.of(Optional.of(emp));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //delete employee by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Long id){
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<String>("employee deleted successfull",HttpStatus.OK);
    }
    
    @DeleteMapping()
    public ResponseEntity<String> deleteAllEmployee(){
        employeeService.deleteAllEmployee();
        return new ResponseEntity<>("Deleted All departments",HttpStatus.OK);
    }

}
