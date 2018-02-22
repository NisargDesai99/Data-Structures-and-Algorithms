package NisargDesai.Project2;

public class LinkedList<E extends IDedObject> {

    private Node<E> head;
    private int size = 0;

    public LinkedList() {  }

    public void clear() {

        if (head == null) {
            System.out.println("The list is already empty. Nothing to clear.");
        }

        head = null;
        size = 0;
        System.out.println("The list is now empty.");
    }

    // Loops through the list and finds the item by its ID
    public E findItemByID(int id) {
        Node<E> iteratorNode = this.head;
        while (iteratorNode != null) {
            if (iteratorNode.item.getID() == id) {
                return iteratorNode.item;
            }
            iteratorNode = iteratorNode.next;
        }

        return null;
    }

    private Node<E> findNodeByID(int id) {
        Node<E> iteratorNode = this.head;

        while (iteratorNode != null) {
            if (iteratorNode.item.getID() == id) {
                return iteratorNode;
            }
            iteratorNode = iteratorNode.next;
        }

        return null;
    }

    public boolean isValidID(int id) {
        if (findItemByID(id) == null) {
            return true;
        }
        return false;
    }

    public boolean insertFront(E item) {
        /*if (!isValidID(item.getID())) {
            System.out.println("That ID already exists");
            return false;
        }*/

        if (size == 0 && head == null) {
            head = new Node<>(item);
            head.next = null;
            this.size++;
            return true;
        } else {
            Node<E> newNode = new Node<>(item);
            newNode.next = head;
            head = newNode;
            this.size++;
            return true;
        }
    }

    public E deleteFront() {
        if (head == null) {
            System.out.println("The list is already empty. Nothing to delete.");
            return null;
        }

        Node<E> node = head;
        head = head.next;
        size--;
        return node.item;
    }

    public E delete(int id) {
        if (head == null) {
            System.out.println("The list is already empty. Nothing to delete.");
            return null;
        }

        if (this.head.item.getID() == id) {
            E itemToReturn = this.head.item;
            if (this.head.next == null) {
                this.head = null;
            } else {
                this.head = this.head.next;
            }
            size--;
            return itemToReturn;
        }

        Node<E> node = this.head.next;
        Node<E> prev = this.head;

        while (node != null) {
            if (node.item.getID() == id) {
                prev.next = node.next;
                size--;
                return node.item;
            }
            prev = node;
            node = node.next;
        }

        System.out.println("That ID does not exist in the list.");
        return null;
    }

    public int size() {
        return this.size;
    }

    public void printAll() {

        // If head is null say list is empty
        if (this.head == null) {
            System.out.println("The list is empty.");
            return;
        }

        Node<E> iteratorNode = head;
        while (iteratorNode != null) {
            iteratorNode.item.printID();
            iteratorNode = iteratorNode.next;
        }
    }

    private class Node<E extends IDedObject> {
        E item;
        Node<E> next;

        public Node() {  }

        public Node(E item) {
            this.item = item;
        }
    }

}