package com.sprint_boot.emp.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private long id;
    private String name;
    private String email;
    
    /* 
        * many to one mapping between employee and department
        * many employee -> one department
    */
    @ManyToOne
    @JoinColumn(name = "dept_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    // @JsonIgnore
    private Department department;

    // /*
    //  * many to many mapping between employee and project
    //  * one employee -> many project and
    //  * one project -> many employee
    //  */
    @ManyToMany
    @JoinTable(name = "employee_project",joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> assignedProject=new HashSet<>();

    public Employee() {
    }

    public Employee(long id, String name, String email, Department department, Set<Project> assignedProject) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.assignedProject = assignedProject;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Project> getAssignedProject() {
        return assignedProject;
    }

    public void setAssignedProject(Set<Project> assignedProject) {
        this.assignedProject = assignedProject;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", department=" + department
                + ", assignedProject=" + assignedProject + "]";
    }
    
    
}
