package com.tingeso.tingeso.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadilyService {

  @Autowired
  private WorkedDaysService workedDaysService;

  private static final String ENTRY_TIME = "08:00"; // Todos los empleados deben llegar a las 8:00
  private static final String EXIT_TIME = "18:00"; // Todos los empleados deben salir a las 18:00 (Pueden haber horas extras)
  List<String> laboral_days = Arrays.asList("lun", "mar", "mié", "jue", "vie"); // 5 laboral days
  private static DateFormat hours_mins = new SimpleDateFormat("hh:mm");
  private static final String NOMBRE_TXT = "DATA.txt";

  public boolean nombre_correcto(String nombre) {
    return nombre.equals(NOMBRE_TXT);
  }
  public boolean readFile() throws FileNotFoundException, ParseException {
    try {
    
    InputStream ins = new FileInputStream("cargas/"+NOMBRE_TXT);
    ArrayList<String> dias = new ArrayList<>();
    Map<String, ArrayList<String>> ruts_map = new HashMap<>();
    try (Scanner obj = new Scanner(ins)) {
      while (obj.hasNextLine()) {
        String[] presence_reg = obj.nextLine().split(";"); //splitting the line into an array of strings
        String date = presence_reg[0];
        String hora = presence_reg[1];
        String rut = presence_reg[2].replace(".",""); // Rut position // Hour position // Date position
        if (!dias.contains(date)) {
          dias.add(date);
          ruts_map.clear(); // the hashmap is cleared every day
        }
        if (!ruts_map.containsKey(rut)) {
          ArrayList<String> temp = new ArrayList<>(); // temporal array to save entry and quit times of a employee by rut
          temp.add(hora); // add entry time
          ruts_map.put(rut, temp); // hashmap rut and entry time
        } else {
          ruts_map.get(rut).add(hora); // hashmap exit time
          workedDaysService.insert_worked_day(rut,date,extraHours(ruts_map.get(rut).get(1)),getLateMinutes(ruts_map.get(rut).get(0)));
        }
      }
    }
    return true;
  } catch (FileNotFoundException e) {
    return false;
  }
   
  }
  // I: 2 Strings con formato HH:mm
  // O: Long con la cantidad de minutos trabajados
  public static Integer getLateMinutes( String exit_t) throws ParseException {
    long horas_trabajadas = hours_mins.parse(exit_t).getTime() - hours_mins.parse(ENTRY_TIME).getTime();
    if(TimeUnit.MILLISECONDS.toMinutes(horas_trabajadas) < 0){ // Si entro antes se toma como hora de entrada (8:00 am)
      return 0;
    }
    return (int) TimeUnit.MILLISECONDS.toMinutes(horas_trabajadas);
  }
  // I: 2 Strings con formato HH:mm
  // O: Long con la cantidad de minutos trabajados
  public static Integer extraHours(String exit_t)throws ParseException {
    Date salida_normal = hours_mins.parse(EXIT_TIME);
    Date salida = hours_mins.parse(exit_t);
    long horas_extra = salida.getTime() - salida_normal.getTime();
    if(horas_extra <= 0){
      return 0;
    }
    return (int) TimeUnit.MILLISECONDS.toHours(horas_extra);
  }

}
