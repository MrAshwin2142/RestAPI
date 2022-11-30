package com.sprint_boot.emp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sprint_boot.emp.entities.Department;
import com.sprint_boot.emp.entities.Employee;
import com.sprint_boot.emp.entities.Project;
import com.sprint_boot.emp.repository.DepartmentRepository;
import com.sprint_boot.emp.repository.EmployeeRepository;
import com.sprint_boot.emp.repository.ProjectRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    public ProjectRepository projectRepository;

    // create employee 
    public Employee addEmployee(Employee emp){
        employeeRepository.save(emp);
        return emp;
    }

    //get all Employee
    public List<Employee> getAllEmployees(){
        return (List<Employee>)this.employeeRepository.findAll();
    }

    //get Employee by id
    public Employee getEmployeeById(Long id){
        Optional<Employee> emp=employeeRepository.findById(id);
        Employee e=null;
        if(emp.isPresent()){
            e=emp.get();
        }
        return e;
    }

    // // get Employee by department id
    // public List<Employee> getEmployeeByDeptId(Long dept_id){
    //     Optional<Department> dept=departmentRepository.findById(dept_id);
    //     if(dept.isPresent()){
    //         return employeeRepository.findEmployeesByDept_id(dept_id);
    //     }
    //     return null;
    // }
    //

    // get employee by project id
    public List<Employee> getEmployeeByPrjId(Long pid){
        Optional<Project> projectOptional=projectRepository.findById(pid);
        List<Employee> res=new ArrayList<>();
        if(projectOptional.isPresent()){
            List<Employee> empList =employeeRepository.findAll();
            for(Employee e:empList){
                Set<Project> temp=e.getAssignedProject();
                for(Project x:temp){
                    if(x.getPid()==pid){res.add(e);}
                }
            }
        }
        return res;
    }

    // get employee by department id
    public List<Employee> getEmployeeByDeptId(Long dept_id){
        Optional<Department> dept=departmentRepository.findById(dept_id);
        List<Employee> res=new ArrayList<>();
        if(dept.isPresent()){
            List<Employee> empList =employeeRepository.findAll();
            for(Employee e:empList){
                    if(e.getDepartment()==dept.get()) {res.add(e);}
            }
        }
        return res;
    }

    // update employee
    public Employee updateEmployee(Employee emp,Long emp_id,Long dept_id){
        Optional<Employee> temp_emp=employeeRepository.findById(emp_id);
        Optional<Department> temp_dept=departmentRepository.findById(dept_id);
        if(temp_emp.isPresent()){
            emp.setId(emp_id);
            if(temp_dept.isPresent()){
                emp.setDepartment(temp_dept.get());
            }
            employeeRepository.save(emp);
            return emp;
        }
        return null;
    }

    // get employee by employee by id and department id
    // public Employee geteEmployeeByIdAndDeptId(Long emp_id,Long dept_id){
    //     return employeeRepository.findByEmployeeIdAndDepertmentId(emp_id,dept_id);
    // }
    
    // delete employee by id
    public Boolean deleteEmployeeById(Long id){
        employeeRepository.deleteById(id);
        return true;
    }

    // delete all employee
    public Boolean deleteAllEmployee(){
        employeeRepository.deleteAll();
        return true;
    }
    
}
