package com.tingeso.tingeso.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
  static DateFormat dateFormaty = new SimpleDateFormat("yyyy/MM/dd");
  static DateFormat dateFormatSQL = new SimpleDateFormat("yyyy-MM-dd");
  static DateFormat dayFormat = new SimpleDateFormat("EEE");

  @Autowired
  ExtraHoursService extraHoursService;
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private WorkedDaysService workedDaysService;

  public double get_sueldo_base(String rut_empleado) throws ParseException{
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
  public double calcular_sueldo_horas_extra(String rut_empleado) throws ParseException{
    int n_horas_extra = 0;
    if(extraHoursService.get_extra_hours_efectivas(rut_empleado) != null){
      for(int i = 0; i < extraHoursService.get_extra_hours_efectivas(rut_empleado).size(); i++){
        n_horas_extra += extraHoursService.get_extra_hours_efectivas(rut_empleado).get(i).getN_hours();
      }
    }
    return valor_horas_extra(rut_empleado, n_horas_extra);
  }
  public double valor_horas_extra(String rut_empleado, Integer n_horas_extra){
    if(employeeService.getEmployeeByRut(rut_empleado).getCategory().equals("A")){
      return n_horas_extra * HORA_EXTRA_A;
    }
    else if(employeeService.getEmployeeByRut(rut_empleado).getCategory().equals("B")){
      return n_horas_extra * HORA_EXTRA_B;
    }
    else if(employeeService.getEmployeeByRut(rut_empleado).getCategory().equals("C")){
      return n_horas_extra * HORA_EXTRA_C;
    }
    return n_horas_extra;
  }
  public Integer get_service_years(String rut_empleado) throws ParseException{
    return (int) Math.floor(TimeUnit.MILLISECONDS.toDays((dateFormatSQL.parse(java.time.LocalDate.now().toString()).getTime() - dateFormaty.parse(employeeService.getEmployeeByRut(rut_empleado).getEntry_date()).getTime()))*0.00273785);
  }
  public double calcular_bonificaciones(String rut_empleado) throws ParseException{
    double sueldo_base = get_sueldo_base(rut_empleado);
    Integer service_years = get_service_years(rut_empleado);
    if(service_years >= 25){
      return sueldo_base * 0.17;
    }
    else if( service_years >= 20){
      return sueldo_base * 0.14;
    }
    else if( service_years >= 15){
      return sueldo_base * 0.11;
    }
    else if( service_years >= 10){
      return sueldo_base * 0.08;
    }
    else if( service_years >= 5){
      return sueldo_base * 0.05;
    }
    return 0;
  }
  public void get_inasistencias(String rut_empleado) throws ParseException{
    Date date = workedDaysService.obtener_fecha_inicio(); // obtengo la fecha de inicio para recorrer el mes
    Calendar c = Calendar.getInstance(); // creo el calendario
    c.setTime(date); // seteo a la fecha actual
    int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH); // dia maximo del mes
    for (int day = 1; day <= lastDay; day++) {
      c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), day);
      Date day_name = c.getTime();
      String str_day_name = dayFormat.format(day_name);
      if(str_day_name.equals("sáb") || str_day_name.equals("dom")) {
        continue;
      };
      if(workedDaysService.get_dia_trabajado(rut_empleado, dateFormaty.format(c.getTime())) == null){
        // System.out.println(rut_empleado + " NO ASISTIO EL DIA: " + dateFormaty.format(c.getTime()));
        // Revisar si tiene justificativo, sino apicar descuento
      }
      else{
        // System.out.println(rut_empleado + " ASISTIO EL DIA: " + dateFormaty.format(c.getTime()));
        // Ver si llego tarde
        // SI llego tarde y no tiene justificativo aplicar descuento
        // Si no, no hacer nada
      }
    }
  }
  public double calcular_descuentos(String rut_empleado){
    return 0;
  }
  public double calcular_sueldo_bruto(String rut_empleado) throws ParseException{
    double sueldo = 0;
    get_inasistencias(rut_empleado);
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
