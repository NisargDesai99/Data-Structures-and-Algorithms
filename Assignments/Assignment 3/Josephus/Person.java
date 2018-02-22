public class Person {
    
    int data;
    Person next;
    
    public Person() {  }

    public Person(int data) {
        this.data = data;
    }

    public String toString() {
        return String.valueOf(this.data);
    }

}