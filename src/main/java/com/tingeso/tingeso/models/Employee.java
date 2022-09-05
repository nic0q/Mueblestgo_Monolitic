package com.tingeso.tingeso.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "employee")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter @Setter @Column(name = "id") // hace referencia a la columna id de la tabla users de la base de datos
  private Integer id;
  @Getter @Setter @Column(name = "rut") // hace referencia a la columna id de la tabla users de la base de datos
  private String rut;
  @Getter @Setter @Column(name = "first_name") // hace referencia a la columna name de la tabla users de la base de datos
  private String first_name;
  @Getter @Setter @Column(name = "last_name") // hace referencia a la columna last_name de la tabla users de la base de datos
  private String last_name;
  @Getter @Setter @Column(name = "middle_name") // hace referencia a la columna id de la tabla users de la base de datos
  private String middle_name;
  @Getter @Setter @Column(name = "category") // hace referencia a la columna last_name de la tabla users de la base de datos
  private String category;
}
