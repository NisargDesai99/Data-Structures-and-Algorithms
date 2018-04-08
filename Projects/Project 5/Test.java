import java.util.Random;
import java.util.Scanner;
import static java.lang.System.out;

public class Test {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random rand = new Random();

		// Test array -- randomly generate one for actual program
		int[] arr = new int[] { 3,1,4,5,2 };
		printArray(arr);

		quickSort(arr, 0, arr.length - 1);
		printArray(arr);
	}

	public static void quickSort(int[] arr, int low, int hi) {
		if (low + CUTOFF <= hi) {
			int pivot = median3(a, low, hi);

			int i = low, j = hi - 1;

			for ( ; ; ) {
				while ( a[++i].compareTo(pivot) < 0 ) {  }
				while ( a[--j].compareTo(pivot) > 0 ) {  }

				if (i < j)
					swap(a, i, j);
				else
					break;
			}

			swap(a, i, hi - 1);

			quickSort(a, low, i - 1);
			quickSort(a, i + 1, hi);
		}

		else
			insertionSort(a, low, hi);
	}


	public void insertionSort(int[] arr, int left, int right) {
		
	}
}