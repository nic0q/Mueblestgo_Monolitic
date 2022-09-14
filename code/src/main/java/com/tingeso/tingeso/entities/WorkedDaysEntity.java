package com.tingeso.tingeso.entities;

import java.sql.Date;

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
  private Long id;
  private Date date;
  private Integer id_employee;
  private String t_entry;
  private String t_exit;
  private Integer worked_hours;
  private Integer extra_hours;
  private Integer late_minutes;
}