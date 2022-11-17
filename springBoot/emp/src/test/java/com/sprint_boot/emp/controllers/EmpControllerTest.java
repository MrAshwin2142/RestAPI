package com.sprint_boot.emp.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import com.sprint_boot.emp.entities.Department;
import com.sprint_boot.emp.entities.Employee;
import com.sprint_boot.emp.service.EmployeeService;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import java.util.HashSet;

@ExtendWith(MockitoExtension.class)
public class EmpControllerTest {
    
    private
    MockMvc mockMvc;
    ObjectMapper objMapper = new ObjectMapper();
    ObjectWriter objWriter = objMapper.writer();

    @Mock
    private EmployeeService eService;

    @InjectMocks
    private EmployeeController eController;

    Department d1= new Department(1l, "dept1", "loc1");
    Employee e1 = new Employee(11l, "emp1", "e1@mail", d1, new HashSet<>(0));
    Employee e2 = new Employee(12l, "emp2", "e2@mail", null, null);
    Employee e3 = new Employee(13l, "emp3", "e3@mail", d1, null);
    Employee e4 = new Employee(14l, "emp4", "e4@mail", null, null);

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(eController).build();

    }

    @Test
    public void addEmployeeTest() throws Exception {
        Mockito.when(eService.addEmployee(e1)).thenReturn(e1);
        String content = objMapper.writeValueAsString(e1);
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .post("/employees")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
                mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isCreated());
    }


    @Test
    public void getAllEmployeesTest() throws Exception {
        List<Employee> emp = new ArrayList<>(Arrays.asList(e1,e2,e3));
        Mockito.when(eService.getAllEmployees()).thenReturn(emp);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].name", is("emp3")))
                .andExpect(jsonPath("$[1].name", is("emp2")))
                .andExpect(jsonPath("$[0].name", is("emp1")));
    }

    @Test
    public void getEmployeeByIdTest() throws Exception {
        Mockito.when(eService.getEmployeeById(e1.getId())).thenReturn(e1);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/employees/11")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("emp1")));
    }

//     @Test
//     public void updateEmployeeTest throws Exception {
//         Mockito.when(eService.updateEmployee(e1, 11l, 1l)).thenReturn(Optional.of(e1));
//         String content = objWriter.writeValueAsString(e1);
//         MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.put("/employees/1/department/1")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .accept(MediaType.APPLICATION_JSON)
//                 .content(content);
//         mockMvc.perform(mockHttpServletRequestBuilder)
//                 // .andExpect(status().isOk());
//                .andExpect(jsonPath("$", notNullValue()));
//                .andExpect(jsonPath("$.firstName", is("Testing")));
//     }

    @Test
   public void updateEmployeeTest ()throws Exception{
       Employee e11= new Employee(1l,"as","e1@mil",d1,null);
       //new Department(2,"eng2","indore2");

       when(eService.updateEmployee(e11,1l,1l)).thenReturn(e11);
       mockMvc.perform(MockMvcRequestBuilders.put("/employees/1/department/1")
                .content(asJsonString(e11))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }


    public String asJsonString(final Object obj) {
        try {
           return   new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
