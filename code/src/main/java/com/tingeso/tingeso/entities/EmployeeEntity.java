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

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  @Getter @Setter private Integer id;
  @Getter @Setter private String rut;
  @Getter @Setter private String first_name;
  @Getter @Setter private String last_name;
  @Getter @Setter private String category;
  @Getter @Setter private Integer service_years;
  public EmployeeEntity(
    String rut,
    String first_name,
    String last_name,
    String category,
    Integer service_years
  ) {
    this.rut = rut;
    this.first_name = first_name;
    this.last_name = last_name;
    this.category = category;
    this.service_years = service_years;
  }
}
