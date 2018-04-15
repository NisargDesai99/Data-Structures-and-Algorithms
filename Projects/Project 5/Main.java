import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.time.Duration;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int input = -1;
        while (input <= 0) {
            try {
                out.print("Enter the size of the array: ");
                input = scanner.nextInt();
                scanner.nextLine();
                out.print((input < 0) ? "Please enter a positive number.\n" : "");
            } catch (Exception ex) {
                scanner.nextLine();
                out.println("Please enter an integer. ");
            }
        }

        scanner.close();

        int size = (input > 0) ? input : -1;

        if (size == -1) {
            out.println("Invalid size");
        }

        String unsortedFileName = "unsorted.txt";
        String sortedFileName = "sorted.txt";

        // Create Quicksort object with the input array
        Quicksort quicksortObj = new Quicksort(size);

        // Print Unsorted to output file
        FileIO unsortedFile = new FileIO(new File(unsortedFileName));
        unsortedFile.clearFile();
        unsortedFile.appendToFile("Array size: " + size);
        unsortedFile.overwriteToFile(quicksortObj.toString());
        
        // Sort and measure times for each sort
        // Print time measurements on the command line
        
        long startTime = System.nanoTime();
        quicksortObj.sort(0);
        long finishTime = System.nanoTime();
        Duration timeRandPivot = Duration.ofNanos(finishTime - startTime);

        quicksortObj.reset();
        startTime = System.nanoTime();
        quicksortObj.sort(1);
        finishTime = System.nanoTime();
        Duration timeFirstPivot = Duration.ofNanos(finishTime - startTime);

        quicksortObj.reset();
        startTime = System.nanoTime();
        quicksortObj.sort(2);
        finishTime = System.nanoTime();
        Duration timeRandMedianPivot = Duration.ofNanos(finishTime - startTime);

        quicksortObj.reset();
        startTime = System.nanoTime();
        quicksortObj.sort(3);
        finishTime = System.nanoTime();
        Duration timeFMLMedianPivot = Duration.ofNanos(finishTime - startTime);

        out.println("Time for random pivot: " + timeRandPivot);
        out.println("Time for first pivot: " + timeFirstPivot);
        out.println("Time for median of random pivot: " + timeRandMedianPivot);
        out.println("Time for median of first, middle, last pivot: " + timeFMLMedianPivot);

        // Print sorted array to sorted file
        FileIO sortedFile = new FileIO(new File(sortedFileName));
        sortedFile.clearFile();
        sortedFile.appendToFile("Sorted: " + quicksortObj.toString());

    }
}