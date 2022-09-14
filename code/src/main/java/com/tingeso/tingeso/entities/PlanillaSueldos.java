package com.tingeso.tingeso.entities;

import lombok.AllArgsConstructor;
import lombok.ToString;

import lombok.Getter;
import lombok.Setter;

@ToString
@AllArgsConstructor
public class PlanillaSueldos {
  public PlanillaSueldos(String rut_empleado,Integer sueldo) {
    this.sueldo = sueldo;
    this.rut_empleado = rut_empleado;
  }
  private @Getter @Setter String rut_empleado;
  private @Getter @Setter float sueldo;
}
