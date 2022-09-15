package com.tingeso.tingeso.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class maina {

  static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
  static DateFormat dayFormat = new SimpleDateFormat("EEE");
  public static void main(String[] args) throws ParseException {
    String date = "2022/09/20";
    Date date1 = dateFormat.parse(date);
    iterar_dias_mes(date1);
  }
  public static void iterar_dias_mes(Date date) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
    for (int day = 1; day <= lastDay; day++) {
      c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), day);
      Date day_name = c.getTime();
      String str_day_name = dayFormat.format(day_name);
      if(str_day_name.equals("sáb") || str_day_name.equals("dom")) {
        continue;
      }
      System.out.println(dateFormat.format(c.getTime()));
    }
  }
}
