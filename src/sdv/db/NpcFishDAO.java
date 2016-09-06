
package sdv.db;

//java packages.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import shared.util.Log;
import sdv.model.Prey;
import sdv.PlayTime.PlayTimePlayer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * saves and retrieves records pertaining to the sdv_playinfo_npcfishes table. 
 * @author Karl
 */
public class NpcFishDAO {
     
    private NpcFishDAO(){}
    
    /**
     * Creates a record of an npc fish in the database.
     * @param prey
     * @param play_id
     * @throws SQLException 
     */
    public static void createNpcRecord(Prey prey, int play_id) throws SQLException{
        Connection connection = null;
        PreparedStatement pstmt;
        String createNpcRecordQuery ="INSERT INTO sdv_playinfo_npcfishes VALUES (?,?,?,?,?,0)";
        try{
            connection = GameDB.getConnection();
            pstmt = connection.prepareStatement(createNpcRecordQuery);
            pstmt.setInt(1, prey.getPrey_id());
            pstmt.setInt(2, play_id);
            pstmt.setInt(3, 91);
            pstmt.setFloat(4, prey.getX());
            pstmt.setFloat(5, prey.getY());
            if(pstmt.executeUpdate() == 0){
                throw new Exception("could not insert record for prey_id: "+prey.getPrey_id());
            }
        }catch(Exception e){
            Log.println_e(e.getMessage());
        }
    }
    
    public static void CreateNpcRecordsForPlay(Map<Integer, Prey> preyMap, int play_id) throws SQLException{
        Connection connection = null;
        PreparedStatement pstmt;
        String createAllNpcRecords = "INSERT INTO sdv_playinfo_npcfishes VALUES(?,?,?,?,?,0)";
        try{
            connection = GameDB.getConnection();
            pstmt = connection.prepareStatement(createAllNpcRecords);
            pstmt.setInt(2, play_id);
            pstmt.setInt(3,91);
            for(Entry<Integer, Prey> entry : preyMap.entrySet()){
                pstmt.setInt(1, entry.getValue().getPrey_id());
                pstmt.setFloat(4, entry.getValue().getX());
                pstmt.setFloat(5, entry.getValue().getY());
                if(pstmt.executeUpdate() == 0){
                    throw new Exception("could not insert record for prey_id: "+entry.getValue().getPrey_id());
                }
                
            }
        }catch(Exception e){
            Log.println_e(e.getMessage());
        }
    }
    
    /**
     * retrieves records of NPCs in a play. (currently only returns prey, TODO: returns
     * all fish in an abstract class, NpcFish)
     * @param play_id the play_id to which the npcs belong to
     * @return hashmap of prey_id -> Prey
     */
    public static Map<Integer,Prey> getNpcRecordsForPlay(int play_id) throws SQLException{
        Connection connection = null;
        PreparedStatement pstmt;
        Map<Integer, Prey> playNpc = new HashMap<Integer, Prey>();

        String getNPCRecordQuery ="SELECT fish_id,species_id,x_pos,y_pos,direction FROM sdv_playinfo_npcfishes WHERE play_id=?";
        try{
            connection = GameDB.getConnection();
            
            pstmt = connection.prepareStatement(getNPCRecordQuery);
            pstmt.setInt(1, play_id);
            ResultSet rs;
            rs = pstmt.executeQuery();
            while(rs.next()){
                Prey prey = new Prey(rs.getInt("fish_id"), rs.getFloat("pos_x"),rs.getFloat("pos_y"));
                playNpc.put(prey.getPrey_id(), prey);
            }
        }catch(Exception e){
            Log.println_e(e.getMessage());
        }
        return playNpc;
    }
    
    
    
    /**
     *  removes all records of npc fish in a play from the npcfishes table
     * @param play_id the play from which to delete NPC records
     * @throws SQLException 
     */
    public static void removeNpcRecordsForPlay(int play_id) throws SQLException{
        Connection connection = null;
        PreparedStatement pstmt;
        String deleteNPCRecordQuery ="DELETE FROM sdv_playinfo_npcfishes WHERE play_id=?";
        try{
            connection = GameDB.getConnection();
            pstmt = connection.prepareStatement(deleteNPCRecordQuery);
            pstmt.setInt(1, play_id);
            
            if(pstmt.executeUpdate() ==0){
                throw new Exception("could not delete npcfish records for play "+play_id);
            }
        }catch(Exception e){
            Log.println_e(e.getMessage());
        }
    }
    
    /**
     *  removes a single npc fish record from sdv_playinfo_npcfishes
     * @param fish_id the unique id of the npc fish
     * @param play_id the play the npc fish is a part of.
     * @throws SQLException 
     */
    public static void removeNpcRecord(int fish_id, int play_id) throws SQLException{
        Connection connection = null;
        PreparedStatement pstmt;
        String deleteNPCRecordQuery ="DELETE FROM sdv_playinfo_npcfishes WHERE play_id=? AND fish_id =?";
        try{
            connection = GameDB.getConnection();
            pstmt = connection.prepareStatement(deleteNPCRecordQuery);
            pstmt.setInt(1, play_id);
            pstmt.setInt(2, fish_id);
            
            if(pstmt.executeUpdate() ==0)
                throw new Exception("could not delete npcfish record "+fish_id+" for play "+play_id);
        }catch(Exception e){
            Log.println_e(e.getMessage());
        }
    }
    
    /**
     * updates a single npcFIsh record
     * @param prey the fish to update
     * @param play_id the play the fish is in.
     */
    public static void updateNpcRecord(Prey prey, int play_id) throws SQLException{
        Connection connection = null;
        PreparedStatement pstmt;
        String updateNPCRecordQuery = "UPDATE sdv_playinfo_npcfishes "
                + "SET fish_id=?, play_id=?, species_id=?,x_pos=?,y_pos=? " 
                + "WHERE fish_id=? AND play_id=?";
        try{
            connection = GameDB.getConnection();
            pstmt = connection.prepareStatement(updateNPCRecordQuery);
            pstmt.setInt(1, prey.getPrey_id());
            pstmt.setInt(2, play_id);
            pstmt.setInt(3, 91);
            pstmt.setFloat(4, prey.getX());
            pstmt.setFloat(5, prey.getY());
            pstmt.setInt(6, prey.getPrey_id());
            pstmt.setInt(7, play_id);
            if(pstmt.executeUpdate() == 0)
                throw new Exception("could not update fish in play "+play_id);
        }catch(Exception e){
            Log.println_e(e.getMessage());
        }
    }
    
     /**
     * updates all npc fish in a play.
     * @param preyMap the map of npc fish in a play.
     * @param play_id the play to update the npc fish.
     * @throws SQLException 
     */
    public static void updateAllNpcRecordsForPlay(Map<Integer, Prey> preyMap, int play_id) throws SQLException{
        Connection connection = null;
        PreparedStatement pstmt;
        Prey prey;
        String updateAllQuery = "UPDATE sdv_playinfo_npcfishes "
                + "SET fish_id=?,play_id=?,species_id=?,x_pos=?,y_pos=? "
                + "WHERE play_id=?";
        try{
            connection = GameDB.getConnection();
            pstmt = connection.prepareStatement(updateAllQuery);
            pstmt.setInt(2,play_id);
            pstmt.setInt(3, 91);
            pstmt.setInt(6, play_id);
            for(Entry<Integer, Prey> entry : preyMap.entrySet()){
                pstmt.setInt(1, entry.getValue().getPrey_id());
                pstmt.setFloat(4,entry.getValue().getX());
                pstmt.setFloat(5, entry.getValue().getY());
                if(pstmt.executeUpdate()==0){
                    throw new Exception("Could not update entry "+entry.getValue().getPrey_id());
                }
            }
            Log.println("updated all Prey entries successfuly.");
        }catch(Exception e){
            Log.println_e(e.getMessage());
        }
    }
    
}
