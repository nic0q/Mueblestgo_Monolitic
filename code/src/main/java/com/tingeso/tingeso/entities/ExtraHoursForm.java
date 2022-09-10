package com.tingeso.tingeso.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class ExtraHoursForm {
  public ExtraHoursForm() {}
  public ExtraHoursForm(String rut_employee, Integer extra_minutes) {
    this.rut_employee = rut_employee;
    this.extra_minutes = extra_minutes * 60;
  }
  private @Getter @Setter
    @NotEmpty(message = "El RUT no puede estar vacio")
    @Size(min=10,max=10,message = "El rut debe estar en el formato  correcto (Puntos y guión)")
    String rut_employee;
  private @Getter @Setter
    @NotNull(message = "Las horas extra no pueden estar vacias")
    Integer extra_minutes;
}
