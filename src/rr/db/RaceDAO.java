// DAO for creating Race instances.
// @author: Joseph Fernandez

package rr.db;

// Java imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import rr.metadata.Constants;

// Other imports
import rr.race.RacePlayer;
import shared.util.Log;

// List of methods (excluding constructor):
// - createRace - create record in DB for a new race
// - createPlayerRecord - create record for player data in race (x_pos, y_pos, etc.)
// - setPlayerSpecies - write player's selected species to DB
// - updateRace - update data (x_pos, y_pos, etc.) in DB of player during race
// - finishRace - update account data (experience, currency, etc.) in DB of player when finishing race
// - leaveRace - update data, remove records of race and player in DB when player leaves

public final class RaceDAO {

    private RaceDAO() {;}
    
    // records to DB for player creating a race
    // @param: raceID - ID of race to be created
    // @throws: SQLException
    public static void createRace(int raceID) throws SQLException
    {
        Connection connection = null;
        PreparedStatement pstmt;
        
        // SQL query for finding existing race record with same ID
        // parametrized to prevent SQL injection
        String findExistingRaceQuery = "SELECT * FROM `runner_racelist` WHERE `race_id` = ?",
                
        // SQL query for creating new record for race (if no record of race with same ID exists)
        // parametrized to prevent SQL injection
        createRaceQuery = "INSERT INTO `runner_racelist` VALUES (?, ?)",        
        
        // SQL query for updating number of players
        // parametrized to prevent SQL injection
        updateNumberOfPlayersQuery = "UPDATE `runner_racelist` SET `num_players` = ? WHERE `race_id` = ?";
        
        try
        {
            Log.println("Connecting to database...");
            
            // establish connection to DB
            // pull out existing records with same race ID
            connection = DAO.getDataSource().getConnection();
            
            Log.println("Successfully connected to database.\n"
                    + "Finding existence of record of race ID " + raceID + "...");
            
            pstmt = connection.prepareStatement(findExistingRaceQuery);
            pstmt.setInt(1, raceID); // ... WHERE `race_id` = ?
            ResultSet rs = pstmt.executeQuery();
            
            // if no existing race records with same race ID according to DB, create new race record with race ID in DB
            if (!rs.first())
            {
                Log.println("Record of race ID " + raceID + " does not exist.\n"
                        + "Creating record of new race ID " + raceID + "...");
                
                // create new race record in DB
                pstmt = connection.prepareStatement(createRaceQuery);
                pstmt.setInt(1, raceID); // race_id
                pstmt.setInt(2, Constants.MAX_NUMBER_OF_PLAYERS); // num_players
                
                // in case of failure to create record
                if (pstmt.executeUpdate() != 1)
                {
                    throw new Exception("Database error. Failed to create new race record of race ID " + raceID + ".");
                }
                
                Log.println("Successfully created record of new race ID " + raceID + ".");
            }
            
            // if a race record with same race ID exists, then just update number of players
            else
            {
                pstmt = connection.prepareStatement(updateNumberOfPlayersQuery);
                pstmt.setInt(1, Constants.MAX_NUMBER_OF_PLAYERS); // ... SET `num_players` = 2
                pstmt.setInt(2, raceID); // ... WHERE `race_id` = ?

                Log.println("Updating number of players of race ID " + raceID + "...");
                
                // execute query...
                if (pstmt.executeUpdate() != 1)
                {
                    // in case of failure to update record
                    throw new Exception("Database error. Failed to update number of players of race ID " + raceID + ".");
                }

                Log.println("Successfully updated number of players of race ID " + raceID + "...");
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
    
    // records to DB for player creating a race
    // @param: playerID - ID of player,
    //         raceID - ID of race to be created
    // @throws: SQLException
    public static void createPlayerRecord(int playerID, int raceID) throws SQLException
    {
        Connection connection = null;
        PreparedStatement pstmt;
        
        // SQL query for creating record of race info of player
        // parametrized to prevent SQL injection
        String setupRaceQuery = "INSERT INTO `runner_raceinfo` VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try
        {
            Log.println("Connecting to database...");
            
            // establish connection to DB
            // set up record for player in selected race (in `runner_raceinfo`)
            connection = DAO.getDataSource().getConnection();
            
            Log.println("Successfully connected to database.\n"
                    + "Creating record for race ID " + raceID + " of player ID " + playerID + "...");
            
            pstmt = connection.prepareStatement(setupRaceQuery);
            pstmt.setInt(1, playerID);
            pstmt.setInt(2, raceID);
            
            // define remainined parameters - defaults
            // runner_id - set to 31 (Tree Mouse) as default
            // other values are invalid unless ID defined in `runner_species` and `species` - won't be able to insert records otherwise
            pstmt.setInt(3, 31);
            pstmt.setFloat(4, 0); // x_pos
            pstmt.setFloat(5, 0); // y_pos
            pstmt.setInt(6, 0); // direction
            pstmt.setBoolean(7, false); // in_air
            pstmt.setInt(8, 0); // health
            pstmt.setFloat(9, 0); // speed
            pstmt.setFloat(10, 0); // power
            pstmt.setInt(11, 0); // status
            
            // runner_item_id  set to 1 (none) as default
            // other values are invalid unless ID defined in `runner_items` - won't be able to insert records otherwise
            pstmt.setInt(12, 1);
            pstmt.setInt(13, 0); // num_items
            pstmt.setInt(14, 0); // time
            pstmt.setInt(15, 0); // score
            
            // execute query...
            if (pstmt.executeUpdate() != 1)
            {
                // in case of failure to update record
                throw new Exception("Database error. Failed to create record of race ID " + raceID + " for player ID " + playerID + ".");
            }
            
            Log.println("Successfully created record for race ID " + raceID + " of player ID " + playerID + ".\n"
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
    
    // save species that player selected to DB right away
    // @param: player - the player
    // @return: (int) ID of species
    // @throws: SQLException
    public static void setPlayerSpecies(RacePlayer player) throws SQLException
    {
        Connection connection = null;
        PreparedStatement pstmt;
        int runnerID, // ID of selected species
                playerID, // ID of player
                raceID; // ID of race that player is in
        
        // SQL query for updating number of players (after joining race)
        // parametrized to prevent SQL injection
        String updateRacePlayerQuery = "UPDATE `runner_raceinfo` SET `runner_id` = ? WHERE `player_id` = ?";
        
        try
        {
            // get ID of selected species, player ID, and ID of race that the player is in
            runnerID = player.getRunnerSpeciesID();
            playerID = player.getID();
            raceID = player.getRaceID();
            
            Log.println("Connecting to database...");
            
            // establish connection to DB
            // update race record of player with ID of selected species
            connection = DAO.getDataSource().getConnection();
            
            Log.println("Successfully connected to database.\n"
                    + "Updating runner ID in record of race ID " + raceID + " for player ID " + playerID + "...");
            
            pstmt = connection.prepareStatement(updateRacePlayerQuery);
            pstmt.setInt(1, runnerID);
            pstmt.setInt(2, playerID);
            
            // in case of failure to create record
            if (pstmt.executeUpdate() != 1)
            {
                throw new Exception("Database error. Failed to write selected species of player ID " + playerID + " in race ID " + raceID + ".");
            }
            
            Log.println("Successfully updated runner ID in record of race ID " + raceID + " for player ID " + playerID + ".\n"
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
    
    // update records of players in DB during race
    // @param: player - the player
    // @throws: SQLException
    public static void updateRace(RacePlayer player) throws SQLException
    {
        Connection connection = null;
        PreparedStatement pstmt;
        int playerID, // ID of player
                raceID; // ID of race that player is in
                
        
        // SQL query for updating number of players (after joining race)
        // parametrized to prevent SQL injection
        String updateRacePlayerQuery = "UPDATE `runner_raceinfo` "
                + "SET `x_pos` = ?, "
                + "`y_pos` = ?, "
                + "`direction` = ?, "
                + "`in_air` = ?, "
                + "`health` = ?, "
                + "`speed` = ?, "
                + "`power` = ?, "
                + "`status` = ?, "
                + "`runner_item_id` = ?, "
                + "`num_items` = ?, "
                + "`time` = ?, "
                + "`score` = ? "
                + "WHERE `player_id` = ?";
        
        try
        {
            Log.println("Connecting to database...");
            
            // get player ID and ID of race that the player is in
            playerID = player.getID();
            raceID = player.getRaceID();
            
            // establish connection to DB
            // update record of player in DB accordingly
            connection = DAO.getDataSource().getConnection();
            
            Log.println("Successfully connected to database.\n"
                    + "Updating record of race ID " + raceID + " for player ID " + playerID + "...");
            
            pstmt = connection.prepareStatement(updateRacePlayerQuery);
            pstmt.setFloat(1, player.getX()); // x_pos
            pstmt.setFloat(2, player.getY()); // y_pos
            
            // direction (0 = no side input, 1 = left, 2 = right)
            if (player.isLeft())
            {
                pstmt.setInt(3, 1);
            }
            else if (player.isRight())
            {
                pstmt.setInt(3, 2);
            }
            else
            {
                pstmt.setInt(3, 0);
            }
            
            // in_air
            // MySQL aliases 0 = true, 1 = false
            if (player.isJump())
            {
                pstmt.setInt(4, 0);
            }
            else
            {
                pstmt.setInt(4, 1);
            }
            pstmt.setInt(5, player.getHealth()); // health
            pstmt.setFloat(6, player.getSpeed()); // speed
            pstmt.setFloat(7, player.getPower()); // power
            pstmt.setInt(8, player.getStatus()); // status
            pstmt.setInt(9, player.getItemID()); // runner_item_id
            pstmt.setInt(10, player.getNumItems()); // num_items
            pstmt.setInt(11, player.getTime()); // time
            pstmt.setInt(12, player.getScore()); // score
            pstmt.setInt(13, playerID); // player_id
            
            // in case of failure to create record
            if (pstmt.executeUpdate() != 1)
            {
                throw new Exception("Database error. Failed to update record of player ID " + playerID + " in race ID " + player.getRaceID() + ".");
            }
            
            Log.println("Successfully updated record of race ID " + raceID + " for player ID " + playerID + ".\n"
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
    
    /*
    // update player record in DB after end of race
    // @param: player - the player,
    //         earnedLevel - level up,
    //         earnedExp - earned experience,
    //         earnedCredits - earned credits
    // @throws: SQLException
    public static void finishRace(RacePlayer player, int earnedLevel, int earnedExp, int earnedCredits) throws SQLException
    {
        Connection connection = null;
        PreparedStatement pstmt;
        int playerID, // ID of player
                level, // current level of player
                exp, // current experience of player
                credits; // current credits of player
        
        // SQL query to load player's level, experience, and credits from database
        String getPlayerDataQuery = "SELECT `level`, `experience`, `credits` FROM `player` WHERE `player_id` = ?",
        
        // SQL query to update player data in DB after race is finished
        updatePlayerDataQuery = "UPDATE `player` "
            + "SET `level` = ?, "
            + "`experience` = ?, "
            + "`credits` = ? "
            + "WHERE `player_id` = ?";
        
        try
        {
            Log.println("Connecting to database...");
            
            // get player ID
            playerID = player.getID();
            
            // establish connection to DB
            // update record of player in DB accordingly
            connection = DAO.getDataSource().getConnection();
            
            Log.println("Successfully connected to database.\n"
                    + "Updating account data of player ID " + playerID + "...");
            
            pstmt = connection.prepareStatement(getPlayerDataQuery);
            pstmt.setInt(1, playerID);
            ResultSet rs = pstmt.executeQuery();
            
            // load player record in DB; throw exception if no records exist
            if (!rs.first())
            {
                throw new Exception("Database error. Failed to load data of player ID " + playerID + ".");
            }
            
            // get player's current level, experience, credits
            level = rs.getInt("level");
            exp = rs.getInt("experience");
            credits = rs.getInt("credits");
            
            // update player's record
            pstmt = connection.prepareStatement(updatePlayerDataQuery);
            pstmt.setInt(1, level + earnedLevel);
            pstmt.setInt(2, exp + earnedExp);
            pstmt.setInt(3, credits + earnedCredits);
            pstmt.setInt(4, playerID);
            
            // in case of failure to update record
            if (pstmt.executeUpdate() != 1)
            {
                throw new Exception("Database error. Failed to update record of player ID " + playerID + ".");
            }
            
            Log.println("Successfully updated account data of player ID " + playerID + ".\n"
                    + "Queries finished. Disconnecting from database...");
            
            // all done with queries
            pstmt.close();
            rs.close();
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
    */
    
    // records to DB for player leaving races
    // @param: player - the leaving player
    // @return: (int) ID of race that the player was in
    // @throws: SQLException
    public static void leaveRace(RacePlayer player) throws SQLException
    {
        Connection connection = null;
        PreparedStatement pstmt;
        int raceID, // ID of race that the player was in
                playerID, // ID of leaving player
                currentNumberOfPlayers; // number of players in the race that the player was in (including the player)
        
        // SQL query for deleting record of the race where the player was
        String deleteRaceRecordQuery = "DELETE FROM `runner_raceinfo` WHERE `player_id` = ?",
        
        // SQL query for getting number of players in the race that the player was in
        getNumberOfPlayersQuery = "SELECT `num_players` FROM `runner_racelist` WHERE `race_id` = ?",
        
        // SQL query for updating number of players (after joining race)
        // parametrized to prevent SQL injection
        updateNumberOfPlayersQuery = "UPDATE `runner_racelist` SET `num_players` = ? WHERE `race_id` = ?";
        
        try
        {
            Log.println("Connecting to database...");
            
            // get player ID and ID of race that the player was in
            playerID = player.getID();
            raceID = player.getRaceID();
            
            // establish connection to DB
            // delete race record of leaving player from `runner_raceinfo`
            connection = DAO.getDataSource().getConnection();
            
            Log.println("Successfully connected to database.\n"
                    + "Deleting record of race ID " + raceID + " for player ID " + playerID + "...");
            
            pstmt = connection.prepareStatement(deleteRaceRecordQuery);
            pstmt.setInt(1, playerID);
            
            // in case deletion fails...
            if (pstmt.executeUpdate() != 1)
            {
                throw new Exception("Database error. Failed to delete record of race ID " + raceID + " of player ID " + playerID + ".");
            }
            
            Log.println("Successfully deleted record of race ID " + raceID + " for player ID " + playerID + ".");
            
            // get number of players (player included) in the race that the player was in
            pstmt = connection.prepareStatement(getNumberOfPlayersQuery);
            pstmt.setInt(1, raceID);
            ResultSet rs = pstmt.executeQuery();
            
            // in case record doesn't exist...
            if (!rs.first())
            {
                throw new Exception("Database error. Failed to find record of race ID + " + raceID + ".");
            }
            
            currentNumberOfPlayers = rs.getInt("num_players"); // get number of players of selected race
            
            Log.println("Updating number of players of race ID " + raceID + "...");
            
            // update number of players of race record (in `racelist_info`)
            pstmt = connection.prepareStatement(updateNumberOfPlayersQuery);
            
            if (currentNumberOfPlayers == 2) // one more player in race
            {
                pstmt.setInt(1, 1); // ... SET `num_players` = 1
            }
            else if (currentNumberOfPlayers == 1) // race is now empty
            {
                pstmt.setInt(1, 0); // ... SET `num_players` = 0
            }
            else
            {
                throw new Exception("Database error. Invalid number of players in record of race ID " + raceID + "."); // problem in accessing DB
            }
            
            pstmt.setInt(2, raceID); // only set change to record that has ID of selected race
            
            // in case of failure to update record
            if (pstmt.executeUpdate() != 1)
            {
                throw new Exception("Database error. Failed to update record of race ID " + raceID + ".");
            }
            
            Log.println("Successfully updated number of players of race ID " + raceID + "...\n"
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
                Log.println("Successfully disconnected from database.");
            }
        }
    }
}
