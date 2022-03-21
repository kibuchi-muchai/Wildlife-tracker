package DAO;

import models.Location;
import models.Sighting;
import models.SightingEndangeredSpecies;

import java.util.List;

public interface LocationDAO {
    //create List
    List<Location> getAllLocations();

    void addLocation(Location location);
    Location getLocationById(int id);
    List<Sighting> getSightingsByLocationId(int id);
    List<SightingEndangeredSpecies> getEndangeredSightingsByLocationId(int id);
}
