package com.tingeso.tingeso.services;

import com.tingeso.tingeso.entities.ExtraHoursEntity;
import com.tingeso.tingeso.entities.WorkedDaysEntity;
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

  @Autowired
  ExtraHoursRepository extraHoursRepository;

  @Autowired
  EmployeeService employeeService;
  @Autowired
  JustificativeService justificativeService;
  @Autowired
  WorkedDaysService workedDaysService;
  static DateFormat dateFormatSQL = new SimpleDateFormat("yyyy-MM-dd");
  static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/mm/dd");
  public Date convertir_fecha(String fecha) throws ParseException{
    
    java.util.Date date = dateFormatSQL.parse(fecha);
    java.sql.Date sqlStartDate = new java.sql.Date(date.getTime()); 
    return sqlStartDate;
  }
  
  public boolean save_extra_hours(ExtraHoursEntity extra_form) {
    System.out.print(extra_form);
    if (employeeService.exists_employee(extra_form.getRut_employee())) {
      ExtraHoursEntity entry = new ExtraHoursEntity(
        extra_form.getDate(),
        extra_form.getRut_employee(),
        extra_form.getN_hours()
      );
      extraHoursRepository.save(entry);
      System.out.println(
        "Horas extra guardadas correctamente " + entry.toString()
      );
      return true;
    }
    System.out.println("El empleado no existe");
    return false;
  }
  public List<ExtraHoursEntity> get_extra_hours_efectivas(String rut_employee) throws ParseException {
    List<ExtraHoursEntity>horas = extraHoursRepository.getHorasExtraEfectivas(rut_employee);
    // for(int i = 0; i < horas.size(); i++){
    //   WorkedDaysEntity dia = workedDaysService.get_dia_trabajado(rut_employee,sdf1.format(horas.get(i).getDate()).toString());
    //   if(dia.getLate_minutes() > 70 && justificativeService.searchJustificative(rut_employee,sdf1.parse( dia.getDate().toString()).toString()) == null){
    //     horas.remove(i);
    //   }
    // }
    return horas;
  }
  public ExtraHoursEntity get_extra_hours(String rut_employee, Date date) throws ParseException {
    return extraHoursRepository.getExtraHours(rut_employee, date);
  }
  public void deleteAll(){
    extraHoursRepository.deleteAll();
  }
}
