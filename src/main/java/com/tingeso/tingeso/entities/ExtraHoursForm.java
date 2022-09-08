package com.tingeso.tingeso.entities;

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
  private @Getter @Setter String rut_employee;
  private @Getter @Setter Integer extra_minutes;
  
}
