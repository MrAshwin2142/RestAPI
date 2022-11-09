package com.rest_api.spring.spring_emp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rest_api.spring.spring_emp.entity.Employee;
@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer>{
    public Employee findById(int id);
}
