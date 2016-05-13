
package sdv.db;

// Java imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import sdv.model.SDSpecies;
import shared.util.Log;
/**
 *  This loads species information for the game from the play_species table.
 * @author Karl
 */
public class SDSpeciesDAO {
    
    private SDSpeciesDAO(){}
    
    
    public static Map<Integer, SDSpecies> getSDSpecies() throws SQLException{
        Map<Integer, SDSpecies> playSpecies = new HashMap<Integer, SDSpecies>();
        Connection connection = null;
        PreparedStatement pstmt;
        
        String getSDSpeciesQuery = "SELECT * FROM sdv_species";
        try{
            connection = GameDB.getConnection();
            pstmt = connection.prepareStatement(getSDSpeciesQuery);
            ResultSet rs;
            rs = pstmt.executeQuery();
            while(rs.next()){
                SDSpecies s = new SDSpecies(rs.getInt("species_id"),rs.getInt("health_max"),rs.getFloat("speed"), rs.getInt("stamina_max"),rs.getFloat("power"));
                playSpecies.put(s.getSpeciesId(), s);
            }
            Log.println("retrieved species data for SeaDivided");
        }catch(Exception e){
            Log.println_e(e.getMessage());
        }
        finally // close connection
        {
            if (connection != null)
            {
                connection.close();
                Log.println("Successfully disconnected from database.");
            }
        }
        return playSpecies;
    }
    
}
