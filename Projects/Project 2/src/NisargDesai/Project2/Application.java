package NisargDesai.Project2;

import NisargDesai.InOut.Console.InputFetcher;
import static java.lang.System.out;

public class Application {

    InputFetcher fetcher = new InputFetcher();
    LinkedList<Magazine> magazineList = new LinkedList<>();

    public static String getMenu() {
        return
            "Choose one of the following:\n" +
            "1. Make empty\n" +
            "2. Find ID\n" +
            "3. Insert at Front\n" +
            "4. Delete from Front\n" +
            "5. Delete ID\n" +
            "6. Print all Records\n" +
            "7. Exit\n";
    }

    public void start() {
        boolean isExit = false;
        int input = 0;

        // Get user input and loop until they either exit or until they enter a valid input
        //      if the input is valid, then it calls different methods to perform certain tasks
        while (!isExit) {
            input = fetcher.fetchPosInteger(getMenu());

            switch (input) {
                case 1:
                    magazineList.clear();
                    break;
                case 2:
                    findMag();
                    break;
                case 3:
                    insertMag();
                    break;
                case 4:
                    deleteMag(4);
                    break;
                case 5:
                    deleteMag(5);
                    break;
                case 6:
                    magazineList.printAll();
                    break;
                case 7:
                    System.exit(10);
                default:
                    out.println("Invalid input. Enter a number in the range 1-7.");
                    break;
            }   
        }
    }

    private void findMag() {
        // Get user input
        int id = fetcher.fetchPosInteger("Enter the ID of the magazine you would like to find: ");

        // Validate and find
        Magazine mag = magazineList.findItemByID(id);

        // print it out
        if (mag != null) mag.printID();
        else out.println("That ID is not in the list.");
    }

    private void insertMag() {
        out.println("You chose to insert a new magazine.");

        int id = fetcher.fetchPosInteger("Enter the ID: ");

        // Checks validity of the ID before the user is asked to enter anything else
        //      Could put this first part in a loop so that they are asked to enter an ID until they enter a valid one
        if (!magazineList.isValidID(id)) {
            out.println("That ID is already in the list.");
            return;
        }

        String name = fetcher.fetchString("Enter the name of the magazine: ");
        String pubName = fetcher.fetchString("Enter the name of the publisher: ");

        magazineList.insertFront(new Magazine(id, name, pubName));
    }

    private void deleteMag(int choice) {
        Magazine mag = null;

        // if choice from main menu was 4 then delete from front
        //      otherwise ask for the id to delete
        if (choice == 4) {
            if (magazineList.size() == 0) {
                out.println("The list is already empty. Nothing to delete");
                return;
            } else {
                mag = magazineList.deleteFront();
            }
        } else if (choice == 5) {
            if (magazineList.size() == 0) {
                out.println("The list is already empty. Nothing to delete");
                return;
            }
            else {
                int id = fetcher.fetchPosInteger("Enter the ID of the magazine you would like to delete: ");
                mag = magazineList.delete(id);
            }
        }
        if (mag != null) mag.printID();
    }

}