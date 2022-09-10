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

  public boolean save_extra_hours(ExtraHoursForm extra) {
    if (employeeRepository.findByRut(extra.getRut_employee()) != null) {
      ExtraHoursEntity entry = new ExtraHoursEntity(extra.getExtra_minutes(), employeeRepository.findByRut(extra.getRut_employee()).getId());
      extraHoursRepository.save(entry);
      System.out.println("Horas extra guardadas correctamente " + entry.toString());
      return true;
    }
    System.out.println("El empleado no existe");
    return false;
  }
}
