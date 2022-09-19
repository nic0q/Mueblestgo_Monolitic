package com.tingeso.tingeso;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tingeso.tingeso.entities.EmployeeEntity;
import com.tingeso.tingeso.repostories.EmployeeRepository;
import com.tingeso.tingeso.services.OfficeRRHH;

@SpringBootTest
class TingesoApplicationTests {
  @Autowired
  EmployeeRepository employeeRepository;
	@Autowired
  OfficeRRHH officeRRHH = new OfficeRRHH();
  @Test
  void testSueldoBase(){
    EmployeeEntity employee = new EmployeeEntity();
    employee.setRut("12345678-9");
    employee.setCategory("A");
    double sueldo_base = officeRRHH.get_sueldo_base(employee.getCategory());
    assertEquals(1700000, sueldo_base, 0.0);
  }
  @Test
  void testValorHorasExtra(){
    EmployeeEntity employee = new EmployeeEntity();
    employee.setRut("12345678-9");
    employee.setCategory("A");
    int horas_extra = 20;
    double valor_horas_extra = officeRRHH.valor_horas_extra(employee.getCategory(), horas_extra);
    assertEquals(500000, valor_horas_extra, 0.0);
  }
  // @Test
  // void testSueldoHorasExtra(){
  //   EmployeeEntity employee = new EmployeeEntity();
  //   employee.setRut("12345678-9");
  //   employee.setCategory("A");

  //   assertEquals(1700000, sueldo_base, 0.0);
  // }
  @Test
  void testAniosServicio() throws ParseException{
    EmployeeEntity employee = new EmployeeEntity();
    employee.setRut("12345678-9");
    employee.setCategory("A");
    employee.setEntry_date("2019-01-01");
    int anios_servicio = officeRRHH.calcular_anios_servicio(employee.getEntry_date());
    assertEquals(3, anios_servicio, 0.0);
  }
  @Test
  void testBonificaciones() throws ParseException{
    EmployeeEntity employee = new EmployeeEntity();
    employee.setRut("24541761-9");
    employee.setCategory("A");
    employee.setEntry_date("2000-01-01");
    double sueldo_base = officeRRHH.get_sueldo_base(employee.getCategory());
    int anios_servicio = officeRRHH.calcular_anios_servicio(employee.getEntry_date());
    double bonificaciones = officeRRHH.calcular_bonificaciones(anios_servicio,sueldo_base);
    assertEquals(238000, bonificaciones, 0.0);
  }
  @Test
  void testDescuentosTarde(){
    EmployeeEntity employee = new EmployeeEntity();
    employee.setRut("24541761-9");
    employee.setCategory("A");
    double sueldo_base = officeRRHH.get_sueldo_base(employee.getCategory());
    int minutos_tarde = 30;
    double descuentos_tarde = officeRRHH.descuentos_tardanza(minutos_tarde, sueldo_base);
    assertEquals(51000, descuentos_tarde, 0.0);
  }
  @Test
  void cotizacionSalud(){
    EmployeeEntity employee = new EmployeeEntity();
    employee.setRut("24541761-9");
    employee.setCategory("A");
    double sueldo_base = officeRRHH.get_sueldo_base(employee.getCategory());
    double cotizacion_salud = officeRRHH.calcular_cotizacion_salud(sueldo_base);
    assertEquals(136000, cotizacion_salud, 0.0);
  }
  @Test
  void cotizacionPrevisional(){
    EmployeeEntity employee = new EmployeeEntity();
    employee.setRut("24541761-9");
    employee.setCategory("A");
    double sueldo_base = officeRRHH.get_sueldo_base(employee.getCategory());
    double cotizacion_previsional = officeRRHH.calcular_cotizacion_previsional(sueldo_base);
    assertEquals(170000, cotizacion_previsional, 0.0);
  }
}