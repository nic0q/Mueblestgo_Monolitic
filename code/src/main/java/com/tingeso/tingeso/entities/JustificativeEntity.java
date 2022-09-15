package com.tingeso.tingeso.entities;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "justificative")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JustificativeEntity {

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
  @NotEmpty(message = "El RUT no puede estar vacio")
  @Size(min=10,max=10,message = "Ingrese el rut en el formato (Puntos y guión)")
  private String rut_employee;

  public JustificativeEntity(
    Date date,
    String rut_employee
  ) {
    this.date = date;
    this.rut_employee = rut_employee;
  }
}
