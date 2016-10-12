package cvg.net.request;

import java.io.DataInputStream;
import java.io.IOException;

import cvg.match.MCMatchManager;
import shared.core.GameClient;
import shared.util.Log;
import lby.net.request.GameRequest;
import cvg.net.response.ResponseConvergeCheckPlayers;
import cvg.net.response.ResponseConvergeBetUpdate;
import cvg.match.*;
import java.util.Map;
import java.util.Iterator;

// DH change - copied + modified from CW

public class RequestConvergeCheckPlayers extends GameRequest {

    private int match_id;
    private int player_id;
    MCMatch match;
    int bet;
    int totalBet;
    int tieCount;
    int bestImprove;
    boolean found;
    boolean anyBet;
    Map<Integer, MCMatchPlayer> playerList;
    private ResponseConvergeCheckPlayers response;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {

    }

    @Override
    public void process() throws Exception {
        response = new ResponseConvergeCheckPlayers();
        MCMatchManager manager = MCMatchManager.getInstance();
        player_id = client.getPlayerID();
        match_id = client.getMatchID();
        
        // Put this player's information in playerList
        match = manager.getMatch(match_id);
        playerList = match.playerList;
        // MCMatchPlayer player = match.playerList.get(player_id);
        // MCMatchPlayer BetStatus
        // 0 -> no response yet
        // 1 -> response, not betting
        // 2 -> response, betting 
        
        // Find out if we've checked yet this found
        // If all betStatus = 0 then we have already removed gone players
        Log.println("RCCP: Did all bet status get processed? ");
        anyBet = false;
        Iterator<Integer> it = playerList.keySet().iterator();
        while ((it.hasNext()) && (!anyBet)) {
            Integer key = it.next();
            MCMatchPlayer player1 = playerList.get(key);
            if (player1.getBetStatus() != 0) {
                anyBet = true;
            }
        }
        
        if (anyBet && !match.getChecking()) {
            match.setChecking(true);    // Make sure only 1 instance of CheckPlayers runs per round
            Log.println("RCCP: checking players. This player id is " + player_id);
            // Check which players have not responded. Remove the first one you find        
            found = false;   // found someone not answered yet
            bet = match.getBetAmount();
            totalBet = 0; 
            tieCount = 1;
            int bestPlayer_id = 0;
            bestImprove = -1000000;
        
            it = playerList.keySet().iterator();
            while(it.hasNext()) {
                Integer key = it.next();
                MCMatchPlayer player1 = playerList.get(key);
                int improve1 = player1.getImproveAmount();
                Log.println("RCCP: Looking at player: " + player1.getPlayerName());
                if (player1.getBetStatus() == 2) {  // only consider if he is betting
                    totalBet += bet;
                    if (improve1 > bestImprove) {
                        bestImprove = improve1;
                        tieCount = 1;
                        bestPlayer_id = key;
                    } else if (improve1 == bestImprove) {
                        bestPlayer_id = 0;  // For tie, no player is considered winner
                        tieCount++;
                    }
                }
                if (player1.getBetStatus() == 0) {
                    Log.println("RCCP: Removed player: " + player1.getPlayerName());
                    playerList.remove(key);                
                    // Start over again on the list
                    it = playerList.keySet().iterator();
                    totalBet = 0; 
                    tieCount = 1;
                    bestImprove = -1000000;
                    found = true;  // Then you know to respond
                }
            }

            Log.println("RCCP: finished purging list");
            if (found) {  // found some to remove - so you can repond
                response.setStatus((short)1);
                Log.println("RCCP: found: Best Improved / Tie count = " + bestImprove + " " + tieCount);
                if (match.getNumRounds() == match.getCurRound()) {
                    int gameTotalBet = match.getNumRounds() * match.getBetAmount() * match.getPlayers().size();
                    Log.println("RCBU: Last round, total game bet: " + gameTotalBet);
                    totalBet += gameTotalBet/2;
                }
                int dividedBet = totalBet / tieCount;
                short won = 1;
                short lost = 0;            
                for (Map.Entry<Integer, MCMatchPlayer> entry : playerList.entrySet()) {
                    MCMatchPlayer player1 = getValue(entry);
                    ResponseConvergeBetUpdate response1 = player1.getResponse();
                    response1.setWinner(bestPlayer_id);
                    Log.println("This player improved: " + player1.getImproveAmount());
                    if (player1.getBetStatus() == 2) { // if this player betted
                        if (player1.getImproveAmount() == bestImprove) {
                            Log.println("He won");
                            response1.setWon(won);
                            response1.setWonAmount(dividedBet);
                            player1.setWinnings(player1.getWinnings() + dividedBet);
                        } else {
                            Log.println("He lost");
                            response1.setWon(lost);
                            response1.setWonAmount(0);
                        } 
                    } else { // this player did not bet
                        Log.println("He did not bet");
                        response1.setWon(lost);  // these entries should not matter
                        response1.setWonAmount(0); 
                    }
                    player1.setBetStatus(0);
                    GameClient client1 = player1.getClient();
                    client1.add(response1);
                } 
            
                short shortResult = match.getCurRound();
                shortResult += (short) 1;
                match.setCurRound(shortResult);
                long timeValue = System.currentTimeMillis();
                Log.println("RCCP Current time is: " + timeValue);
                manager.getMatch(match_id).setStartTime(timeValue);   
            
            } else {
                response.setStatus((short)0);  // no one found this found
            }
        } else {
            Log.println("RCCP: No bet processing this time.");
        }
        client.add(response);     
    }
    
    MCMatchPlayer getValue(Map.Entry<Integer, MCMatchPlayer> entry) {
        return entry.getValue();
    }
}
