import java.util.Random;

public class Quicksort {
    
    private final int RANDOM = 0; // random pivot
    private final int FIRST_ELEMENT = 1; // first element as pivot
    private final int MEDIAN_OF_RANDOM = 2; // median of 3 random numbers from array as pivot
    private final int MEDIAN_F_M_L = 3; // meian of first, middle, and last from array as pivot

    int[] array;

    public Quicksort() {
        generateRandomArray(100);
    }

    public Quicksort(int size) {
        generateRandomArray(size);
    }

    public Quicksort(int[] array) {
        this.array = array;
    }

    public Quicksort(int size, int limit) {
		generateRandomArray(size, limit);
	}

    public void sort(int pivotType) {
        // call recursive quicksort
        quicksort(this.array, 0, this.array.length, pivotType);
    }

    private void quicksort(int[] arrToSort, int leftIndex, int rightIndex, int pivotType) {
        if (leftIndex <= rightIndex) {
            
            int partitionIndex = partition(arrToSort, leftIndex, rightIndex, pivotType);

            quicksort(arrToSort, leftIndex, partitionIndex - 1, pivotType);
            quicksort(arrToSort, partitionIndex + 1, rightIndex, pivotType);
        }
    }

    private int partition(int[] arr, int left, int right, int pivotType) {
        
        int pivotIndex = generatePivotIndex(pivotType, left, right);
        swapReferences(arr, pivotIndex, right - 1);

        int i = left, j = right - 1;
        Integer pivot = arr[pivotIndex];

        for ( ; ; ) {
            while ((new Integer(arr[++i])).compareTo(pivot) < 0) {  }
            while ((new Integer(arr[--j])).compareTo(pivot) > 0) {  }

            if (i < j) {
                swapReferences(arr, i, j);
            } else {
                break;
            }
        }

        swapReferences(arr, i, right - 1);
        return i;
    }

    private void swapReferences(int[] arr, int firstIndex, int secondIndex) {
        int temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }

    // generate a pivot based on the type of pivot that is specified
    // should be called with (pivotType, 0) as parameters if pivot should not be random
    private int generatePivotIndex(final int pivotType, int lowerBound, int upperBound) {
        if (pivotType == RANDOM && upperBound > 0) {
            return getRandomNumRange(lowerBound, upperBound); // return random index to be a pivot
        } else if (pivotType == FIRST_ELEMENT) {
            return 0; // return first index to be a pivot
        } else if (pivotType == MEDIAN_OF_RANDOM) {

            int randA = getRandomNumUpperBound(this.array.length);
            int randB = getRandomNumUpperBound(this.array.length);
            int randC = getRandomNumUpperBound(this.array.length);

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
        int median = (a <= b) ? ((b <= c) ? b : ((a < c) ? c : a)) : ((a <= c) ? a : ((b < c) ? c : b));
        return median;
    }

    // generate random number with no limits
    private int generateRandomNumber() {
        Random rand = new Random();
        int randomNum = rand.nextInt();
        return randomNum;
    }

    // genereate random number with an upper bound
    private int getRandomNumUpperBound(int upperBound) {
        Random rand = new Random();
        int randomNum = rand.nextInt(upperBound);
        return randomNum;
    }

    // generate random number with a lower and upper bound
    private int getRandomNumRange(int lowerBound, int upperBound) {
        Random rand = new Random();
        int randomNum = lowerBound + rand.nextInt(upperBound - lowerBound);
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
            this.array[i] = getRandomNumUpperBound(limit);
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