package com.tingeso.tingeso.services;

import com.tingeso.tingeso.entities.WorkedDaysEntity;
import com.tingeso.tingeso.repostories.WorkedDaysRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkedDaysService {
  @Autowired
  WorkedDaysRepository workedDaysRepository;
  
  public List<WorkedDaysEntity> get_dias_trabajados() {
    return (List<WorkedDaysEntity>) workedDaysRepository.findAll();
  }
  public List<WorkedDaysEntity> obtener_dias_trabajados(String rut_empleado){
    return get_dias_trabajados().stream()
      .filter(arg0 -> arg0.getRut_employee() == rut_empleado)
      .collect(Collectors.toList());
  }
  public void insert_worked_day(String rut_empleado, String date, String t_entry, String t_exit, Integer horas_trabajadas, Integer horas_extra, Integer minutos_tarde){
    WorkedDaysEntity workedDaysEntity = new WorkedDaysEntity(rut_empleado,date, t_entry, t_exit, horas_trabajadas,horas_extra, minutos_tarde);
    workedDaysRepository.save(workedDaysEntity);
  }
}
