import java.util.Random;
import java.util.Scanner;
import static java.lang.System.out;

public class Test {
	public static void main(String[] args) {
		
		// Quicksort quicksort = new Quicksort(10, 100);
		// Quicksort1 quicksort = new Quicksort1(new int[] { 95, 92, 26, 37, 48, 99, 0, 67, 34, 41 });

		Quicksort1 quicksort = new Quicksort1(new int[] { 4,8,66,35,74,33,3,46,91,46 });

		out.println("Before sort");
		quicksort.printArray();

		// Scanner scanner = new Scanner(System.in);
		// out.println("Press Enter to continue...");
		// scanner.nextLine();

		quicksort.sort(0);
		out.println("After sort");
		quicksort.printArray();
	}

	
}


/*
	4 8 66 35 74 33 3 46 91 46
	Tried sorting with pivot type 1 - First Element as pivot
*/