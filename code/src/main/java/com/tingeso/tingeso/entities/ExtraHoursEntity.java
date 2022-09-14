package com.tingeso.tingeso.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "extra_hours")
@Data
public class ExtraHoursEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;

  private @Getter @Setter Integer extra_hours;
  private @Getter @Setter Integer employee_id;

  public ExtraHoursEntity(Integer extra_hours, Integer employee_id) {
    this.extra_hours = extra_hours;
    this.employee_id = employee_id;
  }
}
