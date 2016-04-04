
package sdv.db;

// Java imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import shared.util.Log;
import shared.db.GameDB;
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
public class matchDAO {
    
    private matchDAO(){}
    
    /**
     * creates a match record in the db, for starting a match.
     * @param matchID the matchid to use for creation
     * @throws SQLException 
     */
    public static void createMatch(int matchID) throws SQLException{
        
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
    }
    /**
     * sets the chosen species of the player.
     * @throws SQLException 
     */
    public static void setPlayerSpecies(/*sdvPlayer player (an extension of shared.model.player)*/) throws SQLException{
        
    }
    /**
     * updates a player's data during a match, including position of a player.
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
