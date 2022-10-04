package com.tingeso.tingeso;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tingeso.tingeso.services.ReadilyService;
import java.text.ParseException;

import org.junit.jupiter.api.Test;
class ReadilyServiceTests {
  ReadilyService readilyService = new ReadilyService();
  
  @Test
  void getLateMinutesTest() throws ParseException{
    String entry_time = "09:40";
    String entry_time2 = "06:40";
    int lateMinutes = readilyService.getLateMinutes(entry_time);
    int lateMinutes2 = readilyService.getLateMinutes(entry_time2);
    assertEquals(100, lateMinutes);
    assertEquals(0, lateMinutes2);
  }
  @Test
  void correctNameTest(){
    String name = "DATA.txt";
    assertTrue(readilyService.nombre_correcto(name));
  }
  @Test
  void extraHoursTest() throws ParseException{
    int extraHours = readilyService.extraHours("20:40");
    int extraHours2 = readilyService.extraHours("16:00");
    assertEquals(2, extraHours);
    assertEquals(0, extraHours2);
  }
}