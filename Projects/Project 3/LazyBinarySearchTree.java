import java.lang.StringBuilder;
import java.lang.System;
import static java.lang.System.out;

public class LazyBinarySearchTree {

    TreeNode root;
    private int size;

    public LazyBinarySearchTree() {  }

    /* Should throw IllegalArgumentException
     * 
     * returns true if physically inserted
     * returns false if logically inserted
     */
    public boolean insert(int key) throws IllegalArgumentException {
        
        if (key < 0 || key > 99) {
            throw new IllegalArgumentException("\nNot a valid key. Enter something in the range 0-99.\nYour input: " + key);
        }

        TreeNode newNode = new TreeNode(key);

        // System.out.println("Key to insert: " + newNode.toString());

        if (root == null) {
            root = newNode;
            size++;
            // System.out.println("Insert: " + root.key);
            return true;
        }

        TreeNode iterator = root;

        while (iterator != null) {
            if (newNode.key < iterator.key) {
                if (iterator.leftChild == null) {
                    iterator.leftChild = newNode;
                    size++;
                    // System.out.println(newNode.key + " inserted to the left");
                    return true;
                }
                // System.out.println("Switching to left child: " + iterator.leftChild.toString());
                iterator = iterator.leftChild;

            } else if (newNode.key > iterator.key) {
                if (iterator.rightChild == null) {
                    iterator.rightChild = newNode;
                    // System.out.println(newNode.key + " inserted to the right");
                    size++;
                    return true;
                }
                // System.out.println("Switching to right child: " + iterator.rightChild.toString());
                iterator = iterator.rightChild;
            } else if (iterator.key == key && iterator.deleted) {
                iterator.deleted = false;
                return true;
            } else if (newNode.key == iterator.key && !iterator.deleted) {
                // System.out.println("That key is already in the tree. Key: " + key + ".");
                return false;
            } else {
                System.out.println("An error occurred.");
            }
        }

        return false;
    }

    public boolean delete(int key) throws IllegalArgumentException {
        // Should throw IllegalArgumentException

        if (key < 0 || key > 99) {
            throw new IllegalArgumentException("\nNot a valid key. Enter something in the range 0-99.\nYour input: " + key);
        }

        TreeNode keyNode = search(this.root, key);

        if (keyNode == null) {
            return false;
        }

        // System.out.println("From delete(): " + keyNode.toString());
        keyNode.deleted = true;
        return true;

        /*
        TreeNode iterator = root;
        while (iterator != null) {
            if (iterator.key == key) {
                iterator.deleted = true;
            }

            check = iterator.checked;

            if (iterator.leftChild != null /* && here we need to check if this has already been looked at) {
                iterator = iterator.leftChild;
                iterator = iterator.rightChild;
            } else if (iterator.rightChild != null) {
            } else {
                iterator = (iterator.parent.rightChild != null) ? iterator.parent.rightChild : iterator.parent;
            }
        }*/
    }

    public boolean contains(int key) throws IllegalArgumentException {
        if (key < 0 || key > 99) {
            throw new IllegalArgumentException("\nNot a valid key. Enter something in the range 0-99.\nYour input: " + key);
        }

        TreeNode nodeToFind = search(this.root, key);

        if (nodeToFind != null && !nodeToFind.deleted) {
            return true;
        }

        return false;
    }

    public int findMax() {
        return searchMax(this.root).key;
    }

    public int findMin() {
        return searchMin(this.root).key;
        // TreeNode iterator = root;
        // int min = 0;
        // while (iterator.leftChild != null ) {
        //     iterator = iterator.leftChild;
        // }
        // min = iterator.key;
        // return min;
    }

    public int height() {
        int height = 0;
        height = findHeight(this.root);
        return height;
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        this.size = 0;
        this.root = null;
    }

    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        preOrderToString(this.root, strBuilder);
        //recursiveTraversalToString(this.root, true, 0, strBuilder);
        return strBuilder.toString();
    }

    private TreeNode search(TreeNode root, int key) {

        if (root != null) {
            if (root.key == key) {
                return root;
            } else {
                TreeNode searchNode = search(root.leftChild, key);
                if (searchNode == null) {
                    searchNode = search(root.rightChild, key);
                }
                return searchNode;
            }
        } else {
            return null;
        }
    }

    private TreeNode searchMax(TreeNode root) {
        if (root == null)
            return null;

        TreeNode tempNode = searchMax(root.rightChild);

        if (tempNode != null)
            return tempNode;

        if (!root.deleted)
            return root;

        return searchMax(root.leftChild);
    }

    private TreeNode searchMin(TreeNode root/*, TreeNode currMin*/) {

        if (root == null) {
            return null;
        }

        TreeNode tempNode = searchMin(root.leftChild);
        // System.out.println(tempNode);

        if (tempNode != null) {
            return tempNode;
        }

        if (!root.deleted) {
            return root;
        }

        return searchMin(root.rightChild);

        /*
        if (root != null) {
            if (root.rightChild.key < root.leftChild.key) {
                return root.rightChild;
            } else if (root.rightChild.key > root.leftChild.key) {
                return root.leftChild;
            } else {
                TreeNode searchNode = searchMin(root.leftChild);
                if (searchNode.deleted) {
                    searchNode = searchMin(root.rightChild);
                }
                return searchNode;
            }
        } else {
            return null;
        }
        */

        /*
        if (root == null) {
            return null;
        } else {
            TreeNode leftMin = root.leftChild;
            TreeNode rightMin = root.rightChild;
        
            if (leftMin.deleted || rightMin.deleted) {
                leftMin = searchMin(root.leftChild);
                rightMin = searchMin(root.rightChild);
            } else {
                if (leftMin.key > rightMin.key) {
                    return rightMin;
                } else {
                    return leftMin;
                }
            }
        }
        */
    }

    private String preOrderToString(TreeNode root, StringBuilder strBuilder) {
        if (root == null) {
            strBuilder.append("");
            return strBuilder.toString();
        }

        if (root.deleted) {
            strBuilder.append("*");
            strBuilder.append(root.toString());
            strBuilder.append(" ");
        } else {
            strBuilder.append(root.toString());
            strBuilder.append(" ");
        }

        preOrderToString(root.leftChild, strBuilder);
        preOrderToString(root.rightChild, strBuilder);

        return strBuilder.toString();
    }

    private void recursiveTraversalToString(TreeNode root, boolean isRoot, int child, StringBuilder strBuilder) {

        if (isRoot) {
            strBuilder.append("Root: " + root.toString());
            strBuilder.append("\n");
            out.println("Root: " + root);
            if (root.leftChild != null) {
                // recursiveTraversalToString(root.leftChild, false, 1, strBuilder);
                out.println("first left recursive call");
                recursiveTraversalToString(root, false, 1, strBuilder);
            }
            if (root.rightChild != null) {
                // recursiveTraversalToString(root.rightChild, false, 2, strBuilder);
                out.println("first right recursive call");
                recursiveTraversalToString(root, false, 2, strBuilder);
            }
        }

        if (!isRoot) {
            if (child == 1) { // if its a left child
                TreeNode parent = root;
                out.println(root.leftChild == null ? ""
                        : "Left Child of " + parent.toString() + ": " + root.leftChild.toString());
                strBuilder.append(root.leftChild == null ? ""
                        : "Left Child of " + parent.toString() + ": " + root.leftChild.toString());
                strBuilder.append("\n");
            }
            if (child == 2) {
                TreeNode parent = root;
                out.println("now in the right");
                out.println(root.rightChild == null ? ""
                        : "Right Child of " + parent.toString() + ": " + root.rightChild.toString());
                strBuilder.append(root.rightChild == null ? ""
                        : "Right Child of " + parent.toString() + ": " + root.rightChild.toString());
                strBuilder.append("\n");
            }

            if (root.leftChild != null) {
                out.println("continue left recursive call");
                recursiveTraversalToString(root.leftChild, false, 1, strBuilder);
            }
            if (root.rightChild != null) {
                out.println("continue right recursive call");
                recursiveTraversalToString(root.rightChild, false, 2, strBuilder);
            }
        }

        /*
        if (root.leftChild == null && root.rightChild == null) {
            strBuilder.append(root.toString());
            strBuilder.append("\n");
            if (side == 0) System.out.println("");
            else if (side == 1) System.out.println("Left: " + root.toString());
            else if (side == 2) System.out.println("Right: " + root.toString());
        }
        
        if (root.leftChild != null) {
            strBuilder.append("Parent: " + root.toString());
            strBuilder.append("\n");
            System.out.println("Parent: " + root.toString());
            recursiveTraversalToString(root.leftChild, 1);
        }
        
        if (root.rightChild != null) {
            strBuilder.append("Parent: " + root.toString());
            strBuilder.append("\n");
            System.out.println("Parent: " + root.toString());
            recursiveTraversalToString(root.rightChild, 2);
        }
        */
    }

    private int findHeight(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int heightLeft = findHeight(root.leftChild);
            int heightRight = findHeight(root.rightChild);

            if (heightLeft > heightRight) {
                // System.out.println("height left: " + (heightLeft + 1));
                return heightLeft + 1;
            } else {
                // System.out.println("height right: " + (heightRight + 1));
                return heightRight + 1;
            }
        }
    }

    /*private int findHeight(TreeNode root, int currMax, int prevMax, int realTing, int height) {

        // System.out.println("---FINDHEIGHT(" + root + ", " + currMax + ", " + prevMax + ", " + realTing + ", " + height + ")---");
        System.out.println("root: " + root);
        //System.out.println("currMax at beginning of method: " + currMax);
        // System.out.println("realTing beginning of method: " + realTing);

        // if the input root is null, 
        //  that means we've reached the end of that path
        // if the new max (new height) is greater than the last one
        //  then replace it
        // we need to increment every time that a node is not null
        if (root == null) {
            // System.out.println("root==null currMax: " + currMax);
            // System.out.println("root==null prevMax: " + prevMax);
            if (prevMax > realTing) {
                realTing = prevMax;
            }
            System.out.println("realTing: " + realTing);
            return realTing;
        }

        if (root != null) {
            currMax += 1;
            if (currMax > prevMax) {
                prevMax = currMax;
            }
            System.out.println("currMax: " + currMax);
            // System.out.println("root!=null currMax: " + currMax);
            // System.out.println("prevMax: " + prevMax);
            height = findHeight(root.leftChild, currMax, prevMax, realTing, height);
            System.out.println("Height: " + height);
            height = findHeight(root.rightChild, currMax, prevMax, realTing, height);
            System.out.println("Height: " + height);
            
            // System.out.println("prevMax after recursive calls: " + prevMax);
        }

        return prevMax;
    }*/

    private class TreeNode {

        private int key;
        private TreeNode leftChild;
        private TreeNode rightChild;
        private boolean deleted;

        public TreeNode() { 
            this.leftChild = null;
            this.rightChild = null;
            this.deleted = false;
         }

        public TreeNode(int key) {
            this.leftChild = null;
            this.rightChild = null;
            this.deleted = false;
            this.key = key;
        }

        public String toString() {
            return String.valueOf(key);
        }

    }

}