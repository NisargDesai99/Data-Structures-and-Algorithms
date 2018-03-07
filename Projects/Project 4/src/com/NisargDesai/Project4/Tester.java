package com.NisargDesai.Project4;

import static java.lang.System.out;

public class Tester {
    public static void main(String[] args) {

        RedBlackTree<Integer> redBlackTree = new RedBlackTree<>();

        // TEST INSERT
        out.println("-----INSERT-----");
        try {
            out.println(redBlackTree.insert(null));
        } catch (Exception ex) {
            if (ex instanceof NullPointerException) {
                out.println("Error in Line: Insert");
            }
        }

        redBlackTree.insert(50);
        out.println(redBlackTree);
        redBlackTree.insert(25);
        out.println(redBlackTree);
        redBlackTree.insert(75);
        out.println(redBlackTree);
        redBlackTree.insert(12);
        out.println(redBlackTree);
        redBlackTree.insert(37);
        out.println(redBlackTree);

    }
}
