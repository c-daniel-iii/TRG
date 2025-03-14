package Algorithm;

import java.util.Arrays;

public class Quicksort {

    public static void main(String[] args) {
        int[] numbers = { 4, 3, 1, 2, 5, 9, 7, 10, 6 };

        System.out.print("\nBEFORE SORTING...");
        showArr(numbers);
        System.out.print("\n\nAFTER SORTING...");
        quicksort(numbers);
    }// Main method

    private static void quicksort(int[] numbers) {
        quicksort(numbers, 0, numbers.length - 1);
    }// quicksort; int[]

    private static void quicksort(int[] numbers, int lowIndex, int highIndex) {
        System.out.print("\nRECURSION... lowIndex: [" + lowIndex + "] highIndex: [" + highIndex + "]");
        if (lowIndex >= highIndex) {
            return;
        } // Recursion only if lowIndex <> highIndex

        int start = lowIndex;
        int end = highIndex;
        int median = start + (end - start) / 2;
        int pivot = numbers[median];

        while (start <= end) {
            while (numbers[start] < pivot) {
                showCurrSel(numbers,median, start, end);
                start++;
            }
            while (numbers[end] > pivot) {
                showCurrSel(numbers,median, start, end);
                end--;
            }

            if(start <= end){
                showCurrSel(numbers,median, start, end);
                swap(numbers, start, end);
                start++;
                end--;
            }
        }

        quicksort(numbers, lowIndex, end);
        quicksort(numbers, start, highIndex);
    }// quicksort; int[],int,int

    private static void showCurrSel(int[] numbers, int pivotIndex, int start, int end){

        System.out.print("\n pivotIndex: [" + pivotIndex + "] start: [" + start + "] end: ["+end + "]" );
        System.out.print("\tPivot: " + numbers[pivotIndex] + " 1st: " + numbers[start] + " 2nd: " + numbers[end]);
    }

    private static void swap(int[] numbers, int index1, int index2) {
        int temp = numbers[index1];
        numbers[index1] = numbers[index2];
        numbers[index2] = temp;
        showArr(numbers);
    }// Swap elements on the array param int[]

    private static void showArr(int[] numbers) {
        // System.out.print("\n");
        // for (int number : numbers)
        //     System.out.print(number + " ");

        System.out.println("\n" + Arrays.toString(numbers)); 
    }// Display all elements
}// End of quicksort

/*
 * 
 * public class QuicksortMedian {
 * 
 * public static void main(String[] args) {
 * int [] numbers = {4,3,1,2,5,9,7,10,6};
 * System.out.print("Before sort...");
 * showArr(numbers);
 * System.out.println("\nAfter sort...");
 * quickSort(numbers);
 * }
 * 
 * private static int partition(int []numbers, int lowIndex, int highIndex){
 * System.out.print("\n partition( , " + lowIndex + ", " + highIndex + ")\n");
 * int pivot = numbers[highIndex];
 * int leftPointer = lowIndex - 1;
 * //int rightPointer = lowIndex;
 * 
 * 
 * // for(int rightPointer = lowIndex; rightPointer < highIndex;
 * rightPointer++){
 * // if(rightPointer < pivot){
 * // leftPointer+=1;
 * // swap(numbers, leftPointer, rightPointer);
 * // }
 * // }
 * 
 * // swap(numbers, leftPointer + 1, highIndex);
 * 
 * return leftPointer + 1;
 * }//Returns partition int
 * 
 * private static void quickSort(int[] numbers){
 * quickSort(numbers, 0, numbers.length-1);
 * }//quicksort int[]
 * 
 * private static void quickSort(int[] numbers, int lowIndex, int highIndex){
 * if(lowIndex< highIndex){
 * int pivotIndex = partition(numbers, lowIndex, highIndex);
 * quickSort(numbers, lowIndex, pivotIndex-1); //LEFT PARTITION
 * quickSort(numbers, pivotIndex+1, highIndex); //RIGHT PARTITION
 * }
 * }//quicksort int[], int, int
 * private static void swap(int[] numbers, int index1, int index2){
 * int temp = numbers[index1];
 * numbers[index1] = numbers[2];
 * numbers[index2] = temp;
 * showArr(numbers);
 * }//Swap values
 * 
 * private static void showArr(int[]numbers){
 * System.out.print("\n");
 * for(int number: numbers)
 * System.out.print(number + " ");
 * }//Display array
 * }//
 * 
 */
