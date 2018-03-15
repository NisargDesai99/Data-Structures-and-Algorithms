// package com.NisargDesai.Project4;

import static java.lang.System.out;

public class MainTest {

    public static void main(String[] args) {

        RedBlackTree<Integer> rbt = new RedBlackTree<>();

        out.println(rbt.insert(98));
        out.println(rbt.insert(-68));
        out.println(rbt.insert(55));

        out.println(rbt);
    }

}
