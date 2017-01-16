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

    private final int MAX_OVERTIME = -5;
    private short betStatus;
    private int improveAmount;
    private int match_id;
    private int player_id;
    private int round;
    MCMatch match;
    Map<Integer, MCMatchPlayer> playerList;
    MCMatchPlayer player;
    int bet;
    int totalBet;
    int tieCount;
    int bestImprove;
    int betTime;
    boolean overTime;
    int[] scores = new int[5];
    private ResponseConvergeBetUpdate response;
    short yes = 1;
    short no = 0; 

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        // betStatus: 1 -> bet submitted; 0 -> no bet submitted
        betStatus = DataReader.readShort(dataInput);
        round = (int) (DataReader.readShort(dataInput));
        improveAmount = DataReader.readInt(dataInput);
        scores[0] = DataReader.readInt(dataInput);
        scores[1] = DataReader.readInt(dataInput);
        scores[2] = DataReader.readInt(dataInput);
        scores[3] = DataReader.readInt(dataInput);
        scores[4] = DataReader.readInt(dataInput);
        Log.println("RCBU, parse: bs,r,ia,s0-4: " + betStatus + " " + round + " " + improveAmount + " " + scores[0]);
        Log.println(scores[1] + " " + scores[2] + " " + scores[3] + " " + scores[4] + " ");
    }

    @Override
    public void process() throws Exception {
        Log.println("Inside RCBU process");
        response = new ResponseConvergeBetUpdate();
        response.setRound((short) round); 
        MCMatchManager manager = MCMatchManager.getInstance();
        player_id = client.getPlayerID();
        Log.println("RCBU player_id: " + player_id);
        match_id = client.getMatchID(); 
        match = manager.getMatch(match_id);
        
        long startTime = match.getStartTime();
        long presentTime = System.currentTimeMillis();
        betTime = (int) (match.getTimeWindow() - (presentTime - startTime) / 1000);
        Log.println("RCBU player_id: sT,pT,gTW, bT: " + startTime + " " + presentTime + " " + 
                match.getTimeWindow() + " " + betTime);
        overTime = (betTime < MAX_OVERTIME);
        bet = match.getBetAmount();
        playerList = match.playerList;
        player = playerList.get(player_id);
        Log.println("RCBU: overTime, gBS: " + overTime + " " + player.getBetStatus(round));
        
        // Put this player's information in playerList
        
        // match.setChecking(false);
        
        // if your bet status > 0 then skip loading data and substracting bet     
        if (player.getBetStatus(round) == 0) {
            // I think the next two items have to be indexed by round
            player.setImproveAmount(round, improveAmount);
            player.setScores(scores);
            player.setClient(client);
            // player.setResponse(response);
            if (betStatus == 1) {
                player.setWinnings(player.getWinnings() - bet);            
            }        
            // MCMatchPlayer BetStatus
            // 0 -> no response yet
            // 1 -> response, not betting
            // 2 -> response, betting
            player.setBetStatus(round, betStatus+1);
        
            Log.println("RCBU: player score status");
            for (int i = 0; i < 5; i++) {
                Log.println(" " + i + " " + scores[i]);
            }            
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
            MCMatchPlayer player1 = entry.getValue();    // getValue(entry);
            if (player1.getLeftGame()) {
                continue;
            }
            int improve1 = player1.getImproveAmount(round);
            Log.println("RCBU: improve/betStatus: " + improve1 + " " + player1.getBetStatus(round));
            if (player1.getBetStatus(round) == 2) {  // only consider if he is betting
                totalBet += bet;
                if (improve1 > bestImprove) {
                    bestImprove = improve1;
                    tieCount = 1;
                    // Player_id of round winner.
                    bestPlayer_id = entry.getKey();   
                } else if (improve1 == bestImprove) {
                    bestPlayer_id = 0;    // For tie, no player is considered winner
                    tieCount++;
                }
            } else if (player1.getBetStatus(round) == 0) {
                if (overTime) {
                    player1.setLeftGame(true);                    
                } else {
                    found = true;
                }
            } 
        }
        
        Log.println("Total bet so far: " + totalBet);
        if (!found) {  // everyone has bet. Now we can send off responses
            Log.println("RCBU: All have bet for round = " + round);
            Log.println("Best Improved / Tie count = " + bestImprove + " " + tieCount);
            if (match.getNumRounds() == round) {     // was match.getCurRound()
                int gameTotalBet = match.getNumRounds() * match.getBetAmount() * match.getPlayers().size();
                Log.println("RCBU: Last round, total game bet: " + gameTotalBet);
                totalBet += gameTotalBet/2;
            }
            int dividedBet = totalBet / tieCount;       
            
            response.setWinner(bestPlayer_id);
            response.setRoundComplete(yes);
            if (player.getBetStatus(round) == 2) { // if this player betted
                if (player.getImproveAmount(round) == bestImprove) {
                    Log.println("He won");
                    response.setWon(yes);
                    response.setWonAmount(dividedBet);
                    player.setWinnings(player.getWinnings() + dividedBet);
                } else {
                    Log.println("He lost"); 
                    response.setWon(no);
                    response.setWonAmount(0);
                } 
            } else { // this player did not bet
                Log.println("He did not bet");
                response.setWon(no);  // these entries should not matter
                response.setWonAmount(0); 
            }
               
            /*            
            for (Map.Entry<Integer, MCMatchPlayer> entry : playerList.entrySet()) {
                MCMatchPlayer player1 = entry.getValue();   // getValue(entry);
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
            */
            long timeValue = System.currentTimeMillis();
            Log.println("RCBU Current time/round are: " + timeValue + " " + round);
            manager.getMatch(match_id).setStartTime(timeValue);   
        } else {
            response.setRoundComplete(no);
        }
        client.add(response);
    }
    
    /*
    MCMatchPlayer getValue(Map.Entry<Integer, MCMatchPlayer> entry) {
        return entry.getValue();
    }
    */
}
