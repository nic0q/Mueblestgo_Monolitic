package com.tingeso.tingeso.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "worked_days")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkedDaysEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;
  private String rut_employee;
  private String date;
  private String t_entry;
  private String t_exit;
  private Integer worked_hours;
  private Integer extra_hours;
  private Integer late_minutes;

  public WorkedDaysEntity(String rut_employee, String date, String t_entry, String t_exit, Integer worked_hours,
      Integer extra_hours, Integer late_minutes) {
    this.rut_employee = rut_employee;
    this.date = date;
    this.t_entry = t_entry;
    this.t_exit = t_exit;
    this.worked_hours = worked_hours;
    this.extra_hours = extra_hours;
    this.late_minutes = late_minutes;
  }
}