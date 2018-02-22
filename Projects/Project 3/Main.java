import java.lang.System;
import java.io.File;
import java.util.Scanner;
import static java.lang.System.out;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        LazyBinarySearchTree myLazyBST = new LazyBinarySearchTree();
        
        Scanner scanner = new Scanner(System.in);
        
        String inputFileName = "";
        out.print("Enter the name of the input file: ");
        while (!inputFileName.contains(".txt")) {
        		inputFileName = scanner.nextLine();
        		if (!inputFileName.contains(".txt")) { out.println("Invalid filename. Try again."); }
        }
        
        String outputFileName = "output.txt";
        out.println("Output sent to file: " + outputFileName);
        
        FileIO fileIn = new FileIO(new File(inputFileName));
        FileIO fileOut = new FileIO(new File(outputFileName));
        ArrayList<String> list = fileIn.readAsStringArrayList();
        StringBuilder strBuilder = new StringBuilder();

        for (String str : list) {
            boolean inInsertMethod = false;
            boolean inDeleteMethod = false;
            boolean inContainsMethod = false;

            try {
                if (str.contains("Insert")) {
                		// out.println("Length " + str.length() + " " + "Index of ':' : " + str.indexOf(":"));
                		if (str.indexOf(":") == -1) {
                			strBuilder.append("Error in Line: " + str + "\n");
                			continue;
                		}
                    inInsertMethod = true;
                    boolean isInserted = myLazyBST.insert(Integer.parseInt(str.substring(str.lastIndexOf(":")+1, str.length())));
                    strBuilder.append((isInserted) ? "True":"False");
                    strBuilder.append("\n");
                    inInsertMethod = false;
                } else if (str.contains("Delete")) {
	                	if (str.indexOf(":") == -1) {
	            			strBuilder.append("Error in Line: " + str + "\n");
	            			continue;
	            		}
                    inDeleteMethod = true;
                    boolean isDeleted = myLazyBST.delete(Integer.parseInt(str.substring(str.lastIndexOf(":")+1, str.length()))); 
                    strBuilder.append((isDeleted) ? "True":"False");
                    strBuilder.append("\n");
                    inDeleteMethod = false;
                } else if (str.contains("Contains")) {
	                	if (str.indexOf(":") == -1) {
	            			strBuilder.append("Error in Line: " + str + "\n");
	            			continue;
	            		}
                    inContainsMethod = true;
                    boolean isContain = myLazyBST.contains(Integer.parseInt(str.substring(str.lastIndexOf(":")+1, str.length()))); 
                    strBuilder.append((isContain) ? "True":"False");
                    strBuilder.append("\n");
                    inContainsMethod = false;
                } else if (str.contains("Max")) {
                    // out.println(myLazyBST.findMax());
                    strBuilder.append(myLazyBST.findMax() + "\n");
                } else if (str.contains("Min")) {
                    // out.println(myLazyBST.findMin());
                    strBuilder.append(myLazyBST.findMin() + "\n");
                } else if (str.equals("Size")){
                		// out.println(myLazyBST.size());
                		strBuilder.append(myLazyBST.size() + "\n");
                } else if (str.equals("Height")) {
                    // out.println(myLazyBST.height());
                    strBuilder.append(myLazyBST.height() + "\n");
                } else if (str.equals("PrintTree")) {
                    // out.println(myLazyBST);
                    strBuilder.append(myLazyBST.toString() + "\n");
                } else {
                    // out.println("Error in Line: " + str);
                    strBuilder.append("Error in Line: " + str + "\n");
                }
            } catch (Exception ex) {
                if (ex instanceof IllegalArgumentException) {
                    if (inInsertMethod) {
                        // out.println("Error in insert: IllegalArgumentException raised");
                        strBuilder.append("Error in insert: IllegalArgumentException raised\n");
                    } else if (inDeleteMethod) {
                        // out.println("Error in delete: IllegalArgumentException raised");
                        strBuilder.append("Error in delete: IllegalArgumentException raised\n");
                    } else if (inContainsMethod) {
                        // out.println("Error in contains: IllegalArgumentException raised");
                        strBuilder.append("Error in contains: IllegalArgumentException raised\n");
                    }
                }
            }
        }

        fileOut.overwriteToFile((strBuilder.deleteCharAt(strBuilder.length()-1)).toString());
    }
}