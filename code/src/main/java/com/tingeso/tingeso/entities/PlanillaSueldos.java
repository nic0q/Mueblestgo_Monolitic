package com.tingeso.tingeso.entities;

import lombok.AllArgsConstructor;
import lombok.ToString;

import lombok.Getter;
import lombok.Setter;

@ToString
@AllArgsConstructor
public class PlanillaSueldos {
  private @Getter @Setter String rut_empleado;
  private @Getter @Setter String nombre_empleado;
  private @Getter @Setter String apellido_empleado;
  private @Getter @Setter String categoria;
  private @Getter @Setter Integer anios_servicio;
  private @Getter @Setter float sueldo_fijo;
  private @Getter @Setter float bonificacion;
  private @Getter @Setter float pago_horas_extras;
  private @Getter @Setter float descuento;
  private @Getter @Setter float sueldo_bruto;
  private @Getter @Setter float cotizacion_previsional;
  private @Getter @Setter float sueldo_final;
}
