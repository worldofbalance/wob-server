/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author anu
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
