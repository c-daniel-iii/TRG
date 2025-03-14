/*
 * FACTORY DESIGN CLASS OF THE CALENDAR
 * MUST IMPLEMENT ALL OF THIS ON YOUR CODE
 */
interface CalendarInterface {
    public abstract void initialize();  //Initializes all the global variables; MONTHS_LIST[JAN ~ DEC]; DAYS_OF_THE_WEEK_LIST [SUN ~ SAT]
    public abstract int  getMaximumDay(int year, int monthIndex); //Returns the maximum days of the current Month >> year: 2025; month: 0 [JAN:0, FEB:1 ... DEC:11]
    public abstract int  getFirstDayOfWeek(int year, int monthIndex); //Returns the first day the week of the current Month >> RETURNS: [SUN:1, MON:2,... SAT:7]
    public abstract void printYear(int year); //Print the year header
    public abstract void printMonth(int monthIndex); //Print the month header
    public abstract void printDaysOfWeek();//Print the days of the week
    public abstract void printDays(int year, int monthIndex); //Display the output
} //End of Interface 
