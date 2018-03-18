

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
    private static final String ANSI_GREEN = "\u001B[32m";

    Node root;

    public RedBlackTree() {
        this.root = null;
    }

    public boolean insert(E newElement) throws NullPointerException {

        if (newElement == null) {
            throw new NullPointerException("The element you want to insert is null. Try again.");
        }

        Node nodeToInsert = new Node(newElement);
        nodeToInsert.color = RED;

        if (root == null) {
            nodeToInsert.direction = null;
            nodeToInsert.color = BLACK;
            root = nodeToInsert;
            return true;
        }

        if (newElement.compareTo(this.root.element) == 0) { return false; }

        Node iterator = this.root;
        int compareResult;

        while (iterator != null) {
            compareResult = nodeToInsert.element.compareTo(iterator.element);
            // if (newElement instanceof String) {
            //     if (newElement.equals(iterator.element)) {
            //         return false;
            //     }
            // }
            if (compareResult < 0) {
                if (iterator.leftChild == null) {
                    nodeToInsert.parent = iterator;
                    nodeToInsert.direction = Node.LEFT;
                    nodeToInsert.leftChild = null;
                    nodeToInsert.rightChild = null;

                    iterator.leftChild = nodeToInsert;

                    rebalance(nodeToInsert);

                    return true;
                }
                iterator = iterator.leftChild;

            } else if (compareResult > 0) {
                if (iterator.rightChild == null) {
                    nodeToInsert.parent = iterator;
                    nodeToInsert.direction = Node.RIGHT;
                    nodeToInsert.leftChild = null;
                    nodeToInsert.rightChild = null;

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

    /*public void iterate() {
        iterate("Leo");
    }*/

    public void iterate(E element) {
        Node itr = this.root;
        int compareResult;

        while (itr != null) {
            compareResult = element.compareTo(itr.element);
            if (compareResult < 0) {
                out.println(itr);
                itr = itr.leftChild;
            } else if (compareResult > 0) {
                out.println(itr);
                itr = itr.rightChild;
            } else {
                out.println(itr);
            }
        }
    }

    public boolean contains(E element) {

        if (element == null) return false;
        if (element == this.root.element) return false;

        Node itr = this.root;
        int compareResult;

        while (itr != null) {
            compareResult = element.compareTo(itr.element);
            if (compareResult < 0) {
                itr = itr.leftChild;
            } else if (compareResult > 0) {
                itr = itr.rightChild;
            } else {
                return true;
            }
        }

        return false;
    }

    private void rebalance(Node insertedNode) {

        int changeNeeded = isChangeNeeded(insertedNode);

        if (changeNeeded == RECOLOR) {
            while (changeNeeded != NONE) {
                insertedNode = executeChanges(changeNeeded, insertedNode);
                changeNeeded = ((insertedNode == null) ? NONE : isChangeNeeded(insertedNode));
                // if (changeNeeded == RECOLOR) out.println("recolor");
            }
            return;
        }

        if (changeNeeded == ROTATE) {
            executeChanges(changeNeeded, insertedNode);
        }

    }

    private int isChangeNeeded(Node insertedNode) {

        boolean isParentRed = !(insertedNode.parent.color);
        if (!isParentRed) return NONE;

        Node uncle = null;
        if (hasUncle(insertedNode)) {
            Node grandparent = insertedNode.parent.parent;
            if (insertedNode.parent.direction == Node.LEFT) {
                uncle = grandparent.rightChild;
            } else if (insertedNode.parent.direction == Node.RIGHT) {
                uncle = grandparent.leftChild;
            }
        } else {
            return ROTATE;      // parent is red, and uncle does not exist, so it needs a rotation
        }

        // uncle is not null and uncle color is red -> recolor
        // uncle null or color is black -> rotate
        boolean isUncleRed = (uncle != null) && !(uncle.color);

        return (isUncleRed) ? RECOLOR : ROTATE;
    }

    private boolean hasUncle(Node n) {
        boolean hasGrandparent = (n.parent.parent != null);
        if (!hasGrandparent) return false;

        Node grandparent = n.parent.parent;
        return (grandparent.leftChild != null
                && grandparent.rightChild != null);
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

    private Node recolor(Node insertedNode) {

        out.println("RECOLOR");

        Node grandparent = insertedNode.parent.parent;

        if (grandparent.leftChild != null) {
            grandparent.leftChild.color = BLACK;
        }

        if (grandparent.rightChild != null) {
            grandparent.rightChild.color = BLACK;
        }

        if (grandparent.element.compareTo(this.root.element) == 0) {
            grandparent.color = BLACK;
            return null;
        }

        grandparent.color = RED;
        insertedNode = grandparent;

        return insertedNode;
    }

    private void rotate(Node insertedNode) {

        Node parent = insertedNode.parent;

        // left left case -> single rotation
        if (parent.direction == Node.LEFT && insertedNode.direction == Node.LEFT) {
            singleRotation(insertedNode, LEFT_LEFT);
            return;
        }

        // right right case -> single rotation
        if (parent.direction == Node.RIGHT && insertedNode.direction == Node.RIGHT) {
            singleRotation(insertedNode, RIGHT_RIGHT);
            return;
        }

        // left right case -> double rotation
        if (parent.direction == Node.LEFT && insertedNode.direction == Node.RIGHT) {
            doubleRotation(insertedNode, LEFT_RIGHT);
            return;
        }

        // right left case -> double rotation
        if (parent.direction == Node.RIGHT && insertedNode.direction == Node.LEFT) {
            doubleRotation(insertedNode, RIGHT_LEFT);
            return;
        }

    }

    private void singleRotation(Node node, int rotationCase) {

        Node parent = node.parent;
        Node grandparent = node.parent.parent;

        parent.color = BLACK;
        grandparent.color = RED;

        if (rotationCase == LEFT_LEFT) {
            out.println("LEFT LEFT");

            Node sibling = parent.rightChild;
            grandparent.leftChild = sibling;

            if (sibling != null) {
                sibling.direction = Node.LEFT;
            }

            parent.parent = grandparent.parent;
            if (parent.parent != null) {
                parent.parent.rightChild = parent;
                parent.direction = Node.RIGHT;
            }
            grandparent.parent = parent;

            parent.rightChild = grandparent;
            grandparent.direction = Node.RIGHT;

        } else if (rotationCase == RIGHT_RIGHT) {
            out.println("RIGHT RIGHT");

            Node sibling = parent.leftChild;
            grandparent.rightChild = sibling;

            if (sibling != null) {
                sibling.direction = Node.RIGHT;
            }

            parent.parent = grandparent.parent;
            if (parent.parent != null) {
                parent.parent.leftChild = parent;
                parent.direction = Node.LEFT;
            }
            grandparent.parent = parent;

            parent.leftChild = grandparent;
            grandparent.direction = Node.LEFT;

        } else {
            out.println("There was an error. Try again. Rotation Case: " + rotationCase);
            return;
        }

        if (this.root.element.compareTo(grandparent.element) == 0) {
            this.root = parent;
            parent.color = BLACK;
            parent.direction = null;
            parent.parent = null;
        }

    }

    private void doubleRotation(Node node, int rotationCase) {

        Node parent = node.parent;
        Node grandparent = node.parent.parent;

        out.println("grandparent: " + grandparent);

        Node nodeLeftChild = node.leftChild;
        Node nodeRightChild = node.rightChild;

        if (grandparent != null) {

            node.parent = grandparent.parent;

            if (grandparent.direction == null) {
            } else if (grandparent.direction == Node.LEFT) {
                node.direction = Node.LEFT;
                grandparent.parent.leftChild = node;
            } else if (grandparent.direction == Node.RIGHT) {
                node.direction = Node.RIGHT;
                grandparent.parent.rightChild = node;
            }

        } else {
            node.parent = null;
        }

        node.color = BLACK;
        parent.color = RED;
        grandparent.color = RED;

        if (rotationCase == LEFT_RIGHT) {
            out.println("LEFT RIGHT");

            parent.parent = node;
            grandparent.parent = node;

            node.leftChild = parent;
            parent.direction = Node.LEFT;

            node.rightChild = grandparent;
            grandparent.direction = Node.RIGHT;

            if (nodeLeftChild != null) {
                nodeLeftChild.parent = parent;
                nodeLeftChild.direction = Node.RIGHT;
                parent.rightChild = nodeLeftChild;
            } else {
                parent.rightChild = null;
            }

            if (nodeRightChild != null) {
                nodeRightChild.parent = grandparent;
                nodeRightChild.direction = Node.LEFT;
                grandparent.leftChild = nodeRightChild;
            } else {
                grandparent.leftChild = null;
            }

        }

        if (rotationCase == RIGHT_LEFT) {
            out.println("RIGHT LEFT");

            parent.parent = node;
            grandparent.parent = node;

            node.rightChild = parent;
            parent.direction = Node.RIGHT;

            node.leftChild = grandparent;
            grandparent.direction = Node.LEFT;

            if (nodeLeftChild != null) {
                nodeLeftChild.parent = grandparent;
                nodeLeftChild.direction = Node.RIGHT;
                grandparent.rightChild = nodeLeftChild;
            } else {
                grandparent.rightChild = null;
            }

            if (nodeRightChild != null) {
                nodeRightChild.parent = parent;
                nodeRightChild.direction = Node.LEFT;
                parent.leftChild = nodeRightChild;
            } else {
                parent.leftChild = null;
            }
        }

        if (this.root.element.compareTo(grandparent.element) == 0) {
            this.root = node;
            node.color = BLACK;
            node.parent = null;
            node.direction = null;
        }

    }

    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        preOrderToStringFile(this.root, strBuilder);
        strBuilder.deleteCharAt(strBuilder.length() - 1);
        return strBuilder.toString();
    }

    private String preOrderToStringTerminal(Node n, StringBuilder strBuilder) {
        // out.print(ANSI_GREEN + n + ANSI_RESET);
        // out.println();
        if (n == null) { // if root is null, append a "" and return to start going back up the recursion
            // strBuilder.append(ANSI_WHITE + "NIL " + ANSI_RESET);
            strBuilder.append("");
            return strBuilder.toString();
        }

        strBuilder.append(
                ((n.color) ? ANSI_WHITE : ANSI_RED) + n.toString() + ANSI_RESET); /*+ ((root.color)?"Black":"Red")*/ // if not, only append the root
        strBuilder.append(" ");

        //        out.print(((n.color)?ANSI_WHITE:ANSI_RED) + n.toString() + ANSI_RESET + " ");
        //        out.print(ANSI_GREEN + n.rightChild + ANSI_RESET);

        preOrderToStringTerminal(n.leftChild, strBuilder); // go through the left tree first
        preOrderToStringTerminal(n.rightChild, strBuilder); // go through the right tree after
        // makes it pre-order
        return strBuilder.toString(); // return
    }

    private String preOrderToStringFile(Node n, StringBuilder strBuilder) {
        // out.print(ANSI_GREEN + n + ANSI_RESET);
        // out.println();
        if (n == null) { // if root is null, append a "" and return to start going back up the recursion
            // strBuilder.append(ANSI_WHITE + "NIL " + ANSI_RESET);
            strBuilder.append("");
            return strBuilder.toString();
        }

        strBuilder.append(
                ((n.color) ? "" : "*") + n.toString()); /*+ ((root.color)?"Black":"Red")*/ // if not, only append the root
        strBuilder.append(" ");

        //        out.print(((n.color)?ANSI_WHITE:ANSI_RED) + n.toString() + ANSI_RESET + " ");
        //        out.print(ANSI_GREEN + n.rightChild + ANSI_RESET);

        preOrderToStringFile(n.leftChild, strBuilder); // go through the left tree first
        preOrderToStringFile(n.rightChild, strBuilder); // go through the right tree after
        // makes it pre-order
        return strBuilder.toString(); // return
    }

    private class Node {

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

        public String toString() {
            return element.toString();
        }

    }

}