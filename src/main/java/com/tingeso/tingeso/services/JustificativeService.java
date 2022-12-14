package com.tingeso.tingeso.services;

import com.tingeso.tingeso.entities.JustificativeEntity;
import com.tingeso.tingeso.repositories.JustificativeRepository;

import lombok.Generated;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Generated
@Service
public class JustificativeService {

  @Autowired
  JustificativeRepository justificativeRepository;

  @Autowired
  EmployeeService employeeService;
  @Generated
  public Date convertir_fecha(String fecha) throws ParseException{
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
    java.util.Date date = sdf1.parse(fecha);
    return new java.sql.Date(date.getTime());
  }
  @Generated
  public boolean save_justificative(JustificativeEntity justificative) {
    if (employeeService.exists_employee(justificative.getRut_employee())) {
      JustificativeEntity entry = new JustificativeEntity(
        justificative.getDate(),
        justificative.getRut_employee()
      );
      justificativeRepository.save(entry);
      return true;
    }
    return false;
  }
  @Generated
  public JustificativeEntity searchJustificative(String rut_employee, String date) throws ParseException {
    return justificativeRepository.getJustificative(rut_employee, convertir_fecha(date));
  }
  @Generated
  public void deleteAll(){
    justificativeRepository.deleteAll();
  }
}
