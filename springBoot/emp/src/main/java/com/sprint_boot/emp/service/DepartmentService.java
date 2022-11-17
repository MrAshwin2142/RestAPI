package com.sprint_boot.emp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint_boot.emp.entities.Department;
import com.sprint_boot.emp.repository.DepartmentRepository;


@Service
public class DepartmentService {
    
    @Autowired
    DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository=departmentRepository;
    }

    // add department
    public Department addDepartment(Department d){
        return departmentRepository.save(d);
    }

    // get all department
    public List<Department> getAllDepartment(){
        return (List<Department>)departmentRepository.findAll();
    }

    //get department by ID
    public Department getDepartmentById(Long id){
        Optional<Department> d=departmentRepository.findById(id);
        Department dept=null;
        if(d.isPresent()){
            dept=d.get();
        }
        return dept;
    }

    //update department
    public Department updateDepartment(Department dept,Long id){
        Optional<Department> d=departmentRepository.findById(id);
        if(d.isPresent()){
            dept.setId(id);
            departmentRepository.save(dept);
            return dept;
        }
        return d.get();
    }

    // delete departmetn by id
    public Boolean deleteDepartmentById(Long id){
        Optional<Department> d=departmentRepository.findById(id);
        if(d.isPresent()){
            departmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // delete all department 
    public Boolean deleteAllDepartment(){
        departmentRepository.deleteAll();
        return true;
    }
}
