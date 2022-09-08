package com.tingeso.tingeso.services;

import com.tingeso.tingeso.entities.ExtraHoursEntity;
import com.tingeso.tingeso.entities.ExtraHoursForm;
import com.tingeso.tingeso.repostories.EmployeeRepository;
import com.tingeso.tingeso.repostories.ExtraHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtraHoursService {

  @Autowired
  ExtraHoursRepository extraHoursRepository;

  @Autowired
  EmployeeRepository employeeRepository;

  public boolean verify_form(ExtraHoursForm extra) {
    if (employeeRepository.findByRut(extra.getRut_employee()) == null) {
      System.out.println("El rut no existe en el sistema"); // Mandar mensaje de error por thymeleaf
      return false;
    }
    if (extra.getExtra_minutes() == 0) {
      System.out.println("Las horas extras no pueden ser 0"); // Mandar mensaje de error por thymeleaf
      return false;
    }
    if (extra.getExtra_minutes() < 0) {
      System.out.println("Las horas extras no pueden ser negativas"); // Mandar mensaje de error por thymeleaf
      return false;
    }
    if (extra.getExtra_minutes() > 100) {
      System.out.println("Las horas extras no pueden ser mayores a 100"); // Mandar mensaje de error por thymeleaf
      return false;
    }
    return true;
  }

  public void insertExtraHours(ExtraHoursEntity extraHours) {
    extraHoursRepository.save(extraHours);
  }
}
