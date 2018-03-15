// package com.NisargDesai.Project4;

import static java.lang.System.out;

public class RedBlackTree<E extends Comparable<E>> {

    static final boolean RED = false;
    static final boolean BLACK = true;

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
    private static final String ANSI_WHITE = "\u001B[37m";
//    private static final String ANSI_GREEN = "\u001B[32m";
//    private static final String ANSI_YELLOW = "\u001B[33m";
//    private static final String ANSI_BLUE = "\u001B[34m";
//    private static final String ANSI_PURPLE = "\u001B[35m";
//    private static final String ANSI_CYAN = "\u001B[36m";

    Node root;

    public RedBlackTree() { this.root = null; }

    public boolean insert(E element) throws NullPointerException {

        if (element == null) {
            throw new NullPointerException("The element you want to insert is null. Try again.");
        }

        Node nodeToInsert = new Node(element);
        nodeToInsert.color = RED;

        if (root == null) {
            nodeToInsert.direction = null;
            nodeToInsert.color = BLACK;
            root = nodeToInsert;
            return true;
        }

        if (element == root.element) return false;

        // 1. Find right place to insert
        // 2. Insert
        // 3. Recolor if needed

        Node iterator = this.root;
        int compareResult;

        while (iterator != null) {
            compareResult = nodeToInsert.compareTo(iterator.element);
            out.println("compare result: " + compareResult);

            if (compareResult == -1) {
                if (iterator.leftChild == null) {
                    nodeToInsert.parent = iterator;
                    nodeToInsert.direction = Node.LEFT;
                    iterator.leftChild = nodeToInsert;

                    out.println("hello");
                    rebalance(nodeToInsert);

                    return true;
                }
                iterator = iterator.leftChild;

            } else if (compareResult == 1) {
                if (iterator.rightChild == null) {
                    nodeToInsert.parent = iterator;
                    nodeToInsert.direction = Node.RIGHT;
                    iterator.rightChild = nodeToInsert;

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
        
        if (element == null) return false;
        if (element == this.root.element) return true;

        Node iterator = this.root;
        int compareResult;

        while (iterator != null) {
            compareResult = element.compareTo(iterator.element);
            if (compareResult == -1) {
                iterator = iterator.leftChild;
            } else if (compareResult == 1) {
                iterator = iterator.rightChild;
            } else if (compareResult == 0) {
                return true;
            }
        }

        return false;

        // throw new UnsupportedOperationException("contains(E) has not been implemented.");
    }

    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        preOrderToString(this.root, strBuilder);
        strBuilder.deleteCharAt(strBuilder.length() - 1);
        return strBuilder.toString();
    }

    private boolean hasUncle(Node n) {
        boolean hasGrandparent = (n.parent.parent != null);
        if (!hasGrandparent) return false;

        Node grandparent = n.parent.parent;
        return (grandparent.leftChild != null
                        && grandparent.rightChild != null);
    }

    private int isChangeNeeded(Node current) {

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
        Node uncle = null;
        if (hasUncle(current)) {
            Node grandparent = current.parent.parent;
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

    private Node executeChanges(int change, Node insertedNode) {
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

    private void rotate(Node node) {

        Node parent = node.parent;

        // single rotation LEFT LEFT
        if (parent.direction == Node.LEFT
                    && node.direction == Node.LEFT) {
            out.println("calling singleRotation() with left left case");
            singleRotation(node, LEFT_LEFT);
        }

        // single rotation RIGHT RIGHT
        if (node.direction == Node.RIGHT 
                    && parent.direction == Node.RIGHT) {
            out.println("calling singleRotation() with right right case");
            singleRotation(node, RIGHT_RIGHT);
        }

        // double rotation LEFT RIGHT
        if (parent.direction == Node.LEFT
                    && node.direction == Node.RIGHT) {
            out.println("calling doubleRotation() with left right case");
            doubleRotation(node, LEFT_RIGHT);
        }

        // double rotation RIGHT LEFT
        if (parent.direction == Node.RIGHT
                    && node.direction == Node.LEFT) {
            out.println("calling doubleRotation() with right left case");
            doubleRotation(node, RIGHT_LEFT);
        }

        // throw new UnsupportedOperationException("rotate() has not been implemented.");
    }

    private Node recolor(Node current) {

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
        Node grandparent = current.parent.parent;

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

    private void rebalance(Node insertedNode) {

        int changeNeeded = isChangeNeeded(insertedNode);

        if (changeNeeded == RECOLOR) {
            out.println("recolor");
            while (changeNeeded != NONE) {
                insertedNode = executeChanges(changeNeeded, insertedNode);
                changeNeeded = ((insertedNode == null) ? NONE : isChangeNeeded(insertedNode));
                out.print("fixing recolor with: ");
                if (changeNeeded == RECOLOR) out.println("recolor");
                else if (changeNeeded == ROTATE) out.println("rotate");
                else if (changeNeeded == NONE) out.println("none");
            }
            return;
        }

        if (changeNeeded == ROTATE) {
            executeChanges(changeNeeded, insertedNode);
        }

        // throw new UnsupportedOperationException("rebalance() has not been implemented.");
    }

    private void singleRotation(Node node, int rotationCase) {

        Node parent = node.parent;
        Node grandparent = parent.parent;
        parent.color = BLACK;
        grandparent.color = RED;

        if (rotationCase == LEFT_LEFT) {

            Node sibling = parent.rightChild;
            grandparent.leftChild = sibling;

            if (sibling != null) {
                sibling.direction = Node.LEFT;
            }

            parent.parent = grandparent.parent;
            if (parent.parent != null) {
                parent.parent.leftChild = parent;
            }
            grandparent.parent = parent;

            parent.rightChild = grandparent;
            grandparent.direction = Node.RIGHT;

            if (grandparent == this.root) this.root = parent;

        } else if (rotationCase == RIGHT_RIGHT) {

            Node sibling = parent.leftChild;
            grandparent.rightChild = sibling;

            if (sibling != null) {
                sibling.direction = Node.RIGHT;
            }

            parent.parent = grandparent.parent;
            if (parent.parent != null) {
                parent.parent.rightChild = parent;
            }
            grandparent.parent = parent;

            parent.leftChild = grandparent;
            grandparent.direction = Node.LEFT;

            if (grandparent == this.root) this.root = parent;

        } else {
            out.println("There was an error. Try again. Rotation Case: " + rotationCase);
            return;
        }

        // throw new UnsupportedOperationException("singleRotation() has not been implemented.");
    }

    private void doubleRotation(Node node, int rotationCase) {

        Node parent = node.parent;
        Node grandparent = parent.parent;

        Node left = node.leftChild;
        Node right = node.rightChild;

        parent.color = RED;
        grandparent.color = RED;

        if (rotationCase == LEFT_RIGHT) {

            out.println("LEFT RIGHT");

            if (left != null) {
                left.parent = parent;
                parent.rightChild = left;
                left.direction = Node.RIGHT;
            }
            out.println("left?: " + left);

            if (right != null) {
                right.parent = grandparent;
                grandparent.leftChild = right;
                right.direction = Node.LEFT;
            }
            out.println("right?: " + right);

            parent.parent = node;
            grandparent.parent = node;

            node.leftChild = parent;
            parent.direction = Node.LEFT;

            node.rightChild = grandparent;
            grandparent.direction = Node.RIGHT;

            out.println("node left child: " + node.leftChild);
            out.println("node right child: " + node.rightChild);
        }

        if (rotationCase == RIGHT_LEFT) {

            out.println("RIGHT LEFT");

            left.parent = parent;
            right.parent = grandparent;

            parent.rightChild = right;
            right.direction = Node.RIGHT;

            parent.leftChild = left;
            left.direction = Node.LEFT;

            parent.parent = node;
            grandparent.parent = node;

            node.leftChild = grandparent;
            grandparent.direction = Node.LEFT;

            node.rightChild = parent;
            parent.direction = Node.RIGHT;

        }

        if (grandparent.element.compareTo(this.root.element) == 0) {
            this.root = node;
            node.color = BLACK;
            node.parent = null;
            node.direction = null;
        }

        // throw new UnsupportedOperationException("doubleRotation() has not been implemented.");
    }

    private String preOrderToString(Node root, StringBuilder strBuilder) {
        if (root == null) {					// if root is null, append a "" and return to start going back up the recursion
            return strBuilder.toString();
        }

        strBuilder.append(((root.color)?ANSI_WHITE:ANSI_RED) + root.toString() + ANSI_RESET); /*+ ((root.color)?"Black":"Red")*/	// if not, only append the root
        strBuilder.append(" ");

        // out.print(((root.color)?ANSI_WHITE:ANSI_RED) + root.toString() + ANSI_RESET + " ");

        preOrderToString(root.leftChild, strBuilder);		// go through the left tree first
        preOrderToString(root.rightChild, strBuilder);	    // go through the right tree after
        // makes it pre-order
        return strBuilder.toString();	// return
    }

    private class Node implements Comparable<E> {

        static final boolean LEFT = false;
        static final boolean RIGHT = true;

        E element;
        Node leftChild;
        Node rightChild;
        Node parent;
        boolean color;
        Boolean direction;

        private Node() {  }

        private Node(E element) { this.element = element; }

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