import static java.lang.System.out;

public class DoublyLinked {
    
    private DoubleNode head;
    private DoubleNode tail;
    private int size = 0;
    
    public DoublyLinked() {  }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void add(int data) {
        DoubleNode newNode = new DoubleNode(data);
        
        if (this.head == null) {
            newNode.prev = null;
            this.head = newNode;
            this.tail = this.head;
            size += 1;
            return;
        }
        
        newNode.prev = this.tail;
        this.tail.next = newNode;
        this.tail = this.tail.next;
        this.tail.next = null;
        size += 1;
    }

    public DoubleNode get(int index) {
        DoubleNode iterator = head;

        int counter = 0;
        while (counter < index && iterator != null) {
            iterator = iterator.next;
            counter += 1;
        }

        return iterator;
    }

    public void remove(int data) {
        DoubleNode iterator = head;

        while (iterator != null) {
            if (iterator.data == data) {
                DoubleNode prev = iterator.prev;
                DoubleNode next = iterator.next;
                prev.next = next;
                next.prev = prev;
            }
        }
    }

    public void swap(DoubleNode one, DoubleNode two) {
        if (this.isEmpty()) {
            out.println("Nothing to swap. The list is empty.");
            return;
        }

        if (one.equals(two) || one.data == two.data) {
            out.println("No need to swap");
            return;
        }
        
        DoubleNode onePrev = one.prev;

        if (onePrev == null) {
            DoubleNode twoNext = two.next;
            twoNext.prev = one;
            one.next = twoNext;
            two.next = one;
            one.prev = two;
            two.prev = null;
            this.head = two;
            return;
        }

        DoubleNode twoNext = two.next;
        onePrev.next = two;
        two.next = one;
        one.prev = two;
        one.next = twoNext;
        return;
    }

}
