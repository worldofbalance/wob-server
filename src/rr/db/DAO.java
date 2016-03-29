package rr.db;

// Java Imports
import javax.sql.DataSource;

/**
 * The DAO class manages the database connection for executing queries. Uses the
 * GameDB class to create the connection.
 */
public final class DAO {

    // Singleton Instance
    private static DAO dao;
    // DAO Variables
    private GameDB gameDB;
    private DataSource datasource;

    /**
     * Initialize the DAO and create the connection to the database.
     */
    private DAO() {
        gameDB = new GameDB();
        datasource = gameDB.getDataSource();
    }

    /**
     * Instantiates the DAO on first execution and return the instance.
     *
     * @return the instance of the DAO
     */
    public static DAO getInstance() {
        if (dao == null) {
            dao = new DAO();
        }

        return dao;
    }

    /**
     * Retrieve the data source.
     *
     * @return the data source
     */
    public static DataSource getDataSource() {
        return dao.datasource;
    }
}