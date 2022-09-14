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

  public boolean exists_employee(ExtraHoursForm extra_form) {
    if (employeeRepository.findByRut(extra_form.getRut_employee()) != null) {
      return true;
    }
    return false;
  }
  public boolean save_extra_hours(ExtraHoursForm extra_for) {
    if (exists_employee(extra_for)) {
      ExtraHoursEntity entry = new ExtraHoursEntity(extra_for.getExtra_hours(), employeeRepository.findByRut(extra_for.getRut_employee()).getId());
      extraHoursRepository.save(entry);
      System.out.println("Horas extra guardadas correctamente " + entry.toString());
      return true;
    }
    System.out.println("El empleado no existe");
    return false;
  }
}
