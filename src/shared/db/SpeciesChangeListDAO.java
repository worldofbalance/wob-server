package shared.db;

// Java Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import shared.util.Log;

public final class SpeciesChangeListDAO {
    public final static long scale = 1000 * 60 * 60 * 24;
    public final static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private static int internalDay = 0;    

    private SpeciesChangeListDAO() {
    }

    public static void createEntry(int eco_id, int species_id, int biomass, int day) {
        
        String query_1 = "SELECT * FROM `eco_species_change` WHERE `eco_id` = ? AND `species_id` = ? AND `day` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query_1);
            pstmt.setInt(1, eco_id);
            pstmt.setInt(2, species_id);
            pstmt.setInt(3, day);

            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                pstmt.close();

                String query_2 = 
                        "INSERT INTO `eco_species_change` (`eco_id`, `species_id`, `biomass`, `day`) VALUES (?, ?, ?, ?)";
                pstmt = con.prepareStatement(query_2);
                pstmt.setInt(1, eco_id);
                pstmt.setInt(2, species_id);
                pstmt.setInt(3, biomass);
                pstmt.setInt(4, day);
                Log.println("SpeciesChangeListDAO, createEntry (insert): e/s/b = " + eco_id + " " + species_id + " " + biomass);
            } else {
                int biomassPresent = rs.getInt("biomass");
                pstmt.close();

                String query_3 = "UPDATE `eco_species_change` SET `biomass` = ? "
                        + "WHERE `eco_id` = ? AND `species_id` = ? AND `day` = ?";
                pstmt = con.prepareStatement(query_3);
                pstmt.setInt(1, biomass + biomassPresent);
                pstmt.setInt(2, eco_id);
                pstmt.setInt(3, species_id);
                pstmt.setInt(4, day);
                Log.println("SpeciesChangeListDAO, createEntry (update): e/s/nb/ob = " + eco_id + " " + species_id + " " + biomass + " " + biomassPresent);
            }

            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
            Log.println_e("SpeciesChangeListDAO, createEntry: " + ex.getMessage());
        } finally {
            if (con != null) {
                GameDB.closeConnection(con, pstmt);
            }
        }
    }
    
    public static void removeEntry(int eco_id, int... species_id) throws SQLException {
        String query = "DELETE FROM `eco_species_change` WHERE `eco_id` = ? AND `species_id` IN (";

        for (int i = 0; i < species_id.length; i++) {
            query += "?";

            if (i < species_id.length - 1) {
                query += ", ";
            } else {
                query += ")";
            }
        }

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, eco_id);

            for (int i = 0; i < species_id.length; i++) {
                pstmt.setInt(2 + i, species_id[i]);
            }

            pstmt.executeUpdate();
            pstmt.close();
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
    
    public static int[] getDayInfo(int eco_id) {
        int[] results = new int[3];
        results[0] = getDay();
        Log.println("SpeciesChangeListDAO, getDayInfo: day[0] = " + results[0]);
        
        String query = "SELECT * FROM `eco_species_change` WHERE `eco_id` = ? ORDER BY `day` ASC";
        
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, eco_id);

            ResultSet rs = pstmt.executeQuery();

            results[1] = getDay();
            if (rs.next()) {
                results[1] = rs.getInt("day");
                Log.println("SpeciesChangeListDAO, getDayInfo: day[1] = " + results[1]);
            } 
            
            query = "SELECT * FROM `eco_species_change` WHERE `eco_id` = ? ORDER BY `day` DESC";
            
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, eco_id);

            rs = pstmt.executeQuery();

            results[2] = getDay();
            if (rs.next()) {
                results[2] = rs.getInt("day");
                Log.println("SpeciesChangeListDAO, getDayInfo: day[2] = " + results[2]);
            } 
            
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Log.println_e("SpeciesChangeListDAO, getDayInfo: " + ex.getMessage());
        } finally {
            if (con != null) {
                GameDB.closeConnection(con, pstmt);
            }
        }
        return results;
    }

    public static Map<Integer, Integer> getSpeciesHistory(int eco_id, int species_id) {
        Map<Integer, Integer> speciesHistoryList = new HashMap<Integer, Integer>();
        // Log.println("SpeciesChangeListDAO, getSpeciesHistory: e/s = " + eco_id + " " + species_id);

        String query = "SELECT * FROM `eco_species_change` WHERE `eco_id` = ? AND `species_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, eco_id);
            pstmt.setInt(2, species_id);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                speciesHistoryList.put(rs.getInt("day"), rs.getInt("biomass"));
                // Log.println("SpeciesChangeListDAO, getSpeciesHistory: d/b = " + rs.getInt("day") + " " + rs.getInt("biomass"));
            }

            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Log.println_e("SpeciesChangeListDAO, createEntry: " + ex.getMessage());
        } finally {
            if (con != null) {
                GameDB.closeConnection(con, pstmt);
            }
        }
        return speciesHistoryList;
    }
    
    /* DH 2017-1-11 - This function needs work to be used.
    // (eco_id, species_id) is no longer a unique key
    public static Map<Integer, Integer> getList(int eco_id) throws SQLException {
        Map<Integer, Integer> speciesChangeList = new HashMap<Integer, Integer>();

        String query = "SELECT * FROM `eco_species_change` WHERE `eco_id` = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, eco_id);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                speciesChangeList.put(rs.getInt("species_id"), rs.getInt("biomass"));
            }

            rs.close();
            pstmt.close();
        } finally {
            if (con != null) {
                con.close();
            }
        }

        return speciesChangeList;
    }
    */
    
    /* This version is based upon the actual number of days from 1/1/2015
    public static int getCurrentDay() {
        int result = 0;
        try {
            Date dateRef = sdf.parse("01-01-2015");
            Date today = new Date();
            result = (int) ((today.getTime() - dateRef.getTime()) / scale);
        } catch (Exception e) {
            Log.println_e("getCurrentDay Exception: " + e.toString());            
        } 
        return result;
    }
    */

    // Fetches the highest day from the DB, returns it and updates internalDay
    public static int fetchDay() {
        int result = 0;
        
        String query_1 = "SELECT `day` FROM `eco_species_change` ORDER BY `day` DESC";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = GameDB.getConnection();
            pstmt = con.prepareStatement(query_1);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt("day");
                // Log.println("SpeciesChangeListDAO, getCurrentDay: day = " + result);
            } 
            
            pstmt.close();
        } catch (SQLException ex) {
            Log.println_e("SpeciesChangeListDAO, getCurrentDay: " + ex.getMessage());
        } finally {
            if (con != null) {
                GameDB.closeConnection(con, pstmt);
            }
        }
        
        internalDay = result;
        return result;
    }
    
    public static int getDay() {
        return internalDay;
    }
    
    public static void setDay(int day) {
        internalDay = day;
    }
}
