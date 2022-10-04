package com.tingeso.tingeso;

import com.tingeso.tingeso.entities.EmployeeEntity;
import com.tingeso.tingeso.repositories.EmployeeRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

class EmployeeServiceTests {
  @Mock
  private EmployeeRepository employeeRepository;
  @InjectMocks
  private EmployeeServiceTests employeeService;
  private EmployeeEntity employee;
  @BeforeEach
  void setUp(){
    MockitoAnnotations.openMocks(this);
    employee = new EmployeeEntity();
    employee.setRut("98765432-1");
  }
  @Test
  void findByRut(){
    when(employeeRepository.findByRut("98765432-1")).thenReturn(employee);
    assertNotNull(employeeRepository.findByRut("98765432-1"));
  }
  @Test
  void findAll(){
    List<EmployeeEntity> employees = new ArrayList<EmployeeEntity>();
    employees.add(employee);
    when(employeeRepository.findAll()).thenReturn(employees);
    assertNotNull(employeeRepository.findAll());
  }
  @Test
  void existEmployee(){
    when(employeeRepository.findByRut("92765432-1")).thenReturn(employee);
    assertNotNull(employeeRepository.findByRut("92765432-1"));
  }
  @Test
  void findOne(){
    when(employeeRepository.findOne()).thenReturn(employee);
    assertNotNull(employeeRepository.findOne());
  }
}
