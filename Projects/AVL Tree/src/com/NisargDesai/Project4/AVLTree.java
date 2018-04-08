package com.NisargDesai.Project4;

public class AVLTree<E extends Comparable<E>> {

    Node root;

    public AVLTree() {};

    public boolean insert(E newItem) {

        insert(newItem, this.root);

        return false;
    }

    private Node insert(E newItem, Node n) {

        if (n == null)
            return new Node(newItem, 0, null, null);

        if (newItem.compareTo(n.element) < 0)
            n.leftChild = insert(newItem, n.leftChild);
        else if (newItem.compareTo(n.element) > 0)
            n.rightChild = insert(newItem, n.rightChild);
        else
            return n;

        n.height = 1 + max(height(n.leftChild), height(n.rightChild));

        int balance = getBalance(n);

        // Left left case
        if ( balance > 1 && (newItem.compareTo(n.leftChild.element) < 0) )
            return rightRotate(n);

        // Right right case
        if ( balance < -1 && (newItem.compareTo(n.rightChild.element) > 0) )
            return leftRotate(n);

        // Left Right case
        if (balance > 1 && (newItem.compareTo(n.leftChild.element) > 0) ) {
            n.leftChild = leftRotate(n.leftChild);
            return rightRotate(n);
        }

        // Right Left case
        if (balance < -1 && (newItem.compareTo(n.rightChild.element) < 0) ) {
            n.rightChild = rightRotate(n.rightChild);
            return leftRotate(n);
        }

        return n;
    }

    private Node leftRotate(Node alpha) {
        return null;
    }

    private Node rightRotate(Node alpha) {
        return null;
    }

    private int getBalance(Node n) {
        if (n == null)
            return 0;

        return height(n.leftChild) - height(n.rightChild);
    }

    private int height(Node n) {
        if (n == null)
            return 0;

        return n.height;
    }

    private int max(int one, int two) {
        return (one < two) ? two : one;
    }

    private class Node {

        E element;
        int height;
        Node leftChild;
        Node rightChild;

        public Node() {  }

        public Node(E element, int height, Node leftChild, Node rightChild) {
            this.element = element;
            this.height = height;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public String toString() {
            return this.element.toString();
        }
    }

}
