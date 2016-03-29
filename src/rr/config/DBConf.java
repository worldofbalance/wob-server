package rr.config;

// Java Imports
import java.util.HashMap;
import java.util.Map;

/**
 * The DBConf class stores database configuration information.
 */
public class DBConf {

    private Map<String, String> confRecords;

    public DBConf() {
        confRecords = new HashMap<String, String>();
    }

    public void setConfRecords(Map<String, String> confRecords) {
        this.confRecords = confRecords;
    }

    public String getDBURL() {
        return confRecords.get("DBURL");
    }

    public String getDBName() {
        return confRecords.get("DBName");
    }

    public String getDBUsername() {
        return confRecords.get("DBUsername");
    }

    public String getDBPassword() {
        return confRecords.get("DBPassword");
    }
}