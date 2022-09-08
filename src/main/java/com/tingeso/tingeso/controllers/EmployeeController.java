package com.tingeso.tingeso.controllers;

// import org.springframework.web.bind.annotation.RequestParam;

import com.tingeso.tingeso.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @GetMapping("/employees")
  public String getEmployees(Model model) {
    model.addAttribute("employees", employeeService.getEmployees());
    return "greeting";
  }

  @GetMapping("/")
  public String home() {
    return "home";
  }

  @GetMapping("/insertTest")
  public String insertTest() {
    employeeService.insertEmployee();
    return "greeting";
  }
}
