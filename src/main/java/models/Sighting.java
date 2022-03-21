package models;

public class Sighting {


    //create properties
    private int id;
    private String speciesName;
    private int rangerId;
    private int locationId;
    private String type;
    private static final String TYPE = "Non-Endangered";

    //generate constructor


    public Sighting(String speciesName, int rangerId, int locationId) {
        this.speciesName = speciesName;
        this.rangerId = rangerId;
        this.locationId = locationId;
        this.type = TYPE;
    }


    //generate setters and getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public int getRangerId() {
        return rangerId;
    }

    public void setRangerId(int rangerId) {
        this.rangerId = rangerId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
