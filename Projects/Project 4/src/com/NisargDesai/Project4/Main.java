// package com.NisargDesai.Project4;

import java.io.File;
import java.util.ArrayList;
import static java.lang.System.out;

public class Main {

	static boolean INTEGER = false;
	static boolean STRING = true;

    public static void main(String[] args) {
	    
    	String inputFileName = "";
    	String outputFileName = "";

    	if (args.length < 2) {
    		out.println("Please enter input AND output file location.\n" +
    					"Make sure you run the program in the following format:\n" +
    					"javac Main.java\n" +
    					"java Main <input file> <output file>");
    		System.exit(10);
    	} else {
    		inputFileName = args[0];
    		outputFileName = args[1];
    	}

    	FileIO fileIn = new FileIO(new File(inputFileName));
    	FileIO fileOut = new FileIO(new File(outputFileName));

    	ArrayList<String> commandsList = fileIn.readAsStringArrayList();

    	RedBlackTree<String> stringRedBlackTree = null;
    	RedBlackTree<Integer> integerRedBlackTree = null;

    	if (commandsList == null) {
    		out.println("The input file you enetered was empty.\n" +
    					"Make sure you run the program in the following format:\n" +
    					"javac Main.java\n" +
    					"java Main <input file> <output file>");
    		System.exit(10);
    	}

    	StringBuilder fileOutput = new StringBuilder();

    	if (commandsList.get(0).equals("Integer")) {
    		integerRedBlackTree = new RedBlackTree<>();
    		commandsList.remove(0);
    		fileOutput = run(integerRedBlackTree, INTEGER, commandsList);
    	} else if (commandsList.get(0).equals("String")) {
    		stringRedBlackTree = new RedBlackTree<>();
    		// fileOutput = run(stringRedBlackTree, STRING, commandsList);
    	}

    	fileOut.overwriteToFile((fileOutput.deleteCharAt(fileOutput.length()-1)).toString());

    	out.println("Output sent to file: " + outputFileName + " in the same directory as Main.java.");

    }

    static StringBuilder run(RedBlackTree<Integer> tree, boolean type, ArrayList<String> commands) {

    	StringBuilder results = new StringBuilder();

    	for (String str : commands) {
    		try {
    			if (str.contains("Insert")) {
    				if (!str.contains(":")) {
    					results.append("Error in Line: ").append(str).append("\n");
    					continue;
    				}
    				String inputItem = str.substring(str.lastIndexOf(":")+1, str.length());
    				boolean isInserted;
    				isInserted = tree.insert(Integer.parseInt(inputItem));
    				// isInserted = tree.insert( (type == STRING) ? inputItem : Integer.parseInt(inputItem) );
    				results.append( (isInserted) ? "True" : "False").append("\n");
    			} else if (str.contains("Contains")) {
    				if (!str.contains(":")) {
    					results.append("Error in Line: ").append(str).append("\n");
    					continue;
    				}
    				String checkItem = str.substring(str.lastIndexOf(":")+1, str.length());
    				boolean itemExists;
    				itemExists = tree.insert(Integer.parseInt(checkItem));
    				// itemExists = tree.contains( (type == STRING) ? checkItem : Integer.parseInt(checkItem) );
    				results.append( (itemExists) ? "True" : "False").append("\n");
    			} else if (str.equals("PrintTree")) {
    				results.append(tree.toString()).append("\n");
    			} else {
    				results.append("Error in Line: ").append(str).append("\n");
    			}
    		} catch (NullPointerException nullPtrEx) {
    			results.append("Error in ").append(str.substring(0, str.indexOf(":")).toLowerCase()).append(": NullPointerException raised\n");
    		}
    	}

    	return results;
    }
}
