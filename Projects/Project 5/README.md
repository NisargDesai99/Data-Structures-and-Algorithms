# Project 5 - Quicksort

Project Objective: Write a java program to sort a list of integers using ‘in place’ Quicksort algorithm

Sort using the following types of pivots:

* First element as pivot
* Randomly choosing the pivot element
* Choosing the median of 3 randomly chosen lements as the pivot
* Median of first center and last element (book technique).

Record the sorting times for array sizes: 100, 1000, 5000, 10000 and 50000

After sorting, output the sorted and unsorted arrays to an output file.

Using the first element of the array as the pivot was generally faster than using other types of pivots.
Using the median of three random numbers was slower.

As the array sizes got bigger and bigger, the time to sort generally increased by ten times the previous sort time for the previous size. The sizes used to test were: 100, 1,000, 10,000, 100,000, 1,000,000, and 10,000,000.

To run this program:

1. Open Terminal/Command Line
2. Change directory to this project
3. javac Main.java
4. java Main
5. Look at unsorted.txt and sorted.txt to see the sorted and unsorted arrays
