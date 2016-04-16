
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import model.*;
import PlayTime.PlayTimePlayer;
import utility.Log;
/**
 *  This will retrieve information about a game from the db.
 * @author Karl
 */
public class PlayInfoDAO {
    private PlayInfoDAO(){}
    
    /**
     * Retrieves in-game information about all players in a game.
     * @param play_id the play the players are in.
     * @return a HashMap holding player_id -> PlayTimePlayer 
     * @throws SQLException
     */
    public static Map<Integer, PlayTimePlayer> getAllPlayersInfo(int play_id) throws SQLException{
        Map<Integer, PlayTimePlayer> playerMap = new HashMap<>();
        Connection connection = null;
        PreparedStatement pstmt;
        
        return playerMap;
    }
    
    /**
     * Retrieves information about a single player in a game
     * @param player_id the id of the player to be retrieved
     * @return the PlayTimePlayer object holding database values.
     */
    public static PlayTimePlayer getPlayerInfo(int player_id) throws SQLException{
        Connection connection = null;
        PreparedStatement pstmt;
        PlayTimePlayer player = new PlayTimePlayer(player_id);
        String getPlayerInfoQuery = "SELECT play_id,species_id,x_pos,y_pos,score,stomach,health_max,"
                + "health_current,stamina_max,stamina_current,speed "
                + "FROM sdv_playinfo_player "
                + "WHERE player_id=? ";
        
        try{
            //Log.println("getting player "+player_id+" from database.");
            connection = GameDB.getConnection();
            pstmt = connection.prepareStatement(getPlayerInfoQuery);
            pstmt.setInt(1,player_id);
            ResultSet rs;
            rs = pstmt.executeQuery();
            rs.next();
            player.setRaceID(rs.getInt("play_id"));
            player.setRunnerSpeciesID(rs.getInt("species_id"));
            player.setX(rs.getFloat("x_pos"));
            player.setY(rs.getFloat("y_pos"));
            player.setScore(rs.getInt("score"));
            player.setStomach(rs.getInt("stomach"));
            player.setMaxHealth(rs.getInt("health_max"));
            player.setHealth(rs.getInt("health_current"));
            player.setMaxStamina(rs.getInt("stamina_max"));
            player.setStamina(rs.getInt("stamina_current"));
            player.setSpeed(rs.getFloat("speed"));
            Log.println("retrieved player data.");
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
        
        return player;
    }
    
}
