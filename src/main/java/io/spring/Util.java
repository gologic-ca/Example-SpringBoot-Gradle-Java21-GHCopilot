package io.spring;

import java.sql.Date;

public class Util {
  public static boolean isEmpty(String value) {
    return value == null || value.isEmpty();
  }

  public static Date parseDate(String date) {
    if (isEmpty(date)) {
      return null;
    }
    
    try {
      return Date.valueOf(date);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }
}
