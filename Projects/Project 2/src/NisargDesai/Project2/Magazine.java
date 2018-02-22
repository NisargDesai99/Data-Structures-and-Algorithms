package NisargDesai.Project2;

import static java.lang.System.out;

public class Magazine implements IDedObject {

    private int magazineId;
    private String magazineName;
    private String publisherName;

    // Empty constructor
    public Magazine() {  }

    // Constructor to initialize with values
    public Magazine(int magId, String magName, String publisherName) {
        this.magazineId = magId;
        this.magazineName = magName;
        this.publisherName = publisherName;
    }

    // Overrides the getID function declared by the IDedObject interface
    @Override
    public int getID() {
        return this.magazineId;
    }

    // Overrides the printID function declared by the IDedObject interface
    @Override
    public void printID() {
        out.println("Magazine ID: " + this.magazineId + "  ||  Magazine Name: " + this.magazineName + "  ||  Publisher Name: " + this.publisherName);
    }

    public void setID(int magId) {
        this.magazineId = magId;
    }

    public void setMagazineName(String name) {
        this.magazineName = name;
    }

    public void setPublisherName(String name) {
        this.publisherName = name;
    }

    public String getMagazineName() {
        return this.magazineName;
    }

    public String getPublisherName() {
        return this.publisherName;
    }

    /*public String toString() {
        return "Magazine ID: " + this.magazineId + "  ||  Magazine Name: " + this.magazineName + "  ||  Publisher Name: " + this.publisherName;
    }*/

}