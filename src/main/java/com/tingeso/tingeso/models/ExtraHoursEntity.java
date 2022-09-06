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
@Table(name = "justificative")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtraHoursEntity {

  @Id
  @GeneratedValue
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  private Integer extra_minutes;
  private Integer employee_id;

  public ExtraHoursEntity(Integer extra_minutes, Integer employee_id) {
    this.extra_minutes = extra_minutes;
    this.employee_id = employee_id;
  }
}
