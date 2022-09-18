package com.tingeso.tingeso.services;

import com.tingeso.tingeso.entities.WorkedDaysEntity;
import com.tingeso.tingeso.repostories.WorkedDaysRepository;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkedDaysService {
  @Autowired
  WorkedDaysRepository workedDaysRepository;
  
  public List<WorkedDaysEntity> get_dias_trabajados() {
    return workedDaysRepository.findAll();
  }
  public List<WorkedDaysEntity> obtener_dias_trabajados(String rut_empleado){
    return get_dias_trabajados().stream().filter(dia -> dia.getRut_employee().equals(rut_empleado) && dia.getLate_minutes() <= 70).toList();
  }
  public Date obtener_fecha_inicio(){
    return workedDaysRepository.getDate();
  }
  public WorkedDaysEntity get_dia_trabajado(String rut_empleado, String date) throws ParseException {
    return workedDaysRepository.getWorkedDay(rut_empleado, convertir_fecha(date));
  }
  public Date convertir_fecha(String fecha) throws ParseException{
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
    java.util.Date date = sdf1.parse(fecha);
    java.sql.Date sqlStartDate = new java.sql.Date(date.getTime()); 
    return sqlStartDate;
  }
  public void insert_worked_day(String rut_empleado, String fecha, Integer horas_extra, Integer minutos_tarde) throws ParseException{
    WorkedDaysEntity workedDaysEntity = new WorkedDaysEntity(rut_empleado,convertir_fecha(fecha),horas_extra, minutos_tarde);
    workedDaysRepository.save(workedDaysEntity);
  }
  public void deleteAll(){
    workedDaysRepository.deleteAll();
  }
}
