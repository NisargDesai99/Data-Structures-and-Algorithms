import static java.lang.System.out;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        
        Quicksort quicksort = new Quicksort(1000, 1000000);
        out.println("Before Sort");
        quicksort.printArray();
        quicksort.sort(0);
        out.println("After Random Pivot Sort");
        quicksort.printArray();

        out.println();
        quicksort.reset();
        out.println("Before Sort");
        quicksort.printArray();
        quicksort.sort(1);
        out.println("After First Element Sort");
        quicksort.printArray();

        out.println();
        quicksort.reset();
        out.println("Before Sort");
        quicksort.printArray();
        quicksort.sort(2);
        out.println("After Median of Random Sort");
        quicksort.printArray();

        out.println();
        quicksort.reset();
        out.println("Before Sort");
        quicksort.printArray();
        quicksort.sort(3);
        out.println("After Median of First, Middle, Last Sort");
        quicksort.printArray();
        
        
        // Quicksort quicksort = new Quicksort(new int[] { 95, 92, 26, 37, 48, 99, 0, 67, 34, 41 });
        // out.println("Before Sort");
        // quicksort.printArray();
        // quicksort.sort(0);
        // out.println("After Random Pivot Sort");
        // quicksort.printArray();

        // out.println();
        // quicksort.setArray(new int[] { 95, 92, 26, 37, 48, 99, 0, 67, 34, 41 });
        // out.println("Before Sort");
        // quicksort.printArray();
        // quicksort.sort(1);
        // out.println("After First Element Sort");
        // quicksort.printArray();

        // out.println();
        // quicksort.setArray(new int[] { 95, 92, 26, 37, 48, 99, 0, 67, 34, 41 });
        // out.println("Before Sort");
        // quicksort.printArray();
        // quicksort.sort(2);
        // out.println("After Median of Random Sort");
        // quicksort.printArray();

        // out.println();
        // quicksort.setArray(new int[] { 95, 92, 26, 37, 48, 99, 0, 67, 34, 41 });
        // out.println("Before Sort");
        // quicksort.printArray();
        // quicksort.sort(3);
        // out.println("After Median of First, Middle, Last Sort");
        // quicksort.printArray();

    }
}