package com.tingeso.tingeso.services;
import java.io.FileInputStream;
import java.io.InputStream;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ReadfileService {
    String entrada = "08:00"; // Todos los empleados deben llegar a las 8:00
    String salida = "18:00"; // Todos los empleados deben salir a las 18:00 (Pueden haber horas extras)
    String minutos_obligatorios = "720"; // Todos los empleados deben trabajar 720 minutos (10 horas)
    public static void main(String args[]) throws Exception {
        InputStream ins = new FileInputStream("src/main/resources/txt/data.txt");
        ArrayList<String> dias = new ArrayList<String>();    
        Map<String, ArrayList<String>> ruts_map = new HashMap<String,ArrayList<String>>();
        try (Scanner obj = new Scanner(ins)) {
            while (obj.hasNextLine()){
                String[] registro_asistencia = obj.nextLine().split(";"); //splitting the line into an array of strings
                String date = registro_asistencia[0];   // posicion de la fecha(dia trabajado)
                String hora = registro_asistencia[1]; // posicion de la hora
                String rut = registro_asistencia[2]; // posicion del rut
                if(!dias.contains(date)){
                    System.out.print("DIA: "+date + "\n");
                    dias.add(date);
                    ruts_map.clear(); // Se limpia el hasmap ya que solo es utilizado para 1 día
                }
                if (!ruts_map.containsKey(rut)){
                    ArrayList<String> temp = new ArrayList<>(); // areglo temporal para almacenar las horas (ENTRADA, SALIDA) de un rut
                    temp.add(hora); // se añade la hora de ENTRADA
                    ruts_map.put(rut, temp); // Se añade el rut y la hora de ENTRADA al hashmap
                }
                else{
                    ruts_map.get(rut).add(hora);    // Se añade la hora de SALIDA al hashmap
                    System.out.println("["+rut+"]"+" ENTRADA: " + ruts_map.get(rut).get(0) + " SALIDA: " + ruts_map.get(rut).get(1) + " TIEMPO: " + getWorkedMinutes(ruts_map.get(rut).get(0), ruts_map.get(rut).get(1)) + "min");
                }
            }
        }
    }
    // I: 2 Strings con formato HH:mm
    // O: Long con la cantidad de minutos trabajados
    public static long getWorkedMinutes(String start_t, String end_t) throws ParseException{
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        Date entrada = dateFormat.parse(start_t);
        Date salida = dateFormat.parse(end_t);
        long horas_trabajadas = salida.getTime() - entrada.getTime();
        return  TimeUnit.MILLISECONDS.toMinutes(horas_trabajadas);
    }
}
