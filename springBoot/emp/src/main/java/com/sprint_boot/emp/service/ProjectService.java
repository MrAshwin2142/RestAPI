package com.sprint_boot.emp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sprint_boot.emp.entities.Employee;
import com.sprint_boot.emp.entities.Project;
import com.sprint_boot.emp.repository.EmployeeRepository;
import com.sprint_boot.emp.repository.ProjectRepository;

@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // get all projects
    public List<Project> getAllProject(){
        return (List<Project>) projectRepository.findAll();
    }

    // get project by id
    public Project getProjectById(Long id){
        Optional<Project> prj=projectRepository.findById(id);
        Project p=null;
        if(prj.isPresent()){
            p=prj.get();
        }
        return p;
    }

    //get project by employee id
    public List<Project> getProjectByEmpId(Long emp_id){
        Optional<Employee> emp=employeeRepository.findById(emp_id);
        List<Project> prjs= new ArrayList<>();
        if(emp.isPresent()){
            List<Project> prjlist=projectRepository.findAll();
            for(Project p:prjlist){
                Set<Employee> emps= p.getEmployees();
                for(Employee e:emps){
                    if(e.getId()==emp_id){
                        prjs.add(p);
                    }
                }
            }
        }
        return prjs;
    }

    //add project
    public Project addProject(Project p){
        projectRepository.save(p);
        return p;
    }

    //update Project
    public Project updateProject(Project prj,Long id){
        Optional<Project> p=projectRepository.findById(id);
        if(p.isPresent()){
            prj.setPid(id);
            projectRepository.save(prj);
            return prj;
        }
        return p.get();
    }
    //delete projectby id
    public boolean deleteProjectById(Long id){
        Optional<Project> p=projectRepository.findById(id);
        if(p.isPresent()){
            projectRepository.deleteById(id);
            return true;
        }
        return false; 
    }

    // add project to Employee
    public Project addProjectToEmployee(Project prj,Long emp_id){

        Optional<Employee> emp=employeeRepository.findById(emp_id);
        if(emp.isPresent()){
            prj.getEmployees().add(emp.get());
        }
        projectRepository.save(prj);
        return prj;
    }
    //delete project
    public boolean deleteAllProjects(){
        projectRepository.deleteAll();
        return true;
    }
}
