package shared.db;

// Java Imports
import cow.core.GameServer;
import java.io.File;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

// Other Imports
import shared.config.DBConf;
import shared.util.ConfFileParser;
import shared.util.Log;

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
    private GameDB() {
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
        try 
        {
            String separator=System.getProperty("file.separator");
            String dbFilePath="conf"+separator+"db.conf";
            File f = new File(dbFilePath);
            if (!f.exists()) 
            {
                // get current absolute path for cards of wild
                CodeSource codeSource = cow.core.GameServer.class.getProtectionDomain().getCodeSource();
                File jarFile = new File(codeSource.getLocation().toURI().getPath());
                dbFilePath = jarFile.getParentFile().getPath() +separator +".."+separator+"conf"+separator+"db.conf";
            }
            
            ConfFileParser confFileParser = new ConfFileParser(dbFilePath);
            configuration.setConfRecords(confFileParser.parse());
        }
        catch (Exception e) 
        {
            System.out.println("Error loading database file");
            e.printStackTrace(System.out);
        }
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
