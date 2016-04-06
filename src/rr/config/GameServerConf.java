package rr.config;

// Java Imports
import java.util.HashMap;
import java.util.Map;

/**
 * The GameServerConf class stores important variables such as the port number
 * to be used for the server from the configuration file.
 */
public class GameServerConf {

    private Map<String, String> confRecords; // Stores server config. variables

    public GameServerConf() {
        confRecords = new HashMap<String, String>();
    }

    public void setConfRecords(Map<String, String> confRecords) {
        this.confRecords = confRecords;
    }

    public int getPortNumber() {
        return Integer.valueOf(confRecords.get("portNumber"));
    }
}