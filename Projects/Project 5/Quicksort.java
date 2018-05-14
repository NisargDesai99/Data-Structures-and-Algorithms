import java.util.Random;

public class Quicksort {

    private final int RANDOM = 0; // random pivot
    private final int FIRST_ELEMENT = 1; // first element as pivot
    private final int MEDIAN_OF_RANDOM = 2; // median of 3 random numbers from array as pivot
    private final int MEDIAN_F_M_L = 3; // meian of first, middle, and last from array as pivot

    private int[] array;
    private int[] origArray;

    // public Quicksort() {
    //     generateRandomArray(100);
    // }

    public Quicksort(int size) {
        generateRandomArray(size);
    }

    public Quicksort(int[] array) {
        this.array = array;
        this.origArray = new int[this.array.length];
        for (int i = 0; i < array.length; i++) {
            this.origArray[i] = Integer.valueOf(this.array[i]);
        }
    }

    // public Quicksort(int size, int limit) {
    //     generateRandomArray(size, limit);
    // }

    public void setArray(int[] array) {
        this.array = array;
    }

    public void reset() {
        array = new int[this.origArray.length];
        for (int i = 0; i < this.origArray.length; i++) {
            this.array[i] = Integer.valueOf(this.origArray[i]);
        }
    }

    public void sort(int pivotType) {
        quicksort(this.array, 0, this.array.length - 1, pivotType);
    }

    private void quicksort(int[] arrToSort, int leftIndex, int rightIndex, int pivotType) {
        if (leftIndex < rightIndex) {
            int pivotIndex = generatePivotIndex(pivotType, leftIndex, rightIndex + 1);
            int partitionIndex = partition(arrToSort, leftIndex, rightIndex, pivotIndex);

            quicksort(arrToSort, leftIndex, partitionIndex - 1, pivotType);
            quicksort(arrToSort, partitionIndex + 1, rightIndex, pivotType);
        }
    }

    private int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivot = arr[pivotIndex];    // get pivot
        swapReferences(arr, pivotIndex, right);     // put pivot at the end to hide it
        pivotIndex = right;     // since we swapped the pivot, we need to update the pivotIndex
        int i = (left - 1);     // index of smaller element
        for (int j = left; j < right; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot) {
                i++;
                // swap arr[i] and arr[j]
                swapReferences(arr, i, j);
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        swapReferences(arr, i+1, right);

        return i + 1;
    }

    private int generatePivotIndex(final int pivotType, int lowerBound, int upperBound) {
        if (pivotType == RANDOM && upperBound > 0) {
            return getRandNumRange(lowerBound, upperBound); // return random index to be a pivot
        } else if (pivotType == FIRST_ELEMENT) {
            return lowerBound;
        } else if (pivotType == MEDIAN_OF_RANDOM) {

            int randA = getRandNumRange(lowerBound, upperBound);
            int randB = getRandNumRange(lowerBound, upperBound);
            int randC = getRandNumRange(lowerBound, upperBound);

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
            int indexA = lowerBound;
            int indexB = ((upperBound - lowerBound) / 2) + lowerBound;
            int indexC = upperBound - 1;

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

    private void swapReferences(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    private int getRandomNum() { return (new Random().nextInt()); }

    private int getRandNumUpper(int upperBound) { return (new Random().nextInt(upperBound)); }

    private int getRandNumRange(int lowerBound, int upperBound) { return (lowerBound + (new Random().nextInt(upperBound - lowerBound))); }

    // generate array with random values of specified size
    private void generateRandomArray(int size) {
        this.array = new int[size];
        this.origArray = new int[size];
        for (int i = 0; i < size; i++) {
            this.array[i] = getRandomNum();
            this.origArray[i] = Integer.valueOf(this.array[i]);
        }
    }

    private void generateRandomArray(int size, int limit) {
        this.array = new int[size];
        this.origArray = new int[size];
        for (int i = 0; i < size; i++) {
            this.array[i] = getRandNumUpper(limit);
            this.origArray[i] = Integer.valueOf(this.array[i]);
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

    public void printOrigArray() {
        StringBuilder bldr = new StringBuilder();
        for (int i = 0; i < this.origArray.length; i++) {
            bldr.append(this.origArray[i]).append(" ");
        }
        bldr.deleteCharAt(bldr.length() - 1);
        System.out.println(bldr.toString());
    }

    public String toString() {
        StringBuilder bldr = new StringBuilder();
        for (int i = 0; i < this.array.length; i++) {
            bldr.append(this.array[i]).append("\n");
        }
        bldr.deleteCharAt(bldr.length() - 1);
        return bldr.toString();
    }

}