/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdv.PlayTime;

import sdv.core.GameServer;
import sdv.core.NetworkManager;
import sdv.db.PlayDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.metadata.Constants;
import sdv.model.Player;
import sdv.model.Prey;
import sdv.net.Response.ResponseSDReconnect;
import shared.util.Log;
import sdv.net.Response.ResponseSDStartGame;
// TODO: import dataAccessLayer.RaceDAO;
/**
 *
 * @author anu
 */
public class Play {

//    private 
    private Map<Integer, PlayTimePlayer> rPlayers = new HashMap<Integer, PlayTimePlayer>(); //player_id ->PlayerInformation
    private Map<Integer, Prey> mapNpcFish = new HashMap<Integer, Prey>();
    private int playID;
    private int mapID;
    public int species1;
    public  int species2;
    public  int species3;
    public int species4;
    public  int species5;
    public  int species6;
    public int species7;
    public int species0;
    
    public  int HOST_client_id; 

    private short playersReadyToStart;
    
    public Play(int playID) {
        this.playID = playID;
        species0 = 6;
        species1 = 5;
        species2 = 4;
        species3 = 4;
        species4 =6;
        species5 = 6;
        species6 =4;
        species7 =5;
        HOST_client_id = 0; 
        try {
            PlayDAO.createPlay(playID);
        } catch (SQLException e) {
            Log.println_e("Error in writing record of play " + playID + " into database.");
            Log.println_e(e.getMessage());
        }
        
    }

    public int getSpecies1() {
        return species1;
    }

    public void setSpecies1(int species1) {
        this.species1 = species1;
    }

    public int getSpecies2() {
        return species2;
    }

    public void setSpecies2(int species2) {
        this.species2 = species2;
    }

    public int getSpecies3() {
        return species3;
    }

    public void setSpecies3(int species3) {
        this.species3 = species3;
    }

    public int getSpecies4() {
        return species4;
    }

    public void setSpecies4(int species4) {
        this.species4 = species4;
    }

    public int getSpecies5() {
        return species5;
    }

    public void setSpecies5(int species5) {
        this.species5 = species5;
    }

    public int getSpecies6() {
        return species6;
    }

    public void setSpecies6(int species6) {
        this.species6 = species6;
    }

    public int getSpecies7() {
        return species7;
    }

    public void setSpecies7(int species7) {
        this.species7 = species7;
    }

    public int getSpecies0() {
        return species0;
    }

    public void setSpecies0(int species0) {
        this.species0 = species0;
    }

    public int getHOST_client_id() {
        return HOST_client_id;
    }

    public void setHOST_client_id(int HOST_client_id) {
        this.HOST_client_id = HOST_client_id;
    }
    
    /**
     * adds a player to this game as a new PlayTimePlayer object in the player map.
     * if a player is being added that is already present, in the case of a reconnection, 
     * the play will send the in-game information to the player.
     * @param player 
     * @throws IOException
     */
    public void addPlayer(Player player) throws IOException{
        //attempting to reconnect player.
        try{
            //will throw null pointer exception if player_id is not in rplayers.
            PlayTimePlayer ptp = rPlayers.get(player.getPlayer_id()); 
            Log.printf_e("reconnecting player %d to game %d", ptp.getPlayer_id(), playID);
            //Log.printf_e("Player %dis reconnecting to game %d", player.getPlayer_id(), playID);
            ResponseSDReconnect response = new ResponseSDReconnect();
            response.setMapNpc(mapNpcFish);
            response.setMapPlayer(rPlayers);
            for (int p_id : getPlayers().keySet()) {
                GameServer.getInstance().getThreadByPlayerID(p_id).send(response);
            }
            //adding new player.
        }catch(Exception ex){
            
            this.rPlayers.put(player.getPlayer_id(), new PlayTimePlayer(player.getPlayer_id(), playID));
        
            try {
                PlayDAO.createPlayer(player.getPlayer_id(), playID,0);
            } catch (SQLException e) {
                Log.println_e("Error in writing record of player ID " + player.getPlayer_id()+ " in play ID " + playID + " into database.");
                Log.println_e(e.getMessage());
            }
        }
    }

    public Play(List<Player> players, int playID) {
        this.playID = playID;
        int i=1;
        
        try
        {
            PlayDAO.createPlay(playID);
        }
        catch (SQLException e)
        {
            Log.println_e("Error in writing record of race " + playID + " into database.");
            Log.println_e(e.getMessage());
        }
        
        for (Player player : players) {
            this.rPlayers.put(player.getPlayer_id(), new PlayTimePlayer(player.getPlayer_id(), playID));
            
            
            try
            {
                PlayDAO.createPlayer(player.getPlayer_id(), playID,i);
                i++;
            }
            catch (SQLException e)
            {
                Log.println_e("Error in writing record of player ID " + player.getPlayer_id()+ " in race ID " + playID + " into database.");
                Log.println_e(e.getMessage());
            }
             
        }
    }

    public int getID() {
        return this.playID;
    }

    public Map<Integer, PlayTimePlayer> getPlayers() {
        return rPlayers;
    }
    
    public PlayTimePlayer getPlayer(int player_id){
        try{
        return rPlayers.get(player_id);
        }catch(Exception e){
            return null;
        }
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
    public void startPlay(int player_id) throws IOException {

        for (int p_id : getPlayers().keySet()) {
            if (p_id == player_id) {
                playersReadyToStart++;
            }
        }

        if (playersReadyToStart == Constants.MAX_NUMBER_OF_PLAYERS) {
            try {
                ResponseSDStartGame responseStart = new ResponseSDStartGame();
                for (int p_id : getPlayers().keySet()) {
                    //NetworkManager.addResponseForUser(p_id, responseStart);
                    // changed to this to reduce start game lag
                    // this change made it almost simultaneous start
                    GameServer.getInstance().getThreadByPlayerID(p_id).send(responseStart);
                }
            } catch (ParseException ex) {
                Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /*
    public void setWinningScore(int playerID, float finalScore) {

        PlayTimePlayer temp = this.rPlayers.get(playerID);
        temp.setFinalScore(finalScore);
        this.rPlayers.put(raceID, temp);
    }
*/

    public int getMapID() {
        return mapID;
    }

    public void setMapID(int mapID) {
        this.mapID = mapID;
    }


}
