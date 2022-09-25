package com.tingeso.tingeso.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
  @NotNull
  @Getter @Setter private String rut;
  @Getter @Setter private String name;
  @Getter @Setter private String last_name;
  @Getter @Setter private String category;
  @Getter @Setter private String birth_date;
  @Getter @Setter private String entry_date;
}
