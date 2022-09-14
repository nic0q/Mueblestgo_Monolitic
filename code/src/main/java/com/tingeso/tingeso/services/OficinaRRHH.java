package com.tingeso.tingeso.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.tingeso.tingeso.entities.WorkedDaysEntity;

public class OficinaRRHH {
  static float SUELDO_A = 1700000;
  static float SUELDO_B = 1200000;
  static float SUELDO_C = 800000;
  static float HORA_EXTRA_A = 25000;
  static float HORA_EXTRA_B = 20000;
  static float HORA_EXTRA_C = 10000;
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private WorkedDaysService workedDaysService;
  public float calcular_sueldo(Integer id_empleado){
    float sueldo = 0;
    sueldo =   (get_sueldo_base(id_empleado) + calcular_bonificaciones(id_empleado) + calcular_sueldo_horas_extra(id_empleado) - calcular_descuentos(id_empleado));
    return sueldo;
  }
  public float get_sueldo_base(Integer id_empleado){
    float sueldo = 0;
    if(employeeService.getEmployeeById(id_empleado).getCategory() == "A"){
      sueldo = SUELDO_A;
    }
    else if(employeeService.getEmployeeById(id_empleado).getCategory() == "B"){
      sueldo = SUELDO_B;
    }
    else if(employeeService.getEmployeeById(id_empleado).getCategory() == "C"){
      sueldo = SUELDO_C;
    }
    return sueldo;
  }
  public float calcular_sueldo_horas_extra(Integer id_empleado){
    List <WorkedDaysEntity> dias_trabajados = workedDaysService.obtener_dias_trabajados(id_empleado);
    float sueldo_horas = dias_trabajados.stream().mapToInt(WorkedDaysEntity::getWorked_hours).sum();
    if(employeeService.getEmployeeById(id_empleado).getCategory() == "A"){
      sueldo_horas = sueldo_horas * HORA_EXTRA_A;
    }
    else if(employeeService.getEmployeeById(id_empleado).getCategory() == "B"){
      sueldo_horas = sueldo_horas * HORA_EXTRA_B;
    }
    else if(employeeService.getEmployeeById(id_empleado).getCategory() == "C"){
      sueldo_horas = sueldo_horas * HORA_EXTRA_C;
    }
    return sueldo_horas;
  }
  public float calcular_bonificaciones(Integer id_empleado){
    float bonificacion = 0;
    float sueldo_base = get_sueldo_base(id_empleado);
    if(employeeService.getEmployeeById(id_empleado).getService_years() >= 25){
      return (float) (sueldo_base * 0.17);
    }
    else if(employeeService.getEmployeeById(id_empleado).getService_years() >= 20){
      return (float) (sueldo_base * 0.14);
    }
    else if(employeeService.getEmployeeById(id_empleado).getService_years() >= 15){
      return (float) (sueldo_base * 0.11);
    }
    else if(employeeService.getEmployeeById(id_empleado).getService_years() >= 10){
      return (float) (sueldo_base * 0.08);
    }
    else if(employeeService.getEmployeeById(id_empleado).getService_years() >= 5){
      return (float) (sueldo_base * 0.05);
    }
    return sueldo_base * bonificacion;
  }
  public float calcular_descuentos(Integer id_empleado){
    return 0;
  }
}
