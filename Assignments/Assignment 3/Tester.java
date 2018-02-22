
import static java.lang.System.out;


public class Tester {
    public static void main(String[] args) {

        // TEST: remove in a doubly linked
        

        // TEST: Remove Duplicates
        // SinglyLinked list = new SinglyLinked();
        // for (int i = 0; i < 10; i++) {
        //     list.add(i);
        // }
        // list.add(10);
        // list.add(10);
        // list.add(10);
        // printList(list);
        // SinglyLinked newList = list.removeDuplicates(list.getHead());
        // printList(newList);

        /*
        SinglyLinked singlyLinkedList = new SinglyLinked();
        for (int i = 0; i < 10; i++) {
            singlyLinkedList.add(i);
        }
        out.println("Singly Linked List: ");
        out.println("Before Swap: ");
        printList(singlyLinkedList);
        out.println();
        singlyLinkedList.swap(singlyLinkedList.get(0), singlyLinkedList.get(1));
        out.println("After Swap (0,1): ");
        printList(singlyLinkedList);
        out.println();
        singlyLinkedList.swap(singlyLinkedList.get(5), singlyLinkedList.get(6));
        out.println("After Swap (5,6): ");
        printList(singlyLinkedList);
        out.println();
        singlyLinkedList.swap(singlyLinkedList.get(8), singlyLinkedList.get(9));
        out.println("After Swap (8,9): ");
        printList(singlyLinkedList);
        out.println();

        out.println("----------------------------------------");
        out.println();

        DoublyLinked doublyLinkedList = new DoublyLinked();
        for (int i = 0; i < 10; i++) {
            doublyLinkedList.add(i);
        }
        out.println("Doubly Linked List: ");
        out.println("Before Swap: ");
        printList(doublyLinkedList);
        out.println();
        doublyLinkedList.swap(doublyLinkedList.get(0), doublyLinkedList.get(1));
        out.println("After Swap (0,1): ");
        printList(doublyLinkedList);
        out.println();
        doublyLinkedList.swap(doublyLinkedList.get(6), doublyLinkedList.get(5));
        out.println("After Swap (6,5): ");
        printList(doublyLinkedList);
        out.println();
        doublyLinkedList.swap(doublyLinkedList.get(8), doublyLinkedList.get(9));
        out.println("After Swap (8,9): ");
        printList(doublyLinkedList);
        out.println();

        SinglyLinked singleEmpty = new SinglyLinked();
        out.println("Test singly linked swap with empty list");
        singleEmpty.swap(singlyLinkedList.get(5), singlyLinkedList.get(99));

        DoublyLinked doubleEmpty = new DoublyLinked();
        out.println("Test doubly linked swap with empty list");
        singleEmpty.swap(singlyLinkedList.get(5), singlyLinkedList.get(99));
        */
    }


    public static void printList(SinglyLinked list) {
        for (int i = 0; i < 10; i++) {
            out.println("Index: " + i + "  Data: " + list.get(i));
        }
    }

    public static void printList(DoublyLinked list) {
        for (int i = 0; i < 10; i++) {
            out.println("Index: " + i + "  Data: " + list.get(i));
        }
    }

}