/* Name:				Nisarg Desai
 * Filename:			Main.java
 * Project:			Project 3
 * 
 * Purpose: 	Runner file for Project 3. 
 * 			Reads given input file for operations on a Lazy Binary Search Tree 
 * 			Performs operations from the file
 * 			Outputs results to an output file
 */


import java.lang.System;
import java.io.File;
import java.util.Scanner;
import static java.lang.System.out;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        LazyBinarySearchTree myLazyBST = new LazyBinarySearchTree();
        
        String inputFileName = "";
        String outputFileName = "";
        
        /*
         * Check if they entered something as an argument or not
         * If not, ask them to enter a new path/filename and to re-run the program
         * Set the inputFileName to args[0] if it exists
         */
        if (args.length < 2) {
        		out.println("You must enter a path or a filename for BOTH input AND output.\nPlease re-run the program.");
        		System.exit(10);
        } else {
        		inputFileName = args[0];
        		outputFileName = args[1];
        }
        
        /*
         * All results will be output to output.txt in the directory that Main.java is in
         * 
         * FileIO is a class that allows you to read and write to text files
         * 
         * fileIn is for the input file - commandsList is an String Array List for all the lines of the input file
         * fileOut is for writing the output
         * 
         * results is a StringBuilder that collects all the output for the input commands
         * 	this can later be converted to toString() and written to the output file
         */
        
        FileIO fileIn = new FileIO(new File(inputFileName));
        FileIO fileOut = new FileIO(new File(outputFileName));
        ArrayList<String> commandsList = fileIn.readAsStringArrayList();
        StringBuilder results = new StringBuilder();

        /*
         * Null check here because:
         * 	readAsStringArrayList() tries to open the input file name
         * 	If that file does not exist or has a different name, the file does not open
         * 		causing readAsStringArrayList() to return null
         */
        if (commandsList == null) {
        		System.exit(10);
        }
        
        
        /*
         * Since each index of the commandsList holds one line, 
         * 	we can foreach through it and parse each line 
         * 	and perform the operation specified on that line.
         */
        for (String str : commandsList) {
            // boolean inInsertMethod = false;
            // boolean inDeleteMethod = false;
            // boolean inContainsMethod = false;
            
            // I didn't want three booleans... so I used a Boolean bc it can have 3 values: null, true, false
            Boolean bool = null;
            
            /*
             * The insert(key), delete(key), and contains(key) methods 
             * 	throw exceptions if the key is invalid
             * 	so we wrap everything in a try catch 
             * 	and use the booleans above in the catch block to check which method the exception came from
             */
            try {
                if (str.contains("Insert")) {
                    if (str.indexOf(":") == -1) {	// makes sure that the Insert command in the file has a parameter
                        results.append("Error in Line: " + str + "\n");
                        continue;
                    }
                    // inInsertMethod = true;
                    bool = null;
                    boolean isInserted = myLazyBST.insert(Integer.parseInt(str.substring(str.lastIndexOf(":")+1, str.length())));
                    results.append((isInserted) ? "True":"False");
                    results.append("\n");
                    // inInsertMethod = false;
                } else if (str.contains("Delete")) {
                    if (str.indexOf(":") == -1) {	// makes sure that the Delete command in the file has a parameter
                        results.append("Error in Line: " + str + "\n");
                        continue;
                    }
                    // inDeleteMethod = true;
	                
	                bool = true;
                    boolean isDeleted = myLazyBST.delete(Integer.parseInt(str.substring(str.lastIndexOf(":")+1, str.length()))); 
                    results.append((isDeleted) ? "True":"False");
                    results.append("\n");
                    // inDeleteMethod = false;
                } else if (str.contains("Contains")) {
                    if (str.indexOf(":") == -1) {	// makes sure that the Contains command in the file has a parameter
                        results.append("Error in Line: " + str + "\n");
                        continue;
                    }
                    // inContainsMethod = true;
	                bool = false;
                    boolean isContain = myLazyBST.contains(Integer.parseInt(str.substring(str.lastIndexOf(":")+1, str.length()))); 
                    results.append((isContain) ? "True":"False");
                    results.append("\n");
                    // inContainsMethod = false;
                } else if (str.contains("Max")) {
                	results.append(myLazyBST.findMax() + "\n");
                } else if (str.contains("Min")) {
                	results.append(myLazyBST.findMin() + "\n");
                } else if (str.equals("Size")){
            		results.append(myLazyBST.size() + "\n");
                } else if (str.equals("Height")) {
            		results.append(myLazyBST.height() + "\n");
                } else if (str.equals("PrintTree")) {
            		results.append(myLazyBST.toString() + "\n");
                } else {
            		results.append("Error in Line: " + str + "\n");
                }
            } catch (Exception ex) {		// catch exceptions thrown by insert, delete or contains and print appropriate error message
                if (ex instanceof IllegalArgumentException) {
                    results.append("Error in " + str.substring(0, str.indexOf(":")).toLowerCase() + ": IllegalArgumentException raised\n");
                    /*if (bool == null) {
                        results.append("Error in insert: IllegalArgumentException raised\n");
                    } else if (!bool) {
                        results.append("Error in contains: IllegalArgumentException raised\n");
                    } else if (bool) {
                        results.append("Error in delete: IllegalArgumentException raised\n");
                    }*/
                    /*if (inInsertMethod) {
                    		results.append("Error in insert: IllegalArgumentException raised\n");
                    } else if (inDeleteMethod) {
                    		results.append("Error in delete: IllegalArgumentException raised\n");
                    } else if (inContainsMethod) {
                    		results.append("Error in contains: IllegalArgumentException raised\n");
                    }*/
                }
            }
        }
        
        // Write the results string builder to the output file in the same directory as Main.java
        fileOut.overwriteToFile((results.deleteCharAt(results.length()-1)).toString());
        
        // Print output file name to console
        out.println("Output sent to file: " + outputFileName + " in the same directory as Main.java");
    }
}