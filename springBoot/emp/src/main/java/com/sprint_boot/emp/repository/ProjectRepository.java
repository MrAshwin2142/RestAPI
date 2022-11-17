package com.sprint_boot.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint_boot.emp.entities.Project;

public interface ProjectRepository extends JpaRepository<Project,Long>{
    
        
}
