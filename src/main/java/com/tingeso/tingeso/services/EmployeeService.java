package com.tingeso.tingeso.services;

import com.tingeso.tingeso.models.Employee;
import com.tingeso.tingeso.repostories.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getEmployees() {
		return (List<Employee>) employeeRepository.findAll();
	}
}
