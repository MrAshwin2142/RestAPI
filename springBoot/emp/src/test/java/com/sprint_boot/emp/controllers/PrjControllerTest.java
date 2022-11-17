package com.sprint_boot.emp.controllers;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
import com.sprint_boot.emp.entities.Project;
import com.sprint_boot.emp.service.DepartmentService;
import com.sprint_boot.emp.service.ProjectService;

@ExtendWith(MockitoExtension.class)
public class PrjControllerTest {
    private MockMvc mockMvc;
    ObjectMapper objMapper = new ObjectMapper();
    ObjectWriter objWriter = objMapper.writer();

    @Mock 
    private ProjectService pService;

    @InjectMocks
    private ProjectController pController;

    Department d1 = new Department(1l, "eng1", "indore1");
   
    Project p1 = new Project(1l, "prj1", "tech1", new HashSet<>(), d1);
    Project p2 = new Project(2l, "prj2", "tech2", null, null);
    Project p3 = new Project(3l, "prj3", "tech3", new HashSet<>(), null);
    Project p4 = new Project(4l, "prj4", "tech4", null, d1);
    
    @Before
    public void setUp(){
       MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(pController).build();
    }

   @Test
   public void getAllProjectTest() throws Exception{
        List<Project> prj = new ArrayList<>(Arrays.asList(p1,p2,p3));
        when(pService.getAllProject()).thenReturn(prj);
        mockMvc.perform(MockMvcRequestBuilders.get("/project")
        .contentType(MediaType.APPLICATION_JSON)).
        andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[2].pname", is("prj3")))
        .andExpect(jsonPath("$[1].pname", is("prj2")))
        .andExpect(jsonPath("$[0].pname", is("prj1")));
   }

   @Test
   public void getAllProjectByIdTest() throws Exception{
    when(pService.getProjectById(p1.getPid())).thenReturn(p1);

    mockMvc.perform(MockMvcRequestBuilders
                    .get("/project/1")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$.pname", is("prj1")));


   }

   @Test
   public void addProjectTest() throws Exception{
    when(pService.addProject(p1)).thenReturn(p1);
    Project prj= new Project(1l,"prj1","tech1",null,null);
    when(pService.addProject(Mockito.any(Project.class))).thenReturn(prj);
    mockMvc.perform(MockMvcRequestBuilders.post("/project")
                    .content(asJsonString(prj))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());


   }

   


   public String asJsonString(final Object obj) {
    try {
       return   new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}

}
