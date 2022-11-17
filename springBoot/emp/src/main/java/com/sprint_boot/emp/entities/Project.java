package com.sprint_boot.emp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="projects")
public class Project {
    @Id
    private long pid;
    private String pname;
    private String ptech;


    /*
     * many to one maping between project and department
     * many project -> department
     */
    @ManyToOne
    @JoinColumn(name = "dept_id")
    // @OnDelete(action = OnDeleteAction.CASCADE)
    // @JsonIgnore
    private Department dept;

    /*
     * many to many mapping between employee and project
     * one employee -> many project and
     * one project -> many employee
     */
    @JsonIgnore
    @Column(name = "employeeAssigned")
    @ManyToMany(mappedBy = "assignedProject")
    
    private Set<Employee> employees = new HashSet<>();


    public Project() {
    }


    public Project(long pid, String pname, String ptech, Set<Employee> employees, Department dept) {
        this.pid = pid;
        this.pname = pname;
        this.ptech = ptech;
        this.employees = employees;
        this.dept = dept;
    }


    public long getPid() {
        return pid;
    }


    public void setPid(long pid) {
        this.pid = pid;
    }


    public String getPname() {
        return pname;
    }


    public void setPname(String pname) {
        this.pname = pname;
    }


    public String getPtech() {
        return ptech;
    }


    public void setPtech(String ptech) {
        this.ptech = ptech;
    }


    public Set<Employee> getEmployees() {
        return employees;
    }


    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }


    public Department getDept() {
        return dept;
    }


    public void setDept(Department dept) {
        this.dept = dept;
    }

    

}
