public class CalendarTest {
    
    public static void main(String[] args) {
        CalendarAbstractImplementation calimpl = new CalendarAbstractImplementation();
        calimpl.initialize();
        int first_day_of_week = 4;
        int max_days = 31;

        calimpl.showDaysHeader();
        System.out.println(""); //BREAK LINE
        calimpl.printDays(first_day_of_week, max_days);
        // String str = "012345";
        // System.out.println(str.substring(str.length()-2) + " LEN: "+ str.length() + " LEN: (-2) " + (str.length()-2));
    }//End of main method
}
