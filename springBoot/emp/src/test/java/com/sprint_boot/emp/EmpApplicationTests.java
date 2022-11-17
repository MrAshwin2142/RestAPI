package com.sprint_boot.emp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sprint_boot.emp.repository.DepartmentRepository;
import com.sprint_boot.emp.service.DepartmentService;

@SpringBootTest
class EmpApplicationTests {

	@Autowired
	public DepartmentService dService;

	@MockBean
	public DepartmentRepository dRepository;
	
	@Test
	void contextLoads() {
	}

}
