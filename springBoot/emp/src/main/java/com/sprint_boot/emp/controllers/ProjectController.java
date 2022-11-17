package com.sprint_boot.emp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sprint_boot.emp.entities.Project;
import com.sprint_boot.emp.service.ProjectService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    // get all projects
    @GetMapping("/project")
    public ResponseEntity<List<Project>> getAllProject(){
        return ResponseEntity.of(Optional.of(projectService.getAllProject()));
    }

    // get project by id
    @GetMapping("/project/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") Long id){
        Project temp_prj=projectService.getProjectById(id);
        if(temp_prj==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(temp_prj));
    }

    // get project by employee id
    @GetMapping("/project/employee/{eid}")
    public ResponseEntity<List<Project>> getProjectByEmpId(@PathVariable("eid") Long eid){
        return ResponseEntity.of(Optional.of(projectService.getProjectByEmpId(eid)));
    }

    // add project
    @PostMapping("/project")
    public ResponseEntity<Project> addProject(@RequestBody Project prj){
        return new ResponseEntity<>(projectService.addProject(prj),HttpStatus.OK);
    }

    // delete project by id
    @DeleteMapping("/project/{pid}")
    public ResponseEntity<String> deleteById(@PathVariable("pid") Long pid){
        boolean f=projectService.deleteProjectById(pid);
        if(f){
            return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Project not found",HttpStatus.NOT_FOUND);
    }

    // delete project 
    @DeleteMapping("/project")
    public ResponseEntity<String> deleteAllProjects(){
        projectService.deleteAllProjects();
        return new ResponseEntity<>("All Projects Deleted Successfully",HttpStatus.OK);
    }
    
}
