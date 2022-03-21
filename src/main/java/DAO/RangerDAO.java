package DAO;

import models.Ranger;
import models.Sighting;
import models.SightingEndangeredSpecies;

import java.util.List;

public interface RangerDAO {
    //List
    List<Ranger> getAllRangers();
    void addRanger(Ranger ranger);
    Ranger getRangerById(int id);
    List<Sighting> getSightingsByRangerId(int id);
    List<SightingEndangeredSpecies> getEndangeredSightingsByRangerId(int id);
}
