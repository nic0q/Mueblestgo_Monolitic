package com.tingeso.tingeso.models;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "justificative")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JustificativeEntity {

  @Id
  @GeneratedValue
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  private Date date;
  private String description;
  private Integer employee_id;

  public JustificativeEntity(
    Date date,
    String description,
    Integer employee_id
  ) {
    this.date = date;
    this.description = description;
    this.employee_id = employee_id;
  }
}
