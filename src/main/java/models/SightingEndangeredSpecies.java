package models;

public class SightingEndangeredSpecies extends Sighting {
    private String speciesAge;
    private String speciesHealth;
    private static final String TYPE = "Endangered";

    //generate constructors

    public SightingEndangeredSpecies(String speciesName, int rangerId, int locationId, String speciesAge, String speciesHealth) {
        super(speciesName, rangerId, locationId);
        this.speciesAge = speciesAge;
        this.speciesHealth = speciesHealth;
        this.setType(TYPE);
    }

    //generate setters and getters

    public String getSpeciesAge() {

        return speciesAge;
    }

    public void setSpeciesAge(String speciesAge) {

        this.speciesAge = speciesAge;
    }

    public String getSpeciesHealth() {

        return speciesHealth;
    }

    public void setSpeciesHealth(String speciesHealth) {

        this.speciesHealth = speciesHealth;
    }
}
