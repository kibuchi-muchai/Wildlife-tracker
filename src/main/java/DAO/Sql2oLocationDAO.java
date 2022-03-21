package DAO;

import models.Location;
import models.Sighting;
import models.SightingEndangeredSpecies;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oLocationDAO implements LocationDAO{
    private final Sql2o sql2o;

    public Sql2oLocationDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Location> getAllLocations() {
        String sql = "SELECT * FROM locations";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .executeAndFetch(Location.class);
        }catch (Sql2oException ex){
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public void addLocation(Location location) {
        String sql = "INSERT INTO locations (locationName) VALUES (:locationName)";
        try (Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql, true)
                    .bind(location)
                    .executeUpdate()
                    .getKey();
            location.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public Location getLocationById(int id) {
        String sql = "SELECT * FROM locations WHERE id=:id";
        try (Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Location.class);
        } catch (Sql2oException ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public List<Sighting> getSightingsByLocationId(int id) {
        String sql = "SELECT * FROM sightings WHERE type = 'Non-Endangered' AND locationId=:id";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Sighting.class);
        }catch(Sql2oException ex){
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public List<SightingEndangeredSpecies> getEndangeredSightingsByLocationId(int id) {
        String sql = "SELECT * FROM sightings WHERE type ='Endangered' AND locationId=:id";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(SightingEndangeredSpecies.class);
        }catch(Sql2oException ex) {
            System.out.println(ex);
            return null;
        }
    }
}
