public class SingleNode {
    
    int data;
    SingleNode next;
    
    public SingleNode() {  }

    public SingleNode(int data) {
        this.data = data;
    }

    public SingleNode(int data, SingleNode next) {
        this.next = next;
    }

    public String toString() {
        return String.valueOf(this.data);
    }
}