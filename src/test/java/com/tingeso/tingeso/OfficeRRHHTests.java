package com.tingeso.tingeso;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tingeso.tingeso.entities.EmployeeEntity;
import com.tingeso.tingeso.entities.ExtraHoursEntity;
import com.tingeso.tingeso.services.EmployeeService;
import com.tingeso.tingeso.services.OfficeRRHH;
import com.tingeso.tingeso.services.ReadilyService;

@SpringBootTest
class OfficeRRHHTests {
  @Autowired
  OfficeRRHH officeRRHH;
  @ParameterizedTest
  @MethodSource
  void sueldos(String category, double sueldo_base) {
    double sueldo_base_expected = officeRRHH.get_sueldo_base(category);
    assertEquals(sueldo_base_expected, sueldo_base, 0.0);
  }
  private static Stream<Arguments> sueldos() {
    return Stream.of(
      Arguments.of("A", 1700000),
      Arguments.of("B", 1200000),
      Arguments.of("C", 800000)
    );
  }
  @ParameterizedTest
  @MethodSource
  void horas_extra(String category, int horas_extra, double expected) {
    double valor_horas_extra = officeRRHH.valor_horas_extra(category, horas_extra);
    assertEquals(expected, valor_horas_extra, 0.0);
  }
  private static Stream<Arguments> horas_extra() {
    return Stream.of(
      Arguments.of("A", 20, 500000),
      Arguments.of("B", 20, 400000),
      Arguments.of("C", 20, 200000)
    );
  }
  @Test
  void horas_extra_mes(){
    List <ExtraHoursEntity> horas_extra = new ArrayList<ExtraHoursEntity>();
    ExtraHoursEntity extraHoursDay1 = new ExtraHoursEntity();
    ExtraHoursEntity extraHoursDay2 = new ExtraHoursEntity();
    ExtraHoursEntity extraHoursDay3 = new ExtraHoursEntity();
    extraHoursDay1.setN_hours(20);
    extraHoursDay2.setN_hours(10);
    extraHoursDay3.setN_hours(10);
    horas_extra.add(extraHoursDay1);
    horas_extra.add(extraHoursDay2);
    horas_extra.add(extraHoursDay3);
    double sueldo_horas_extra = officeRRHH.calcular_sueldo_horas_extra("A", horas_extra);
    assertEquals(1000000, sueldo_horas_extra, 0.0);
  }
  @ParameterizedTest
  @MethodSource
  void anios_servicio(String entry_date, double expected) throws ParseException {
    int anios_servicio = officeRRHH.calcular_anios_servicio(entry_date);
    assertEquals(expected, anios_servicio, 0.0);
  }
  private static Stream<Arguments> anios_servicio() {
    return Stream.of(
      Arguments.of("2019/01/01", 3),
      Arguments.of("2014/01/01", 8),
      Arguments.of("2011/01/01", 11)
    );
  }
  @ParameterizedTest
  @MethodSource
  void test_bonificaciones(int anios_servicio, double sueldo_base, double expected) throws ParseException {
    double bonificaciones = officeRRHH.calcular_bonificaciones(anios_servicio,sueldo_base);
    assertEquals(expected, bonificaciones, 0.0);
  }
  private static Stream<Arguments> test_bonificaciones() {
    return Stream.of(
      Arguments.of(30,1000000, 170000),
      Arguments.of(23,1000000, 140000),
      Arguments.of(18,1000000, 110000),
      Arguments.of(14,1000000, 80000),
      Arguments.of(9, 1000000,50000),
      Arguments.of(4,1000000, 0)
    );
  }
  @ParameterizedTest
  @MethodSource
  void test_descuentos_tardanza(int minutos_tarde, double sueldo_base, double expected) throws ParseException {
    double descuentos_tarde = officeRRHH.descuentos_tardanza(minutos_tarde, sueldo_base);
    assertEquals(expected, descuentos_tarde, 0.0);
  }
  private static Stream<Arguments> test_descuentos_tardanza() {
    return Stream.of(
      Arguments.of(50,1000000, 60000),
      Arguments.of(30,1000000, 30000),
      Arguments.of(15,1000000, 10000),
      Arguments.of(5,1000000, 0)
    );
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
  @Test 
  void descuentos() throws ParseException{
    EmployeeEntity employee = new EmployeeEntity();
    employee.setRut("27752982-4");
    employee.setCategory("B");
    double descuentos = officeRRHH.calcular_descuentos(employee.getRut());
    assertEquals(360000, descuentos, 0.0);
  }
  @Test
  void testSueldoFinal(){
    double sueldo_bruto = 3000000;
    double sueldo_final = officeRRHH.calcular_sueldo_final(sueldo_bruto);
    assertEquals(2460000, sueldo_final, 0.0);
  }
  @Mock
  private EmployeeService employeeService;
  @InjectMocks
  private OfficeRRHHTests officeRRHHTests;
  private EmployeeEntity employee;
  @BeforeEach
  void setUp(){
    MockitoAnnotations.openMocks(this);
    employee = new EmployeeEntity();
    employee.setRut("98765432-1");
  }
  @Test
  void testDescuentos(){
    when(employeeService.getEmployeeByRut("98765432-1")).thenReturn(employee);
  }
}
class ReadilyServiceTests {
  ReadilyService readilyService = new ReadilyService();
  
  @Test
  void getLateMinutesTest() throws ParseException{
    String entry_time = "09:40";
    int lateMinutes = readilyService.getLateMinutes(entry_time);
    assertEquals(100, lateMinutes);
  }
  @Test
  void extraHoursTest() throws ParseException, FileNotFoundException{
    boolean success = readilyService.readFile(1);
    assertEquals(true, success);
  }
}