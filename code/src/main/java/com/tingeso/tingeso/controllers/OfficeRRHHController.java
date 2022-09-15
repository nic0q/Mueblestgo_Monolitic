package com.tingeso.tingeso.controllers;

import com.tingeso.tingeso.entities.EmployeeEntity;
import com.tingeso.tingeso.entities.PlanillaSueldos;
import com.tingeso.tingeso.services.EmployeeService;
import com.tingeso.tingeso.services.OfficeRRHH;

import java.text.ParseException;
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
  public String getSalaries(Model model) throws ParseException {
    List <EmployeeEntity> employees  = employeeService.getEmployees();

    List <PlanillaSueldos> sueldos = new ArrayList<PlanillaSueldos>();
    for(int i = 0; i < employees.size(); i++){
      PlanillaSueldos planillaSueldos = new PlanillaSueldos(
      employees.get(i).getRut(),
      employees.get(i).getName(),
      employees.get(i).getLast_name(),
      employees.get(i).getCategory(),
      officeRRHH.get_service_years(employees.get(i).getRut()),
      officeRRHH.get_sueldo_base(employees.get(i).getRut()),
      officeRRHH.calcular_bonificaciones(employees.get(i).getRut()),
      officeRRHH.calcular_sueldo_horas_extra(employees.get(i).getRut()),
      officeRRHH.calcular_descuentos(employees.get(i).getRut()),
      officeRRHH.calcular_sueldo_bruto(employees.get(i).getRut()),
      officeRRHH.calcular_cotizacion_previsional(officeRRHH.calcular_sueldo_bruto(employees.get(i).getRut())),
      officeRRHH.calcular_cotizacion_salud(officeRRHH.calcular_sueldo_bruto(employees.get(i).getRut())),
      officeRRHH.calcular_sueldo_final(officeRRHH.calcular_sueldo_bruto(employees.get(i).getRut()))
      );
      sueldos.add(planillaSueldos);
    }
    model.addAttribute("planilla_sueldos",  sueldos);
    return "viewSalaries";
  }
}
