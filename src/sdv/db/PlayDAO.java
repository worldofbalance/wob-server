
package sdv.db;

// Java imports
import sdv.PlayTime.PlayTimePlayer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sdv.model.Player;
import shared.metadata.Constants;
import shared.util.Log;

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
 * leaveMatch - updates tables when a player willingly leaves a match.

 */
public class PlayDAO {
    
    private PlayDAO(){}
    
    /**
     * creates a match record in the db, for starting a match.
     * @param playID the matchid to use for creation
     * @throws SQLException 
     */
    public static void createPlay(int playID) throws SQLException{
        Connection connection = null;
        PreparedStatement pstmt;
        
        String createPlayQuery = "INSERT INTO sdv_playlist VALUES (?,?) ",
        findExistingPlayQuery = "SELECT * FROM sdv_playlist WHERE play_id = ? ",
        updateNumPlayersQuery = "UPDATE sdv_playlist SET num_players = ? WHERE play_id = ? ";
        
        try
        {
            //Log.println("Connecting to database...");
            
            // establish connection to DB
            // pull out existing records with same race ID
            connection = GameDB.getConnection();
            
//            Log.println("Successfully connected to database.\n"
//                    + "Finding existence of record of play ID " + playID + "...");
            
            pstmt = connection.prepareStatement(findExistingPlayQuery);
            pstmt.setInt(1, playID); // ... WHERE `race_id` = ?
            ResultSet rs = pstmt.executeQuery();
            
            // if no existing race records with same race ID according to DB, create new race record with race ID in DB
            if (!rs.first())
            {
                Log.println("Record of play ID " + playID + " does not exist.\n"
                        + "Creating record of new play ID " + playID + "...");
                
                // create new race record in DB
                pstmt = connection.prepareStatement(createPlayQuery);
                pstmt.setInt(1, playID); // race_id
                pstmt.setInt(2, 2); // num_players
                
                // in case of failure to create record
                if (pstmt.executeUpdate() != 1)
                {
                    throw new Exception("Database error. Failed to create new play record of race ID " + playID + ".");
                }
                
                Log.println("Successfully created record of new play ID " + playID + ".");
            }
            
            // if a race record with same race ID exists, then just update number of players
            else
            {
                pstmt = connection.prepareStatement(updateNumPlayersQuery);
                pstmt.setInt(1, 2); // ... SET `num_players` = 2
                pstmt.setInt(2, playID); // ... WHERE `matchid` = ?

                Log.println("Updating number of players of play ID " + playID + "...");
                
                // execute query...
                if (pstmt.executeUpdate() != 1)
                {
                    // in case of failure to update record
                    throw new Exception("Database error. Failed to update number of players of play ID " + playID + ".");
                }

                Log.println("Successfully updated number of players of play ID " + playID + "...");
            }
            
            //Log.println("Queries finished. Disconnecting from database...");
            
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
                //Log.println("Successfully disconnected from database.");
            }
        }
    }
    /**
     * Creates a record for a player's initial match information.
     * @param playerID the player id of the person who is player one
     * @param playID the match the player is a part of
     * @param playerNumber determines starting position and direction
     * @throws SQLException 
     */
    public static void createPlayer(int playerID, int playID, int playerNumber) throws SQLException{
        /*
            putting a record of the players in the matchinfo_player table. 
        */
        Connection connection = null;
        PreparedStatement pstmt;
        
        String setupPlayQuery = "INSERT INTO sdv_playinfo_player VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try
        {
            //Log.println("Connecting to database...");
            
            // establish connection to DB
            // set up record for player in selected race (in `runner_raceinfo`)
            connection = GameDB.getConnection();
            
            Log.println("Successfully connected to database.\n"
                    + "Creating record for play ID " + playID + " of player ID " + playerID + "...");
            
            pstmt = connection.prepareStatement(setupPlayQuery);
            pstmt.setInt(1, playerID);
            pstmt.setInt(2, playID);
            
            // define remainined parameters - defaults
            // TODO: species_id - set to  88 (Nile Crocodile) as default temporarily until
            // new supporting data for aquatic animals are added
            // other values are invalid unless ID defined in `runner_species` and `species` - won't be able to insert records otherwise
            pstmt.setInt(3, 88);
            pstmt.setFloat(4, 0); // x_pos
            pstmt.setFloat(5, 0); // y_pos
            pstmt.setInt(6, 2); //direction default is right
            pstmt.setInt(7, 0); // score
            pstmt.setInt(8, 0); //stomach
            pstmt.setInt(9, 100); // health_max
            pstmt.setInt(10, 100); // health_current
            pstmt.setInt(11, 100); // stamina_max
            pstmt.setInt(12, 100); // stamina_current
            pstmt.setFloat(13, 1); // speed
            
            
            // execute query...
            if (pstmt.executeUpdate() != 1)
            {
                // in case of failure to update record
                throw new Exception("Database error. Failed to create record of play ID " + playID + " for player ID " + playerID + ".");
            }
            
//            Log.println("Successfully created record for play ID " + playID + " of player ID " + playerID + ".\n"
//                    + "Queries finished. Disconnecting from database...");
            
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
                //Log.println("Successfully disconnected from database.");
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
     * @param player the in-game player updating the database
     * @throws SQLException 
     */
    public static void updatePlay(PlayTimePlayer player) throws SQLException{
        
        Connection connection = null;
        PreparedStatement pstmt;
        int playerID, // ID of player
            playID; // ID of race that player is in
        
        String updatePlayTimePlayerQuery = "UPDATE sdv_playinfo_player "
                + "SET x_pos = ? , "
                + "y_pos = ? , "
                + "score = ? , "
                + "health_max = ? , "
                + "health_current = ? , "
                + "speed = ? , "
                + "direction = ? , "
                + "stomach = ? , "
                + "stamina_max = ? , "
                + "stamina_current = ? "
                + "WHERE player_id = ? ";
        
        try
        {
            //Log.println("Connecting to database...");
            
            // get player ID and ID of race that the player is in
            playerID = player.getPlayer_id();
            playID = player.getRaceID();
            
            // establish connection to DB
            // update record of player in DB accordingly
            connection = GameDB.getConnection();
            
            Log.println("Successfully connected to database.\n"
                    + "Updating record of play ID " + playID + " for player ID " + playerID + "...");
            
            pstmt = connection.prepareStatement(updatePlayTimePlayerQuery);
            pstmt.setFloat(1, player.getX()); // x_pos
            pstmt.setFloat(2, player.getY()); // y_pos
            
            // direction (0 = no side input, 1 = left, 2 = right)
            if (player.isLeft())
            {
                pstmt.setInt(7, 1);
            }
            else if (player.isRight())
            {
                pstmt.setInt(7, 2);
            }
            else
            {
                pstmt.setInt(7, 0);
            }
            
            
            
            pstmt.setFloat(3, player.getScore()); // score
            pstmt.setInt(4, player.getMaxHealth()); //maxHealth
            pstmt.setInt(5, player.getHealth()); // health
            pstmt.setFloat(6, player.getSpeed()); // speed
            pstmt.setInt(8, player.getStomach()); //stomach
            pstmt.setInt(9, player.getMaxStamina()); //max stamina
            pstmt.setInt(10, player.getStamina()); //stamina
            pstmt.setInt(11, playerID); // player_id
            
            // in case of failure to create record
            if (pstmt.executeUpdate() != 1)
            {
                throw new Exception("Database error. Failed to update record of player ID " + playerID + " in play ID " + player.getRaceID() + ".");
            }
            
            Log.println("Successfully updated record of play ID " + playID + " for player ID " + playerID + ".\n");
            
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
                //Log.println("Successfully disconnected from database.");
            }
        }
                
        
    }
    
    
    
    /**
     * updates db with end of match results. This can include winner, scores,
     * and other information for recording matches. Also removes temporary
     * match data from database.
     * @param playID the play being ended.
     * @param winner the player_id of the player that won the match.
     * @throws SQLException 
     */
    public static void endPlay(int playID, int winner, float finalScore) throws SQLException{
        Connection connection = null;
        PreparedStatement pstmt, pstmt1,pstmt2; 
        
        String updateRewardQuery = "UPDATE player SET credits=credits+? WHERE player_id=?";
        
        String removePlayQuery = "DELETE FROM sdv_playlist WHERE play_id=?";
        //  winner's id.
        String recordPlayQuery = "INSERT INTO sdv_playhistory (winner,final_score) VALUES (?,?)";
        
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        
        //Date date = new Date();
        //rewarding the winner credits
        if(winner > 0){
        try{
            connection = GameDB.getConnection();
            pstmt = connection.prepareStatement(updateRewardQuery);
            pstmt.setInt(1, Constants.CREDIT_REWARD); //credits to award
            pstmt.setInt(2, winner); 
            if(pstmt.executeUpdate() != 1){
                throw new SQLException("couldn't update player "+winner+" credits");
                
            }            
            Log.println("added 50 to player of id: "+winner);
        }catch(SQLException e){
            Log.println_e(e.getMessage());
        }
        
        try{
            pstmt2 = connection.prepareStatement(recordPlayQuery);
            pstmt2.setInt(1, winner);
            pstmt2.setFloat(2, finalScore);
            if(pstmt2.executeUpdate() != 1){
                throw new SQLException("could not insert record of match");
            }
            
        }catch(SQLException e){
            Log.println_e(e.getMessage());
        }
        
        //removing the play from the database, 
        try{
            pstmt1 = connection.prepareStatement(removePlayQuery);
            pstmt1.setInt(1, playID);
            if(pstmt1.executeUpdate() != 1){
                throw new SQLException("could not remove play "+playID+" from database");
            }
            Log.println("removed play "+playID+" from sdv_playlist");
        }catch(SQLException e){
            Log.println_e(e.getMessage());
        }
        
        finally
        {
            if (connection != null)
            {
                connection.close();
                //Log.println("Successfully disconnected from database.");
            }
        }
        
        Log.println("successfuly recorded match ending.");
        }
    }
    
    /**
     * updates db when a player leaves a match
     * @param playerID the in-game player's ID leaving the game
     * @param playID the game the player is leaving
     * @throws SQLException 
     */
    public static void leavePlay(int playerID, int playID) throws SQLException{
        Connection connection = null;
        PreparedStatement pstmt;
        int currentNumberOfPlayers; // number of players in the race that the player was in (including the player)
        
        // SQL query for deleting record of the race where the player was
        String deletePlayRecordQuery = "DELETE FROM sdv_playinfo_player WHERE player_id = ? ",
        
        // SQL query for getting number of players in the race that the player was in
        getNumPlayersQuery = "SELECT num_players FROM sdv_playlist WHERE play_id = ? ",
        
        // SQL query for updating number of players (after joining race)
        // parametrized to prevent SQL injection
        updateNumPlayersQuery = "UPDATE sdv_playlist SET num_players = ? WHERE play_id = ? ";
        
        try
        {
            //Log.println("Connecting to database...");
            
            // get player ID and ID of race that the player was in
            
            
            // establish connection to DB
            // delete race record of leaving player from `runner_raceinfo`
            connection = GameDB.getConnection();
            
            Log.println("Successfully connected to database.\n"
                    + "Deleting record of play ID " + playID + " for player ID " + playerID + "...");
            
            pstmt = connection.prepareStatement(deletePlayRecordQuery);
            pstmt.setInt(1, playerID);
            
            // in case deletion fails...
            if (pstmt.executeUpdate() != 1)
            {
                throw new Exception("Database error. Failed to delete record of play ID " + playID + " of player ID " + playerID + ".");
            }
            
            Log.println("Successfully deleted record of play ID " + playID + " for player ID " + playerID + ".");
            
            // get number of players (player included) in the race that the player was in
            pstmt = connection.prepareStatement(getNumPlayersQuery);
            pstmt.setInt(1, playID);
            ResultSet rs = pstmt.executeQuery();
            
            // in case record doesn't exist...
            if (!rs.first())
            {
                throw new Exception("Database error. Failed to find record of play ID + " + playID + ".");
            }
            
            currentNumberOfPlayers = rs.getInt("num_players"); // get number of players of selected race
            
            Log.println("Updating number of players of play ID " + playID + "...");
            
            // update number of players of race record (in `sdv_playlist`)
            pstmt = connection.prepareStatement(updateNumPlayersQuery);
            
            if (currentNumberOfPlayers == 2) // one more player in play
            {
                pstmt.setInt(1, 1); // ... SET `num_players` = 1
            }
            else if (currentNumberOfPlayers == 1) // race is now empty
            {
                pstmt.setInt(1, 0); // ... SET `num_players` = 0
            }
            else
            {
                throw new Exception("Database error. Invalid number of players in record of play ID " + playID + "."); // problem in accessing DB
            }
            
            pstmt.setInt(2, playID); // only set change to record that has ID of selected race
            
            // in case of failure to update record
            if (pstmt.executeUpdate() != 1)
            {
                throw new Exception("Database error. Failed to update record of play ID " + playID + ".");
            }
            
            Log.println("Successfully updated number of players of race ID " + playID + "...\n"
                    + "Queries finished. Disconnecting from database...");
              
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
                //Log.println("Successfully disconnected from database.");
            }
        }
    }
    
    
}
