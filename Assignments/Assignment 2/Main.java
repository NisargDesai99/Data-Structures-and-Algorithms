import java.lang.System;
import static java.lang.System.out;

public class Main {
    public static void main (String[] args) {
        // int[] arr = new int[] {4, 5, 6, 7, 8, 9, 10, 2};
        // fun(arr.length, arr);

        // Does there exist an integer i such that array[i] = i;
        // int[] array = new int[]{-5,-4,1,2,3,5,9,10,11};
        // out.println("Length: " + array.length);
        // out.println(binaryChecker(array, array.length));

        // Max subsequence sum
        // int[] array = new int[]{10,-5,5,7,8,-15};
        // out.println("Max sum: " + maxSubSum(array));

        // int[] array = new int[]{10,-5,5,7,8,-10,-2,-3,2,-40};
        // out.println("Min sum: " + minSubSum(array));

        out.println("Sum = " + assignNumThree(5));
    }

    static int assignNumThree(int n) {
        int sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i*i; j++) {
                for (int k = 0; k < j; k++) {
                    sum++;
                }
            }
        }

        return sum;
    }

    static int maxSubSumPPT(int[] array) {
        int max = 0, current = 0;

        for (int i = 0; i < array.length; i++) {
            current += array[i];

            if (current > max) {
                max = current;
            } else if (current < 0) {
                current = 0;
            }
        }

        return max;
    }

    static boolean binaryChecker(int[] array, int size) {
        int first = 0;
        int last = size - 1;
        int middle;

        while (first <= last) {
            middle = (first + last)/2;
            //out.println("Middle: " + middle);
            if (middle == array[middle]) {
                return true;
            }
            if (array[middle] < middle) {
                first = middle + 1;
            } else {    //array[middle] > middle
                last = middle - 1;
            }
        }

        return false;
    }

    static int maxSubSum(int[] array) {
        int current = 0, max = 0;

        for (int i = 0; i < array.length; i++) {
            out.println("Current: " + current);

            if (array[i] < 0) {
                current = 0;
                continue;
            } else {
                current += array[i];
                if (current > max) {
                    max = current;
                }
            }
        }

        return max;
    }

    static int minSubSum(int[] array) {

        int current = 0, min = 0;

        for (int i = 0; i < array.length; i++) {
            out.println("Current: " + current);

            current += array[i];
            
            if (current < min) {
                min = current;
            } else if (current > 0) {
                current = 0;
            }


            // if (array[i] > 0) {
            //     current = 0;
            //     continue;
            // } else {
            //     current += array[i];
            //     if (current < min) {
            //         min = current;
            //     }
            // }
        }

        return min;
    }

    /*
    static void fun(int n, int arr[]) {
        int i = 0, j = 0;
        int iteration = 1;
        for (; i < n; ++i) {
            out.println("ITERATION: " + iteration);
            //out.println("Value of I: " + i);
            out.println("arr[i] = " + arr[i]);
            out.println("arr[j] = " + arr[j]);
            while (j < n && (arr[i] < arr[j])) {
                out.println("   Value of J: " + j);
                out.println("   arr[j] = " + arr[j]);
                j++;
                out.println("   Value of J: " + j);
            }
            iteration += 1;
        }
    }
    */
}