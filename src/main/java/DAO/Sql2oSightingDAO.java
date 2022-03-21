package DAO;

import models.Sighting;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oSightingDAO implements SightingDAO{
    private final Sql2o sql2o;

    public Sql2oSightingDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Sighting> getNormal() {
        String sql = "SELECT * FROM sightings WHERE type = 'Non-Endangered'";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Sighting.class);
        }catch (Sql2oException ex){
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public void addNormal(Sighting sighting) {
        String sql = "INSERT INTO sightings(speciesName, rangerId, type, locationId) VALUES(:speciesName, :rangerId, :type, :locationId)";
        try (Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql, true)
                    .bind(sighting)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
            sighting.setId(id);

        }catch (Sql2oException ex){
            System.out.println(ex);
        }

    }


}
