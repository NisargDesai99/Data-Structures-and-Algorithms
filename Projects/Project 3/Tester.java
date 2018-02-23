import static java.lang.System.out;

public class Tester {
    public static void main(String[] args) {

        LazyBinarySearchTree balancedTesterBST = new LazyBinarySearchTree();
        LazyBinarySearchTree unbalancedTesterBST = new LazyBinarySearchTree();
        
        // TEST INSERT
        out.println("-----INSERT-----");
        out.println("Edge cases");
        try {
            out.println("Inserting 500: " + balancedTesterBST.insert(500));
        } catch (Exception ex) {
            if (ex instanceof IllegalArgumentException)
                out.println("IllegalArgumentException caught.");
        }
        try {
            out.println("Inserting -10: " + balancedTesterBST.insert(-10));
        } catch (Exception ex) {
            if (ex instanceof IllegalArgumentException)
                out.println("IllegalArgumentException caught.");
        }
        out.println("Creating tree: 50 25 20 30 75 70 80");
        out.println("Insert 50: " + balancedTesterBST.insert(50));
        out.println("Insert 25: " + balancedTesterBST.insert(25));
        out.println("Insert 75: " + balancedTesterBST.insert(75));
        out.println("Try insert 25 again: " + balancedTesterBST.insert(25));
        out.println("Insert 20: " + balancedTesterBST.insert(20));
        out.println("Insert 30: " + balancedTesterBST.insert(30));
        out.println("Insert 70: " + balancedTesterBST.insert(70));
        out.println("Insert 80: " + balancedTesterBST.insert(80));
        out.println("Try insert 30 again: " + balancedTesterBST.insert(30));
        out.println("Current Tree (Pre-order): " + balancedTesterBST);

        // TEST DELETE
        out.println("-----DELETE-----");
        out.println("Delete 30: " + balancedTesterBST.delete(30));
        out.println("Delete 80: " + balancedTesterBST.delete(80));
        out.println("Current Tree (Pre-order): " + balancedTesterBST);

        // TEST CONTAINS
        out.println("-----CONTAINS-----");
        out.println("Contains 30?: " + balancedTesterBST.contains(30));
        out.println("Contains 80?: " + balancedTesterBST.contains(80));
        out.println("Contains 20?: " + balancedTesterBST.contains(20));
        out.println("Contains 50?: " + balancedTesterBST.contains(50));
        out.println("-----RE-INSERT from DELETE-----");
        out.println("Insert 30: " + balancedTesterBST.insert(30));
        out.println("Insert 80: " + balancedTesterBST.insert(80));
        out.println("Current Tree (Pre-order): " + balancedTesterBST);

        // TEST FIND MIN
        out.println("-----FINDMIN-----");
        // out.println("Delete 20: " + balancedTesterBST.delete(20));
        out.println("Delete 25: " + balancedTesterBST.delete(25));
        out.println("Current Tree (Pre-order): " + balancedTesterBST);
        out.println("Minimum: " + balancedTesterBST.findMin());

        // SEPARATE BALANCED AND UNBALANCED TREES
        out.println("Reinsert 25: " + balancedTesterBST.insert(25));

        out.println("Creating new unbalanced tree");
        out.println("Insert 50: " + unbalancedTesterBST.insert(50));
        out.println("Insert 25: " + unbalancedTesterBST.insert(25));
        out.println("Insert 75: " + unbalancedTesterBST.insert(75));
        out.println("Insert 20: " + unbalancedTesterBST.insert(20));
        out.println("Insert 30: " + unbalancedTesterBST.insert(30));
        out.println("Insert 70: " + unbalancedTesterBST.insert(70));
        out.println("Insert 80: " + unbalancedTesterBST.insert(80));
        out.println("Insert 77: " + unbalancedTesterBST.insert(77));
        out.println("Insert 78: " + unbalancedTesterBST.insert(78));
        out.println("Insert 29: " + unbalancedTesterBST.insert(29));
        out.println("Insert 32: " + unbalancedTesterBST.insert(32));
        out.println("Insert 31: " + unbalancedTesterBST.insert(31));
        out.println("Insert 40: " + unbalancedTesterBST.insert(40));
        out.println("Insert 28: " + unbalancedTesterBST.insert(28));
        out.println("Insert 27: " + unbalancedTesterBST.insert(27));
        out.println("Insert 26: " + unbalancedTesterBST.insert(26));
        out.println("New unbalanced tree: " + unbalancedTesterBST);

        // TEST FIND MAX
        out.println("-----FINDMAX-----");
        out.println("Using unbalanced tree");
        out.println("Delete 75: " + unbalancedTesterBST.delete(75));
        out.println("Delete 80: " + unbalancedTesterBST.delete(80));
        out.println("Current Tree (Pre-order): " + unbalancedTesterBST);
        out.println("Maximum: " + unbalancedTesterBST.findMax());

        // TEST HEIGHT
        out.println("-----HEIGHT-----");
        out.println("Reinsert 75: " + unbalancedTesterBST.insert(75));
        out.println("Reinsert 80: " + unbalancedTesterBST.insert(80));
        out.println("Balanced Tree (Pre-order): " + balancedTesterBST);
        out.println("Unbalanced Tree (Pre-order): " + unbalancedTesterBST);
        out.println("Balanced: " + balancedTesterBST.height());     // works for the 50 25 20 30 75 70 80 case because it is balanced
        out.println("Unbalanced: " + unbalancedTesterBST.height());

        // // TEST SIZE
        out.println("-----SIZE-----");
        out.println("Balanced Tree size: " + balancedTesterBST.size());
        out.println("Unbalanced Tree size: " + unbalancedTesterBST.size());

        // TEST FILEIO STUFF


    }
}