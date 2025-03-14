package Tests;

public class TestMain {

    public static void main(String[] args) {
        showMedian(0,6);
        showMedian(1,5);
        String input = "12";
        input = String.format("%-3s", " ").concat(input);

        /** 
        System.out.println(input);

            String day = "SU";
            System.out.println(day.substring(1));

            day = " ".repeat(3).concat(day);
            day = day.substring(day.length()-3);
            System.out.println(day + " L: " + day.length() + " L-3: " + (day.length()-3));
            

            day = "SUN";    //[000SUN]
            day = " ".repeat(3).concat(day);
            System.out.println(day + " L: " + day.length() + " L-3: " + (day.length()-3));
         */   
    }

    private static void showMedian(int start, int end){
        int median = getMedian(start, end);
        for(int i = start; i<=end; i++){
            System.out.print(i + " ");
        }
        System.out.println("\nstart: " + start + " end: " + end +  " Median: " + median);
    }
    private static int getMedian(int start, int end){
        return start + (end-start)/2;
    }//Displays median
    
}
