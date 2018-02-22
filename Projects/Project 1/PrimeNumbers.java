/*
Name:           Nisarg Desai
Class:          Data Structures and Algorithms
Section:        3345.004
Semester:       Spring 2018
Project No.:    1
Description:    Given an upper bound by the user, this program uses the Sieve of Eratosthenes
                to find all prime numbers in the range [2, limit).
                Prints a list of all the prime numbers found in that range.

                For TESTING purposes: Prints the x = ( (i^2) + (j*i) ) calculations for each j of each i
*/

import java.lang.System;
import java.util.Scanner;
import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import static java.lang.System.out;


public class PrimeNumbers {
    public static void main(String[] args) {

        int limit = getLimit();
        out.println("Limit: " + limit);

        List<Boolean> numbersList = new ArrayList<Boolean>(Arrays.asList(new Boolean[limit - 2]));
        Collections.fill(numbersList, Boolean.TRUE);
        findPrimes(numbersList, limit);

        //Print all the prime numbers
        printPrimesBool(limit, numbersList);
    }

    //Find all primes in the range [2, limit)
    public static void findPrimes(List<Boolean> list, int limit) {
        for (int i = 2; i <= Math.floor(Math.sqrt(limit)); i++) {
            boolean val = list.get(i - 2);
            if (val) {
                int j = 0;
                int x = (i * i) + (j * i);
                while (x < limit) {
                    list.set(x - 2, false);
                    j += 1;
                    x = (i * i) + (j * i);
                }
            }
        }
    }

    //Function to print all primes from a list of booleans
    public static void printPrimesBool(int limit, List<Boolean> boolList) {
        out.print("All primes in the range [2, " + limit + "): ");
        int counter = 2;
        StringBuilder strBuilder = new StringBuilder();
        for (boolean b : boolList) {
            if (b) {
                strBuilder.append(counter + ", ");
            }
            counter += 1;
        }
        strBuilder.deleteCharAt(strBuilder.length() - 2);
        out.println(strBuilder.toString());
    }

    //Function to print a list of booleans with some details
    public static void printBoolList(List<Boolean> boolList) {
        int counter = 0;
        for (boolean b : boolList) {
            out.println("Index: " + (counter + 2) + "\tBoolean Value: " + b);
            counter++;
        }
    }

    //Get the limit from the user
    public static int getLimit() {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false;
        int input = -2;

        while (!isValid) {
            try {
                out.print("Enter the limit (Enter -1 to quit): ");
                input = scanner.nextInt();
                if (input > 0) {
                    isValid = true;
                } else if (input == -1) {
                    out.println("You chose to exit the program.");
                    System.exit(10);
                } else if (input < 0) {
                    out.println("Please enter a positive integer.");
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


//Old stuff that I tried to do but couldn't figure out
//  so I came up with a new implementation that uses a list of boolean values
//  This made it a little bit easier.

/*public class PrimeNumbers {
    public static void main(String[] args) {
        //LinkedHashSet<Integer> numbersHashSet = new LinkedHashSet<>();
        List<Integer> numbersList = new ArrayList<>();

        int limit = getLimit();

        out.println("Limit: " + limit);
        for (int i = 2; i < limit; i++) {
            //numbersHashSet.add(i);
            numbersList.add(i);
        }

        //printHashSet(numbersHashSet);

        for (int i : numbersList) {
            if (i > Math.sqrt(limit)) { printList(numbersList); System.exit(10); }
            int j = 0;
            int x = (i * i) + (j * i);
            while (x < limit) {
                out.println("i: " + i);
                out.println("j: " + j);
                out.println("j*i: " + x);
                
                if (numbersList.contains(x)) {
                    numbersList.remove(numbersList.indexOf(x));
                }
                
                j += 1;
                x = (i * i) + (j * i);
            }
        }

        /*int size = numbersHashSet.size();
        for (int i : numbersHashSet) {
            if (i > Math.sqrt(limit)) { printHashSet(numbersHashSet); System.exit(10); }
            int j = 0;
            int x = (i * i) + (j * i);
            while (x < limit) {
                out.println("i: " + i);
                out.println("j: " + j);
                out.println("(i^2) + (j*i): " + x);
                if (numbersHashSet.contains(x)) {
                    numbersHashSet.remove(x);
                    printHashSet(numbersHashSet);
                } else {
                    break;
                }
                j += 1;
                x = (i * i) + (j * i);
            }
        }
    }

    static void printHashSet(LinkedHashSet<Integer> arrayList) {
        for (int i : arrayList) {
            out.println(i);
        }
    }

    static void printList(List<Integer> list) {
        for (int i : list) {
            out.println(i);
        }
    }

    static int getLimit() {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false;
        int input = -1;

        while (!isValid) {
            try {
                out.print("Enter the limit: ");
                input = scanner.nextInt();
                if (input > 0) {
                    isValid = true;
                }
            } catch (Exception ex) {
                out.println("Please enter an integer.");
                scanner.nextLine();
            }
        }

        return input;
    }
}*/