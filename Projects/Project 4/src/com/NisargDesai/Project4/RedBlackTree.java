// package com.NisargDesai.Project4;

import static java.lang.System.out;

public class RedBlackTree<E extends Comparable<E>> {

    static final boolean RED = false;
    static final boolean BLACK = true;

    Integer integer = 3;

    private static final int NONE = 0;
    private static final int RECOLOR = 1;
    private static final int ROTATE = 2;

    private static final int LEFT_LEFT = 10;
    private static final int LEFT_RIGHT = 11;
    private static final int RIGHT_RIGHT = 12;
    private static final int RIGHT_LEFT = 13;

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    Node<E> root;

    public RedBlackTree() { this.root = null; }

    public boolean insert(E element) throws NullPointerException {

        if (element == null) {
            throw new NullPointerException("The element you want to insert is null. Try again.");
        }

        Node<E> nodeToInsert = new Node<>();
        nodeToInsert.color = RED;
        nodeToInsert.element = element;

        if (root == null) {
            nodeToInsert.direction = null;
            nodeToInsert.color = BLACK;
            root = nodeToInsert;
            // out.println("Inserted " + element + " Color: " + ((nodeToInsert.color)?"Black":"Red"));
            return true;
        }

        if (element == root.element) return false;

        // 1. Find right place to insert
        // 2. Insert
        // 3. Recolor if needed

        Node<E> iterator = this.root;
        int compareResult;

        while (iterator != null) {
            compareResult = nodeToInsert.compareTo(iterator.element);

            if (compareResult == -1) {
                if (iterator.leftChild == null) {
                    nodeToInsert.parent = iterator;
                    nodeToInsert.direction = Node.LEFT;
                    iterator.leftChild = nodeToInsert;
                    // out.println("Inserted " + element + " Color: " + ((nodeToInsert.color)?"Black":"Red"));

                    out.println(this);

                    rebalance(nodeToInsert);

                    return true;
                }
                iterator = iterator.leftChild;

            } else if (compareResult == 1) {
                if (iterator.rightChild == null) {
                    nodeToInsert.parent = iterator;
                    nodeToInsert.direction = Node.RIGHT;
                    iterator.rightChild = nodeToInsert;
                    // out.println("Inserted " + element + " Color: " + ((nodeToInsert.color)?"Black":"Red"));

                    out.println(this);

                    rebalance(nodeToInsert);

                    return true;
                }
                iterator = iterator.rightChild;

            } else if (compareResult == 0) {
                out.println(this);
                return false;
            }
        }

        out.println(this);
        return false;
    }

    public boolean contains(E element) {
        throw new UnsupportedOperationException("contains(E) has not been implemented.");
    }

    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        preOrderToString(this.root, strBuilder);
        strBuilder.deleteCharAt(strBuilder.length() - 1);
        return strBuilder.toString();
    }

    private boolean hasUncle(Node<E> n) {
        boolean hasGrandparent = (n.parent.parent != null);
        if (!hasGrandparent) return false;

        Node<E> grandparent = n.parent.parent;
        boolean hasUncle = (grandparent.leftChild != null
                        && grandparent.rightChild != null);

        return hasUncle;
    }

    private int isChangeNeeded(Node<E> current) {

        // child and parent and uncle red = recolor
        // child and parent red = rotation
            // single:
            // double:
        // no need to check if parent exists bc the root will always get set by insert

        boolean isParentRed = !(current.parent.color);  // true if parent is red
        if (!isParentRed) return NONE;  // if parent is black, do nothing

        // if parent is red - is the uncle red too?
        // (code won't come here if parent is black)
        // does the current node have an uncle?
        Node<E> uncle = null;
        if (hasUncle(current)) {
            Node<E> grandparent = current.parent.parent;
            if (current.parent.direction == Node.LEFT) {
                uncle = grandparent.rightChild;
            } else if (current.parent.direction == Node.RIGHT) {
                uncle = grandparent.leftChild;
            }
        } else {            // if no uncle, check for rotation
            return ROTATE;
        }

        boolean isUncleRed = (uncle!=null) && !(uncle.color);

        return (isUncleRed) ? RECOLOR : ROTATE;   // if red, recolor. else rotate

        // throw new UnsupportedOperationException("isChangeNeeded(Node) has not been implemented.");
    }

    private Node<E> executeChanges(int change, Node<E> insertedNode) {
        switch (change) {
            case NONE:
                return null;
            case RECOLOR:
                return recolor(insertedNode);
            case ROTATE:
                rotate(insertedNode);
                return null;
            default:
                return null;
        }
    }

    private void rotate(Node<E> node) {

        Node<E> parent = node.parent;

        // single
        if (node.parent.direction == Node.LEFT && node.direction == Node.LEFT)
            singleRotation(node, LEFT_LEFT);

        if (node.direction == Node.RIGHT && !node.parent.direction == Node.RIGHT)
            singleRotation(node, RIGHT_RIGHT);

        // double
        if (node.parent.direction == Node.LEFT && node.direction == Node.RIGHT)
            doubleRotation(node, LEFT_RIGHT);

        if (node.parent.direction === Node.RIGHT && node.direction == Node.LEFT) {
            doubleRotation(node, RIGHT_LEFT);
        }

        // throw new UnsupportedOperationException("rotate() has not been implemented.");
    }

    private Node<E> recolor(Node<E> current) {

//            Recolor Steps:
//
//            Node to insert = child
//            1. Insert child normally
//            2. If child is root, change color to Black
//            3. If child's uncle is Red
//                a. Change parent and uncle to Black
//                b. Change grandparent to Red
//                c. Iterate child to grandparent (child = child.grandparent)
//
//            Steps 2 and 3 are repeated by the rebalance() method.
//            It keeps iterating to find changes until everything is fine.
        Node<E> grandparent = current.parent.parent;

        if (grandparent.leftChild != null) {
            grandparent.leftChild.color = BLACK;
        }

        if (grandparent.rightChild != null) {
            grandparent.rightChild.color = BLACK;
        }

        if (grandparent == this.root) {
            grandparent.color = BLACK;
            return null;
        }

        grandparent.color = RED;
        current = grandparent;

        return current;
        // throw new UnsupportedOperationException("recolor() has not been implemented.");
    }

    private void rebalance(Node<E> insertedNode) {

        int changeNeeded = isChangeNeeded(insertedNode);

        if (changeNeeded == RECOLOR) {
            while (changeNeeded != NONE) {
                out.println("RECOLORING: " + insertedNode);
                insertedNode = executeChanges(changeNeeded, insertedNode);
                changeNeeded = (insertedNode != null) ? isChangeNeeded(insertedNode) : NONE;
            }
            out.println(this);
            return;
        }

        if (changeNeeded == ROTATE) {
            executeChanges(changeNeeded, insertedNode);
            return;
        }

        // throw new UnsupportedOperationException("rebalance() has not been implemented.");
    }

    private void singleRotation(Node<E> node, int rotationCase) {

        if (rotationCase == LEFT_LEFT) {

            Node<E> parent = node.parent;
            Node<E> grandparent = parent.parent;

            parent.color = !parent.color;
            grandparent.color = !grandparent.color;

            parent.parent = null;
            grandparent.parent = parent;
            grandparent.leftChild = null;
            parent.rightChild = grandparent;
        }

        if (rotationCase == RIGHT_RIGHT) {

            Node<E> parent = node.parent;
            Node<E> grandparent = parent.parent;

            parent.color = !parent.color;
            grandparent.color = !grandparent.color;

            parent.parent = null;
            grandparent.parent = parent;
            grandparent.rightChild = null;
            parent.leftChild = grandparent;
        }

    }

    private void doubleRotation(Node<E> node, int rotationCase) {



        throw new UnsupportedOperationException("doubleRotation() has not been implemented.");
    }

    private String preOrderToString(Node<E> root, StringBuilder strBuilder) {
        if (root == null) {					// if root is null, append a "" and return to start going back up the recursion
            strBuilder.append("");
            return strBuilder.toString();
        }

//        if (root.deleted) {						// if it's deleted then append the root and a * in front of it
//            strBuilder.append("*");
//            strBuilder.append(root.toString());
//            strBuilder.append(" ");
//        } else {
        strBuilder.append(((root.color)?ANSI_BLACK:ANSI_RED) + root.toString() + ANSI_RESET); /*+ ((root.color)?"Black":"Red")*/	// if not, only append the root
        strBuilder.append(" ");

        preOrderToString(root.leftChild, strBuilder);		// go through the left tree first
        preOrderToString(root.rightChild, strBuilder);	    // go through the right tree after
        // makes it pre-order
        return strBuilder.toString();	// return
    }

    private class Node<E extends Comparable<E>> implements Comparable<E> {

        static final boolean LEFT = false;
        static final boolean RIGHT = true;

        E element;
        Node<E> leftChild;
        Node<E> rightChild;
        Node parent;
        boolean color;
        Boolean direction;

        public Node() {  }

        @Override
        public int compareTo(E o) {
            return this.element.compareTo(o);
        }

        public String toString() {
            return element.toString();
            // return "";
        }

    }
}



/*
    Try recolor first
    then try rotation
 */