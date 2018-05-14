import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;
import java.lang.Comparable;

public class FlightNetwork {

    int numCities = 0;
    int numEdges = 0;
	ArrayList<LinkedList<Flight>> adjListArray = new ArrayList<LinkedList<Flight>>();
	HashMap<City, Integer> cityMap = new HashMap<>();
	HashMap<Integer, City> inverseCityMap = new HashMap<>();

	public FlightNetwork(ArrayList<String> flightData) {
		preProcessData(flightData); // returns a size
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
			City temp1 = new City(cityOne);
			adjListSize = addToMap(temp1, adjListSize);

			// get the second city and add it to the hash map if its not already in there
			cityTwo = data.substring(cityOne.length() + 1, data.indexOf("|", cityOne.length() + 1));
			City temp2 = new City(cityTwo);
			adjListSize = addToMap(temp2, adjListSize);

			// get the price and travel time and create a Flight object
			price = data.substring((cityOne.length() + cityTwo.length() + 2), data.lastIndexOf("|"));
			travelTime = data.substring((data.lastIndexOf("|") + 1), (data.length()));

			Flight flight = new Flight(cityOne, cityTwo, Integer.parseInt(price), Integer.parseInt(travelTime));
			addEdge(temp1, temp2, Integer.parseInt(price), Integer.parseInt(travelTime));
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

	private void addEdge(City src, City dest, int price, int travelTime) {

		// Add an edge from src to dest.
		// adjListArray[cityMap.get(src)].add(cityMap.get(dest), edge);
		// adjListArray.get(cityMap.get(src)).add(cityMap.get(dest), edge);

		LinkedList<Flight> cityNeighbors = adjListArray.get(cityMap.get(src));

		Flight edge = new Flight(src, dest, price, travelTime);

		int destIndex = cityMap.get(dest);
		if (cityNeighbors.size() > destIndex && cityNeighbors.get(destIndex) == null) {
			cityNeighbors.set(destIndex, edge);
			numFlights++;
			return;
		}

		int i = 0;
		while (i < cityNeighbors.size()) {
			i++;
		}
		while (i < destIndex) {
			cityNeighbors.add(i, null);
			i++;
		}

		cityNeighbors.add(destIndex, edge);
		numFlights++;

		// If graph is undirected, add an edge from dest
		// to src also
		// adjListArray[dest].addFirst(src);
	}

	private void initializeVertex(int adjListArrIndex) {
		// System.out.println(this.adjListArray.size() == 0);
		LinkedList<Flight> newList = new LinkedList<>();
		this.adjListArray.add(adjListArrIndex, newList);
	}

	private void addVertex(City city) {
		throw new UnsupportedOperationException();
	}

	private int addToMap(City city, int index) {
		if (!cityMap.containsKey(city)) {
            cityMap.put(city, index);
            inverseCityMap.put(index,city);
			initializeVertex(index);
			index++;
			numCities++;
		}
		return index;
	}

	public boolean containsVertex(City city) {
		if (cityMap.containsKey(city)) {
			return true;
		}
		return false;
	}

	public boolean containsEdge(Flight flight) {

		if (cityMap.get(flight.src) == null) {
			return false;
		}

		LinkedList<Flight> edgeList = adjListArray.get(cityMap.get(flight.src));

		if (edgeList == null) {
			return false;
		}

		if (edgeList.get(cityMap.get(flight.dest)) != null) {
			return true;
		} else {
			return false;
		}

	}

	public String testContains() {
		StringBuilder bldr = new StringBuilder();
		bldr.append(containsEdge(new Flight("Dallas", "Austin", 98, 47))).append("\n");
		bldr.append(containsEdge(new Flight("Chicago", "Austin", 155, 200))).append("\n");
		bldr.append(containsVertex(new City("Houston"))).append("\n");
		bldr.append(containsVertex(new City("Austin"))).append("\n");
		bldr.append(containsVertex(new City("New York City"))).append("\n");
		bldr.append(containsEdge(new Flight("New York City", "Austin", 200, 200))).append("\n");
		return bldr.toString();
	}

	public void getShortestPath(City src, City dest) {

        // // HashMap (String key for the city, boolean for visited or not)
        // HashMap<String, Boolean> visited = new HashMap<>();
        
        // Set<String> citiesSet = cityMap.keySet();
        // // ArrayList<Integer> valueSet = cityMap.values();
        
        // String[] citiesList = citiesSet.toArray();
        // for (int i = 0; i < cityMap.size(); i++) {
        //     visited.put(citiesList[i], false);
        // }

		PriorityQueue<City> priorityPath = new PriorityQueue<>();
        PriorityQueue<Flight> pQueue;
        ArrayList<City> cities = new ArrayList<>();

        for (int i = 0; i < inverseCityMap.size(); i++) {
            City c = inverseCityMap.get(i);
            c.distance = Integer.MAX_VALUE;
            c.visited = false;
            if (c.name.equals(src.name)) {
                c.distance = 0;
            }
            cities.add(i, c);
        }

        ArrayList<Flight> forPq = adjListArray.get(cityMap.get(src));
        pQueue = (!forPq.equals(null)) ? new PriorityQueue( forPq ) : null;
        if (pQueue.equals(null)) {
            return;
        }

        for (int count = 0; count < cities.size() - 1; i++) {
            Flight f = pQueue.poll();
            int indexFlight = findInList(cities, f);
            cities.get(indexFlight).visited = true;

            int counter = 0;
            for(City c : cities) {

                Flight flightCheck = forPq.get(cityMao.get(dest));

                if (!c.visited && flightCheck != null) {
                    
                }

                counter++;
            }

            for (int v = 0; v < cites.size(); i++) {
                if (!cities.get(v).visited && graph[u][v]!=0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u]+graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
            }


        }

    }

    private int findInList(ArrayList<Flight> arrList, Flight f) {
        int counter = 0;
        for (Flight flight : arrList) {
            if (flight.equals(f)) {
                return counter;
            }
            counter++;
        }

        return -1;
    }

	private int findMin() {
		throw new UnsupportedOperationException();
	}

	public void getCheapestPath(String src, String dest) {
		kjhjk
	}

	public String toString() {
		StringBuilder bldr = new StringBuilder();
		for (int v = 0; v < adjListArray.size(); v++) {
			bldr.append("Adjacency list of vertex " + v + " ");
			bldr.append("head");
			for (Flight pCrawl : this.adjListArray.get(v)) {
				bldr.append(" -> " + pCrawl).append(" ");
			}
			bldr.append("\n");
		}

		return bldr.toString();
	}

	private class Flight extends Comparable {

		City src;
		City dest;
		int price;
		int travelTime;

		public Flight(City src, City dest, int price, int travelTime) {
			this.src = src;
			this.dest = dest;
			this.price = price;
			this.travelTime = travelTime;
		}

		public int compareTo(Flight o) {
			if (this.travelTime < o.travelTime) {
                return -1;
            } else if (this.travelTime > o.travelTime) {
                return 1;
            } else {
                return 0;
            }
		}

		public String toString() {
			return this.price + ", " + this.travelTime;
		}

	}

}