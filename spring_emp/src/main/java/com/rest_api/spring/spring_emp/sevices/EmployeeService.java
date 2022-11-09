package com.rest_api.spring.spring_emp.sevices;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rest_api.spring.spring_emp.dao.EmployeeRepository;
import com.rest_api.spring.spring_emp.entity.Employee;

@Component
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    // private static List<Employee> list=new ArrayList<>();
    // static{
    //     list.add(new Employee(2,"ashwin2","mrk"));
    //     list.add(new Employee(3,"ashwin3","mrk"));
    // }

    //get all employee
    public List<Employee> getAllEmployees(){
        List<Employee> list=(List<Employee>) this.employeeRepository.findAll();
        return list;
    }

    // get employee by id
    public Employee getEmployeeById(int id){
        Employee employee=null;
        try{
            //employee=list.stream().filter(e->e.getId()==id).findFirst().get();
           employee = this.employeeRepository.findById(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return employee;
    }

    // add Employee
    public Employee addEmployee(Employee emp){
        employeeRepository.save(emp);
        return emp;
    }

    // delete Employee
    public void deletEmployee(int id){
        // list=list.stream().filter(e->{
        //     if(e.getId()!=id){
        //         return true;
        //     }else{
        //         return false;
        //     }
        // }).collect(Collectors.toList());
        employeeRepository.deleteById(id);
    }

    //update the book
    public void updateEmployee(Employee emp,int id){
        // list.stream().map(b->{
        //     if(b.getId()==id){
        //         b.setDepartment(emp.getDepartment());
        //         b.setName(emp.getName());
        //     }
        //     return b;
        // }).collect(Collectors.toList());
        emp.setId(id);
        employeeRepository.save(emp);
    }
}
