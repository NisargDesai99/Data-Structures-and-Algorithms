public class Duplicates {
    public static void main(String[] args) {
        SinglyLinked list = new SinglyLinked();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        list.add(10);
        list.add(10);
        list.add(10);

        SinglyLinked newList = list.removeDuplicates(list.getHead());
    }


}