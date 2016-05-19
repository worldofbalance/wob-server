/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdv.model;

import sdv.core.GameClient;

/**
 *
 * @author anu
 */
public class Player {
     private int player_id;
     private GameClient client; // References GameClient instance
     protected String username;
     protected String password;
     protected String opponentClientSessionID;
      
     public Player(int player_id) {
        this.player_id = player_id;
    }


    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }
      public GameClient getClient() {
        return client;
    }

    public GameClient setClient(GameClient client) {
        return this.client = client;
    }
      public String getPassword() {
        return password;
    }

    public String setPassword(String password) {
        return this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String setUsername(String username) {
        return this.username = username;
    }
    
      /**
     * @return the opponentClientSessionID
     */
    public String getOpponentClientSessionID() {
        return opponentClientSessionID;
    }

    /**
     * @param opponentClientSessionID the opponentClientSessionID to set
     */
    public void setOpponentClientSessionID(String opponentClientSessionID) {
        this.opponentClientSessionID = opponentClientSessionID;
    }
}
