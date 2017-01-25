package shared.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class LogDataDAO {
    
    private LogDataDAO(){
        
    }
    
    /**
     * Insert tile ownership change log in which a player claims a neutral tile.
     * @param new_owner Player ID of the new tile owner.
     * @param new_tile Tile ID of the player's new tile.
     * @throws SQLException 
     */
    public static void createTileChangeLog(int new_owner, int new_tile) throws SQLException {
        createTileChangeLog(new_owner, new_tile, -1, -1);
    }
    
    /**
     * Insert tile ownership change log in which a player annexes a tile owned
     * by another player.
     * @param winner Player ID of the new tile owner.
     * @param winner_tile Tile ID of the player's new tile.
     * @param loser Player ID of the old tile owner.
     * @throws SQLException 
     */
    public static void createTileChangeLog(int winner, int winner_tile, int loser) throws SQLException {
        createTileChangeLog(winner, winner_tile, loser, -1);
    }
    
    /**
     * Insert tile ownership change log in which a player annexes a neutral tile
     * after winning a remote battle.
     * @param winner Player ID of the new tile owner.
     * @param winner_tile Tile ID of the player's new tile
     * @param loser Player ID of the player losing the remote battle.
     * @param loser_tile Tile ID of the neutral tile the loser attempted to
     * annex.
     * @throws SQLException 
     */
    public static void createTileChangeLog(
            int winner, 
            int winner_tile, 
            int loser, 
            int loser_tile
            ) throws SQLException {
        
        String query;
        
        if (loser >= 0) {
            if (loser_tile >= 0) {
                query = "INSERT INTO `log_tile_change` (`timestamp`, `winner_id`, `winner_tile`, `loser_id`, `loser_tile`) VALUES (?, ?, ?, ?, ?)";
            } else {
                query = "INSERT INTO `log_tile_change` (`timestamp`, `winner_id`, `winner_tile`, `loser_id`) VALUES (?, ?, ?, ?)";
            }
        } else {
            query = "INSERT INTO `log_tile_change` (`timestamp`, `winner_id`, `winner_tile`) VALUES (?, ?, ?)";
        }
        
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            
            pstmt.setTimestamp(1, new Timestamp(new Date().getTime()));
            pstmt.setInt(2, winner);
            pstmt.setInt(3, winner_tile);
            
            if (loser >= 0) {
                pstmt.setInt(4, loser);
            }
            
            if (loser_tile >= 0) {
                pstmt.setInt(5, loser_tile);
            }
            
            pstmt.executeQuery();
            
            pstmt.close();
            
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
    
    /**
     * Retrieve a CSV string of all tile ownership changes concerning a player
     * given their player ID.
     * @param player_id ID of the player requesting logs.
     * @return Returns a CSV string containing individual logs on each line with
     * all concerned tile and player IDs.
     * @throws SQLException 
     */
    public static String getTileChangeLogs(int player_id) throws SQLException {
        String result = "";
        String query = "SELECT * FROM `log_tile_change` WHERE `winner_id` = ? OR `loser_id` = ?";
        
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);

            pstmt.setInt(1, player_id);
            pstmt.setInt(2, player_id);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Timestamp time  = rs.getTimestamp("timestamp");
                int winner      = rs.getInt("winner_id");
                int winner_tile = rs.getInt("winner_tile");
                int loser       = rs.getInt("loser_id");
                int loser_tile  = rs.getInt("loser_tile");

                result += new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(time)
                          + ", " + Integer.toString(winner)
                          + ", " + Integer.toString(winner_tile);

                if (loser >= 0) {
                    result += ", " + Integer.toString(loser);
                }

                if (loser_tile >= 0) {
                    result += ", " + Integer.toString(loser_tile);
                }

                result += "\n";
            }

            rs.close();
            pstmt.close();

        } finally {
            if (con != null) {
                con.close();
            }
        }
        
        
        return result;
    }
}
