package com.tingeso.tingeso.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tingeso.tingeso.entities.WorkedDaysEntity;

@Service
public class OfficeRRHH {
  static double SUELDO_A = 1700000;
  static double SUELDO_B = 1200000;
  static double SUELDO_C = 800000;
  static double HORA_EXTRA_A = 25000;
  static double HORA_EXTRA_B = 20000;
  static double HORA_EXTRA_C = 10000;
  static double COTIZACION_PREVISIONAL = 0.1; 
  static double COTIZACION_SALUD = 0.08;

  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private WorkedDaysService workedDaysService;

  public double get_sueldo_base(String rut_empleado){
    double sueldo = 0;
    if(employeeService.getEmployeeByRut(rut_empleado).getCategory().equals("A")){
      sueldo = SUELDO_A;
    }
    else if(employeeService.getEmployeeByRut(rut_empleado).getCategory().equals("B")){
      sueldo = SUELDO_B;
    }
    else if(employeeService.getEmployeeByRut(rut_empleado).getCategory().equals("C")){
      sueldo = SUELDO_C;
    }
    return sueldo;
  }
  public double calcular_sueldo_horas_extra(String rut_empleado){
    List <WorkedDaysEntity> dias_trabajados = workedDaysService.obtener_dias_trabajados(rut_empleado);
    System.out.println(dias_trabajados);
    double sueldo_horas = dias_trabajados.stream().mapToInt(WorkedDaysEntity::getWorked_hours).sum();
    if(employeeService.getEmployeeByRut(rut_empleado).getCategory().equals("A")){
      sueldo_horas = sueldo_horas * HORA_EXTRA_A;
    }
    else if(employeeService.getEmployeeByRut(rut_empleado).getCategory().equals("B")){
      sueldo_horas = sueldo_horas * HORA_EXTRA_B;
    }
    else if(employeeService.getEmployeeByRut(rut_empleado).getCategory().equals("C")){
      sueldo_horas = sueldo_horas * HORA_EXTRA_C;
    }
    System.out.println(sueldo_horas);
    return sueldo_horas;
  }
  public double calcular_bonificaciones(String rut_empleado){
    double sueldo_base = get_sueldo_base(rut_empleado);
    if(employeeService.getEmployeeByRut(rut_empleado).getService_years() >= 25){
      return sueldo_base * 0.17;
    }
    else if(employeeService.getEmployeeByRut(rut_empleado).getService_years() >= 20){
      return sueldo_base * 0.14;
    }
    else if(employeeService.getEmployeeByRut(rut_empleado).getService_years() >= 15){
      return sueldo_base * 0.11;
    }
    else if(employeeService.getEmployeeByRut(rut_empleado).getService_years() >= 10){
      return sueldo_base * 0.08;
    }
    else if(employeeService.getEmployeeByRut(rut_empleado).getService_years() >= 5){
      return sueldo_base * 0.05;
    }
    return 0;
  }
  public double calcular_descuentos(String rut_empleado){
    return 0;
  }
  public double calcular_sueldo_bruto(String rut_empleado){
    double sueldo = 0;
    sueldo = (get_sueldo_base(rut_empleado) + calcular_bonificaciones(rut_empleado) + calcular_sueldo_horas_extra(rut_empleado) - calcular_descuentos(rut_empleado));
    return sueldo;
  }
  public double calcular_cotizacion_salud(double sueldo_bruto){
    return sueldo_bruto * COTIZACION_SALUD;
  }
  public double calcular_cotizacion_previsional(double sueldo_bruto){
    return sueldo_bruto * COTIZACION_PREVISIONAL;
  }
  public double calcular_sueldo_final(double sueldo_bruto){
    return sueldo_bruto - calcular_cotizacion_salud(sueldo_bruto) - calcular_cotizacion_previsional(sueldo_bruto);
  }
}
