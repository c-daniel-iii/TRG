/**
 * Create a class that will display the days on the calendar of the current month
 * 
*   JANUARY 2025
*   SUN     MON     TUE     WED     THU     FRI     SAT
*                            1       2       3       4
*    5       6       7       8       9      10      11
*   12      13      14      15      16      17      18
*   19      20      21      22      23      24      25
*   26      27      28      29      30      31
 */
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;


public class LSN01{
    // private static Calendar cal;
    private static GregorianCalendar calendar;
    private static String MONTHS[] = new String[12];            //new DateFormatSymbols().getMonths();
    private static String DAYS_OF_WEEK[] = new String[8];  //new DateFormatSymbols().getShortWeekdays();

    public static void main(String[] args) {
       init();
       Calendar.getInstance();
       calendar = new GregorianCalendar();
    //    calendar = new GregorianCalendar(2024, Calendar.DECEMBER, 1);

    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    //    showNecessaryItems();
       showResult(year, month);
       while( reRun() == true){
        showResult(2025, month);
       }//while check if program will rerun
       

    }//End of Main method


    private static boolean reRun(){
        Scanner scan = new Scanner(System.in);
        System.out.print("\nDo you want to re-run the Application? [Y/N]: ");
        String input = String.valueOf(scan.next().charAt(0)).toUpperCase();
        System.out.println(input);
        if(input.equals("Y")){
            return true;
        } else if(input.equals("N")){
            System.out.println("Application terminating...");
            System.exit(0);
            return false;
        } else {
            System.out.println("Invalid Input");
            return reRun();
        }
        // return false;
    }//Asks for user input to rerun the program [Y/N]?
    /**
     * Displays CALENDAR in console
    **/
    private static void showResult(int year, int month){
        //Display MONTH and YEAR
        System.out.println("_________________________________________________________________");
        System.out.println(MONTHS[month] + " " + year );
        for(String days: DAYS_OF_WEEK){
            if(days == "") continue;

            System.out.print(days + "\t");
        }
        System.out.println("");

        calendar = new GregorianCalendar(year, month, 1);
        printDays(calendar.get(Calendar.DAY_OF_WEEK), calendar.getActualMaximum(Calendar.DATE));

        System.out.println("\n_________________________________________________________________");
    }//End of show Result void method

    private static void printDays(int first_day_of_month, int max_days_of_month){
        /** TABBING ON INITIAL WEEK OF THE MONTH **/
        int day_index;
        for(day_index = Calendar.SUNDAY; day_index <= DAYS_OF_WEEK.length; day_index++){
            if(day_index == first_day_of_month) 
                break;
            System.out.print(" ".repeat(3) + "\t");
        }


        //count_days
        day_index = first_day_of_month;
        for(int curr_day = 1; curr_day <= max_days_of_month; curr_day++){
            String curr_dayStr = " ".repeat(3) + curr_day;
            System.out.print(curr_dayStr.substring(curr_dayStr.length()-3) + "\t");

            if(day_index == Calendar.SATURDAY){
                System.out.println("");
                day_index = Calendar.SUNDAY;
            } else{
                day_index++;
            }           
            // int day_of_week_curr = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), curr_day).get(Calendar.DAY_OF_WEEK);
            // System.out.println("Curr_day: " + day_of_week_curr);
        }//Display all days 1~31 [Depending on month]
    }//End of printDays void
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