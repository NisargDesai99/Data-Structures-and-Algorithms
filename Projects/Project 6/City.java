public class City {
    
    public String name;
    public City previous;
    public boolean visited;
    public int distance;

    public City(String cityName) {
        this.name = cityName;
    }

    public String tosString() {
        return this.name;
    }

}