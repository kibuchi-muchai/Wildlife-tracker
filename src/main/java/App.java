import DAO.Sql2oLocationDAO;
import DAO.Sql2oRangerDAO;
import DAO.Sql2oSightingDAO;
import DAO.Sql2oSightingEndangeredSpeciesDAO;
import models.Location;
import models.Ranger;
import models.Sighting;
import models.SightingEndangeredSpecies;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {

        port(getHerokuAssignedPort());

        staticFileLocation("/public");
        //String connectionString = "jdbc:postgresql://localhost:5432/animal";
        String connectionString = "jdbc:postgresql://ec2-3-210-23-22.compute-1.amazonaws.com:5432/dbtopfbspaho5k";
        //Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access");
        Sql2o sql2o = new Sql2o(connectionString, "ipnozpdkiqbvuj", "32bd6602fc46b2fc2150c47aa6c3b9623772df8e57ec5b9bd33ad83eb1a1c840");
        Sql2oLocationDAO locationDAO = new Sql2oLocationDAO(sql2o);
        Sql2oRangerDAO rangerDAO = new Sql2oRangerDAO(sql2o);
        Sql2oSightingDAO sightingDAO = new Sql2oSightingDAO(sql2o);
        Sql2oSightingEndangeredSpeciesDAO sightingEndangeredSpeciesDAO = new Sql2oSightingEndangeredSpeciesDAO(sql2o);

        //Declare the Map<> globally to DRY the code
        Map<String, Object> model = new HashMap<>();

        //Start writing the path codes
        get("/",  (request, response) -> {
            model.put("normalSightings", sightingDAO.getNormal());
            model.put("endangeredSightings", sightingEndangeredSpeciesDAO.getAllEndangered());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

//locations code
        get("/locations", (request, response) -> {
            model.put("locations", locationDAO.getAllLocations());
            return new ModelAndView(model, "location.hbs");
        }, new HandlebarsTemplateEngine());

        get("/addlocation", (request, response) -> {
            return new ModelAndView(model, "location-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/addlocation", (request, response) -> {
            String name = request.queryParams("locationName");
            Location newLocation = new Location(name);
            locationDAO.addLocation(newLocation);
            model.put("locations", locationDAO.getAllLocations());
            return new ModelAndView(model, "location.hbs");

        }, new HandlebarsTemplateEngine());

        //rangers code
        get("/rangers", (request, response) -> {
            model.put("rangers", rangerDAO.getAllRangers());
            return new ModelAndView(model, "rangers.hbs");
        }, new HandlebarsTemplateEngine());

        get("/addranger", (request, response) -> {
            return new ModelAndView(model, "rangers-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/addranger",(req, res)->{
            String name = req.queryParams("name");
            int badge = Integer.parseInt(req.queryParams("badge"));
            String contact = req.queryParams("contact");
            Ranger newRanger = new Ranger(name,badge,contact);
            rangerDAO.addRanger(newRanger);
            model.put("rangers", rangerDAO.getAllRangers());
            return new ModelAndView(model,"rangers.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightnormal", (req, res)->{
            model.put("rangers", rangerDAO.getAllRangers());
            model.put("locations", locationDAO.getAllLocations());
            return new ModelAndView(model, "sightings-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightnormal", (req, res) -> {
            String speciesName = req.queryParams("name");
            int rangerId = Integer.parseInt(req.queryParams("ranger"));
            int locationId = Integer.parseInt(req.queryParams("location"));
            Sighting newSighting = new Sighting(speciesName, rangerId, locationId);
            sightingDAO.addNormal(newSighting);
            model.put("endangeredSightings", sightingEndangeredSpeciesDAO.getAllEndangered());
            model.put("normalSightings", sightingDAO.getNormal());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightendangered", (req, res)->{
            model.put("endangered", true);
            model.put("rangers", rangerDAO.getAllRangers());
            model.put("locations", locationDAO.getAllLocations());
            return new ModelAndView(model, "sightings-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightendangered", (req, res) -> {
            String speciesName = req.queryParams("name");
            int rangerId = Integer.parseInt(req.queryParams("ranger"));
            int locationId = Integer.parseInt(req.queryParams("location"));
            String speciesAge = req.queryParams("age");
            String speciesHealth = req.queryParams("health");
            SightingEndangeredSpecies newSightingEndangeredSpecies = new SightingEndangeredSpecies(speciesName, rangerId, locationId, speciesAge, speciesHealth);
            sightingEndangeredSpeciesDAO.addEndangeredSpecies(newSightingEndangeredSpecies);
            model.put("endangeredSightings", sightingEndangeredSpeciesDAO.getAllEndangered());
            model.put("normalSightings", sightingDAO.getNormal());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/rangers/:id",(req, res)->{
            int id = Integer.parseInt(req.params("id"));
            model.put("ranger", rangerDAO.getRangerById(id));
            model.put("endangeredSightings", rangerDAO.getEndangeredSightingsByRangerId(id));
            model.put("normalSightings", rangerDAO.getSightingsByRangerId(id));
            return new ModelAndView(model,"ranger-details.hbs");
        }, new HandlebarsTemplateEngine());

        get("/locations/:id",(req, res)->{
            int id = Integer.parseInt(req.params("id"));
            model.put("location", locationDAO.getLocationById(id));
            model.put("endangeredSightings", locationDAO.getEndangeredSightingsByLocationId(id));
            model.put("normalSightings", locationDAO.getSightingsByLocationId(id));
            return new ModelAndView(model,"location-details.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
