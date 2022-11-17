package com.sprint_boot.emp.services;

import java.util.Arrays;
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
import com.sprint_boot.emp.repository.DepartmentRepository;
import com.sprint_boot.emp.service.DepartmentService;

@ExtendWith(MockitoExtension.class)
public class DeptServicesTest {
    // @Autowired
    private MockMvc mockMvc;

    
    Department d1 = new Department(1, "java1", "test1");
    Department d2 = new Department(2, "java2", "test2");
    Department d3 = new Department(3, "java3", "test3");
    Department d4 = new Department(4, "java4", "test4");
    
    @Mock
    private DepartmentRepository dRepository;

    @InjectMocks
    private DepartmentService dService;
    
    
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(dService).build();
    }

    @Test
    public void getAllDepartmentTest() throws Exception{
        Mockito.when(dRepository.findAll()).thenReturn(Arrays.asList(d1,d2,d3));
        assertThat(dService.getAllDepartment().size()).isEqualTo(3);

    }
 
    @Test
    public void getDepartmentByIdTest(){
        Long id=1l;
        Mockito.when(dRepository.findById(id)).thenReturn(Optional.ofNullable(d1));
        assertThat(dService.getDepartmentById(id)).isNotNull();
    }

    @Test
    public void addDepartmentTest(){
        Mockito.when(dRepository.save(d4)).thenReturn(d4);
        assertThat(dService.addDepartment(d4)).isNotNull();
    }

    @Test
    public void updateDepartmentTest(){
        Mockito.when(dRepository.save(d4)).thenReturn(d4);
        Mockito.when(dRepository.findById(d4.getId())).thenReturn(Optional.ofNullable(d4));
        assertThat(dService.updateDepartment(d4, d4.getId())).isNotNull();
    }

    @Test
    public void deleteDepartmentByIdTest(){
        Mockito.when(dRepository.findById(1l)).thenReturn(Optional.of(d1));
        dService.deleteDepartmentById(1l);
        assertThat(dService.deleteDepartmentById(1l)).isTrue();

    }

    @Test
    public void deleteAllDepartmentTest(){
        Mockito.when(dRepository.findAll()).thenReturn(Arrays.asList(d1,d2,d3));
        dService.deleteAllDepartment();
        assertThat(dService.deleteAllDepartment()).isTrue();
    }
    
}
