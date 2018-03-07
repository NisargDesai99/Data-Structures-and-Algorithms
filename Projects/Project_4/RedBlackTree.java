public class RedBlackTree<E extends Comparable<E>> {
    
    static boolean RED = false;
    static boolean BLUE = true;

    Node<E> root;

    public RedBlackTree() {

    }

    public boolean insert() throws NullPointerException {

        return false;
    }

    public boolean contains() {
        
        return false;
    }

    public String toString() {

        return "";
    }

    private void rebalance() {

    }

    private class Node<E extends Comparable<E>> {

        E element;
        Node<E> leftChild;
        Node<E> rightChild;
        Node parent;
        boolean color;

        public Node() {

        }

        public String toString() {
            
            return "";
        }

    }
}