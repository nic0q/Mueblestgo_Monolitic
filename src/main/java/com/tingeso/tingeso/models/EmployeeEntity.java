package com.tingeso.tingeso.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {

  @Id
  @GeneratedValue
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  private String rut;
  private String first_name;
  private String last_name;
  private String middle_name;
  private String category;

  public EmployeeEntity(
    String rut,
    String first_name,
    String last_name,
    String middle_name,
    String category
  ) {
    this.rut = rut;
    this.first_name = first_name;
    this.last_name = last_name;
    this.middle_name = middle_name;
    this.category = category;
  }
}
