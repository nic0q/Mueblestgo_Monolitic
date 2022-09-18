package com.tingeso.tingeso.services;

import com.tingeso.tingeso.entities.ExtraHoursEntity;
import com.tingeso.tingeso.repostories.ExtraHoursRepository;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtraHoursService {
  private DateFormat dateFormatSQL = new SimpleDateFormat("yyyy-MM-dd");

  @Autowired
  ExtraHoursRepository extraHoursRepository;

  @Autowired
  EmployeeService employeeService;

  @Autowired
  JustificativeService justificativeService;
  
  @Autowired
  WorkedDaysService workedDaysService;

  public Date convertir_fecha(String fecha) throws ParseException{
    
    java.util.Date date = dateFormatSQL.parse(fecha);
    java.sql.Date sqlStartDate = new java.sql.Date(date.getTime()); 
    return sqlStartDate;
  }
  
  public boolean save_extra_hours(ExtraHoursEntity extra_form) {
    if (employeeService.exists_employee(extra_form.getRut_employee())) {
      ExtraHoursEntity entry = new ExtraHoursEntity(
        extra_form.getDate(),
        extra_form.getRut_employee(),
        extra_form.getN_hours()
      );
      extraHoursRepository.save(entry);
      return true;
    }
    return false;
  }
  public List<ExtraHoursEntity> get_extra_hours_efectivas(String rut_employee){
    return extraHoursRepository.getHorasExtraEfectivas(rut_employee);
  }
  public ExtraHoursEntity get_extra_hours(String rut_employee, Date date){
    return extraHoursRepository.getExtraHours(rut_employee, date);
  }
  public void deleteAll(){
    extraHoursRepository.deleteAll();
  }
}
