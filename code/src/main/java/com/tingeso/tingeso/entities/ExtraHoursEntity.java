package com.tingeso.tingeso.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "extra_hours")
@Data

public class ExtraHoursEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  private Integer extra_minutes;
  private Long employee_id;

  public ExtraHoursEntity(Integer extra_minutes, Long employee_id) {
    this.extra_minutes = extra_minutes;
    this.employee_id = employee_id;
  }
}
