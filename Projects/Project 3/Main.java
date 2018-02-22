import java.lang.System;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import static java.lang.System.out;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        LazyBinarySearchTree myLazyBST = new LazyBinarySearchTree();
        FileIO fileIn = new FileIO(new File("input.txt"));
        FileIO fileOut = new FileIO(new File("output.txt"));
        ArrayList<String> list = fileIn.readAsStringArrayList();
        StringBuilder strBuilder = new StringBuilder();

        for (String str : list) {
            boolean inInsertMethod = false;
            boolean inDeleteMethod = false;
            boolean inContainsMethod = false;

            try {
                if (str.contains("Insert")) {
                    inInsertMethod = true;
                    strBuilder.append(myLazyBST.insert(Integer.parseInt(str.substring(str.lastIndexOf(":")+1, str.length()))) + "\n");
                    inInsertMethod = false;
                } else if (str.contains("Delete")) {
                    inDeleteMethod = true;
                    strBuilder.append(myLazyBST.delete(Integer.parseInt(str.substring(str.lastIndexOf(":")+1, str.length()))) + "\n");
                    inDeleteMethod = false;
                } else if (str.contains("Contains")) {
                    inContainsMethod = true;
                    strBuilder.append(myLazyBST.contains(Integer.parseInt(str.substring(str.lastIndexOf(":")+1, str.length()))) + "\n");
                    inContainsMethod = false;
                } else if (str.contains("Max")) {
                    out.println(myLazyBST.findMax());
                    strBuilder.append(myLazyBST.findMax() + "\n");
                } else if (str.contains("Min")) {
                    out.println(myLazyBST.findMin());
                    strBuilder.append(myLazyBST.findMin() + "\n");
                } else if (str.equals("Height")) {
                    out.println(myLazyBST.height());
                    strBuilder.append(myLazyBST.height() + "\n");
                } else if (str.equals("PrintTree")) {
                    out.println(myLazyBST);
                    strBuilder.append(myLazyBST.toString() + "\n");
                } else {
                    out.println("Error in line: " + str);
                    strBuilder.append("Error in line: " + str + "\n");
                }
            } catch (Exception ex) {
                if (ex instanceof IllegalArgumentException) {
                    if (inInsertMethod) {
                        out.println("Error in insert: IllegalArgumentException raised");
                        strBuilder.append("Error in insert: IllegalArgumentException raised\n");
                    } else if (inDeleteMethod) {
                        out.println("Error in delete: IllegalArgumentException raised");
                        strBuilder.append("Error in delete: IllegalArgumentException raised\n");
                    } else if (inContainsMethod) {
                        out.println("Error in contains: IllegalArgumentException raised");
                        strBuilder.append("Error in contains: IllegalArgumentException raised\n");
                    }
                }
            }
        }

        fileOut.overwriteToFile(strBuilder.toString());
    }
}