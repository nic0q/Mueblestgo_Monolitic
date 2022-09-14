package com.tingeso.tingeso.entities;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class JustificativeForm {
  public JustificativeForm() {}
  public JustificativeForm(String rut_employee, Date date, String description) {
    this.rut_employee = rut_employee;
    this.date = date;
    this.description = description;
  }
  private @Getter @Setter
    @NotEmpty(message = "El RUT no puede estar vacio")
    @Size(min=10,max=10,message = "Ingrese el rut en el formato (Puntos y guión)")
    String rut_employee;
  private @Getter @Setter
    @NotNull(message = "Debe ingresar horas extra")
    Date date;
  private @Getter @Setter
    @NotNull(message = "Debe ingresar una descripción")
    String description;
}
