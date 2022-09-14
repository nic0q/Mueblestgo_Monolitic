package com.tingeso.tingeso.controllers;

import com.tingeso.tingeso.entities.EmployeeEntity;
import com.tingeso.tingeso.entities.PlanillaSueldos;
import com.tingeso.tingeso.services.EmployeeService;
import com.tingeso.tingeso.services.OfficeRRHH;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class OfficeRRHHController {
  
  @Autowired
  OfficeRRHH officeRRHH;

  @Autowired
  EmployeeService employeeService;

  @GetMapping("/calcular-sueldos")
  public String getSalaries(Model model) {
    List <EmployeeEntity> employees  = employeeService.getEmployees();

    List <PlanillaSueldos> sueldos = new ArrayList<PlanillaSueldos>();
    for(int i = 0; i < employees.size(); i++){
      PlanillaSueldos planillaSueldos = new PlanillaSueldos(employees.get(i).getRut(), officeRRHH.calcular_sueldo(employees.get(i).getId()));
      sueldos.add(planillaSueldos);
    }
    model.addAttribute("planilla_sueldos",  sueldos);
    return "viewSalaries";
  }
}
