package com.sprint_boot.emp.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.junit.Assert;
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
import com.sprint_boot.emp.entities.Employee;
import com.sprint_boot.emp.entities.Project;
import com.sprint_boot.emp.repository.EmployeeRepository;
import com.sprint_boot.emp.repository.ProjectRepository;
import com.sprint_boot.emp.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmpServiceTest {
    private MockMvc mockMvc;

    @Mock
    private EmployeeRepository eRepository;
    
    @Mock
    private DepartmentRepository dRepository;

    @Mock 
    private ProjectRepository pRepository;

    @InjectMocks
    private EmployeeService eService;

    Department d1 = new Department(1, "eng", "remote");
    Project p1 = new Project(1, "prj1", "java", new HashSet<>(), d1);

    Employee e1 = new Employee(11, "Ashwin1", "a1@gmail", d1, new HashSet<>());
    Employee e2 = new Employee(12, "Ashwin2", "a2@gmail", null, null);
    Employee e3 = new Employee(13, "Ashwin3", "a3@gmail", d1, null);
    Employee e4 = new Employee(14, "Ashwin4", "a4@gmail", null, new HashSet<>());

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(eService).build();
    }

    @Test
    public void getAllEmployeesTest(){
        Mockito.when(eRepository.findAll()).thenReturn(Arrays.asList(e1,e2,e3,e4));
        Assert.assertEquals(eService.getAllEmployees().size(), 4);
    }

    @Test
    public void getEmployeeByDeptId(){
        Mockito.when(eRepository.findById(11l)).thenReturn(Optional.ofNullable(e1));
        Assert.assertEquals(eService.getEmployeeById(11l),e1);
    }

    @Test 
    public void addEmployeeTest(){
        Mockito.when(eRepository.save(e4)).thenReturn(e4);
        assertThat(eService.addEmployee(e4)).isNotNull();
    }

    @Test 
    public void updateEmployee(){
        Mockito.when(eRepository.save(e4)).thenReturn(e4);
        Mockito.when(eRepository.findById(e4.getId())).thenReturn(Optional.ofNullable(e4));
        assertThat(eService.updateEmployee(e4, 14l,1l)).isNotNull();
    }
    
    @Test
    public void deleteEmployeeByIdTest(){
        Mockito.when(eRepository.findById(11l)).thenReturn(Optional.of(e1));
        eService.deleteEmployeeById(1l);
        assertThat(eService.deleteEmployeeById(11l)).isTrue();

    }

    @Test
    public void deleteAllEmployee(){
        Mockito.when(eRepository.findAll()).thenReturn(Arrays.asList(e1,e2,e3));
        eService.deleteAllEmployee();
        assertThat(eService.deleteAllEmployee()).isTrue();
    }

}
