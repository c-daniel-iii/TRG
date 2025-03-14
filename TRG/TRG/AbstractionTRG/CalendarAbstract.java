import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public abstract class CalendarAbstract {
    HashMap<Integer, String> MONTHS_MAPLIST = new HashMap<Integer, String>();
    List<String> MONTHS_LIST = new ArrayList<String>();
    String DAYS_OF_THE_WEEK_LIST [] = new DateFormatSymbols().getShortWeekdays();

    public abstract void displayCalendar(); //Display Calendar

    //Print month days [1-31]
    //first_day_of_week: SUN-1 ... SAT-7 ; max_days: 28/30/31 days of a month
    public void printDays(int first_day_of_week, int max_days){
        int day_index;
        for(day_index = Calendar.SUNDAY; day_index < DAYS_OF_THE_WEEK_LIST.length; day_index++){
           if(first_day_of_week == Calendar.SUNDAY || first_day_of_week == day_index) 
                break;
            System.out.print(" ".repeat(3) + "\t");
        }//Tab for the first week

        day_index = first_day_of_week;
        for(int curr_day = 1; curr_day<= max_days; curr_day++){
            String curr_dayStr = " ".repeat(3).concat(String.valueOf(curr_day));
            curr_dayStr = curr_dayStr.substring(curr_dayStr.length()-3);    // [4-3]0001 → 01 ; [5-3]00012

            System.out.print(curr_dayStr + "\t");

            if(day_index == Calendar.SATURDAY){
                System.out.println("");
                day_index = Calendar.SUNDAY;
            } else{
                day_index++;
            }
        }//Print day [1~ max_days(28/ 29/ 31)]
    }//Print Days with indentation

    //Displays the header [January ~ December]
    public void showMonthHeader(int monthIndex){
        System.out.print(MONTHS_LIST.get(monthIndex));
    }//End of showMonthHeader

    //Displays the header [SUN ~ SAT]
    public void showDaysHeader(){
        for(String day : DAYS_OF_THE_WEEK_LIST){
            if(day.isEmpty() || day == null) continue;
            System.out.print(day + "\t");
        }
    }//End of showDaysHeader

    public void initialize(){
        String monthsArr [] = new DateFormatSymbols().getMonths();
        for(int index = Calendar.JANUARY; index <= monthsArr.length; index++){
            if(monthsArr[index].isEmpty() || monthsArr[index] == null) 
                break;

            String curr_month = monthsArr[index].toUpperCase();
            MONTHS_MAPLIST.put(index, curr_month) ;
        }//For loop for population

        //--------------------------------------------------------------------
        for(String month: monthsArr){
            if(month.isEmpty()|| month == null) 
                break;
    
            MONTHS_LIST.add(month.toUpperCase());
        }//enhanced for loop

         //--------------------------------------------------------------------
        for(int index = 0; index < DAYS_OF_THE_WEEK_LIST.length; index++){
            DAYS_OF_THE_WEEK_LIST[index] = DAYS_OF_THE_WEEK_LIST[index] .toUpperCase();
        }//CONVERT TO UPPER CASE
        
    }//Initializes Global variables
}//End of AbstractClass CalendarAbstract
