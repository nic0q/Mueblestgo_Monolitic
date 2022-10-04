package com.tingeso.tingeso;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

import com.tingeso.tingeso.entities.WorkedDaysEntity;
import com.tingeso.tingeso.repositories.WorkedDaysRepository;
import com.tingeso.tingeso.services.WorkedDaysService;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;

class WorkedDaysTest {
  @Mock
  private WorkedDaysRepository workedDaysRepository;
  @InjectMocks
  private WorkedDaysService workedDaysService;
  private WorkedDaysEntity workedDay;
  @BeforeEach
  void setUp() throws ParseException{
    MockitoAnnotations.openMocks(this);
    workedDay = new WorkedDaysEntity();
    workedDay.setDate(workedDaysService.convertir_fecha("2000/01/01"));
    workedDay.setRut_employee("98765432-1");
  }
  @Test
  void obtener_fecha_inicio() throws ParseException{
    when(workedDaysRepository.getDate()).thenReturn(workedDay.getDate());
    assertNotNull(workedDaysService.obtener_fecha_inicio());
  }
  @Test
  void getWorkedDay() throws ParseException{
    when(workedDaysRepository.getWorkedDay("98765432-1", workedDaysService.convertir_fecha("2000/01/01"))).thenReturn(workedDay);
    assertNotNull(workedDaysService.get_dia_trabajado("98765432-1", "2000/01/01"));
  }
  @Test
  void fechaTest() throws ParseException{
    assertNotNull(workedDaysService.convertir_fecha("2000/01/01"));
  }
  @Test
  void insertWorkedDay() throws ParseException{
    workedDaysService.insert_worked_day("98765432-1", "2000/01/01", 0, 0);
    assertNotNull(workedDay);
  }
}
