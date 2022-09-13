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

import org.springframework.stereotype.Service;

@Service
public class ReadilyService {
  static String entry_time = "08:00"; // Todos los empleados deben llegar a las 8:00
  String quit_time = "18:00"; // Todos los empleados deben salir a las 18:00 (Pueden haber horas extras)
  String minutes_per_day = "600"; // Todos los empleados deben trabajar 600 minutos (10 horas)
  List<String> laboral_days = Arrays.asList("lun", "mar", "mié", "jue", "vie"); // 5 laboral days
  DateFormat dateFormaty = new SimpleDateFormat("yyyy/MM/dd");
  DateFormat dayFormat = new SimpleDateFormat("EEE");

  public boolean readFile() throws FileNotFoundException, ParseException {
    InputStream ins = new FileInputStream("src/main/resources/txt/data.txt");
    ArrayList<String> dias = new ArrayList<String>();
    Map<String, ArrayList<String>> ruts_map = new HashMap<String, ArrayList<String>>();
    try (Scanner obj = new Scanner(ins)) {
      while (obj.hasNextLine()) {
        String[] presence_reg = obj.nextLine().split(";"); //splitting the line into an array of strings
        String date = presence_reg[0];String hora = presence_reg[1];String rut = presence_reg[2]; // Rut position // Hour position // Date position
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
          System.out.println("DIA: "+date+" RUT: "+"[" +rut +"]" +" ENTRADA: " + ruts_map.get(rut).get(0) +" SALIDA: "+ruts_map.get(rut).get(1)+" TARDANZA: "+
          getWorkedMinutes(entry_time,ruts_map.get(rut).get(0)) +" TIEMPO: " + getWorkedMinutes(ruts_map.get(rut).get(0),ruts_map.get(rut).get(1)) +"min");
        }
      }
    }
    return true;
  }

  // I: 2 Strings con formato HH:mm
  // O: Long con la cantidad de minutos trabajados
  public static long getWorkedMinutes(String start_t, String end_t)
    throws ParseException {
    DateFormat dateFormat = new SimpleDateFormat("hh:mm");
    Date entrada = dateFormat.parse(start_t);
    Date salida = dateFormat.parse(end_t);
    long horas_trabajadas = salida.getTime() - entrada.getTime();
    return TimeUnit.MILLISECONDS.toMinutes(horas_trabajadas);
  }
  
}
