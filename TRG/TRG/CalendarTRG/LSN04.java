/**
 * LSN04:
	>> Input: Year
	>> Display the calendar of all the months on the input year
 */

import java.util.Scanner;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class LSN04 {
    private static Scanner scan = new Scanner(System.in);
    private static List <String> MONTHS = new ArrayList<String>();
    private static List <String> DAYS_OF_WEEK = new ArrayList<String>();
    private static GregorianCalendar cal;
    public static void main(String[] args) {
        initialize();
        int year = getYear();
        
        showCalendar(year);
    }//End of main method

    private static void showCalendar(int year){
        System.out.print(year);
        //Iterate months
        for(int monthIndex=Calendar.JANUARY; monthIndex < MONTHS.size(); monthIndex++){
            System.out.println("");
            printMonth(monthIndex);
            System.out.println("");
            printDaysOfWeek();

            System.out.println("");
            cal = new GregorianCalendar(year, monthIndex, 1);
            int first_day_of_week  = cal.get(Calendar.DAY_OF_WEEK);
            int max_days = cal.getActualMaximum(Calendar.DATE);
            printDays(first_day_of_week, max_days);
        }
    }//Build the output here

    private static void printMonth(int month){
        if(month > Calendar.JANUARY) 
            System.out.print("\n"); //PAGE BREAK IF FEBRUARY ONWARDS
        System.out.print(MONTHS.get(month));
    }//Print month header

    private static void printDaysOfWeek(){
        for(String day: DAYS_OF_WEEK){
            if(day.isEmpty() || day ==null) continue;
            System.out.print(day + "\t");
        }
    }//Print all days of week [SUN ~ SAT]

    private static void printDays(int first_day_of_week, int max_days){
        // System.out.println(first_day_of_week + "/ " + max_days);
        String padding = " ".repeat(3);
        int day_index;
        for(day_index = Calendar.SUNDAY; day_index < DAYS_OF_WEEK.size(); day_index++){
            if(day_index == first_day_of_week) break;
            System.out.print(padding + "\t");
        }//Tabbing spaces on the first week
        
        day_index = first_day_of_week;
        for(int curr_day = 1; curr_day <= max_days; curr_day++){
            String curr_dayStr = padding.concat(String.valueOf(curr_day));
            curr_dayStr = curr_dayStr.substring(curr_dayStr.length()-3); //REPLICATES RIGHT(string, index) 0001 → 01; 00021 → 21
            System.out.print(curr_dayStr + "\t");

            if(day_index == Calendar.SATURDAY){
                System.out.print("\n"); //BREAK LINE IF SATURDAY
                day_index = Calendar.SUNDAY; //RESETS TO SUNDAY
            } else {
                day_index++; //ELSE VARIABLE UPDATE
            }
        }//Print days with line breaks at Saturdays
    }//Print days [1 - 28/30/31]

    private static void initialize(){
        String months[] = new DateFormatSymbols().getMonths();
        for(String month: months){
            if(month.isEmpty() || month ==null)
                break;
            MONTHS.add(month.toUpperCase());
        }//populate MONTHS <LIST>

        String days_of_week[] = new DateFormatSymbols().getShortWeekdays();
        for(String day: days_of_week){
            DAYS_OF_WEEK.add(day.toUpperCase());
        }
    }//Initializes global variables

    private static int getYear(){
        System.out.print("\n Enter the year[1900~9999]: ");
        String input = scan.nextLine();
        try{
            int year = Integer.parseInt(input);
            if((year >= 1900 && year <= 9999)){
                return year;
            } else {
                System.out.print("\n>> Invalid Input: [1900 ~ 9999]<<");
                return getYear();
            }
            
        } catch(java.lang.NumberFormatException ex){
            System.out.print("\n>> Invalid Input: ");
            return getYear();
        }//Parsing error
    }//Ask for user inputs and returns year (int)
}//End of class
