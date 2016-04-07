/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlayTime;

import core.GameServer;
import core.NetworkManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import model.Player;
import net.Response.ResponsePlayInit;
import net.Response.ResponseSDEndGame;

/**
 *
 * @author anu
 */
public class PlayManager {

    // Singleton Instance
    public static PlayManager manager;

    // Regerence Tables
    private Map<Integer, Play> playList = new HashMap<Integer, Play>(); //RaceID -> play
    public Map<Integer, Play> playerRaceList = new HashMap<Integer, Play>(); //PlayerID -> play

    private List<Player> players = new ArrayList<Player>(); //used to create a play

    public static PlayManager getInstance() {
        if (manager == null) {
            manager = new PlayManager();
        }

        return manager;
    }

    public Play createRace(int player_id) {
        Play play = null;

        if (players.isEmpty()) {
            players.add(GameServer.getInstance().getActivePlayer(player_id));
        } else {
            if (player_id != players.get(0).getPlayer_id()) {
                // random generator used for generating map
                Random randomGenerator = new Random();
                // create playID randomly
                int playID = randomGenerator.nextInt(2001);
                while (playList.containsKey(playID)) {
                    playID = randomGenerator.nextInt(2001);
                    System.out.println("Race ID:" + playID);
                }
                System.out.println("Race ID:" + playID);
                players.add(GameServer.getInstance().getActivePlayer(player_id));
                play = new Play(players, playID);  // fix 2nd parameter
                play.setMapID(randomGenerator.nextInt(101));
                //System.out.println("Map ID:" + play.getMapID());
                add(play);
                // Respond to Players to load the Runner scene
                ResponsePlayInit response = new ResponsePlayInit();
                for (int p_id : play.getPlayers().keySet()) {
                    NetworkManager.addResponseForUser(p_id, response);
                }
                players.clear();
            }

        }
        return play;
    }

    public Play createRace(int player_id, int playID) {
        Play play = this.playList.get(playID);

        if (play == null) {
            System.out.println("Creating a Race with id = [" + playID + "]");
            play = new Play(playID);
            Random randomGenerator = new Random();
            play.setMapID(randomGenerator.nextInt(101));
            playList.put(play.getID(), play);
        } else {
            System.out.println("Race with id = [" + playID + "] "
                    + "already exists, add player " + player_id);
        }
        
        play.addPlayer(GameServer.getInstance().getActivePlayer(player_id));
        playerRaceList.put(player_id, play);

        ResponsePlayInit response = new ResponsePlayInit();
        for (int p_id : play.getPlayers().keySet()) {
            NetworkManager.addResponseForUser(p_id, response);
        }
        return play;
    }

    /**
     * This method will end a play and delete the existing instances of a play
     *
     * @param playID is the caller's play ID
     * @param playerID is the caller's player ID
     * @param winningScore this is the caller's winning Score
     * @throws Exception
     */
    public void endRace(int playID, int playerID, String winningScore) throws Exception {
        Play play = playList.get(playID);
        playList.remove(playID);
        // check if play exists
        // this eliminates loser calling end play
        if (play != null) {
            int opponentID = play.getOpponentID(playerID);

            // remove play instances
            playerRaceList.remove(playerID);
            playerRaceList.remove(opponentID);

            // create resposes
            ResponseSDEndGame response = new ResponseSDEndGame();
            response.setHighestScore(winningScore);

            // send responses to both players
            for (int p_id : play.getPlayers().keySet()) {
                if (playerID == p_id) {
                    response.setWin(true);
                } else {
                    response.setWin(false);
                }
                GameServer.getInstance().getThreadByPlayerID(p_id).send(response);
            }
            //System.out.println("endRace");
        }
    }

    public void removePlayerFromRaceList(int player_id) {
        playerRaceList.remove(player_id);
    }

    public void destroyRace(int play_id) {
        playList.remove(play_id);
    }

    public Play add(Play play) {
        for (int id : play.getPlayers().keySet()) {
            playerRaceList.put(id, play);
        }
        return playList.put(play.getID(), play);
    }

    public Play getRaceByPlayerID(int playerID) {
        return playerRaceList.get(playerID);
    }


}
