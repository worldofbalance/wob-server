
package db;

// Java imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.Log;
import db.GameDB;
/**
 *
 * @author Karl Bigley-Bodlak
 * NOTE: right now this heavily borrows from running rhino, but only for creating
 * a base for a real-time multiplayer game. This will not be a copy of running rhino's
 * code, as the needs of this game differ.
 * 
 * createMatch - creates a record in DB for a new match
 * createPlayerRecord - creates a record for a player in a match (x_pos, y_pos, etc.)
 * setPlayerSpecies - writes a player's selected species to DB
 * updateMatch - updates data (x_pos, y_pos, etc.) in DB of a player during a match
 * endMatch - updates account data (experience, currency, etc.) in DB of a player when finishing a match

 */
public class PlayDAO {
    
    private PlayDAO(){}
    
    /**
     * creates a match record in the db, for starting a match.
     * @param matchID the matchid to use for creation
     * @throws SQLException 
     */
    public static void createMatch(int matchID) throws SQLException{
        Connection connection = null;
        PreparedStatement pstmt;
        
        String createMatchQuery = "INSERT INTO 'sdv_matchlist' VALUES (?,?)",
        findExistingMatchQuery = "SELECT * FROM 'sdv_matchlist' WHERE 'match_id' = ?",
        updateNumPlayersQuery = "UPDATE `sdv_matchlist` SET `num_players` = ? WHERE `match_id` = ?";
        
        try
        {
            Log.println("Connecting to database...");
            
            // establish connection to DB
            // pull out existing records with same race ID
            connection = GameDB.getConnection();
            
            Log.println("Successfully connected to database.\n"
                    + "Finding existence of record of race ID " + matchID + "...");
            
            pstmt = connection.prepareStatement(findExistingMatchQuery);
            pstmt.setInt(1, matchID); // ... WHERE `race_id` = ?
            ResultSet rs = pstmt.executeQuery();
            
            // if no existing race records with same race ID according to DB, create new race record with race ID in DB
            if (!rs.first())
            {
                Log.println("Record of race ID " + matchID + " does not exist.\n"
                        + "Creating record of new race ID " + matchID + "...");
                
                // create new race record in DB
                pstmt = connection.prepareStatement(createMatchQuery);
                pstmt.setInt(1, matchID); // race_id
                pstmt.setInt(2, 2); // num_players
                
                // in case of failure to create record
                if (pstmt.executeUpdate() != 1)
                {
                    throw new Exception("Database error. Failed to create new race record of race ID " + matchID + ".");
                }
                
                Log.println("Successfully created record of new race ID " + matchID + ".");
            }
            
            // if a race record with same race ID exists, then just update number of players
            else
            {
                pstmt = connection.prepareStatement(updateNumPlayersQuery);
                pstmt.setInt(1, 2); // ... SET `num_players` = 2
                pstmt.setInt(2, matchID); // ... WHERE `matchid` = ?

                Log.println("Updating number of players of race ID " + matchID + "...");
                
                // execute query...
                if (pstmt.executeUpdate() != 1)
                {
                    // in case of failure to update record
                    throw new Exception("Database error. Failed to update number of players of race ID " + matchID + ".");
                }

                Log.println("Successfully updated number of players of race ID " + matchID + "...");
            }
            
            Log.println("Queries finished. Disconnecting from database...");
            
            // all done with queries
            rs.close();
            pstmt.close();
        }
        
        // catch any exceptions when connecting to database
        catch (Exception e)
        {
            Log.println_e(e.getMessage());
        }
        
        // close connection
        finally
        {
            if (connection != null)
            {
                connection.close();
                Log.println("Successfully disconnected from database.");
            }
        }
    }
    /**
     * Creates a record for a player's initial match information.
     * @param playerID the player id of the person who is player one
     * @param matchID the match the player is a part of
     * @param playerNumber determines starting position
     * @throws SQLException 
     */
    public static void createPlayer(int playerID, int matchID, int playerNumber) throws SQLException{
        /*
            putting a record of the players in the matchinfo_player table. 
        */
        Connection connection = null;
        PreparedStatement pstmt;
        
        String setupRaceQuery = "INSERT INTO `sdv_matchinfo_player` VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try
        {
            Log.println("Connecting to database...");
            
            // establish connection to DB
            // set up record for player in selected race (in `runner_raceinfo`)
            connection = GameDB.getConnection();
            
            Log.println("Successfully connected to database.\n"
                    + "Creating record for match ID " + matchID + " of player ID " + playerID + "...");
            
            pstmt = connection.prepareStatement(setupRaceQuery);
            pstmt.setInt(1, playerID);
            pstmt.setInt(2, matchID);
            
            // define remainined parameters - defaults
            // species_id - set to  88 (Nile Crocodile) as default temporarily until
            // new supporting data for aquatic animals are added
            // other values are invalid unless ID defined in `runner_species` and `species` - won't be able to insert records otherwise
            pstmt.setInt(3, 88);
            pstmt.setFloat(4, 0); // x_pos
            pstmt.setFloat(5, 0); // y_pos
            pstmt.setInt(6, 0); // score
            pstmt.setInt(7, 100); // health_max
            pstmt.setInt(8, 100); // health_current
            
            
            // execute query...
            if (pstmt.executeUpdate() != 1)
            {
                // in case of failure to update record
                throw new Exception("Database error. Failed to create record of race ID " + matchID + " for player ID " + playerID + ".");
            }
            
            Log.println("Successfully created record for race ID " + matchID + " of player ID " + playerID + ".\n"
                    + "Queries finished. Disconnecting from database...");
            
            // all done with queries
            pstmt.close();
        }
        
        // catch any exceptions when connecting to database
        catch (Exception e)
        {
            Log.println_e(e.getMessage());
        }
        
        // close connection
        finally
        {
            if (connection != null)
            {
                connection.close();
                Log.println("Successfully disconnected from database.");
            }
        }
        
    }
    /**
     * sets the chosen species of the player.
     * @throws SQLException 
     */
    public static void setPlayerSpecies(/*sdvPlayer player (an extension of shared.model.player)*/) throws SQLException{
        //will be implemented once the ocean species are added to the species table and supported by game.
    }
    /**
     * updates a player's data during a match.  not position, only saved data
     * @throws SQLException 
     */
    public static void updateMatch(/*sdvPlayer player*/) throws SQLException{
        
    }
    
    
    /**
     * updates db with end of match results. This can include winner, scores,
     * and other information for recording matches. Also removes temporary
     * match data from database.
     * @throws SQLException 
     */
    public static void endMatch() throws SQLException{
        
    }
    
    
}
