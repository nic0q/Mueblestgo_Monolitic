package com.tingeso.tingeso.services;

import com.tingeso.tingeso.entities.ExtraHoursEntity;
import com.tingeso.tingeso.repostories.ExtraHoursRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtraHoursService {
  @Autowired
  ExtraHoursRepository extraHoursRepository;
  @Autowired
  EmployeeService employeeService;
  
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
  public void deleteAll(){
    extraHoursRepository.deleteAll();
  }
}
