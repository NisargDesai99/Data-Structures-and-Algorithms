import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class FlightNetwork {

    private int numCities;
    private int numFlights;
    ArrayList<LinkedList<Flight>> adjListArray = new ArrayList<LinkedList<Flight>>();
    HashMap<String, Integer> cityMap = new HashMap<>();

    public FlightNetwork(ArrayList<String> flightData) {
        preProcessData(flightData);     // returns a size
    }

    private void addEdge(String src, String dest, Flight edge) {
        
        // Add an edge from src to dest.
        // adjListArray[cityMap.get(src)].add(cityMap.get(dest), edge);
        // adjListArray.get(cityMap.get(src)).add(cityMap.get(dest), edge);

        LinkedList<Flight> cityNeighbors = adjListArray.get(cityMap.get(src));

        int destIndex = cityMap.get(dest);
        if (cityNeighbors.size() > destIndex && cityNeighbors.get(destIndex) == null) {
            cityNeighbors.set(destIndex, edge);
            numFlights++;
            return;
        }

        int i = 0;
        while (i < cityNeighbors.size()) { i++; }
        while (i < destIndex) { cityNeighbors.add(i, null); i++; }

        cityNeighbors.add(destIndex, edge);
        numFlights++;

        // If graph is undirected, add an edge from dest
        // to src also
        // adjListArray[dest].addFirst(src);
    }

    private void addVertex(String newCity, int adjListArrIndex) {
        System.out.println(this.adjListArray.size() == 0);
        LinkedList<Flight> newList = new LinkedList<>();
        this.adjListArray.add(adjListArrIndex, newList);
    }

    private int preProcessData(ArrayList<String> fileData) {
        
        int adjListSize = 0;
        int size = fileData.size();
        String data, cityOne, cityTwo, price, travelTime;
        for (int i = 0; i < size; i++) {
            
            // get a data line from the array
            data = fileData.get(i);

            // get the first city and add it to the hash map if its not already in there
            cityOne = data.substring(0, data.indexOf("|"));
            adjListSize = addToMap(cityOne, adjListSize);

            // get the second city and add it to the hash map if its not already in there
            cityTwo = data.substring(cityOne.length() + 1, data.indexOf("|", cityOne.length() + 1));
            adjListSize = addToMap(cityTwo, adjListSize);

            // get the price and travel time and create a Flight object
            price = data.substring((cityOne.length() + cityTwo.length() + 2), data.lastIndexOf("|"));
            travelTime = data.substring((data.lastIndexOf("|") + 1), (data.length()));

            Flight flight = new Flight(Integer.parseInt(price), Integer.parseInt(travelTime));
            addEdge(cityOne, cityTwo, flight);
        }

        // System.out.println(this);

        // adjListSize is how large our array of linked lists should be
        // at this point all the cities are in the hashmap
        // and that hashmap holds the indices of all of the cities in the array of linked lists
        // now all that we need to do is populate the linked lists in the array with the proper Flight objects

        // for (int i = 0; i < size; i++) {
            
        //     // get a data line from the array
        //     data = fileData.get(i);

        //     // get the first city
        //     cityOne = data.substring(0, data.indexOf("|"));

        //     // get the second city
        //     cityTwo = data.substring(cityOne.length() + 1, data.indexOf("|", cityOne.length() + 1));

        //     // get the price and travel time and create a Flight object
        //     price = data.substring((cityOne.length() + cityTwo.length() + 2), data.lastIndexOf("|"));
        //     travelTime = data.substring((data.lastIndexOf("|") + 1), (data.length()));

        //     Flight flight = new Flight(Integer.parseInt(price), Integer.parseInt(travelTime));
        //     addEdge(cityOne, cityTwo, flight);
        // }

        return adjListSize;
    }

    private int addToMap(String city, int index) {
        if (!cityMap.containsKey(city)) {
            cityMap.put(city, index);
            addVertex(city, index);
            index++;
            numCities++;
        }
        return index;
    }

    public void getShortestPath(String src, String dest) {

    }

    public void getCheapestPath(String src, String dest) {

    }

    public String toString() {
        StringBuilder bldr = new StringBuilder();
        for (int v = 0; v < adjListArray.size(); v++) {
            bldr.append("Adjacency list of vertex " + v);
            bldr.append("head");
            for (Flight pCrawl : this.adjListArray.get(v)) {
                bldr.append(" -> " + pCrawl).append(" ");
            }
            bldr.append("\n");
        }

        return bldr.toString();
    }

    private class Flight {
        int price;
        int travelTime;

        public Flight(int price, int travelTime) {
            this.price = price;
            this.travelTime = travelTime;
        }

        public String toString() {
            return this.price + ", " + this.travelTime;
        }
    }

}