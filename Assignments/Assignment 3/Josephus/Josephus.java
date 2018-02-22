public class Josephus {
    
    private int numPeople;
    private int skips;
    private CircularList people;
    private Person head;
    private Person tail;

    public Josephus() {
        people = new CircularList();
        numPeople = 0;
        skips = 0;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }

    public void setSkips(int skips) {
        this.skips = skips;
    }

    public int getNumPeople() {
        return this.numPeople;
    }

    public int getSkips() {
        return this.skips;
    }

    public void populate() {
        for (int i = 1; i < (numPeople + 1); i++) {
            people.add(i);
        }
    }

    public void skip() {
        int newSkips = skips;
        if (skips > numPeople) {
            newSkips = skips - numPeople;
        }
        for (int i = 0; i < skips; i++) {
            tail = head;
            head = head.next;
        }
    }

    public void execute() {
        tail = null;
        head = people.getHead();
        
        while (people.size() != 1) {
            skip();
            people.delete(head.data);
            head = head.next;
            numPeople--;
            display();
        }
    }

    public void display() {
        System.out.print("Alive:  ");
        people.displayList();
    }

}