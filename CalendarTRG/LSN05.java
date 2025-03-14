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
        while (reRun() == true) {
            run();
        }
        System.out.println("Application terminating...");
        System.exit(0);
    }// Main method

    private static boolean reRun(){
        System.out.print("\nDo you want to re-run the Application again [Y/N]?");
        String input = String.valueOf(scan.nextLine().charAt(0));
        if(input.equalsIgnoreCase("Y"))
            return true;
        else if(input.equalsIgnoreCase("N"))
            return false;
        else {
            System.out.print("\n>>Invalid input!!!");
            return reRun();
        }
    }//Asks the user if the program should be executed again.

    private static void run() {
        int year, span;
        year = getYear();
        span = getSpanYear();
        initDayPointerMap(year, span);

        System.out.println("");
        showCalendar(year, span);
    }// Run the Application

    private static String getPrintedDaysPerWeek(int curr_day, int max_days, int dateIndex, int curr_year) {
        String days_per_week = "";

        while (curr_day <= max_days) {
            String curr_dayStr = " ".repeat(3) + String.valueOf(curr_day);
            curr_dayStr = curr_dayStr.substring(curr_dayStr.length() - 3);
            days_per_week += curr_dayStr.concat("\t");

            if (dateIndex == Calendar.SATURDAY) {
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

            int nWeeksMax = getMaxNumOfWeeks(year, span, monthIndex);
            int nWeekCurrMax;   // = getMaxNumOfWeeks(year, monthIndex); ;
            for (int curr_year = year; curr_year < (year + span); curr_year++) {
                doDisplay = false;
                nWeekCurrMax = getMaxNumOfWeeks(curr_year, monthIndex);
                int max_days = getMaxDay(curr_year, monthIndex);
                isLastYear = curr_year == (year + span - 1);

                if (nWeek == 1) {
                    dateIndex = getFirstDayOfWeek(curr_year, monthIndex);
                } else {
                    dateIndex = Calendar.SUNDAY;
                }

                int curr_day = day_pointerMap.get(curr_year) + 1;
                String daysNum = getPrintedDaysPerWeek(curr_day, max_days, dateIndex, curr_year);

                int lastDayIndex = getLastDayOfWeek(curr_year, monthIndex);

                if (nWeek == 1) {
                    daysNum = padding(dateIndex, false).concat(daysNum);
                    System.out.print(daysNum);
                } else if (nWeekCurrMax < nWeeksMax && nWeek >= nWeekCurrMax && curr_day >= max_days) {
                    System.out.print(padding());
                } else if (nWeek == nWeekCurrMax) { 
                    daysNum = daysNum.concat(padding(lastDayIndex, true));
                    System.out.print(daysNum);
                } else if (nWeek >= nWeekCurrMax){
                    System.out.print(padding());
                }else {
                    System.out.print(daysNum);
                }
                    

                // Increment nWeek (Line) only when end of year to (year+span) LOOP
                // ADD Page Break
                // nWeek = curr_year == (year + span - 1) ? nWeek + 1 : nWeek;
                if (isLastYear) {
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
            } // for loop
        } // while loop

    }// Displays the calendar

    private static String padding() {
        String padding = " ".repeat(3).concat("\t");
        int repCount = Calendar.SATURDAY;
        return padding.repeat(repCount);
    }// Padding lines that has no days on the current week

    private static String padding(int dateIndex, boolean isLast) {
        String padding = " ".repeat(3).concat("\t");
        int repCount = dateIndex - Calendar.SUNDAY;
        if (isLast) {
            repCount = Calendar.SATURDAY - dateIndex;
        }

        return padding.repeat(repCount);
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
            // System.out.print(curr_month.concat(getFillerSpaces(curr_month) + tab));
            String monthNamePerLine = curr_month.concat(getFillerSpaces(curr_month) + tab);
            // return curr_month.concat(getFillerSpaces(curr_month) + tab);
            return monthNamePerLine.repeat(span);
        }
    }// Return the whole line of the month header xn [where n: span]

    private static String getFillerSpaces(String currString) {
        return " ".repeat(daysOfWeekCnt - currString.length());
    }// Filler spaces for MONTH/ YEAR

    private static void showYearHeader(int year, int span) {
        for (int curr_year = year; curr_year < (year + span); curr_year++) {
            if (curr_year > 9999)
                break;
            System.out.print(curr_year + getFillerSpaces(String.format("%04d", curr_year)) + tab);
        } // Year Iteration up to range
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
}// End of class
