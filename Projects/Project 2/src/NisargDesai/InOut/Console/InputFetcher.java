/*
Created by Nisarg Desai.

This class allows a user to create an instance of it and use it to get user input from the console.
It uses the Validator class to validate the user input, and it keeps looping until the user enters valid input.
*/

package NisargDesai.InOut.Console;

import java.util.Scanner;
import static java.lang.System.out;

public class InputFetcher {

    private Scanner scanner = new Scanner(System.in);
    private Validator validator = new Validator();

    public InputFetcher() {}

    public long fetchPosLong(String prompt) {
        boolean isValid = false;
        long input = 0;
        while (!isValid) {
            try {
                out.print(prompt);
                input = scanner.nextLong();
                scanner.nextLine();
                if (validator.isPositive(input)) isValid = true;
                else out.println("Please enter a positive number.");
            } catch (Exception ex) {
                scanner.nextLine();
                out.println("Please enter an long.");
            }
        }
        return input;
    }

    public long fetchNegLong(String prompt) {
        boolean isValid = false;
        long input = 0;
        while (!isValid) {
            try {
                out.print(prompt);
                input = scanner.nextLong();
                scanner.nextLine();
                if (validator.isNegative(input)) isValid = true;
                else out.println("Please enter a negative number.");
            } catch (Exception ex) {
                scanner.nextLine();
                out.println("Please enter an long.");
            }
        }
        return input;
    }

    public int fetchPosInteger(String prompt) {
        boolean isValid = false;
        int input = 0;
        while (!isValid) {
            try {
                out.print(prompt);
                input = scanner.nextInt();
                scanner.nextLine();
                if (validator.isPositive(input)) isValid = true;
                else out.println("Please enter a positive number.");

            } catch (Exception ex) {
                scanner.nextLine();
                out.println("Please enter an integer. ");
            }
        }
        return input;
    }

    public int fetchNegInteger(String prompt) {
        boolean isValid = false;
        int input = 0;
        while (!isValid) {
            try {
                out.print(prompt);
                input = scanner.nextInt();
                scanner.nextLine();
                if (validator.isNegative(input)) isValid = true;
                else out.println("Please enter a negative number.");

            } catch (Exception ex) {
                scanner.nextLine();
                out.println("Please enter an integer. ");
            }
        }
        return input;
    }

    public double fetchPosDouble(String prompt) {
        boolean isValid = false;
        double input = 0.0;
        while (!isValid) {
            try {
                out.print(prompt);
                input = scanner.nextDouble();
                scanner.nextLine();
                if (validator.isPositive(input)) isValid = true;
                else out.println("Please enter a positive number.");
            } catch (Exception ex) {
                scanner.nextLine();
                out.println("Please enter a double.");
            }
        }
        return input;
    }

    public double fetchNegDouble(String prompt) {
        boolean isValid = false;
        double input = 0.0;
        while (!isValid) {
            try {
                out.print(prompt);
                input = scanner.nextDouble();
                scanner.nextLine();
                if (validator.isNegative(input)) isValid = true;
                else out.println("Please enter a negative number.");
            } catch (Exception ex) {
                scanner.nextLine();
                out.println("Please enter a double.");
            }
        }
        return input;
    }

    public float fetchPosFloat(String prompt) {
        boolean isValid = false;
        float input = 0;
        while (!isValid) {
            try {
                out.print(prompt);
                input = scanner.nextFloat();
                scanner.nextLine();
                if (validator.isPositive(input)) isValid = true;
                else out.println("Please enter a positive number.");
            } catch (Exception ex) {
                scanner.nextLine();
                out.println("Please enter a float.");
            }
        }
        return input;
    }

    public float fetchNegFloat(String prompt) {
        boolean isValid = false;
        float input = 0;
        while (!isValid) {
            try {
                out.print(prompt);
                input = scanner.nextFloat();
                scanner.nextLine();
                if (validator.isNegative(input)) isValid = true;
                else out.println("Please enter a negative number.");
            } catch (Exception ex) {
                scanner.nextLine();
                out.println("Please enter a double.");
            }
        }
        return input;
    }

    public String fetchString(String prompt) {
        boolean isValid = false;
        String input = "z";

        while (!isValid) {
            out.print(prompt);
            input = scanner.nextLine();
            if (validator.isNonEmptyString(input)) {
                isValid = true;
            }
        }

        return input;
    }

}
