import java.util.Random;

public class Quicksort {
	
	private final int RANDOM = 0;				// random pivot
	private final int FIRST_ELEMENT = 1;		// first element as pivot
	private final int MEDIAN_OF_RANDOM = 2;		// median of 3 random numbers from array as pivot
	private final int MEDIAN_F_M_L = 3;			// meian of first, middle, and last from array as pivot

	int[] array;
	int pivotIndex;

	public Quicksort() {
		generateRandomArray();
	}

	public Quicksort(int[] arr) {
		this.array = arr;
	}

	private int generatePivot(final int pivotType) {
		switch (pivotType) {
			case RANDOM:
				generateRandomPivot();
			case FIRST_ELEMENT:
				this.pivot = 
		}
	}

	private int generateRandomArray() {

	}

}