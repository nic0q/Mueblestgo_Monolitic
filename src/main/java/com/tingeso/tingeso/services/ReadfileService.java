package com.tingeso.tingeso.services;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class ReadfileService {
    String entrada = "08:00";
    String salida = "18:00";
    public static void main(String args[]) throws Exception {
        InputStream ins = new FileInputStream("src/main/resources/txt/data.txt");
        
        try (Scanner obj = new Scanner(ins)) {
            while (obj.hasNextLine()){
                String[] stringarray = obj.nextLine().split(";"); //splitting the line into an array of strings
                String date = stringarray[0];
                String entrada = stringarray[1];
                String salida = stringarray[2];
                System.out.println(date + " " + entrada + " " + salida);
            }
        }   
    }
    // I: null
    // O: String
    // Read the .txt file and group by day
    public String[] readfile() throws Exception {
        InputStream ins = new FileInputStream("src/main/resources/txt/data.txt");
        try (Scanner obj = new Scanner(ins)) {
            while (obj.hasNextLine()){
                String[] stringarray = obj.nextLine().split(";"); //splitting the line into an array of strings
                String day = stringarray[0];
                String entrada = stringarray[1];
                String salida = stringarray[2];
                System.out.println(day + " " + entrada + " " + salida);
                return stringarray;
            }
        }
        return null;
    }
}
