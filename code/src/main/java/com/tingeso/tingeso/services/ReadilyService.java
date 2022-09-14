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
  private EmployeeService employeeService;

  static String ENTRY_TIME = "08:00"; // Todos los empleados deben llegar a las 8:00
  static String EXIT_TIME = "18:00"; // Todos los empleados deben salir a las 18:00 (Pueden haber horas extras)
  String minutes_per_day = "600"; // Todos los empleados deben trabajar 600 minutos (10 horas)
  List<String> laboral_days = Arrays.asList("lun", "mar", "mié", "jue", "vie"); // 5 laboral days
  static DateFormat dateFormaty = new SimpleDateFormat("yyyy/MM/dd");
  static DateFormat dayFormat = new SimpleDateFormat("EEE");
  static DateFormat hours_mins = new SimpleDateFormat("hh:mm");
  static DateFormat hours_format = new SimpleDateFormat("hh");

  public boolean readFile() throws FileNotFoundException, ParseException {
    InputStream ins = new FileInputStream("src/main/resources/txt/data.txt");
    ArrayList<String> dias = new ArrayList<String>();
    Map<String, ArrayList<String>> ruts_map = new HashMap<String, ArrayList<String>>();
    try (Scanner obj = new Scanner(ins)) {
      while (obj.hasNextLine()) {
        String[] presence_reg = obj.nextLine().split(";"); //splitting the line into an array of strings
        String date = presence_reg[0];String hora = presence_reg[1];String rut = presence_reg[2].replace(".",""); // Rut position // Hour position // Date position
        if (!dias.contains(date)) {
          System.out.print("DIA: " + date + "\n");
          dias.add(date);
          String str = dayFormat.format(dateFormaty.parse(date));
          System.out.println("DAY N°" + str);
          ruts_map.clear(); // the hashmap is cleared every day
        }
        if (!ruts_map.containsKey(rut)) {
          ArrayList<String> temp = new ArrayList<>(); // tempora array to save entry and quit times of a employee by rut
          temp.add(hora); // add entry time
          ruts_map.put(rut, temp); // hashmap rut and entry time
        } else {
          ruts_map.get(rut).add(hora); // hashmap exit time
          System.out.println("DIA: " + date + " RUT: "+"[" + rut +"]"+ employeeService.getEmployeeByRut(rut) +
          " ENTRADA: " + ruts_map.get(rut).get(0) +" SALIDA: "+ruts_map.get(rut).get(1)
          +" TARDANZA: "+ getLateMinutes(ENTRY_TIME,ruts_map.get(rut).get(0)) +
          " TIEMPO: " + getWorkedHours(ruts_map.get(rut).get(0),ruts_map.get(rut).get(1))
          + "HORAS EXTRA: " + extraHours(EXIT_TIME,ruts_map.get(rut).get(1)));
        }
      }
    }
    return true;
  }
  // I: 2 Strings con formato HH:mm
  // O: Long con la cantidad de minutos trabajados
  public static long getLateMinutes(String start_t, String exit_t) throws ParseException {
    Date entrada = hours_mins.parse(start_t);
    Date salida = hours_mins.parse(exit_t);
    long horas_trabajadas = salida.getTime() - entrada.getTime();
    if(TimeUnit.MILLISECONDS.toMinutes(horas_trabajadas) < 0){ // Si entro antes se toma como hora de entrada (8:00 am)
      return 0;
    }
    return TimeUnit.MILLISECONDS.toMinutes(horas_trabajadas);
  }
  // I: 2 Strings con formato HH:mm
  // O: Long con la cantidad de minutos trabajados
  public static long getWorkedHours(String start_t, String end_t) throws ParseException {
    Date entrada, salida;
    entrada = hours_format.parse(start_t);
    salida = hours_format.parse(end_t);
    if(getLateMinutes(ENTRY_TIME,start_t) == 0){ // Si entra antes se toman las horas como si entrara a las (8:00 am)
      entrada = hours_format.parse(ENTRY_TIME);
    }
    if(getLateMinutes(end_t,EXIT_TIME) >= 0 && getLateMinutes(end_t,EXIT_TIME) <= 15){ // si sale de las 17:45 para adelante se permite como salida a las 18:00
      salida = hours_format.parse(EXIT_TIME);
    }
    return TimeUnit.MILLISECONDS.toHours(salida.getTime() - entrada.getTime());
  }
  // I: 2 Strings con formato HH:mm
  // O: Long con la cantidad de minutos trabajados
  public static Long extraHours(String start_t, String exit_t)throws ParseException {
    Date entrada = hours_mins.parse(start_t);
    Date salida = hours_mins.parse(exit_t);
    long horas_trabajadas = salida.getTime() - entrada.getTime();
    if(horas_trabajadas <= 0){
      return (long) 0;
    }
    return TimeUnit.MILLISECONDS.toMinutes(horas_trabajadas);
  }
}
