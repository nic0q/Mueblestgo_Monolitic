package com.tingeso.tingeso.controllers;

import com.tingeso.tingeso.entities.EmployeeEntity;
import com.tingeso.tingeso.entities.PlanillaSueldos;
import com.tingeso.tingeso.services.EmployeeService;
import com.tingeso.tingeso.services.ExtraHoursService;
import com.tingeso.tingeso.services.OfficeRRHH;
import com.tingeso.tingeso.services.WorkedDaysService;

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
  @Autowired
  ExtraHoursService extraHoursService;
  @Autowired
  WorkedDaysService workedDaysService;
  List<PlanillaSueldos> sueldos = new ArrayList<>();
  @GetMapping("/calcular-sueldos")
  public String calculateSalaries(Model model) throws ParseException {
    List<EmployeeEntity> employees = employeeService.getEmployees();
    sueldos.clear();
    if(workedDaysService.getAll().size() != 0){
      for (int i = 0; i < employees.size(); i++) {
        PlanillaSueldos planillaSueldos = new PlanillaSueldos(
          employees.get(i).getRut(),
          employees.get(i).getName(),
          employees.get(i).getLast_name(),
          employees.get(i).getCategory(),
          officeRRHH.calcular_anios_servicio(employees.get(i).getEntry_date()),
          officeRRHH.get_sueldo_base(employees.get(i).getCategory()),
          officeRRHH.calcular_bonificaciones(
            officeRRHH.calcular_anios_servicio(employees.get(i).getEntry_date()),
            officeRRHH.get_sueldo_base(employees.get(i).getCategory())
          ),
          officeRRHH.calcular_sueldo_horas_extra(
            employees.get(i).getCategory(),
            extraHoursService.get_extra_hours_efectivas(employees.get(i).getRut())
          ),
          officeRRHH.calcular_descuentos(employees.get(i).getRut()),
          officeRRHH.calcular_sueldo_bruto(employees.get(i).getRut()),
          officeRRHH.calcular_cotizacion_previsional(
            officeRRHH.calcular_sueldo_bruto(employees.get(i).getRut())
          ),
          officeRRHH.calcular_cotizacion_salud(
            officeRRHH.calcular_sueldo_bruto(employees.get(i).getRut())
          ),
          officeRRHH.calcular_sueldo_final(
            officeRRHH.calcular_sueldo_bruto(employees.get(i).getRut())
          )
        );
        sueldos.add(planillaSueldos);
      }
    }
    return "redirect:/planilla-sueldos";
  }
  @GetMapping("/planilla-sueldos")
  public String getSalaries(Model model) throws ParseException {
    model.addAttribute("planilla_sueldos", sueldos);
    return "viewSalaries";
  }
    
}
