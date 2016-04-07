/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlayTime;

import core.GameServer;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import metadata.Constants;
import model.Player;
import utility.Log;
import net.Response.ResponseSDStartGame;
// TODO: import dataAccessLayer.RaceDAO;
/**
 *
 * @author anu
 */
public class Play {

//    private 
    private Map<Integer, PlayTimePlayer> rPlayers = new HashMap<Integer, PlayTimePlayer>();

    private int raceID;
    private int mapID;

    private short playersReadyToStart;
    
    public Play(int raceID) {
        this.raceID = raceID;
        /* TODO:
        try {
            RaceDAO.createRace(raceID);
        } catch (SQLException e) {
            Log.println_e("Error in writing record of race " + raceID + " into database.");
            Log.println_e(e.getMessage());
        }
        */
    }
    
    public void addPlayer(Player player) {
        this.rPlayers.put(player.getPlayer_id(), new PlayTimePlayer(player.getPlayer_id(), raceID));
        /* TODO:
        try {
            RaceDAO.createPlayerRecord(player.getID(), raceID);
        } catch (SQLException e) {
            Log.println_e("Error in writing record of player ID " + player.getID() + " in race ID " + raceID + " into database.");
            Log.println_e(e.getMessage());
        }
         */
    }

    public Play(List<Player> players, int raceID) {
        this.raceID = raceID;
        /* TODO:
        try
        {
            RaceDAO.createRace(raceID);
        }
        catch (SQLException e)
        {
            Log.println_e("Error in writing record of race " + raceID + " into database.");
            Log.println_e(e.getMessage());
        }
        */
        for (Player player : players) {
            this.rPlayers.put(player.getPlayer_id(), new PlayTimePlayer(player.getPlayer_id(), raceID));
            
            /* TODO:
            try
            {
                RaceDAO.createPlayerRecord(player.getPlayer_id(), raceID);
            }
            catch (SQLException e)
            {
                Log.println_e("Error in writing record of player ID " + player.getID() + " in race ID " + raceID + " into database.");
                Log.println_e(e.getMessage());
            }
             */
        }
    }

    public int getID() {
        return this.raceID;
    }

    public Map<Integer, PlayTimePlayer> getPlayers() {
        return rPlayers;
    }

    public PlayTimePlayer getOpponent(Player racePlayer) {

        for (PlayTimePlayer player : rPlayers.values()) {
            if (player.getPlayer_id() != racePlayer.getPlayer_id()) {
                return player;
            }
        }

        return null; // error
    }

    public int getOpponentID(int playerID) {

        for (PlayTimePlayer player : rPlayers.values()) {
            if (player.getPlayer_id() != playerID) {
                return player.getPlayer_id();
            }
        }

        return -1; // error
    }

    // USSAGE: Called by RequestRRStartGame.
    // Sends an output to the clients of this race to start the countdown 
    // sequence to the start of a race.
    public void startRace(int player_id) throws IOException {

        for (int p_id : getPlayers().keySet()) {
            if (p_id == player_id) {
                playersReadyToStart++;
            }
        }

        if (playersReadyToStart == Constants.MAX_NUMBER_OF_PLAYERS) {
            ResponseSDStartGame responseStart = new ResponseSDStartGame();
            for (int p_id : getPlayers().keySet()) {
                //NetworkManager.addResponseForUser(p_id, responseStart);
                // changed to this to reduce start game lag
                // this change made it almost simultaneous start
                GameServer.getInstance().getThreadByPlayerID(p_id).send(responseStart);
            }
        }
    }

    public void setFinalTime(int playerID, float finalString) {

        PlayTimePlayer temp = this.rPlayers.get(playerID);
        temp.setFinalTime(finalString);
        this.rPlayers.put(raceID, temp);
    }

    public int getMapID() {
        return mapID;
    }

    public void setMapID(int mapID) {
        this.mapID = mapID;
    }


}
