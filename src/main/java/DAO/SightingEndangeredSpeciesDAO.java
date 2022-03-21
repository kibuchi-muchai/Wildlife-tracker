package DAO;

import models.SightingEndangeredSpecies;

import java.util.List;

public interface SightingEndangeredSpeciesDAO {
    List<SightingEndangeredSpecies> getAllEndangered();
    void addEndangeredSpecies(SightingEndangeredSpecies sightingEndangeredSpecies);
}
