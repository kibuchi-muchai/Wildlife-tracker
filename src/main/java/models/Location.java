package models;

public class Location {
    //create properties
    private int id;
    private String locationName;

    //generate constructor
    public Location(String locationName) {

        this.locationName = locationName;
    }

    // generate setters and getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
