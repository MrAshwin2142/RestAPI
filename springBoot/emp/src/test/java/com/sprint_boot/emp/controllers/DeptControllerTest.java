package com.sprint_boot.emp.controllers;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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



import com.sprint_boot.emp.entities.Department;
import com.sprint_boot.emp.service.DepartmentService;

@ExtendWith(MockitoExtension.class)
public class DeptControllerTest {
    private MockMvc mockMvc;
    ObjectMapper objMapper = new ObjectMapper();
    ObjectWriter objWriter = objMapper.writer();

    @Mock 
    private DepartmentService dService;

    @InjectMocks
    private DepartmentController dController;

    Department d1 = new Department(1l, "eng1", "indore1");
    Department d2 = new Department(2l, "eng2", "indore2");
    Department d3 = new Department(3l, "eng3", "indore3");

    @Before
    public void setUp(){
       MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(dController).build();
    }

   @Test
   public void getAllDepartmentTest() throws Exception{
        List<Department> dept = new ArrayList<>(Arrays.asList(d1,d2,d3));
        when(dService.getAllDepartment()).thenReturn(dept);
        mockMvc.perform(MockMvcRequestBuilders.get("/dept")
        .contentType(MediaType.APPLICATION_JSON)).
        andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[2].dname", is("eng3")))
        .andExpect(jsonPath("$[1].dname", is("eng2")))
        .andExpect(jsonPath("$[0].dname", is("eng1")));
   }

   @Test
   public void getAllDepartmentByIdTest() throws Exception{
    when(dService.getDepartmentById(d1.getId())).thenReturn(d1);

    mockMvc.perform(MockMvcRequestBuilders
                    .get("/dept/1")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$.dname", is("eng1")));


   }

   @Test
   public void addDepartmentTest() throws Exception{
    when(dService.addDepartment(d1)).thenReturn(d1);
    Department dept= new Department(1,"eng1","remote1");
    when(dService.addDepartment(Mockito.any(Department.class))).thenReturn(dept);
    mockMvc.perform(MockMvcRequestBuilders.post("/dept")
                    .content(asJsonString(dept))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());


   }

   @Test
   public void updateDepartmentTest ()throws Exception{

       Department d1= new Department(1,"eng1","indore1");
       //new Department(2,"eng2","indore2");

       when(dService.updateDepartment(d1, d1.getId())).thenReturn(d1);
       mockMvc.perform(MockMvcRequestBuilders.put("/dept/2")
                .content(asJsonString(d1))
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
