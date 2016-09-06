// @author Joseph Fernandez
// Database object for creating RunnerSpecies models (playable species).

package rr.db;

// Java imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

// Other imports
import rr.model.RunnerSpecies;
import shared.util.Log;

public final class RunnerSpeciesDAO {
    private RunnerSpeciesDAO() {;}
    
    // Load all runner species from DB.
    // @returns: hashmap of all runner species (species ID mapped to species)
    // @throws: SQLException
    public static Map<Integer, RunnerSpecies> getRunnerSpecies() throws SQLException
    {
        Map<Integer, RunnerSpecies> runnerSpeciesMap = new HashMap<Integer, RunnerSpecies>(); // map of available species to play as
        
        // SQL query to load all records of each species by species ID (join `runner_species` and `species` tables together)
        String query = "SELECT * FROM `runner_species` JOIN `species` ON `runner_species`.`species_id` = `species`.`species_id`";
        Connection connection = null; // DB connection
        PreparedStatement pstmt; // SQL execution
        
        try
        {
            Log.println("Connecting to database...");
            
            connection = DAO.getDataSource().getConnection();
            
            Log.println("Successfully connected to database.\n"
                    + "Loading RunnerSpecies data from database...");
            
            pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery(); // result of SQL execution
            
            RunnerSpecies rSpecies = null;
            int ID; // species ID
            
            // pull out all runner species from DB and define them
            while (rs.next())
            {
                if ((ID = rs.getInt("species_id")) > 0) // ID check; don't retrieve any records with invalid ID
                {
                    // define species
                    rSpecies = new RunnerSpecies(ID, rs.getInt("health"), rs.getFloat("speed"), rs.getFloat("jump"), rs.getFloat("power"), rs.getBoolean("flight"));
                    rSpecies.setName(rs.getString("name"));
                    rSpecies.setDescription(rs.getString("description"));
                    rSpecies.setDietType(rs.getShort("diet_type"));
                }
                
                // add defined species of map
                runnerSpeciesMap.put(ID, rSpecies);
            }
            
            Log.println("Successfully loaded RunnerSpecies data from database.\n"
                    + "Loading predators and prey data from database...");
            
            // all done with queries
            rs.close();
            pstmt.close(); 
            
            // load and define prey and predators of each species
            // refer to ConsumeDAO for prey and predator loading info
            Map<Integer, List<Integer>> predatorMap = ConsumeDAO.getPreyToPredatorTable(); // map of predators
            Map<Integer, List<Integer>> preyMap = ConsumeDAO.getPredatorToPreyTable(); // map of prey
            
            for (int runnerID : runnerSpeciesMap.keySet()) {
                rSpecies = runnerSpeciesMap.get(runnerID);
                
                // get list of predators
                if (predatorMap.containsKey(runnerID)) {
                    for (int predator_id : predatorMap.get(runnerID)) {
                        rSpecies.addPredatorID(predator_id);
                    }
                }
                
                // get list of prey
                if (preyMap.containsKey(runnerID)) {
                    for (int prey_id : preyMap.get(runnerID)) {
                        rSpecies.addPreyID(prey_id);
                    }
                }
            }
            
            Log.println("Successfully loaded predators and prey data from database.\n"
                    + "Queries finished. Disconnecting from database...");
        }
        catch (Exception e)
        {
            Log.println_e("Database error. Failed to load RunnerSpecies data.");
        }
        finally // close connection
        {
            if (connection != null)
            {
                connection.close();
                Log.println("Successfully disconnected from database.");
            }
        }
        
        return runnerSpeciesMap;
    }
}
