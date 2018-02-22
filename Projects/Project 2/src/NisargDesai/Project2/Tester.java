package NisargDesai.Project2;

import static java.lang.System.out;

public class Tester {
    public static void main(String[] args) {

        // Test getMenu()
        out.println(Application.getMenu());
        LinkedList<Magazine> magList = new LinkedList<>();
        Magazine mag;

        /* Test insertFront() and isValidID() */
        out.println("--------------------");
        out.println("Test valid ID function.");
        magList.insertFront(new Magazine(10, "Vogue Paris", "Conde Nast"));
        magList.insertFront(new Magazine(10, "Vogue US", "Conde Nast"));
        magList.printAll();
        out.println("Size: " + magList.size());
        out.println("--------------------\n");

        out.println("--------------------");
        out.println("Test clear function.");
        magList.clear();
        magList.printAll();
        out.println("Size: " + magList.size());
        out.println("--------------------\n");

        out.println("--------------------");
        out.println("Test insert after a clear.");
        magList.insertFront(new Magazine(10, "Vogue Paris", "Conde Nast"));
        magList.insertFront(new Magazine(15, "Vogue US", "Conde Nast"));
        magList.insertFront(new Magazine(5, "TIME", "Nisarg Desai"));
        magList.printAll();
        out.println("Size: " + magList.size());
        out.println("--------------------\n");

        out.println("--------------------");
        out.println("Test delete from front.");
        mag = magList.deleteFront();
        out.println("Deleted Mag: \n" + mag);
        out.println("----");
        magList.printAll();
        out.println("Size: " + magList.size());
        out.println("--------------------\n");

        out.println("--------------------");
        out.println("Test delete from front with empty list.");
        LinkedList<Magazine> emptyList = new LinkedList<>();
        mag = emptyList.deleteFront();
        out.println("--------------------\n");

        out.println("--------------------");
        out.println("Test delete ID.");
        Magazine deletedMag = magList.delete(10);
        out.println("Deleted Mag: \n" + deletedMag);
        out.println("----");
        magList.printAll();
        out.println("Size: " + magList.size());
        out.println("----");
        deletedMag = magList.delete(15);
        out.println("Deleted Mag: \n" + deletedMag);
        out.println("----");
        magList.printAll();
        out.println("Size: " + magList.size());
        out.println("--------------------\n");

    }
}