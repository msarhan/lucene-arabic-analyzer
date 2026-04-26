package AlKhalil2.util;

import java.util.Calendar;

public class Times {
  private static long start;
  
  private static long end;
  
  public static void start() {
    start = Calendar.getInstance().getTimeInMillis();
  }
  
  public static long Start() {
    return Calendar.getInstance().getTimeInMillis();
  }
  
  public static long End() {
    return Calendar.getInstance().getTimeInMillis();
  }
  
  public static void end() {
    end = Calendar.getInstance().getTimeInMillis();
  }
  
  public static long getTimes() {
    return end - start;
  }
  
  public static int getMilSecTimes() {
    return (int)(end - start) % 1000;
  }
  
  public static int getSecTimes() {
    return (int)((end - start) / 1000L) % 60;
  }
  
  public static int getMinTimes() {
    return (int)((end - start) / 60000L % 60L);
  }
  
  public static int getHourTimes() {
    return (int)((end - start) / 3600000L % 24L);
  }
}
