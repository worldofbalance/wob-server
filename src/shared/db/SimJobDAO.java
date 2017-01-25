package shared.db;

// Java Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Other Imports
import shared.simulation.simjob.SimJob;

/**
 * SimJob table stores history of simulation jobs, most importantly the node
 configuration used to generate the simulation and the CSV results of the
 simulations.
 * @author Justina
 */
public final class SimJobDAO {

    private static final int NO_ID = -1;

    private SimJobDAO() {
    }

    /**
     * createPStmt is used by all calls to the sim_job table to configure the
     * prepared statement as necessary.
     * @param query
     * @param connection
     * @param simJob
     * @return PreparedStatement
     * @throws SQLException
     */
    public static PreparedStatement createPstmt(String query,
            Connection connection, SimJob simJob) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        pstmt.setString(1, simJob.getJob_Descript());
        pstmt.setInt(2, simJob.getTimesteps());
        pstmt.setString(3, simJob.getNode_Config());
        //only store result information if manipulation id has been set
        if (simJob.getManipulation_Id() != null) {
            pstmt.setString(4, simJob.getManipulation_Id());
            pstmt.setString(5, simJob.getManip_Timestamp());
            pstmt.setString(6, simJob.getCsv());
            pstmt.setString(7, simJob.getBiomassCSV());
        } else {
            pstmt.setNull(4, java.sql.Types.VARCHAR);
            pstmt.setNull(5, java.sql.Types.VARCHAR);
            pstmt.setNull(6, java.sql.Types.LONGVARCHAR);
            pstmt.setNull(7, java.sql.Types.LONGVARCHAR);
        }
        pstmt.setBoolean(8, simJob.getInclude());
        //if job ID already exists, this is an save to existing record; add WHERE info.
        if (simJob.getJob_Id() != NO_ID) {
            pstmt.setInt(9, simJob.getJob_Id());
        }

        return pstmt;
    }

    /**
     * Create a new sim_job table entry
     * @param simJob
     * @return int job_id
     * @throws SQLException
     */
    public static int createJob(SimJob simJob) throws SQLException {
        int job_id = -1;

        String query = "INSERT INTO `sim_job`(`job_descript`, `timesteps`, `node_config`, "
                + "`manipulation_id`, `manip_timestamp`, `csv`, `csv_atn`, `include`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = GameDB.getConnection();  //9/25/14, JTC, integration w/ Gary's code
            pstmt = createPstmt(query, connection, simJob);
            pstmt.execute();

            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                job_id = rs.getInt(1);
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
        return job_id;
    }

    /**
     * updateJob updates an existing sim_job table entry
     * @param simJob
     * @return
     * @throws SQLException
     */
    public static int updateJob(SimJob simJob) throws SQLException {

        String query = "UPDATE `sim_job` SET `job_descript` = ?, `timesteps` = ?, `node_config` = ?, "
                + "`manipulation_id` = ?, `manip_timestamp` = ?, `csv` = ?, `csv_atn` = ?, `include` = ? "
                + "WHERE `job_id` = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = GameDB.getConnection();  //9/25/14, JTC, integration w/ Gary's code
            pstmt = createPstmt(query, connection, simJob);
            pstmt.execute();

            ResultSet rs = pstmt.getGeneratedKeys();

            rs.close();
            pstmt.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return simJob.getJob_Id();
    }

    /**
     * loadJobNoHistory loads the job such that it can be used as the basis for 
     * CREATing a new job if the existing loaded job has a history (in this case
     * job_id not set and therefore will not save back to same entry).
     * If the job does NOT have a history (no manipulation_id), it 
     * is loaded as an editable job and can be saved back to same table entry.
     * @param job_id
     * @param saveAsNew
     * @return SimJob
     * @throws SQLException
     */
    /*9/25/14, JTC, "saveAsNew" flag specifies whether loaded Job should be retrieved with
      with an existing ID or not (as long as Job hasn't already been run, that is).
    */
    public static SimJob loadJobNoHistory(int job_id, boolean saveAsNew) throws SQLException {
        SimJob job = null;

        String query = "SELECT * FROM `sim_job` WHERE `job_id` = ?";

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = GameDB.getConnection();  //9/25/14, JTC, integration w/ Gary's code
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, job_id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                job = new SimJob();
                job.setJob_Descript(rs.getString("job_descript"));
                job.setTimesteps(rs.getInt("timesteps"));
                job.setNode_Config(rs.getString("node_config"));
                //OK to keep existing job ID if simulation has not been run and
                //saveAsNew flag not true
                if (rs.getString("manipulation_id") == null && !saveAsNew) {
                    job.setJob_Id(rs.getInt("job_id"));
                }
                //job.setManipulation_Id(rs.getString("manipulation_id"));
                //job.setManip_Timestamp(rs.getString("manip_timestamp"));
                //job.setCsv(rs.getString("csv"));
                //job.setInclude(rs.getBoolean("include"));
            }

            rs.close();
            pstmt.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return job;
    }

    /**
     * loadCompletedJob loads the job with history information for analysis.
     * @param job_id
     * @return SimJob
     * @throws SQLException
     */ 
    public static SimJob loadCompletedJob(int job_id) throws SQLException {
        SimJob job = null;

        String query = "SELECT * FROM `sim_job` WHERE `job_id` = ? AND `manipulation_id` IS NOT NULL";

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = GameDB.getConnection();  //9/25/14, JTC, integration w/ Gary's code
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, job_id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                job = new SimJob();
                job.setJob_Id(rs.getInt("job_id"));
                job.setJob_Descript(rs.getString("job_descript"));
                job.setTimesteps(rs.getInt("timesteps"));
                job.setNode_Config(rs.getString("node_config"));
                job.setManipulation_Id(rs.getString("manipulation_id"));
                job.setManip_Timestamp(rs.getString("manip_timestamp"));
                job.setCsv(rs.getString("csv"));
                job.setBiomassCsv(rs.getString("csv_atn"));
                job.setInclude(rs.getBoolean("include"));
            }

            rs.close();
            pstmt.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return job;
    }

    /**
     * Obtain list of job IDs with "include" flag set.
     * @param jobDescript  //if not empty, get list with matching description
     * @return List<Integer> job IDs
     * @throws SQLException
     */
    public static List<Integer> getJobIdsToInclude(String jobDescript) 
            throws SQLException {
        List<Integer> jobIdList = new ArrayList<Integer>();
        String query;

        if (jobDescript.isEmpty()) {
            query = ""
                    + "SELECT `job_id` FROM `sim_job` WHERE `include` = 1 "
                    + "ORDER BY `job_id`";
        } else {
            query = ""
                    + "SELECT `job_id` FROM `sim_job` "
                    + "WHERE `job_descript` LIKE '" + jobDescript + "%' "
                    + "ORDER BY `job_descript`";
        }

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = GameDB.getConnection();  //9/25/14, JTC, integration w/ Gary's code
            pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                jobIdList.add(rs.getInt("job_id"));
            }

            rs.close();
            pstmt.close();

        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return jobIdList;
    }
    
    /**
     * Obtain list of unprocessed job IDs.
     *
     * @param all - true if all unprocessed jobs should be included
     * @param start - start ID
     * @param end - end ID
     * @param providedList - list of IDs
     * @return List<Integer> job IDs
     * @throws SQLException
     */
    public static List<Integer> getUnprocessedJobIds(boolean all,
            int start, int end, String providedList) throws SQLException {
        List<Integer> jobIdList = new ArrayList<Integer>();
        String query = "";

        if (all) {
            query = ""
                    + "SELECT `job_id` FROM `sim_job` WHERE "
                    + "(`manipulation_id` = '' "
                    + "OR `manipulation_id` IS NULL) "
                    + "ORDER BY `job_id`";
        } else if (start > 0 && end > 0) {
            query = ""
                    + "SELECT `job_id` FROM `sim_job` WHERE "
                    + "`job_id` >= " + start + " "
                    + "AND `job_id` <= " + end + " "
                    + "AND (`manipulation_id` = '' "
                    + "OR `manipulation_id` IS NULL) "
                    + "ORDER BY `job_id`";
        } else if (!providedList.isEmpty()) {
            query = ""
                    + "SELECT `job_id` FROM `sim_job` WHERE "
                    + "`job_id` IN (" + providedList + ") "
                    + "AND (`manipulation_id` = '' "
                    + "OR `manipulation_id` IS NULL) "
                    + "ORDER BY `job_id`";
        }

        if (!query.isEmpty()) {
            Connection con = null;
            PreparedStatement pstmt = null;

            try {
                con = GameDB.getConnection();
                pstmt = con.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    jobIdList.add(rs.getInt("job_id"));
                }

                rs.close();
                pstmt.close();

            } finally {
                if (con != null) {
                    con.close();
                }
            }
        }
        return jobIdList;
    }

}
