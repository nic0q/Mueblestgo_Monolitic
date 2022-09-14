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
  public List<WorkedDaysEntity> obtener_dias_trabajados(Integer id_empleado){
    List<WorkedDaysEntity> dias_trabajados = get_dias_trabajados();
    return dias_trabajados.stream()
      .filter(arg0 -> arg0.getId_employee() == id_empleado)
      .collect(Collectors.toList());
  }
}
