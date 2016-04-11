package db;

// Java Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

// Other Imports
import configuration.DBConf;
import utility.ConfFileParser;
import utility.Log;

/**
 * The GameDB class configures the database connection by using information
 * parsed from the configuration file.
 */
public class GameDB {

    // Singleton Instance
    private static GameDB gameDB;
    // GameDB Variables
    private final DBConf configuration = new DBConf();
    private static DataSource dataSource;

    /**
     * Configures the database connection.
     */
    public GameDB() {
        // Load the configuration file into memory
        configure();
        // Create a connection to the database
        String connectURI = String.format(
                "jdbc:mysql://%s/%s?user=%s&password=%s",
                configuration.getDBURL(), configuration.getDBName(),
                configuration.getDBUsername(), configuration.getDBPassword()
        );

        dataSource = ConnectionPool.setupDataSource(connectURI);
    }

    /**
     * Parses the database configuration file and store those values into
     * memory.
     */
    private void configure() {
        // Parse the configuration file
        ConfFileParser confFileParser = new ConfFileParser("conf/db.conf");
        configuration.setConfRecords(confFileParser.parse());
    }

    /**
     * Retrieve a connection from data source for database access.
     *
     * @return connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        if (gameDB == null) {
            gameDB = new GameDB();
        }

        return dataSource.getConnection();
    }

    public static void closeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Log.println_e(ex.getMessage());
            }
        }

        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException ex) {
                Log.println_e(ex.getMessage());
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Log.println_e(ex.getMessage());
            }
        }
    }

    public static void closeConnection(Connection con, PreparedStatement pstmt) {
        GameDB.closeConnection(con, pstmt, null);
    }
}
