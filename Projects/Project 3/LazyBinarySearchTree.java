/* Name:				Nisarg Desai
 * Filename:			LazyBinarySearchTree.java
 * Project:			Project 3
 * 
 * Purporse:		A class to represent a lazy binary search tree. 
 * 				Works like library code.
 * 				
 */

import java.lang.StringBuilder;
import java.lang.System;
import static java.lang.System.out;

public class LazyBinarySearchTree {

    TreeNode root;
    private int size;

    public LazyBinarySearchTree() {  }

    /* This method inserts a new node if it does not already exist in the tree
     * 
     * Should throw IllegalArgumentException if the key entered is not in the range [1,99]
     * 
     * returns true if physically inserted
     * returns false if logically inserted
     * 
     */
    public boolean insert(int key) throws IllegalArgumentException {
        
        if (key < 1 || key > 99) {
            throw new IllegalArgumentException("\nNot a valid key. Enter something in the range 0-99.\nYour input: " + key);
        }

        TreeNode newNode = new TreeNode(key);

        if (root == null) {
            root = newNode;
            size++;
            return true;
        }

        TreeNode iterator = root;

        while (iterator != null) {

            if (newNode.key < iterator.key) {           // input key is less than iterator key, iterate to the left or insert to the left
                
                if (iterator.leftChild == null) {
                    iterator.leftChild = newNode;
                    size++;
                    return true;
                }
                iterator = iterator.leftChild;

            } else if (newNode.key > iterator.key) {    // input key is greater than iterator key, iterate to the right or insert to the right
                
                if (iterator.rightChild == null) {
                    iterator.rightChild = newNode;
                    size++;
                    return true;
                }
                iterator = iterator.rightChild;

            } else if (iterator.key == key && iterator.deleted) {   // input key exists and its deleted, then undelete it and return false
                iterator.deleted = false;
                return false;

            } else if (newNode.key == iterator.key && !iterator.deleted) {  // input key exists and is not deleted, return false
                return false;
            }
        }

        return false;
    }

    /* This method does not physically delete
     * instead it MARKS a node deleted (lazy deletion)
     * 
     * Should throw IllegalArgumentException if the key entered is not in the range [1,99]
     * 
     * returns true if the node exists and was marked deleted
     * returns false if the node is null or already marked deleted
     * 
     */
    public boolean delete(int key) throws IllegalArgumentException {

        if (key < 1 || key > 99) {
            throw new IllegalArgumentException("\nNot a valid key. Enter something in the range 0-99.\nYour input: " + key);
        }

        TreeNode keyNode = search(this.root, key);		// search for given key and return it

        if (keyNode == null) {		// node does not exist so return false
            return false;
        }
        
        if (keyNode.deleted) {		// node was already deleted so return false
        	return false;
        }

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

    /* This method checks to see if a node with a certain key exists in the tree
     * 
     * Should throw IllegalArgumentException if the key entered is not in the range [1,99]
     * 
     * returns true if the given key is in the tree
     * returns false otherwise
     * 
     */
    public boolean contains(int key) throws IllegalArgumentException {
        if (key < 1 || key > 99) {
            throw new IllegalArgumentException("\nNot a valid key. Enter something in the range 0-99.\nYour input: " + key);
        }

        TreeNode nodeToFind = search(this.root, key);			// search for the given node

        if (nodeToFind != null && !nodeToFind.deleted) {		// if the node exists and is NOT marked as deleted, return true
            return true;
        }

        return false;
    }

    /* This method finds and returns the largest value in the tree
     * 
     * Calls a recursive method to do it because we are doing lazy deletion.
     * Lazy deletion means that the actual max could be in the tree
     * 	but marked deleted, which means we can't return that as the max.
     * 
     */
    public int findMax() {
        return searchMax(this.root).key;        // calls the recursive function searchMax
    }

    /* This method finds and returns the smallest value in the tree
     * 
     * Calls a recursive method to do it because we are doing lazy deletion.
     * Lazy deletion means that the actual min could be in the tree
     * 	but marked deleted, which means we can't return that as the min.
     * 
     */
    public int findMin() {
        return searchMin(this.root).key;            // calls the recursive functino searchMin
        // TreeNode iterator = root;
        // int min = 0;
        // while (iterator.leftChild != null ) {
        //     iterator = iterator.leftChild;
        // }
        // min = iterator.key;
        // return min;
    }

    /* This method returns the height of the root node
     * 
     * Calls a recursive method to do it
     * 
     * Subtracts 1 before returning because the recursive method goes over by 1
     * Returns -1 if the tree is empty
     */
    public int height() {
        int height = 0;
        height = findHeight(this.root);
        return height - 1;
    }

    /* This method returns the size (number of nodes) of the tree
     * Size is updated as you insert things, so no need to iterate or recurse here.
     */
    public int size() {
        return this.size;
    }

    /* This method clears the tree by setting the root to null and it resets size to 0
     */
    public void clear() {
        this.size = 0;
        this.root = null;
    }

    /* This method returns a String representation of the tree
     * 
     * Uses a StringBuilder to collect all the info, and then converts it to a String after
     * Calls a recursive pre-order method with the StringBuilder as a parameter
     * 
     */
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        preOrderToString(this.root, strBuilder);
        strBuilder.deleteCharAt(strBuilder.length()-1);
        return strBuilder.toString();
    }
    
    /* This recursive method searches for a given key 
     * 	and returns the TreeNode with that key
     */
    private TreeNode search(TreeNode root, int key) {

        if (root != null) {
            if (root.key == key) {	// if the key equals root key stop recursing and return root
                return root;
            } else {
                TreeNode searchNode = search(root.leftChild, key);	// search left side first
                if (searchNode == null) {							// if left side returns null instead of root from above, search right
                    searchNode = search(root.rightChild, key);
                }
                return searchNode;									// return node if actually found
            }
        } else {
            return null;
        }
        
    }

    /* This recursive method searches for the maximum in the tree
     * and returns the max TreeNode
     * Called by the findMax() function
     */
    private TreeNode searchMax(TreeNode root) {
        if (root == null)		// if root null, stop recursing and return null
            return null;

        TreeNode tempNode = searchMax(root.rightChild);	// should theoretically return a maximum node

        if (tempNode != null)	// if the tempNode is not null, then return this and go up in the recursion
            return tempNode;

        if (!root.deleted)		// if the root is not deleted then return root
            return root;

        return searchMax(root.leftChild);	// if all fails check the left subtree
    }

    /* This recursive method searches for the maximum in the tree
     * and returns the max TreeNode
     * Called by the findMax() function
     */
    private TreeNode searchMin(TreeNode root/*, TreeNode currMin*/) {

        if (root == null) {		// if root null, stop recursing and return null
            return null;
        }

        TreeNode tempNode = searchMin(root.leftChild);	// should theoretically return a minimum node

        if (tempNode != null)	// if the tempNode is not null, then return this and go up in the recursion
            return tempNode;

        if (!root.deleted)		// if the root is not deleted then return root and go up in the recursion
            return root;

        return searchMin(root.rightChild);	// if all fails check the right subtrees

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

    /* This pre-order recursive method populates a StringBuilder with the String value of each TreeNode
     * Returns a String, but the strBuilder is what is of use here
     * When called by the toString(), a StringBuilder is passed in as a parameter
     * 	Since its pass by reference we can get the whole string out of that paramenter in the toString()
     */
    private String preOrderToString(TreeNode root, StringBuilder strBuilder) {
        if (root == null) {					// if root is null, append a "" and return to start going back up the recursion
            strBuilder.append("");
            return strBuilder.toString();
        }

        if (root.deleted) {						// if it's deleted then append the root and a * in front of it
            strBuilder.append("*");
            strBuilder.append(root.toString());
            strBuilder.append(" ");
        } else {
            strBuilder.append(root.toString());	// if not, only append the root
            strBuilder.append(" ");
        }
        
        preOrderToString(root.leftChild, strBuilder);		// go through the left tree first
        preOrderToString(root.rightChild, strBuilder);	// go through the right tree after
        													// makes it pre-order
        return strBuilder.toString();	// return
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
    
    /* This recursive method calculates the height of the node from the root
     * Called by the height() method
     */
    private int findHeight(TreeNode root) {
        if (root == null) {		// if root is null, return 0 and go back up the recursion
            return 0;
        } else {
            int heightLeft = findHeight(root.leftChild);		// get the height from the left subtree
            int heightRight = findHeight(root.rightChild);	// get the height from the right subtree

            // depending on which one is greater increment one of them
            if (heightLeft > heightRight) {
                return heightLeft + 1;
            } else {
                return heightRight + 1;
            }
        }
    }

    /* Each node of the tree is represented by this class
     * Has 4 data members, two constructors and a toString()
     */
    private class TreeNode {

        private int key;
        private TreeNode leftChild;
        private TreeNode rightChild;
        private boolean deleted;
        
        // Default constructor
        public TreeNode() { 
            this.leftChild = null;
            this.rightChild = null;
            this.deleted = false;
         }

        // Constructor with key parameter to set key at the start
        public TreeNode(int key) {
            this.leftChild = null;
            this.rightChild = null;
            this.deleted = false;
            this.key = key;
        }

        // String value of the key
        public String toString() {
            return String.valueOf(key);
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
