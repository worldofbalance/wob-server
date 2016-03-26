package rr.race;

import shared.core.GameServer;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;
import rr.model.Player;
import rr.networking.response.ResponseRRStartGame;
import shared.metadata.Constants;
import java.io.IOException;
import rr.db.RaceDAO;
import shared.utility.Log;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author markfavis
 */
public class Race {

//    private 
    private Map<Integer, RacePlayer> rPlayers = new HashMap<Integer, RacePlayer>();

    private int raceID;
    private int mapID;

    private short playersReadyToStart;
    
    public Race(int raceID) {
        this.raceID = raceID;
        try {
            RaceDAO.createRace(raceID);
        } catch (SQLException e) {
            Log.println_e("Error in writing record of race " + raceID + " into database.");
            Log.println_e(e.getMessage());
        }
    }
    
    public void addPlayer(Player player) {
        this.rPlayers.put(player.getID(), new RacePlayer(player.getID(), raceID));
        try {
            RaceDAO.createPlayerRecord(player.getID(), raceID);
        } catch (SQLException e) {
            Log.println_e("Error in writing record of player ID " + player.getID() + " in race ID " + raceID + " into database.");
            Log.println_e(e.getMessage());
        }
    }

    public Race(List<Player> players, int raceID) {
        this.raceID = raceID;
        
        try
        {
            RaceDAO.createRace(raceID);
        }
        catch (SQLException e)
        {
            Log.println_e("Error in writing record of race " + raceID + " into database.");
            Log.println_e(e.getMessage());
        }
        
        for (Player player : players) {
            this.rPlayers.put(player.getID(), new RacePlayer(player.getID(), raceID));
            try
            {
                RaceDAO.createPlayerRecord(player.getID(), raceID);
            }
            catch (SQLException e)
            {
                Log.println_e("Error in writing record of player ID " + player.getID() + " in race ID " + raceID + " into database.");
                Log.println_e(e.getMessage());
            }
        }
    }

    public int getID() {
        return this.raceID;
    }

    public Map<Integer, RacePlayer> getPlayers() {
        return rPlayers;
    }

    public RacePlayer getOpponent(Player racePlayer) {

        for (RacePlayer player : rPlayers.values()) {
            if (player.getID() != racePlayer.getID()) {
                return player;
            }
        }

        return null; // error
    }

    public int getOpponentID(int playerID) {

        for (RacePlayer player : rPlayers.values()) {
            if (player.getID() != playerID) {
                return player.getID();
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
            ResponseRRStartGame responseStart = new ResponseRRStartGame();
            for (int p_id : getPlayers().keySet()) {
                //NetworkManager.addResponseForUser(p_id, responseStart);
                // changed to this to reduce start game lag
                // this change made it almost simultaneous start
                GameServer.getInstance().getThreadByPlayerID(p_id).send(responseStart);
            }
        }
    }

    public void setFinalTime(int playerID, float finalString) {

        RacePlayer temp = this.rPlayers.get(playerID);
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
