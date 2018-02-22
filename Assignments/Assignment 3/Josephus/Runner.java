import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        
        ArrayList<Integer> list = new ArrayList<>();

        Josephus kill = new Josephus();

        kill.setNumPeople(10);
        
        kill.populate();
        kill.display();
        
        kill.setSkips(2);
        kill.execute();
    }
}