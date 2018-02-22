public class CircularList {
    
    Person head;
    Person tail; 
    Person current;
    int size;

    public CircularList() {  }

    public boolean isEmpty() {
        return head == null;
    }

    public void step() {
        current = current.next;
    }

    public Person getHead() {
        return this.head;
    }

    public Person getTail() {
        return this.tail;
    }

    public int size() {
        return this.size;
    }

    public void add(int data) {
        Person person = new Person(data);

        if (head == null) {
            head = person;
            current = head;
        } else {
            current.next = person;
        }

        person.next = head;
        tail = person;
        step();
        size++;
    }

    public void delete(int data) {
        Person prev = head;
        Person curr = head.next;

        if (head.data == data) {        // if head is the node to delete, unlink it and return
            tail.next = head.next;
            head = head.next;
            size -= 1;
            return;
        }

        while (curr.data != data) {     // otherwise loop through and find the data to unlink
            prev = curr;
            curr = curr.next;
        }

        if (size == 1) {                // if size == 1, null the head and decrement size
            head = null;
            size = 0;
            return;
        } else {                        // else, unlink the curr node and decrement size
            prev.next = curr.next;
            size -= 1;
            return;
        }
    }

    public void displayList() {
        int counter = 0;
        Person printer = head;

        while (counter < size) {
            System.out.print(printer);
            printer = printer.next;
            counter++;
        }
        System.out.println("");
    }

}