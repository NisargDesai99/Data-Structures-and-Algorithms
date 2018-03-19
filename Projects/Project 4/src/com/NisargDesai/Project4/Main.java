import java.io.File;
import java.util.ArrayList;
import static java.lang.System.out;
import java.lang.String;

public class Main {

	static boolean INTEGER = false;
	static boolean STRING = true;

    public static void main(String[] args) {
	    
    	String inputFileName = "";
    	String outputFileName = "";

		// Make sure that the user sent in input and output file locations
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
		fileOut.clearFile();	// make sure that the output file is clear before adding to it

		// read in commands from the input file
    	ArrayList<String> commandsList = fileIn.readAsStringArrayList();

		// make sure that the input file had commands in it
    	if (commandsList.size() == 0) {
    		out.println("The input file you entered was empty.\n" +
    					"Make sure you run the program in the following format:\n" +
    					"javac Main.java\n" +
    					"java Main <input file> <output file>");
    		System.exit(10);
		}
		
		RedBlackTree<String> stringRedBlackTree = null;
		RedBlackTree<Integer> integerRedBlackTree = null;

    	StringBuilder fileOutput = new StringBuilder();

		/*
			if the first line in the commands list is "Integer"
				call the run method with the Integer RedBlackTree
			if the first line in the commands list is "String"
				call the run method with the String RedBlackTree
			The run method is a helper method that gets the results of the commands
		*/
    	if (commandsList.get(0).equals("Integer")) {
    		integerRedBlackTree = new RedBlackTree<>();
			if (commandsList.get(1) == null || commandsList.get(1) == "") {
				out.println("No commands to read. Try again with more commands.");
			}
			commandsList.remove(0);
    		fileOutput = run(integerRedBlackTree, INTEGER, commandsList);
    	} else if (commandsList.get(0).equals("String")) {
			stringRedBlackTree = new RedBlackTree<>();
			if (commandsList.get(1) == null || commandsList.get(1) == "") {
				out.println("No commands to read. Try again with more commands.");
			}
			commandsList.remove(0);
			fileOutput = run(stringRedBlackTree, STRING, commandsList);
    	}

		// write results to file
		fileOut.overwriteToFile((fileOutput.deleteCharAt(fileOutput.length()-1)).toString());
		
		// notify user that output was sent to the file
    	out.println("Output sent to file: " + outputFileName + " in the same directory as Main.java.");
    }

	// run the commands from the commands list read in the main method
    private static StringBuilder run(RedBlackTree tree, boolean type, ArrayList<String> commands) {

    	StringBuilder results = new StringBuilder();

    	for (String command : commands) {
			StringBuilder tempBldr = new StringBuilder();
			tempBldr = runCommand(tree, command, type);
			results.append(tempBldr);
		}

    	return results;
	}
	
	/*
		checks input type and performs operation of the command
		Commands include:
			Insert
			Contains
			PrintTree
	*/
	@SuppressWarnings("unchecked")
	private static StringBuilder runCommand(RedBlackTree tree, String str, boolean inputType) {
		StringBuilder results = new StringBuilder();

		try {
			if (str.contains("Insert")) {
				if (!str.contains(":")) {
					results.append("Error in Line: ").append(str).append("\n");
					return results;
				}
				String inputItem = str.substring(str.lastIndexOf(":")+1, str.length());
				boolean isInserted;
				isInserted = tree.insert( (inputType == STRING) ? inputItem : Integer.parseInt(inputItem) );
				results.append( (isInserted) ? "True" : "False").append("\n");
			} else if (str.contains("Contains")) {
				if (!str.contains(":")) {
					results.append("Error in Line: ").append(str).append("\n");
					return results;
				}
				String checkItem = str.substring(str.lastIndexOf(":")+1, str.length());
				boolean itemExists;
				itemExists = tree.contains( (inputType == STRING) ? checkItem : Integer.parseInt(checkItem) );
				results.append( (itemExists) ? "True" : "False").append("\n");
				return results;
			} else if (str.equals("PrintTree")) {
				results.append(tree.toString()).append("\n");
				return results;
			} else {
				results.append("Error in Line: ").append(str).append("\n");
				return results;
			}
		} catch (NullPointerException nullPtrEx) {
			results.append("Error in ").append(str.substring(0, str.indexOf(":")).toLowerCase()).append(": NullPointerException raised\n");
			return results;
		}
		return results.append("");
	}


}
