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

// RECOLOR ONLY CASE
//        redBlackTree.insert(50);
//        out.println(redBlackTree);
//        redBlackTree.insert(25);
//        out.println(redBlackTree);
//        redBlackTree.insert(75);
//        out.println(redBlackTree);
//        redBlackTree.insert(12);
//        out.println(redBlackTree);
//        redBlackTree.insert(37);
//        out.println(redBlackTree);
//        redBlackTree.insert(6);
//        out.println(redBlackTree);

        // RECOLOR AND RIGHT RIGHT ROTATION WITH ROOT CASE
//        redBlackTree.insert(10);
//        redBlackTree.insert(20);
//        redBlackTree.insert(30);
//        redBlackTree.insert(15);

        // MY TEST CASE
//        out.println("insert 50");
//        redBlackTree.insert(50);
//        out.println(redBlackTree);
//
//        out.println("insert 25");
//        redBlackTree.insert(25);
//        out.println(redBlackTree);
//
//        out.println("insert 75");
//        redBlackTree.insert(75);
//        out.println(redBlackTree);
//
//        out.println("insert 12");
//        redBlackTree.insert(12);
//        out.println(redBlackTree);
//
//        out.println("insert 6");
//        redBlackTree.insert(6);
//        out.println(redBlackTree);
//
//        out.println("insert 18");
//        redBlackTree.insert(18);
//        out.println(redBlackTree);
//
//        out.println("insert 37");
//        redBlackTree.insert(37);
//        out.println(redBlackTree);
//
//        out.println("insert 31");
//        redBlackTree.insert(31);
//        out.println(redBlackTree);
//
//        out.println("insert 43");
//        redBlackTree.insert(43);
//        out.println(redBlackTree);
//
//        out.println("insert 40");
//        redBlackTree.insert(40);
//        out.println(redBlackTree);
//
//        out.println("RESULT: " + redBlackTree);
//
//        out.println("Contains 43?: " + redBlackTree.contains(43));
//        out.println("Contains 99?: " + redBlackTree.contains(99));
//
//        out.println("insert 50");
//        redBlackTree.insert(50);
//        out.println(redBlackTree);
//
//        out.println("insert 25");
//        redBlackTree.insert(25);
//        out.println(redBlackTree);
//
//        out.println("insert 75");
//        redBlackTree.insert(75);
//        out.println(redBlackTree);
//
//        out.println("insert 12");
//        redBlackTree.insert(12);
//        out.println(redBlackTree);
//
//        out.println("insert 37");
//        redBlackTree.insert(37);
//        out.println(redBlackTree);
//
//        out.println("insert 6");
//        redBlackTree.insert(6);
//        out.println(redBlackTree);
//
//        out.println("insert 18");
//        redBlackTree.insert(18);
//        out.println(redBlackTree);
//
//        out.println("insert 31");
//        redBlackTree.insert(31);
//        out.println(redBlackTree);
//
//        out.println("insert 43");
//        redBlackTree.insert(43);
//        out.println(redBlackTree);
//
//        out.println("insert 40");
//        redBlackTree.insert(40);
//        out.println(redBlackTree);
//
//        out.println("RESULT: " + redBlackTree);

        // EXAMPLE INPUT 1
//        out.println("insert 98");
//        redBlackTree.insert(98);
//        out.println(redBlackTree);
//
//        out.println("insert -68");
//        redBlackTree.insert(-68);
//        out.println(redBlackTree);
//
//        out.println("insert 55");
//        redBlackTree.insert(55);
//
//        // redBlackTree.testPrint();
//
//        out.println(redBlackTree);
//
//        out.println("insert 45");
//        redBlackTree.insert(45);
//        out.println(redBlackTree);
//
//        // PrintTree command
//
//        out.println("Contains 45?: " + redBlackTree.contains(45));
//
//        out.println("insert 84");
//        redBlackTree.insert(84);
//        out.println(redBlackTree);
//
//        out.println("insert 32");
//        redBlackTree.insert(32);
//        out.println(redBlackTree);
//
//        out.println("insert 132");
//        redBlackTree.insert(132);
//        out.println(redBlackTree);
//
//        out.println("Contains 45?: " + redBlackTree.contains(45));
//        out.println("Contains 32?: " + redBlackTree.contains(32));
//
//        out.println("insert 45");
//        redBlackTree.insert(45);
//        out.println(redBlackTree);
//
//        out.println("RESULT: " + redBlackTree);
    }
}
