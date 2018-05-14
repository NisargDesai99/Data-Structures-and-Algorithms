import java.io.File;
import java.util.ArrayList;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {

        FileIO dataFile = new FileIO(new File("flightData.txt"));
        FileIO queryFile = new FileIO(new File("query.txt"));

        ArrayList<String> dataFileArrLst = dataFile.readAsStringArrayList();
        FlightNetwork flightNetwork = new FlightNetwork(dataFileArrLst);

        // The graph initializes perfectly... at least the given case does.
        System.out.println(flightNetwork.toString());

        out.println(flightNetwork.testContains());

        ArrayList<String> queriesList = queryFile.readAsStringArrayList();
        int numQueries = queriesList.size();
        // for (int i = 0; i < numQueries; i++) {
        //     String query = queriesList.get(i);
        //     String cityOne = query.substring(0, query.indexOf("|"));
        //     String cityTwo = query.substring(query.indexOf("|") + 1, query.lastIndexOf("|"));
        //     String operation = query.substring(query.lastIndexOf("|") + 1, query.length());

        //     if (operation.equals("T")) {
        //         flightNetwork.getShortestPath(cityOne, cityTwo);
        //     } else if (operation.equals("C")) {
        //         flightNetwork.getCheapestPath(cityOne, cityTwo);
        //     }
        // }

    }
    
}



/*

Cities are vertices
Flights 
Graph is the connection between these two

*/