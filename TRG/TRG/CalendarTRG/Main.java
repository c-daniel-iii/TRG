
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {
    private static Calendar calendar = Calendar.getInstance();
    // private static GregorianCalendar calendar;
    private static String MONTHS[] = new String[12];            //new DateFormatSymbols().getMonths();
    private static String DAYS_OF_WEEK[] = new String[8];  //new DateFormatSymbols().getShortWeekdays();
    private static List <String> DAYS_OF_WEEKLIST = new ArrayList<String>();
    private static int [] currDayofMonth;
    public static void main(String [] args){
        init();
        // getDayOfTheWeek(2025, Calendar.MARCH, 2);
        // getDaysPerWeek(2024, Calendar.MARCH, 31);
        getLastDayOfWeek(2025, Calendar.MARCH);
        getLastDayOfWeek(2024, Calendar.FEBRUARY);
        // String[] daysOfWeek = new DateFormatSymbols().getShortWeekdays();
        // for (String day : daysOfWeek) {
        //     DAYS_OF_WEEKLIST.add(day.toUpperCase());
        // }
        // for(String day: DAYS_OF_WEEKLIST)
        //     System.out.print(day + "\t");

        // System.out.println(String.format("%04d", 202));
        // System.out.println(Arrays.toString(MONTHS));
        // setGregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)+ 1);

        /**
        System.out.println("Display DAY_OF_WEEK >> ");
        displayDayOfWeek();
 
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        String[] days_of_week = dfs.getShortWeekdays();
 
        // for(String month: months) {
        for(int index = 0; index < months.length; index++){
         System.out.println("MMM: " + months[index] + " index: " + index) ;
        }//Display all months
 
        // for(String days: days_of_week){
        for(int index = 0; index < days_of_week.length; index++){  
         System.out.println("Days_of_week: " + days_of_week[index] + " index: " + index ) ;
        }
         **/
     }//End of main method
 
     private static int getLastDayOfWeek(int year, int monthIndex) {
        calendar = new GregorianCalendar(year, monthIndex, 1);
        int maxDay = calendar.getActualMaximum(Calendar.DATE);
        System.out.println("Max Day: " + maxDay + " Calendar.MONDAY: " + Calendar.MONDAY);;
        // calendar = new GregorianCalendar(year, monthIndex, maxDay);
        calendar.set(Calendar.DATE, maxDay);
        System.out.println("Last Day of Week: " + calendar.get(Calendar.DAY_OF_WEEK));
        return calendar.get(Calendar.DAY_OF_WEEK);
    }//Returns FirstDayOfWeek

     private static void getDayOfTheWeek(int year, int monthIndex, int week){
        calendar = new GregorianCalendar(year, monthIndex, 28);
        System.out.println(calendar.getFirstDayOfWeek());
    }//

    private static void getDaysPerWeek(int year, int monthIndex, int curr_day){
        calendar = new GregorianCalendar(year, monthIndex, curr_day);
        System.out.println("DATE: " + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE));
        System.out.println("DATE(DAY): " + calendar.get(Calendar.DATE));
        System.out.println("WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));

        calendar = new GregorianCalendar(year, monthIndex, 1);
        int maxWeekOfMonth = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        int maxDayOfMonth = calendar.getActualMaximum(Calendar.DATE);
        int firstDay = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("Max Week Counts: " + maxWeekOfMonth);
        System.out.println("Max Day: " + maxDayOfMonth);
        System.out.println( "First Day: "+ firstDay);
    }//End of getDaysPerWeek
     private static void setGregorianCalendar(int year, int month, int day){
         System.out.println(year + "-" + month + "-" + day);
         GregorianCalendar calendar = new GregorianCalendar(year, month, day);
 
         // Display current time and date information.
         System.out.print("Date: ");
         System.out.print(calendar.get(Calendar.MONTH));
         System.out.print(" " + calendar.get(Calendar.DATE) + " ");
         System.out.println(year = calendar.get(Calendar.YEAR));
         System.out.println("DAY OF WEEK: " +  calendar.get(Calendar.DAY_OF_WEEK));
 
     }//setGregorianCalendar
 
     private static void displayDayOfWeek(){
         System.out.println("SUNDAY: " + Calendar.SUNDAY);
         System.out.println("MONDAY: " + Calendar.MONDAY);
         System.out.println("TUESDAY: " + Calendar.TUESDAY);
         System.out.println("WEDNESDAY: " + Calendar.WEDNESDAY);
         System.out.println("THURSDAY: " + Calendar.THURSDAY);
         System.out.println("FRIDAY: " + Calendar.FRIDAY);
         System.out.println("SATURDAY: " + Calendar.SATURDAY);
 
         System.out.println("JANUARY: " + Calendar.JANUARY);
         System.out.println("DECEMBER: " + Calendar.DECEMBER);
     }//End of class

         /**
     * Initializes String constants [MONTHS, DAYS_OF_WEEK]
     */
    private static void init(){
        for(int index = 0; index <= MONTHS.length; index++){
            if(new DateFormatSymbols().getMonths()[index] == "" || new DateFormatSymbols().getMonths()[index] == null){
                break;
            }

            MONTHS[index] = new DateFormatSymbols().getMonths()[index].toUpperCase();
        }//Months population

        for(int index = 0; index < DAYS_OF_WEEK.length; index++){
            DAYS_OF_WEEK[index] = new DateFormatSymbols().getShortWeekdays()[index].toUpperCase();
        }//Days of week population
    }//Initialize String arrays [MONTHS, DAYS_OF_WEEK]

    /*
     * NECESSARY METHODS, VARIABLES TO BE USED
     */
    public static void showNecessaryItems(){
        System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
        // System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
        System.out.println("MONTH: " + MONTHS[calendar.get(Calendar.MONTH)]);
        System.out.println("DATE: " + calendar.get(Calendar.DATE));
        System.out.println("");
        System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println("WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
        System.out.println("WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));
        System.out.println("DAY_OF_WEEK_IN_MONTH: "
                        + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println("MAX DAYS:    " + calendar.getActualMaximum(Calendar.DATE));

        System.out.println("Test: ");
    }//End of necessary items

    
 }//End of class