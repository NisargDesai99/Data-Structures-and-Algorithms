import static java.lang.System.out;

public class SinglyLinked {

    private SingleNode head = null;
    private SingleNode tail = null;
    private int size = 0;

    public SinglyLinked() {  }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void add(int data) {
        SingleNode newNode = new SingleNode(data);
        //out.println(data);
        if (this.head == null) {
            this.head = newNode;
            this.tail = this.head;
            // out.println(head);
            size += 1;
            return;
        }

        this.tail.next = newNode;
        this.tail = this.tail.next;
        size += 1;
        // out.println(tail);
    }

    private SingleNode getPrev(SingleNode n) {
        SingleNode prev = null, curr = this.head;

        while (curr != null && !curr.equals(n)) {
            prev = curr;
            curr = curr.next;
        }

        return prev;
    }

    public void swap(SingleNode one, SingleNode two) {
        if (this.isEmpty()) {
            out.println("Nothing to swap. The list is empty.");
            return;
        }

        if (one.equals(two) || one.data == two.data) {
            out.println("No need to swap.");
            return;
        }

        SingleNode onePrev = this.getPrev(one);
        
        if (onePrev == null) {
            SingleNode third = two.next;
            one.next = third;
            two.next = one;
            head = two;
            return;
        }

        onePrev.next = two;
        one.next = two.next;
        two.next = one;
    }

    public SingleNode get(int index) {
        SingleNode nodeAtIndex = head;

        int counter = 0;
        while (counter < index && nodeAtIndex != null) {
            nodeAtIndex = nodeAtIndex.next;
            counter++;
        }

        return nodeAtIndex;
    }

    public SingleNode getHead() {
        return this.head;
    }

    public void remove(int data) {
        if (data == head.data) {
            head = head.next;
        } else {
            SingleNode prev = getPrev();
            
        }
    }

    public SingleNode removeDuplicates(SingleNode head) {
        SingleNode iterator = head;
        while (iterator.next != null) {
            if (iterator.data == iterator.next.data) {
                this.remove(iterator.data);
            }
            iterator = iterator.next;
        }
        return head;
    }
}