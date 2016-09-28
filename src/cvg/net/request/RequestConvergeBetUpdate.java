package cvg.net.request;

import java.io.DataInputStream;
import java.io.IOException;

import cvg.match.MCMatchManager;
import shared.core.GameClient;
import shared.util.DataReader;
import shared.util.Log;
import lby.net.request.GameRequest;
import cvg.net.response.ResponseConvergeBetUpdate;
import cvg.match.*;
import java.util.*;

// DH change - copied + modified from CW

public class RequestConvergeBetUpdate extends GameRequest {

    private short betStatus;
    private int improveAmount;
    private int match_id;
    private int player_id;
    MCMatch match;
    Map<Integer, MCMatchPlayer> playerList;
    MCMatchPlayer player;
    int bet;
    int totalBet;
    int tieCount;
    int bestImprove;
    int[] scores = new int[5];
    private ResponseConvergeBetUpdate response;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        // betStatus: 1 -> bet submitted; 0 -> no bet submitted
        betStatus = DataReader.readShort(dataInput);
        improveAmount = DataReader.readInt(dataInput);
        scores[0] = DataReader.readInt(dataInput);
        scores[1] = DataReader.readInt(dataInput);
        scores[2] = DataReader.readInt(dataInput);
        scores[3] = DataReader.readInt(dataInput);
        scores[4] = DataReader.readInt(dataInput);
    }

    @Override
    public void process() throws Exception {
        response = new ResponseConvergeBetUpdate();
        MCMatchManager manager = MCMatchManager.getInstance();
        player_id = client.getPlayerID();
        match_id = client.getMatchID();
        
        // Put this player's information in playerList
        match = manager.getMatch(match_id);
        bet = match.getBetAmount();
        playerList = match.playerList;
        Integer i1 = new Integer(player_id);
        player = playerList.get(player_id);
        // MCMatchPlayer BetStatus
        // 0 -> no response yet
        // 1 -> response, not betting
        // 2 -> response, betting
        player.setBetStatus(betStatus+1);
        player.setImproveAmount(improveAmount);
        player.setScores(scores);
        player.setClient(client);
        player.setResponse(response);
        player.setWinnings(player.getWinnings() - bet);
        
        Log.println("RCBU: player score status");
        for (int i = 0; i < 5; i++) {
            Log.println(" " + i + " " + scores[i]);
        }
        // Check if all players have responsed. 
        // Iterator it = match.playerList.entrySet().iterator();
        boolean found = false;   // found someone not answered yet
        totalBet = 0; 
        tieCount = 1;
        int bestPlayer_id = 0;
        bestImprove = -1000000;
        for (Map.Entry<Integer, MCMatchPlayer> entry : playerList.entrySet()) {
            // Integer key = entry.getKey();
            MCMatchPlayer player1 = getValue(entry);
            int improve1 = player1.getImproveAmount();
            Log.println("RCBU: improve/betStatus: " + improve1 + " " + player1.getBetStatus());
            if (player1.getBetStatus() == 2) {  // only consider if he is betting
                totalBet += bet;
                if (improve1 > bestImprove) {
                    bestImprove = improve1;
                    tieCount = 1;
                    // Player_id of round winner. If tie, only first is captured
                    bestPlayer_id = entry.getKey();   
                } else if (improve1 == bestImprove) {
                    tieCount++;
                }
            }
            if (player1.getBetStatus() == 0) {
                found = true; 
            } 
        }
        
        Log.println("Total bet so far: " + totalBet);
        if (!found) {  // everyone has bet. Now we can send off responses
            Log.println("Best Improved / Tie count = " + bestImprove + " " + tieCount);
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
            Log.println("RCBU Current time/round are: " + timeValue + " " + shortResult);
            manager.getMatch(match_id).setStartTime(timeValue);   
        }
    }
    
    MCMatchPlayer getValue(Map.Entry<Integer, MCMatchPlayer> entry) {
        return entry.getValue();
    }
}
