package com.NisargDesai.Project4;

import static java.lang.System.out;

public class RedBlackTree<E extends Comparable<E>> {

    static final boolean RED = false;
    static final boolean BLACK = true;

    private static final int NONE = 0;
    private static final int SINGLE = 1;
    private static final int DOUBLE = 2;
    private static final int RECOLOR = 3;
    private static final int ROTATE = 4;

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
            out.println("Inserted " + element + " Color: " + ((nodeToInsert.color)?"Black":"Red"));
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
                    out.println("Inserted " + element + " Color: " + ((nodeToInsert.color)?"Black":"Red"));

                    rebalance(nodeToInsert);

                    return true;
                }
                iterator = iterator.leftChild;
            } else if (compareResult == 1) {
                if (iterator.rightChild == null) {
                    nodeToInsert.parent = iterator;
                    nodeToInsert.direction = Node.RIGHT;
                    iterator.rightChild = nodeToInsert;
                    out.println("Inserted " + element + " Color: " + ((nodeToInsert.color)?"Black":"Red"));

                    rebalance(nodeToInsert);

                    return true;
                }
                iterator = iterator.rightChild;
            } else if (compareResult == 0) {
                return false;
            }
        }

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
        boolean hasUncle = hasUncle(current);
        Node<E> uncle = null;
        if (hasUncle) {
            Node<E> grandparent = current.parent.parent;
            if (current.parent.direction == Node.LEFT) {
                uncle = grandparent.rightChild;
            } else if (current.parent.direction == Node.RIGHT) {
                uncle = grandparent.leftChild;
            }
        } else {    // if no uncle, rotate
            return ROTATE;
        }

        boolean isUncleRed = (uncle!=null) && !(uncle.color);

        return (isUncleRed) ? RECOLOR : ROTATE;   // if red, recolor. else rotate

        // throw new UnsupportedOperationException("isChangeNeeded(Node) has not been implemented.");
    }

    private void executeChanges(int change, Node<E> current) {
        switch (change) {
            case NONE:
                return;
            case RECOLOR:
                recolor(current);
                break;
            case ROTATE:
                rotate();
                break;
            default:
                return;
        }
    }

    private void rotate() {
        throw new UnsupportedOperationException("rotate() has not been implemented.");
    }

    private void recolor(Node<E> current) {

        if (current == this.root) {
            current.color = BLACK;
        }

        Node<E> parent = current.parent;

        if (parent.parent == null) {    // means its a root
            Node<E> grandparent = parent.parent;
            grandparent.leftChild.color = !(grandparent.leftChild.color);
            grandparent.rightChild.color = !(grandparent.rightChild.color);
        }

        while (parent != null) {
            if (parent == this.root) {
                return;
            }
            parent.color = !parent.color;
            parent = parent.parent;
        }

        // throw new UnsupportedOperationException("recolor() has not been implemented.");
    }

    private void rebalance(Node<E> insertedNode) {

        int changeNeeded = isChangeNeeded(insertedNode);

        while (changeNeeded != NONE) {
            executeChanges(changeNeeded, insertedNode);
            changeNeeded = isChangeNeeded(insertedNode);
        }

        // throw new UnsupportedOperationException("rebalance() has not been implemented.");
    }

    private void singleRotation() {
        throw new UnsupportedOperationException("singleRotation() has not been implemented.");
    }

    private void doubleRotation() {
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
        strBuilder.append(root.toString() + ((root.color)?"Black":"Red"));	// if not, only append the root
        strBuilder.append(" ");
//        }

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
            return o.compareTo(this.element);
        }

        public String toString() {
            return element.toString();
            // return "";
        }

    }
}