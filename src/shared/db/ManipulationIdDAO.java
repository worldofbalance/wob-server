package shared.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import shared.simulation.simjob.SimJob;

public class ManipulationIdDAO {

	   /**
     * Create a new mnaipulationId table entry
     * @param webManipId
     * @param atnManipId
     * @return int manip_id
     * @throws SQLException
     */
    public static int createJob(String webManipId, String atnManipId) throws SQLException {
        int manip_id = -1;

        String query = "INSERT INTO `manuipulationId`(`web_manipId`, `atn_manipId`) VALUES (?, ?)";

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = GameDB.getConnection();  //9/25/14, JTC, integration w/ Gary's code
            pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, webManipId);
            pstmt.setString(2, atnManipId);
            pstmt.execute();

            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                manip_id = rs.getInt(1);
            }

            rs.close();
            pstmt.close();
            
        } catch (SQLException ex) {
            System.err.println ("SQL exception: " + ex.getMessage() + 
                    ", cause: " + ex.getCause());
            
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return manip_id;
    }

}
