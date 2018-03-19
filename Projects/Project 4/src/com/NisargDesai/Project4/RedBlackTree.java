import static java.lang.System.out;

/*
    E must extend Comparable...
        this is a necessity for the comparisons to work for the insert and contains methods
*/

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

    /*
        Method to insert a new node into the red black tree
        Look at comments inside the method for more details
    */
    public boolean insert(E element) throws NullPointerException {

        // If inserted element is null, throw a nullptr exception
        if (element == null) {
            throw new NullPointerException("The element you want to insert is null. Try again.");
        }

        Node nodeToInsert = new Node(element);
        nodeToInsert.color = RED;

        // If no nodes have been inserted, create a root and return true
        if (this.root == null) {
            nodeToInsert.direction = null;
            nodeToInsert.color = BLACK;
            this.root = nodeToInsert;
            return true;
        }

        /*
            If the element in question 
                is the same as the root's element (meaning it already exists)
                return false
        */
        if (element.compareTo(this.root.element) == 0) {
            return false;
        }

        Node iterator = this.root;
        int compareResult;

        /*
            iterate through the tree by comparing each node's element with the element in question
            if the new element is less than the current iterator node
                insert if the left child is empty
                then rebalance
                OR
                move left
            if the new element is greater than the current iterator node
                insert if the right child is empty
                then rebalance
                OR
                move right
            if they are equal (meaning the "new" element already exists)
                return false;
        */
        while (iterator != null) {
            compareResult = nodeToInsert.element.compareTo(iterator.element);

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

    /*
        Check if a certain element exists in the tree
        iterate through the tree by comparing each node's element with the element the user is searching for
            if the new element is less than the current iterator node
                move left
            if the new element is greater than the current iterator node
                move right
            if they are equal (meaning the element already exists)
                return true;
        
        if the iterator exits the loop
            it means that the element does not exist 
            so just return false
    */
    public boolean contains(E element) {

        if (element == null)
            return false;
        if (element == this.root.element)
            return false;

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

    /*
        Calls the isChangeNeeded(Node) method to check what kind of change is needed if any
        If a recolor is needed
            Perform recolor using the executeChanges(Node) method
            Keep looping until no change is needed
            This is because if something gets recolored, we might need another recolor or rotation
            When no change is needed, return back to insert(E)
        If a rotation is needed
            Perform rotation using the executeChanges(Node) method and return back to insert(E)
    */
    private void rebalance(Node insertedNode) {

        int changeNeeded = isChangeNeeded(insertedNode);

        if (changeNeeded == RECOLOR) {
            while (changeNeeded != NONE) {
                insertedNode = executeChanges(changeNeeded, insertedNode);
                changeNeeded = ((insertedNode == null) ? NONE : isChangeNeeded(insertedNode));
            }
            return;
        }

        if (changeNeeded == ROTATE) {
            executeChanges(changeNeeded, insertedNode);
        }

    }

    /*
        Find what kind of change is needed (recolor or rotation)
        Recolor is needed if the new node, it's parent, and it's uncle are all red
        A rotation is needed when new node and parent are both red 
            but the new node doesn't have an uncle or the uncle is black
    */
    private int isChangeNeeded(Node insertedNode) {

        boolean isParentRed = !(insertedNode.parent.color);
        if (!isParentRed)
            return NONE;

        Node uncle = null;
        if (hasUncle(insertedNode)) {
            Node grandparent = insertedNode.parent.parent;
            if (insertedNode.parent.direction == Node.LEFT) {
                uncle = grandparent.rightChild;
            } else if (insertedNode.parent.direction == Node.RIGHT) {
                uncle = grandparent.leftChild;
            }
        } else {
            return ROTATE; // parent is red, and uncle does not exist, so it needs a rotation
        }

        // uncle is not null and uncle color is red -> recolor
        // uncle null or color is black -> rotate
        boolean isUncleRed = (uncle != null) && !(uncle.color);

        return (isUncleRed) ? RECOLOR : ROTATE;
    }

    /*
        Checks whether a given node n has an uncle or not
    */
    private boolean hasUncle(Node n) {
        boolean hasGrandparent = (n.parent.parent != null);
        if (!hasGrandparent)
            return false;

        Node grandparent = n.parent.parent;
        return (grandparent.leftChild != null && grandparent.rightChild != null);
    }

    /*
        Takes in a change integer 
            (this can be one of the static final ints at the top: RECOLOR, ROTATE)
        and takes in the newly inserted node
        Using a switch case statement
            it calls the right method to execute the change
    */
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

    /*
        Performs a recolor of the insertedNode's parent, uncle and grandparent
        makes sure that if the grandparent is the root, then its color is blac
    */
    private Node recolor(Node insertedNode) {

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

    /*
        Checks the insertedNode and it's parent to see which child they are (left or right)
        Based on this, it calls one of the rotation methods with a particular rotation case
        Rotation cases include:
            Left Left (needs Single Rotation)
            Right Right (needs Single Rotation)
            Left Right (needs Double Rotation)
            Right Left (needs Double Rotation)

        Example method call: singleRotation(insertedNode, LEFT_LEFT)
        where LEFT_LEFT is the rotation case
    */
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

    /*
        Single rotation method for the Left Left and Right Right cases
        The node being operated on is called "node" in this method
    */
    private void singleRotation(Node node, int rotationCase) {

        Node parent = node.parent;
        Node grandparent = node.parent.parent;

        // set parent and grandparent colors
        // this is the same for both cases
        parent.color = BLACK;
        grandparent.color = RED;

        /*
            set node's parent's parent to node's greatgrandparent
            if grandparent is a left child
                set node's greatgrandparent's left child to node's parent
                fix node's parent's direction and color
            after that, set grandparent's and parent's children correctly
            this applies to both cases
            the only difference is in the direction of the children (left or right children)
        */
        if (rotationCase == LEFT_LEFT) {
            
            Node greatGrandparent = grandparent.parent;
            if (greatGrandparent != null) {

                parent.parent = greatGrandparent;

                if (grandparent.direction == Node.LEFT) {
                    greatGrandparent.leftChild = parent;
                    parent.direction = Node.LEFT;
                    parent.color = BLACK;
                    grandparent.leftChild = null;
                } else if (grandparent.direction = Node.RIGHT) {
                    greatGrandparent.rightChild = parent;
                    parent.direction = Node.RIGHT;
                    parent.color = BLACK;
                    grandparent.leftChild = null;
                }

            }

            grandparent.leftChild = parent.rightChild;
            if (grandparent.leftChild != null) {
                grandparent.leftChild.parent = grandparent;
                grandparent.leftChild.direction = Node.LEFT;
            }

            parent.rightChild = grandparent;
            grandparent.color = RED;
            grandparent.parent = parent;
            grandparent.direction = Node.RIGHT;

        } else if (rotationCase == RIGHT_RIGHT) {

            Node greatGrandparent = grandparent.parent;
            if (greatGrandparent != null) {

                parent.parent = greatGrandparent;

                if (grandparent.direction == Node.LEFT) {
                    greatGrandparent.leftChild = parent;
                    parent.direction = Node.LEFT;
                    parent.color = BLACK;
                    grandparent.rightChild = null;
                } else if (grandparent.direction = Node.RIGHT) {
                    greatGrandparent.rightChild = parent;
                    parent.direction = Node.RIGHT;
                    parent.color = BLACK;
                    grandparent.rightChild = null;
                }

            }

            grandparent.rightChild = parent.leftChild;
            if (grandparent.rightChild != null) {
                grandparent.rightChild.parent = grandparent;
                grandparent.rightChild.direction = Node.RIGHT;
            }

            parent.leftChild = grandparent;
            grandparent.color = RED;
            grandparent.parent = parent;
            grandparent.direction = Node.LEFT;

        } else {
            out.println("There was an error. Try again. Rotation Case: " + rotationCase);
            return;
        }

        // if grandparent was the root, make sure that the parent, after rotation, is set as the root
        if (this.root.element.compareTo(grandparent.element) == 0) {
            this.root = parent;
            parent.color = BLACK;
            parent.direction = null;
            parent.parent = null;
        }

    }

    /*
        Double rotation method for the Left Right and Right Left cases
        The node being operated on is named "node" in this method
    */
    private void doubleRotation(Node node, int rotationCase) {

        Node parent = node.parent;
        Node grandparent = node.parent.parent;

        Node nodeLeftChild = node.leftChild;
        Node nodeRightChild = node.rightChild;

        /*
            If a grandparent exists
                the node's parent should be set to its grandparent's parent
            If grandparent has a direction (left or right) (if its neither then grandparent is the root)
                set node's direction to the direction of the grandparent
                set node as the <direction>Child of grandparent's parent (great grandparent)
        */
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

        // set colors to what they should be at the end of the rotation
        // this is the same for both double rotation cases
        node.color = BLACK;
        parent.color = RED;
        grandparent.color = RED;

        /*
            set the node as the parent of its parent and grandparent
            make sure the children nodes of the node are set correctly 
                as children of node's original parent and grandparent
            this goes for both cases
            the only difference is in the side (left or right) that something gets set to
        */
        if (rotationCase == LEFT_RIGHT) {

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

        } else if (rotationCase == RIGHT_LEFT) {

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
        } else {
            out.println("There was an error. Try again. Rotation Case: " + rotationCase);
            return;
        }

        // if grandparent was the root, make sure that the node, after rotation, is set as the root
        if (this.root.element.compareTo(grandparent.element) == 0) {
            this.root = node;
            node.color = BLACK;
            node.parent = null;
            node.direction = null;
        }

    }

    /*
        Returns a string representation of the tree with a pre-order traversal
    */
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        preOrderToStringFile(this.root, strBuilder);
        strBuilder.deleteCharAt(strBuilder.length() - 1);
        return strBuilder.toString();
    }

    /*
        This method is to be used when printing results to a terminal or cmd
        It uses Ansi colors instead of asterisks to mark red/black nodes. 
        Black nodes are printed in white color, because my terminal is dark themed.
    */
    private String preOrderToStringTerminal(Node n, StringBuilder strBuilder) {
        if (n == null) { // if root is null, append a "" and return to start going back up the recursion
            strBuilder.append("");
            return strBuilder.toString();
        }

        strBuilder.append(((n.color) ? ANSI_WHITE : ANSI_RED) + n.toString() + ANSI_RESET);
        strBuilder.append(" ");

        preOrderToStringTerminal(n.leftChild, strBuilder); // go through the left tree first
        preOrderToStringTerminal(n.rightChild, strBuilder); // go through the right tree after

        return strBuilder.toString(); // return
    }

    private String preOrderToStringFile(Node n, StringBuilder strBuilder) {
        if (n == null) { // if root is null, append a "" and return to start going back up the recursion
            strBuilder.append("");
            return strBuilder.toString();
        }

        strBuilder.append(((n.color) ? "" : "*") + n.toString());
        strBuilder.append(" ");

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

        private Node(E element) {
            this.element = element;
        }

        public String toString() {
            return element.toString();
        }
    }

}