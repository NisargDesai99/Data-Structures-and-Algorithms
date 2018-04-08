import java.util.Random;
import java.util.Scanner;
import static java.lang.System.out;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Random rand = new Random();

		out.print("Enter the size: ");
		int size = scanner.nextInt();
		// int[] arr = new int[size];

		int[] arr = new int[] { 3,1,4,5,2 };
		printArray(arr);

		// for (int i = 0; i < size; i++) {
		// 	arr[i] = 0 + rand.nextInt(Integer.MAX_VALUE);
		// }

		quickSort(arr, 0, arr.length - 1);
		printArray(arr);
	}

	public static String printArray(int[] array) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i : array) {
			stringBuilder.append(i).append(" ");
		}
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
		// out.println(stringBuilder);
		return stringBuilder.toString();
	}

	public static int randomPivot(Random rand) {
		return rand.nextInt(Integer.MAX_VALUE);
	}

	public static int partition(int[] arr, int lowIndex, int highIndex) {

		int pivot = arr.length - 1;		// set pivot to last element
		out.println("Pivot = " + arr[pivot]);

		int i = lowIndex - 1;					// set beginning index
		out.println("i = " + i);

		for (int j = lowIndex; j < highIndex; j++) {	// move elements...
			if (arr[j] < arr[pivot]) {
				i = i + 1;
				swap(arr, i, j);
			}
		}

		swap(arr, i+1, highIndex);

		return i + 1;					// return the partition index value
	}

	public static void quickSort(int[] arr, int left, int right) {

		out.println("quickSort(" + printArray(arr) + ", " + left + ", " + right + ")");
		int partitionIndex = partition(arr, left, right);
		out.println("Partition Index = " + partitionIndex);

		quickSort(arr, left, partitionIndex - 1);
		quickSort(arr, partitionIndex + 1, right);
	}

	public static void swap(int[] arr, int posOne, int posTwo) {
		int temp = arr[posOne];
		arr[posOne] = arr[posTwo];
		arr[posTwo] = temp;
	}

}