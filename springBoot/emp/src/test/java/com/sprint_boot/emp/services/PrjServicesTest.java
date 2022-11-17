package com.sprint_boot.emp.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


import com.sprint_boot.emp.entities.Department;
import com.sprint_boot.emp.entities.Employee;
import com.sprint_boot.emp.entities.Project;
import com.sprint_boot.emp.repository.DepartmentRepository;
import com.sprint_boot.emp.repository.EmployeeRepository;
import com.sprint_boot.emp.repository.ProjectRepository;
import com.sprint_boot.emp.service.ProjectService;

@ExtendWith(MockitoExtension.class)
public class PrjServicesTest {
   
    @Mock
    private ProjectRepository pRepository;

    @InjectMocks
    private ProjectService pService;

    @Mock
    private DepartmentRepository dRepository;

    @Mock
    private EmployeeRepository eRepository;

    // @autowired 
    private MockMvc mockMvc;

    Department d1 = new Department(1, "Engineering", "Remote"); 
    Employee e1 = new Employee(11, "Ashwin", "a1@gmail", d1, new HashSet<>());

    Project p1 = new Project(21, "prj1", "java1", null, d1);
    Project p2 = new Project(22, "prj2", "java2", new HashSet<>(), d1);
    Project p3 = new Project(23, "prj3", "java3", null, null);
    Project p4 = new Project(24, "prj4", "java4", null, d1);

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(pService).build();
    }

    @Test
    public void getAllProjectsTest(){
        Mockito.when(pRepository.findAll()).thenReturn(Arrays.asList(p1,p2,p3));
        assertThat(pService.getAllProject().size()).isEqualTo(3);

    }

    @Test
    public void getProjectByIdTest(){
        Long id=21l;
        Mockito.when(pRepository.findById(id)).thenReturn(Optional.ofNullable(p1));
        assertThat(pService.getProjectById(id)).isNotNull();
    
    }

    @Test
    public void addProjectTest(){
        Mockito.when(pRepository.save(p4)).thenReturn(p4);
        assertThat(pService.addProject(p4)).isNotNull();
    
    }


    @Test
    public void deleteProjectByIdTest(){
        Mockito.when(pRepository.findById(21l)).thenReturn(Optional.of(p1));
        pService.deleteProjectById(21l);
        assertThat(pService.deleteProjectById(21l)).isTrue();

    }

    @Test
    public void deleteAllProjectTest(){
        Mockito.when(pRepository.findAll()).thenReturn(Arrays.asList(p1,p2,p3));
        pService.deleteAllProjects();
        assertThat(pService.deleteAllProjects()).isTrue();
    }
}
