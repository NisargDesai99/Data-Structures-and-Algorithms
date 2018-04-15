import java.util.Random;

public class Quicksort {
    
    public Quicksort() {

    }

    public Quicksort(int size) {

    }

    public Quicksort(int[] array) {

    }

    public void sort(int pivotType) {
        // call recursive quicksort
        quicksort(this.array, 0, this.array.length, pivotType);
    }
}