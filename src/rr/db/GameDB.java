package rr.db;

// Java Imports
import javax.sql.DataSource;

// Other Imports
import shared.config.DBConf;
import shared.core.GameServer;
import java.io.File;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.db.ConnectionPool;
import shared.util.ConfFileParser;

/**
 * The GameDB class configures the database connection by using information
 * parsed from the configuration file.
 */
public class GameDB {

    private DBConf configuration;
    private DataSource dataSource;

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
        String serverConf = "conf/db.conf";
        File f = new File(serverConf);
        
        if (!f.exists()) {
            // get current absolute path
            CodeSource codeSource = GameServer.class.getProtectionDomain().getCodeSource();
            File jarFile;
            try {
                jarFile = new File(codeSource.getLocation().toURI().getPath());
                serverConf = jarFile.getParentFile().getPath() + "/../conf/db.conf";
            } catch (URISyntaxException ex) {
                Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Prepare for database info storage
        configuration = new DBConf();
        // Parse the configuration file
        ConfFileParser confFileParser = new ConfFileParser(serverConf);
        configuration.setConfRecords(confFileParser.parse());
    }

    /**
     * Retrieve the data source for database access.
     *
     * @return the data source
     */
    public DataSource getDataSource() {
        return dataSource;
    }
}