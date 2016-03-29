/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rr.race;

import java.util.HashMap;
import rr.core.GameServer;
import rr.core.NetworkManager;  // rr.core.NetworkManager
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import rr.model.Player;
import rr.net.response.ResponseRaceInit;
import java.util.Random;
import rr.net.response.ResponseRREndGame;

/**
 *
 * @author markfavis
 */
public class RaceManager {

    // Singleton Instance
    public static RaceManager manager;

    // Regerence Tables
    private Map<Integer, Race> raceList = new HashMap<Integer, Race>(); //RaceID -> race
    public Map<Integer, Race> playerRaceList = new HashMap<Integer, Race>(); //PlayerID -> race

    private List<Player> players = new ArrayList<Player>(); //used to create a race

    public static RaceManager getInstance() {
        if (manager == null) {
            manager = new RaceManager();
        }

        return manager;
    }

    public Race createRace(int player_id) {
        Race race = null;

        if (players.isEmpty()) {
            players.add(GameServer.getInstance().getActivePlayer(player_id));
        } else {
            if (player_id != players.get(0).getID()) {
                // random generator used for generating map
                Random randomGenerator = new Random();
                // create raceID randomly
                int raceID = randomGenerator.nextInt(2001);
                while (raceList.containsKey(raceID)) {
                    raceID = randomGenerator.nextInt(2001);
                    System.out.println("Race ID:" + raceID);
                }
                System.out.println("Race ID:" + raceID);
                players.add(GameServer.getInstance().getActivePlayer(player_id));
                race = new Race(players, raceID);  // fix 2nd parameter
                race.setMapID(randomGenerator.nextInt(101));
                //System.out.println("Map ID:" + race.getMapID());
                add(race);
                // Respond to Players to load the Runner scene
                ResponseRaceInit response = new ResponseRaceInit();
                for (int p_id : race.getPlayers().keySet()) {
                    NetworkManager.addResponseForUser(p_id, response);
                }
                players.clear();
            }

        }
        return race;
    }

    public Race createRace(int player_id, int raceID) {
        Race race = this.raceList.get(raceID);

        if (race == null) {
            System.out.println("Creating a Race with id = [" + raceID + "]");
            race = new Race(raceID);
            Random randomGenerator = new Random();
            race.setMapID(randomGenerator.nextInt(101));
            raceList.put(race.getID(), race);
        } else {
            System.out.println("Race with id = [" + raceID + "] "
                    + "already exists, add player " + player_id);
        }
        
        race.addPlayer(GameServer.getInstance().getActivePlayer(player_id));
        playerRaceList.put(player_id, race);

        ResponseRaceInit response = new ResponseRaceInit();
        for (int p_id : race.getPlayers().keySet()) {
            NetworkManager.addResponseForUser(p_id, response);
        }
        return race;
    }

    /**
     * This method will end a race and delete the existing instances of a race
     *
     * @param raceID is the caller's race ID
     * @param playerID is the caller's player ID
     * @param winningTime this is the caller's winning time
     * @throws Exception
     */
    public void endRace(int raceID, int playerID, String winningTime) throws Exception {
        Race race = raceList.get(raceID);
        raceList.remove(raceID);
        // check if race exists
        // this eliminates loser calling end race
        if (race != null) {
            int opponentID = race.getOpponentID(playerID);

            // remove race instances
            playerRaceList.remove(playerID);
            playerRaceList.remove(opponentID);

            // create resposes
            ResponseRREndGame response = new ResponseRREndGame();
            response.setWinningTime(winningTime);

            // send responses to both players
            for (int p_id : race.getPlayers().keySet()) {
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

    public void destroyRace(int race_id) {
        raceList.remove(race_id);
    }

    public Race add(Race race) {
        for (int id : race.getPlayers().keySet()) {
            playerRaceList.put(id, race);
        }
        return raceList.put(race.getID(), race);
    }

    public Race getRaceByPlayerID(int playerID) {
        return playerRaceList.get(playerID);
    }
}
