package com.tingeso.tingeso.services;

import com.tingeso.tingeso.entities.EmployeeEntity;
import com.tingeso.tingeso.repositories.EmployeeRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  public List<EmployeeEntity> getEmployees() {
    return employeeRepository.findAll();
  }
  public EmployeeEntity getEmployeeByRut(String rut) {
    return employeeRepository.findByRut(rut);
  }
  public boolean exists_employee(String rut){
    return employeeRepository.findByRut(rut) != null;
  }
  public EmployeeEntity findOne(){
    return employeeRepository.findOne();
  }
}
