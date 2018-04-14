import java.util.Scanner;
import java.util.Random;
import static java.lang.System.out;

public class Quicksort {
	
	private final int RANDOM = 0;				// random pivot
	private final int FIRST_ELEMENT = 1;		// first element as pivot
	private final int MEDIAN_OF_RANDOM = 2;		// median of 3 random numbers from array as pivot
	private final int MEDIAN_F_M_L = 3;			// meian of first, middle, and last from array as pivot

	private int[] array;

	public Quicksort() {
		generateRandomArray(100);		// if no array or size is given generate a random array of size 100
	}

	public Quicksort(int size) {
		generateRandomArray(size);		// size is given... generate random array of that size
	}

	public Quicksort(int size, int limit) {
		generateRandomArray(size, limit);
	}

	public Quicksort(int[] arr) {
		this.array = arr;				// array is given... set the array in this class to that array
	}

	public int[] getArray() {
		return this.array;				// return array from here
	}

	// quicksort algorithm
	public void sort(int pivotType) {
		// call recursive quicksort
		quicksort(this.array, 0, this.array.length, pivotType);
	}


	public void quicksort(int[] arrToSort, int left, int right, int pivotType) {
		if (left < right) {
			int partitionIndex = partition(arrToSort, left, right, pivotType);

			quicksort(arrToSort, left, partitionIndex - 1, pivotType);
			quicksort(arrToSort, partitionIndex + 1, right, pivotType);
		}
	}


	public int partition(int[] arrToSort, int left, int right, int pivotType) {
		
		// Generate pivot and hide it at the end of the array
		int pivotIndex = generatePivotIndex(pivotType, right);
		swapReferences(arrToSort, pivotIndex, right - 1);
		pivotIndex = right - 1;

		// Scanner scanner = new Scanner(System.in);
		// out.println("Press Enter to continue: ");
		// scanner.nextLine();

		int i = left;
		int j = right - 1;

		for ( ; ; ) {
			while( arrToSort[ i ] < ( arrToSort[pivotIndex] ) /*< 0*/ ) {
				i++;
				if (i > j) break;
			}
			while( arrToSort[ j ] > ( arrToSort[pivotIndex] ) /*> 0*/ ) {
				j--;
				if (j < 0) break;
			}

			if (i < j) {
				swapReferences( arrToSort, i, j );
			}
			else {
				break;
			}
		}

		swapReferences( arrToSort, i, right - 1 ); // Restore pivot
		return i + 1;
	}

	private void swapReferences(int[] arr, int firstIndex, int secondIndex) {
		int temp = arr[firstIndex];
		arr[firstIndex] = arr[secondIndex];
		arr[secondIndex] = temp;
	}

	// generate a pivot based on the type of pivot that is specified
	// should be called with (pivotType, 0) as parameters if pivot should not be random
	private int generatePivotIndex(final int pivotType, int upperBound) {
		if (pivotType == RANDOM && upperBound > 0) {
			return generateRandomNumber(upperBound);		// return random index to be a pivot
		} else if (pivotType == FIRST_ELEMENT) {
			return 0;										// return first index to be a pivot
		} else if (pivotType == MEDIAN_OF_RANDOM) {

			int randA = generateRandomNumber(this.array.length);
			int randB = generateRandomNumber(this.array.length);
			int randC = generateRandomNumber(this.array.length);

			int a = this.array[randA];
			int b = this.array[randB];
			int c = this.array[randC];

			int median = median(a, b, c);

			if (median == a)
				return randA;
			else if (median == b) 
				return randB;
			else if (median == c)
				return randC;

		} else if (pivotType == MEDIAN_F_M_L) {
			int indexA = 0;
			int indexB = this.array.length / 2;
			int indexC = this.array.length - 1;


			int a = this.array[indexA];
			int b = this.array[indexB];
			int c = this.array[indexC];

			int median = median(a, b, c);

			if (median == a)
				return indexA;
			else if (median == b)
				return indexB;
			else if (median == c)
				return indexC;
		}

		return -1;
	}

	// find median of 3 values
	private int median(int a, int b, int c) {
		int median = (a <= b) 
				? ((b <= c) ? b : ((a < c) ? c : a)) 
				: ((a <= c) ? a : ((b < c) ? c : b));

		return median;
	}

	// generate random number with no limits
	private int generateRandomNumber() {
		Random rand = new Random();
		int randomNum = rand.nextInt();
		return randomNum;
	}

	// genereate random number with an upper bound
	private int generateRandomNumber(int limit) {
		Random rand = new Random();
		int randomNum = rand.nextInt(limit);
		return randomNum;
	}

	// generate array with random values of specified size
	private void generateRandomArray(int size) {
		this.array = new int[size];
		for (int i = 0; i < size; i++) {
			this.array[i] = generateRandomNumber();
		}
	}

	private void generateRandomArray(int size, int limit) {
		this.array = new int[size];
		for (int i = 0; i < size; i++) {
			this.array[i] = generateRandomNumber(limit);
		}
	}

	public void printArray() {
		StringBuilder bldr = new StringBuilder();
		for (int i = 0; i < this.array.length; i++) {
			bldr.append(this.array[i]).append(" ");
		}
		bldr.deleteCharAt(bldr.length() - 1);
		System.out.println(bldr.toString());
	}

}