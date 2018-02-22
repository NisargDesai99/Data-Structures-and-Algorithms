public class DoubleNode {
    
    int data;
    DoubleNode next;
    DoubleNode prev;

    public DoubleNode() {  }

    public DoubleNode(int data) {
        this.data = data;
    }

    public DoubleNode(int data, DoubleNode prev, DoubleNode next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public String toString() {
        return String.valueOf(this.data);
    }

}