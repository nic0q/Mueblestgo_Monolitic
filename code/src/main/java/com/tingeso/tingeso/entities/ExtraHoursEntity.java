package com.tingeso.tingeso.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
  private Integer extra_hours;

  @Getter
  @Setter
  private String rut_employee;

  public ExtraHoursEntity(Integer extra_hours, String rut_employee) {
    this.extra_hours = extra_hours;
    this.rut_employee = rut_employee;
  }
}
