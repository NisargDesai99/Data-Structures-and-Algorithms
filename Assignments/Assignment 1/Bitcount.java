import java.util.Scanner;
import java.lang.System;
import static java.lang.System.out;


public class Bitcount {
    public static void main(String[] args) {
        int input = getIntegerInput();
        int count = 0;

        while (input > 0) {
            int boolVal = input & 1;
            if (boolVal == 1)
                count++;
            input = input >> 1;
        }

        out.println("Count: " + count);
    }

    public static int getIntegerInput() {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false;
        int input = -1;

        while (!isValid) {
            try {
                out.println("Enter a number (Enter -1 to quit): ");
                input = scanner.nextInt();
                if (input == -1) {
                    out.println("You chose to quit the program.");
                } else {
                    isValid = true;
                }
            } catch (Exception ex) {
                out.println("Please enter an integer.");
                scanner.nextLine();
            }
        }

        scanner.close();
        return input;
    }



}