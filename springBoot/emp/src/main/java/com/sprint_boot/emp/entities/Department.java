package com.sprint_boot.emp.entities;

import javax.persistence.*;

@Entity
@Table(name="Department")
public class Department {
    @Id
    @Column(name = "dept_id")
    private long id;
    private String dname;
    private String dlocation;

    public Department() {
    }
    public Department(long id, String dname, String dlocation) {
        this.id = id;
        this.dname = dname;
        this.dlocation = dlocation;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getDname() {
        return dname;
    }
    public void setDname(String dname) {
        this.dname = dname;
    }
    public String getDlocation() {
        return dlocation;
    }
    public void setDlocation(String dlocation) {
        this.dlocation = dlocation;
    }    
}
