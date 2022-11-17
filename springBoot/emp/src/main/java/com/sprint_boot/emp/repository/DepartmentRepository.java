package com.sprint_boot.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sprint_boot.emp.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    
 }