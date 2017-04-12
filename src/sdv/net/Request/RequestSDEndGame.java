/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdv.net.Request;

import java.io.IOException;
import shared.util.DataReader;
import sdv.net.Response.ResponseSDEndGame;
import sdv.PlayTime.Play;
import sdv.PlayTime.PlayManager;
import sdv.PlayTime.PlayTimePlayer;
import shared.util.Log;

/**
 *
 * @author anu
 */
public class RequestSDEndGame extends GameRequest{


    private boolean gameCompleted;
    private float finalScore;
    private int p_id_opponent;
    private ResponseSDEndGame responseSDEndGame;
    private int status;
    private int flag;
    private float winningscore;
    private int winningID;
    
   
    public RequestSDEndGame() {
        gameCompleted = false;
        finalScore = 0.0f;
        Log.println("A ResponseSDEndGame has been sent out");
    }

    @Override
    public void parse() throws IOException {
        gameCompleted = DataReader.readBoolean(dataInput);
        finalScore = DataReader.readFloat(dataInput);
    }

    @Override
    public void doBusiness() throws Exception {

	try{
      
        int thisPlayerID = this.client.getPlayer().getPlayer_id();
        PlayManager.manager.getPlayByPlayerID(thisPlayerID).getPlayers().get(thisPlayerID).setFinalScore(finalScore);
        PlayManager.manager.getPlayByPlayerID(thisPlayerID).getPlayers().get(thisPlayerID).setScoreflag(1);
       
        flag = PlayManager.manager.getPlayByPlayerID(thisPlayerID).getPlayers().get(thisPlayerID).getScoreflag();
        // end race
        Play play = PlayManager.manager.getPlayByPlayerID(thisPlayerID);
        if (play != null) {
            
            p_id_opponent = PlayManager.manager.getPlayByPlayerID(thisPlayerID)
                             .getOpponent(client.getPlayer()).getPlayer_id();
            int opponentflag = PlayManager.manager.getPlayByPlayerID(p_id_opponent).getPlayers().get(p_id_opponent).getScoreflag();
            // will get executed only when second player calls endgame
            if(!gameCompleted){
             float opponentscore = PlayManager.manager.getPlayByPlayerID(thisPlayerID)
                                    .getOpponent(client.getPlayer()).getScore();
              status = 2; // opponent won
           PlayManager.manager.endPlay(play.getID(), p_id_opponent, opponentscore, status);
            
            }
            if(gameCompleted){
            if((flag==1)&&(opponentflag==1)){
                float opponentscore = PlayManager.manager.getPlayByPlayerID(thisPlayerID)
                .getOpponent(client.getPlayer()).getFinalScore();
          
                if (finalScore>opponentscore){
                    status = 1; // client calling request wins
                    winningscore = finalScore;
                    winningID = thisPlayerID;
                }
                else if(opponentscore>finalScore){
                    status = 2; // opponent won
                    winningscore = opponentscore;
                    winningID = p_id_opponent;
            
                } else {
                    status = 3; // draw
                    winningscore = finalScore;
                    winningID = 0;
                }
             
            PlayManager.manager.endPlay(play.getID(), winningID , winningscore, status);
            }
         }
        }
		}
		catch(Exception ex) {}
    }

}
