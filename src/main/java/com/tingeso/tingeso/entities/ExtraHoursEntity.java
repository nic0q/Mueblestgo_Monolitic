package com.tingeso.tingeso.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "extra_hours")
@Data
public class ExtraHoursEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;

  @Getter
  @Setter
  @Past(message = "La fecha no puede ser futura")
  private Date date;

  @Getter
  @Setter
  @Min(0)
  @NotNull(message = "Debe ingresar horas extra")
  private Integer n_hours;
  @Getter
  @Setter
  @NotEmpty(message = "Debe ingresar un usuario")
  @Size(min=10,max=10,message = "Ingrese el rut en el formato (Puntos y guión)")
  private String rut_employee;

  public ExtraHoursEntity(Date date, String rut_employee, Integer n_hours) {
    this.date = date;
    this.rut_employee = rut_employee;
    this.n_hours = n_hours;
  }
}
