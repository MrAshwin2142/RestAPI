package com.sprint_boot.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sprint_boot.emp.entities.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> { 
}
