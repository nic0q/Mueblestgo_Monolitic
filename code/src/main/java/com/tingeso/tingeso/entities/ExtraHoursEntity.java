package com.tingeso.tingeso.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "extra_hours")
@Data
@AllArgsConstructor
public class ExtraHoursEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;

  private @Getter @Setter Integer extra_hours;
  private @Getter @Setter String rut_employee;
  public ExtraHoursEntity(@NotNull(message = "Debe ingresar horas extra") Integer extra_hours2, String rut_employee2) {
  }

}
