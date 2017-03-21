package common;

import conf.Configuration;
import shared.db.GameDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestUtils {

    // WARNING: The database identified by this URL and name will have its data altered and deleted!
    public static final String TEST_DBURL = "localhost";
    public static final String TEST_DBNAME = "wob_test";

    /**
     * For the database tests to work, a local MySQL database called wob_test is required,
     * and it must contain the species table from the main WoB database,
     * as well as an empty score_history table (created using sql/create_score_history.sql).
     */
    public static void useTestDatabase() {
        Configuration.DBURL = TEST_DBURL;
        Configuration.DBName = TEST_DBNAME;
    }

    /**
     * Delete all records from the given table
     */
    public static void clearTable(String tableName) throws SQLException {
        if (!Configuration.DBURL.equals(TEST_DBURL) || !Configuration.DBName.equals(TEST_DBNAME)) {
            throw new RuntimeException("Refusing to delete records from non-test database");
        }
        Connection conn = GameDB.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("DELETE FROM score_history");
        pstmt.execute();
        GameDB.closeConnection(conn, pstmt);
        pstmt.close();
    }
}
