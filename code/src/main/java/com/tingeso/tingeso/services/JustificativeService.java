package com.tingeso.tingeso.services;

import com.tingeso.tingeso.entities.JustificativeEntity;
import com.tingeso.tingeso.repostories.JustificativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JustificativeService {

  @Autowired
  JustificativeRepository justificativeRepository;

  @Autowired
  EmployeeService employeeService;

  public boolean save_justificative(JustificativeEntity justificative) {
    System.out.print(justificative);
    if (employeeService.exists_employee(justificative.getRut_employee())) {
      JustificativeEntity entry = new JustificativeEntity(
        justificative.getDate(),
        justificative.getRut_employee()
      );
      justificativeRepository.save(entry);
      System.out.println(
        "Justificativo guardado correctamente " + entry.toString()
      );
      return true;
    }
    System.out.println("El empleado no existe");
    return false;
  }
}
