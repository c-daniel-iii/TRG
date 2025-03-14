
/*
 * LSN05:
	>> Input: Year, YearSpan
	>> Display the calendar that is on the year span
 */
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class LSN05 {
    private static Scanner scan = new Scanner(System.in);
    private static List<String> MONTHS = new ArrayList<String>();
    private static List<String> DAYS_OF_WEEK = new ArrayList<String>();
    private static int daysOfWeekCnt;
    private static String tab;
    private static GregorianCalendar calendar;
    private static HashMap<Integer, Integer> day_pointerMap = new HashMap<Integer, Integer>();

    public static void main(String[] args) {
        initialize();
        run();
    }// Main method

    private static void run() {
        int year, span;
        // year = getYear();
        // span = getSpanYear();
        year = 2025;
        span = 2;

        initDayPointerMap(year, span);

        System.out.println("");
        showCalendar(year, span);
        // getPrintedDaysPerWeek(1,31,1, year);
    }// Run the Application

    private static String getPrintedDaysPerWeek(int curr_day, int max_days, int dateIndex, int curr_year) {
        String days_per_week = "";

        while (curr_day <= max_days) {
            String curr_dayStr = " ".repeat(3) + String.valueOf(curr_day);
            curr_dayStr = curr_dayStr.substring(curr_dayStr.length() - 3);
            days_per_week += curr_dayStr.concat("\t");

            if (dateIndex == Calendar.SATURDAY) {
                // int next_day = (curr_day + 1) > max_days ? 1 : (curr_day + 1);
                // setDayPointerMap(curr_year, next_day);

                int temp_day = curr_day <= max_days ? curr_day : 0;
                setDayPointerMap(curr_year, temp_day);
                break;
            }

            dateIndex++;
            curr_day++;
        }
        // System.out.println(days_per_week);
        return days_per_week;
    }

    private static void showCalendar(int year, int span) {
        showYearHeader(year, span);
        System.out.print("\n");

        int monthIndex = Calendar.JANUARY;
        int nWeek = 1;
        int dateIndex;
        boolean doDisplay = true;
        boolean isLastYear = false;
        while (monthIndex < MONTHS.size()) {
            if (doDisplay == true) {
                System.out.print(getMonthHeader(monthIndex, span));
                System.out.print("\n");
                System.out.print(getWeeksHeader(span));
                System.out.print("\n");
            }

            int nWeeksMax = getMaxNumOfWeeks(year, span, monthIndex); // This calculates the highest week count of the
                                                                      // MONTH[Jan to Dec] for the entire year range
                                                                      // [year to (year + n -1)]

            for (int curr_year = year; curr_year < (year + span); curr_year++) {
                // System.out.print("> " + getMaxNumOfWeeks(curr_year, monthIndex) + ", " +
                // getMaxDay(curr_year, monthIndex) + "\t");
                // int nWeekCurr = getMaxNumOfWeeks(curr_year, monthIndex);
                doDisplay = false;
                int max_days = getMaxDay(curr_year, monthIndex);
                isLastYear = curr_year == (year + span - 1);

                if (nWeek == 1) {
                    dateIndex = getFirstDayOfWeek(curr_year, monthIndex);
                } else {
                    dateIndex = Calendar.SUNDAY;
                }

                String test = day_pointerMap.toString() + " Key: " + curr_year;
                int curr_day = day_pointerMap.get(curr_year) + 1;
                String daysNum = getPrintedDaysPerWeek(curr_day, max_days, dateIndex, curr_year);

                int fetchDay = day_pointerMap.get(curr_year);
                int lastDayIndex = getLastDayOfWeek(curr_year, monthIndex);

                if (nWeek == 1){
                    padding(dateIndex, false);
                    System.out.print(daysNum);
                } else if (nWeek == nWeeksMax){  // Check if Last Line and last day of the month
                    System.out.print(daysNum);
                    padding(lastDayIndex, true);
                } else {
                    System.out.print(daysNum);
                }
                    
                // if (nWeek != nWeeksMax && fetchDay == max_days){
                //     padding();
                // } 
                
                
                // Increment nWeek (Line) only when end of year to (year+span) LOOP
                // ADD Page Break
                // nWeek = curr_year == (year + span - 1) ? nWeek + 1 : nWeek;
                if (isLastYear) {
                    System.out.print("\t" + day_pointerMap.toString() + " nWeek: " + nWeek + "/ " + nWeeksMax);
                    System.out.print("\n"); // line break ath the end
                }

                if (nWeek == nWeeksMax && isLastYear) {
                    monthIndex++; // This only increments when nWeek == nWeeksMax;
                    nWeek = 1; // Week count resets at every new Month
                    doDisplay = true; // Flag to identify if Months and weeks header are needed to be printed
                    initDayPointerMap(year, span); // Resets Map values only when the monthIndex has been incremented
                    System.out.print("\n"); // Line break
                } else {
                    nWeek = isLastYear ? nWeek + 1 : nWeek;
                }
                // System.out.print(nWeek + "_nWeek");
            } // for loop
        } // while loop

    }// Displays the calendar

    private static void padding() {
        String padding = "p".repeat(3).concat("\t");
        int repCount = Calendar.SATURDAY - Calendar.SUNDAY;
        System.out.print(padding.repeat(repCount));
    }// Padding lines that has no days on the current week

    private static void padding(int dateIndex, boolean isLast) {
        String padding = "-".repeat(3).concat("\t");
        int repCount = dateIndex - Calendar.SUNDAY;
        if (isLast) {
            repCount = Calendar.SATURDAY - dateIndex;
        }

        System.out.print(padding.repeat(repCount));
        // for (int day = Calendar.SUNDAY; day < dateIndex; day++) {
        // System.out.print(padding);
        // }
    }// Creates padding on end or beginning

    private static int getFirstDayOfWeek(int year, int monthIndex) {
        calendar = new GregorianCalendar(year, monthIndex, 1);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }// Returns FirstDayOfWeek

    private static int getLastDayOfWeek(int year, int monthIndex) {
        calendar = new GregorianCalendar(year, monthIndex, 1);
        int maxDay = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DATE, maxDay);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }// Returns FirstDayOfWeek

    private static int getMaxNumOfWeeks(int year, int monthIndex) {
        calendar = new GregorianCalendar(year, monthIndex, 1);
        return calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }// Get line count (max week count)

    // private static int getMaxNumOfWeeks(int year, int span, int monthIndex) {

    // }//Get current nWeek

    private static int getMaxNumOfWeeks(int year, int span, int monthIndex) {
        int maxNumOfWeeks = 0;
        int curr_year = year;
        while (curr_year < (span + year)) {
            calendar = new GregorianCalendar(year, monthIndex, 1);
            int numOfWeeks = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
            maxNumOfWeeks = maxNumOfWeeks < numOfWeeks ? numOfWeeks : maxNumOfWeeks;
            curr_year++;
        }
        return maxNumOfWeeks;
    }// Get line count (max week count)

    private static int getMaxDay(int year, int monthIndex) {
        calendar = new GregorianCalendar(year, monthIndex, 1);
        return calendar.getActualMaximum(Calendar.DATE);
    }// Get line count (max week count)

    private static String getWeeksHeader(int span) {
        String weeks = "";
        for (int yearspan = 1; yearspan <= span; yearspan++) {
            int dayIndex = Calendar.SUNDAY;
            while (dayIndex < DAYS_OF_WEEK.size()) {
                String day = DAYS_OF_WEEK.get(dayIndex);
                weeks = weeks.concat(day + "\t");
                dayIndex++;
            }
        }
        return weeks;
    }// Days of Week header

    private static String getMonthHeader(int monthIndex, int span) {
        return getMonthHeader(monthIndex, span, true);
    }// Return the whole line of the month header xn [where n: span]

    private static String getMonthHeader(int monthIndex, int span, boolean doDisplay) {
        if (doDisplay == false) {
            return null;
        } else {
            String curr_month = MONTHS.get(monthIndex);
            System.out.print(curr_month.concat(getFillerSpaces(curr_month) + tab));
            return curr_month.concat(getFillerSpaces(curr_month) + tab);
        }
    }// Return the whole line of the month header xn [where n: span]

    private static String getFillerSpaces(String currString) {
        return " ".repeat(daysOfWeekCnt - currString.length());
    }// Filler spaces for MONTH/ YEAR

    private static void showYearHeader(int year, int span) {
        // String weekdays_spaces = "0".repeat(21).concat("\t".repeat(21));
        for (int curr_year = year; curr_year < (year + span); curr_year++) {
            if (curr_year > 9999)
                break;
            System.out.print(curr_year + getFillerSpaces(String.format("%04d", curr_year)) + tab);
        } // Year Iteration up to range
          // System.out.println(weekdays_spaces);
    }// Display year header

    private static int getYear() {
        System.out.print("Enter year [1900~9999]:");
        String input = scan.nextLine();
        if (isValidDigit(input) == true) {
            if (isValidRange(input) == false) {
                System.out.println(">> Invalid Input [NOT IN RANGE]!!!");
                return getYear();
            }
        } else {
            System.out.println("> Invalid Input [NOT A DIGIT]!!!");
            return getYear();
        }

        return Integer.parseInt(input);
    }// Return year input

    private static int getSpanYear() {
        System.out.print("Enter Year span [+Year range]: ");
        String input = scan.nextLine();

        if (isValidDigit(input) == false) {
            return getSpanYear();
        }

        int inputInt = Integer.parseInt(input);

        if (inputInt < 1)
            return getSpanYear();

        return inputInt;
    }// Return year span [0~num]

    private static boolean isValidDigit(String input) {
        String regexNum = "^\\d+$";
        // System.out.println("Is Number: " + input.matches(regexNum));
        return input.matches(regexNum);
    }

    private static boolean isValidRange(String input) {
        String regexRange = "^\\d{4}$";
        // System.out.println("Is Valid Range: " + input.matches(regexRange));
        return input.matches(regexRange);
    }

    private static void initialize() {
        System.out.println(" ********* ********* INITIALIZING ********* ********* ");
        String[] months = new DateFormatSymbols().getMonths();
        for (String month : months) {
            if (month.isEmpty() || month == null)
                continue;
            MONTHS.add(month.toUpperCase());
        }

        String[] daysOfWeek = new DateFormatSymbols().getShortWeekdays();
        for (String day : daysOfWeek) {
            DAYS_OF_WEEK.add(day.toUpperCase());
        }

        for (String day : DAYS_OF_WEEK) {
            // System.out.print(day);
            daysOfWeekCnt += day.length();
        }
        // tab = "\t".repeat(DAYS_OF_WEEK.size()-1);
        tab = "\t".repeat(5);
    }// Initializes global variables

    private static void initDayPointerMap(int year, int span) {
        day_pointerMap.clear();
        for (int curr_year = year; curr_year < (span + year); curr_year++) {
            // day_pointerMap.put(curr_year, 1);
            day_pointerMap.put(curr_year, 0);
        }
    }// Resets the Current Day if new Month

    private static void setDayPointerMap(int key, int value) {
        day_pointerMap.put(key, value);
        // System.out.print(day_pointerMap.toString());
    }

    /**
     * private static void showCalendar(int year, int span){
     * System.out.println("");
     * showYearHeader(year, span);
     * 
     * for(int monthIndex = Calendar.JANUARY; monthIndex < MONTHS.size();
     * monthIndex++){
     * System.out.print("\n");
     * showMonthHeader(year, span, monthIndex);
     * 
     * System.out.print("\n");
     * showDayDetails(year, span, monthIndex);
     * }//Iterates months
     * }//Displays the calendar output
     * 
     * private static void showDayDetails(int year, int span, int monthIndex){
     * for(int curr_year = year; curr_year < (year + span); curr_year++) {
     * if(curr_year > 9999)
     * break;
     * 
     * int dayOfMonth = 1;
     * calendar = new GregorianCalendar(year, monthIndex, dayOfMonth);
     * int first_day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
     * int max_days = calendar.getActualMaximum(Calendar.DATE);
     * setPadding(first_day_of_week); System.out.print("x");
     * // for(int day = dayOfMonth; day <= max_days; day++){
     * 
     * // }
     * }
     * }//Displays days[1~ 28/30/31]
     * 
     * private static void setPadding(int first_day_of_week){
     * String padding = " ".repeat(3);
     * int day_index = Calendar.SUNDAY;
     * 
     * while(day_index < DAYS_OF_WEEK.size()){
     * if(day_index == first_day_of_week) break;
     * System.out.print(padding + "\t");
     * day_index++;
     * }
     * }
     * 
     * 
     * private static void showMonthHeader(int year, int span, int monthIndex){
     * for(int curr_year = year; curr_year < (year + span); curr_year++){
     * if(curr_year > 9999)
     * break;
     * 
     * String curr_month = MONTHS.get(monthIndex);
     * int fillerSpaces = daysOfWeekCnt - curr_month.length();
     * // System.out.print( (daysOfWeekCnt - curr_month.length()));
     * System.out.print(curr_month + getFillerSpaces(fillerSpaces) + tab);
     * }
     * 
     * System.out.print("\n");
     * showDaysHeader(year, span);
     * 
     * }//Display month header
     * 
     * private static void showDaysHeader(int year, int span){
     * for(int curr_year = year; curr_year < (year + span); curr_year++){
     * if(curr_year > 9999)
     * break;
     * 
     * for(String day: DAYS_OF_WEEK){
     * if(day.isEmpty() || day == null)
     * continue;
     * 
     * System.out.print(day + "\t");
     * }
     * }
     * }//Display days header
     * 
     */
}// End of class
