/**
 * Create a program that prints a calendar
 * Input is [year] and [month]:
 *  JANUARY 2025
*   SUN     MON     TUE     WED     THU     FRI     SAT
*                            1       2       3       4
*    5       6       7       8       9      10      11
*   12      13      14      15      16      17      18
*   19      20      21      22      23      24      25
*   26      27      28      29      30      31
 */

import java.util.GregorianCalendar;
import java.util.Scanner;
import java.text.DateFormatSymbols;
import java.util.Calendar;
public class LSN03{
    private static Scanner scan = new Scanner(System.in);
    private static GregorianCalendar calendar; 
    private static String DAYS_OF_WEEK[] = new DateFormatSymbols().getShortWeekdays();
    private static String MONTHS[] = new String[12];

    public static void main(String[] args) {
        init();
        runProgram();

        while(reRun() == true){
            runProgram();
        }
    }//END OF MAIN CLASS

    private static boolean reRun(){
        System.out.print("\n Do you want to re-run the Application? [Y/N]:");
        String input = String.valueOf(scan.nextLine().charAt(0)).toUpperCase();

        if(input.equals("Y")){
            return true;
        } else if(input.equals("N")){
            System.out.println("Application terminating...");
            System.exit(0);
            return false;
        } else {
            System.out.println(">> Invalid Input");
            return reRun();
        }
    }//

    private static void runProgram(){
        int year, month;
        year = getYear();
        month = getMonth();
        // year = 2025; month = 3;
        // year = 2024; month = 12;
        
        calendar = new GregorianCalendar(year, month-1, 1);

        showHeader(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
        printDays(calendar.get(Calendar.DAY_OF_WEEK), calendar.getMaximum(Calendar.DATE));
    }//Run Program
    
    private static void showHeader(int year, int month){
        System.out.println("\n" + MONTHS[month] + " " + year);
        for(String day: DAYS_OF_WEEK){
            if(day == "") continue;
            System.out.print(day + "\t");
        }
        System.out.println("");
    }//Print Header MONTH, YEAR, DAYS OF THE WEEK

    private static void printDays(int first_day_of_month, int max_days_of_month){
        /** TABBING ON INITIAL WEEK OF THE MONTH **/
        int day_index;
        for(day_index = Calendar.SUNDAY; day_index <= DAYS_OF_WEEK.length; day_index++){
            if(day_index == first_day_of_month) 
                break;
            System.out.print(" ".repeat(3) + "\t");
        }
        
        //PRINT DAYS
        day_index = first_day_of_month;
        for(int curr_day = 1; curr_day <= max_days_of_month; curr_day++){
            String curr_dayStr = " ".repeat(3) + curr_day;
            curr_dayStr = curr_dayStr.substring(curr_dayStr.length()-3); //0001 → 001;  00012 → 012
            System.out.print(curr_dayStr + "\t");

            if(day_index == Calendar.SATURDAY){
                System.out.println("");
                day_index = Calendar.SUNDAY;
            } else {
                day_index++;
            }   

        }//END OF LOOP
    }//End of printDays() void method

    private static void init(){
        for(int index = 0; index < DAYS_OF_WEEK.length; index++){
            DAYS_OF_WEEK[index] = DAYS_OF_WEEK[index].toUpperCase(); 
        }//DAYS_OF_WEEK POPULATION

        for(int index = 0; index < MONTHS.length; index++){
            MONTHS[index] = new DateFormatSymbols().getMonths()[index].toUpperCase(); 
        }//MONTHS POPULATION
    }//Initializes program

    //GET YEAR INPUT [1900 ~ 9999]
    private static int getYear(){
        System.out.print("\nEnter the YEAR: ");
        try{
            int year = Integer.parseInt(scan.nextLine());
            if(year >= 1900 && year <= 9999)
                return year;
            else{
                System.out.print(">> YEAR out of Range [1900 ~ 9999] <<");
                return getYear();
            }
        } catch (NumberFormatException ex){
            System.out.print(">> Invalid Input!!! <<");
            return getYear();
        }//Number Format Exception catch
    }//getYear

     //GET YEAR INPUT [1900 ~ 9999]
     private static int getMonth(){
        System.out.print("\nEnter the MONTH: ");
        try{
            int month = Integer.parseInt(scan.nextLine());
            if(month >= 1 && month <= 12)
                return month;
            else{
                System.out.print(">> MONTH out of Range [1 ~ 12] <<");
                return getMonth();
            }
        } catch (NumberFormatException ex){
            System.out.print(">> Invalid Input!!! <<");
            return getMonth();
        }//Number Format Exception catch
    }//getYear


    /*
     *  System.out.println("YEAR: "+ calendar.get(Calendar.YEAR));
        System.out.println("MONTH :"+ calendar.get(Calendar.MONTH));
        System.out.println("DAY_OF_WEEK :"+ calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println("MAXDATE :"+ calendar.getMaximum(Calendar.DATE));
     */
    
}//END OF CLASS