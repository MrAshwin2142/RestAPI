package com.sprint_boot.emp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sprint_boot.emp.entities.Department;
import com.sprint_boot.emp.service.DepartmentService;

import org.springframework.web.bind.annotation.*;


@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    //add department
    @PostMapping("/dept")
    public ResponseEntity<Department> addDepartment(@RequestBody Department d){
        return ResponseEntity.of(Optional.of(departmentService.addDepartment(d)));
    }

    //get all department ..
    @GetMapping("/dept")
    public List<Department> getAllDepartment(){
        return departmentService.getAllDepartment();
    }
    
    // det department by id ..
    @GetMapping("/dept/{id}")
    public ResponseEntity<Department> getDeptById(@PathVariable("id") Long id){
        Department temp_dept = departmentService.getDepartmentById(id);
        if(temp_dept==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<Department>(departmentService.getDepartmentById(id),HttpStatus.OK);
        
        // return ResponseEntity.of(Optional.of(departmentService.getDepartmentById(id)));
    }

   

    // update Department ..
    @PutMapping("/dept/{id}")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department dept,@PathVariable("id") Long id){
        Department temp_dept=departmentService.updateDepartment(dept,id);
        if(temp_dept==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        return ResponseEntity.of(Optional.of(dept));
    }
    //delete all department
    @DeleteMapping("/dept")
    public ResponseEntity<String> deleteAllDept(){
        departmentService.deleteAllDepartment();
        return new ResponseEntity<>("Deleted All departments",HttpStatus.OK);
    }

    //delete  department by id
    @DeleteMapping("/dept/{id}")
    public ResponseEntity<String> deleteDeptById(@PathVariable("id") Long id){
        Boolean b = departmentService.deleteDepartmentById(id);
        if(b){
            return new ResponseEntity<>("departments deleted successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Department not found",HttpStatus.NOT_FOUND);
        }
    }
}
