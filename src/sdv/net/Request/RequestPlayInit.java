
package sdv.net.Request;

/**
 *
 * @author anu
 */

import sdv.PlayTime.Play;
import sdv.PlayTime.PlayManager;
import sdv.core.GameServer;
import sdv.core.NetworkManager;
import java.io.IOException;
import shared.metadata.Constants;
import sdv.net.Response.ResponsePlayInit;
import sdv.net.Response.ResponseSDReconnect;
import shared.util.DataReader;
import shared.util.Log;

public class RequestPlayInit extends GameRequest {

    private int player_id;
    private int room_id;
    private short status;
    @Override
    public void parse() throws IOException {
        player_id = DataReader.readInt(dataInput);
        room_id = DataReader.readInt(dataInput);
    }

    @Override
    public void doBusiness() throws Exception {
        int tester; //number of players in game test.
        Log.println("Trying to start Play: PlayerID[" +
                player_id + "], RoomID[" + room_id + "]");
        
        //reconnection check.
        Play test = PlayManager.getInstance().getPlayByPlayerID(player_id);
        if(test != null){
            status = 2;
        }else{
        //since the play exists, check if it's full.
        try{
            tester = PlayManager.getInstance().playerPlayList.get(room_id).getPlayers().size();
            if(tester == Constants.MAX_NUMBER_OF_PLAYERS){
                status = 1;
            }
        }catch(Exception ex){}
        }
        
        Play play = PlayManager.getInstance().createPlay(player_id, room_id);
        // if((PlayManager.getInstance().playerPlayList.get(room_id).getPlayers().size())==1){
        if(play.getPlayers().size() == 1){
            play.HOST_client_id = player_id;
            Log.printf("The host of Play:" + play.getID() + " is Player:" + player_id);
        }
        if(status !=2)
            play.getPlayer(player_id).setNumber(play.getPlayers().size());
        Log.printf("play created, assigning player number %d.", play.getPlayers().size());
        
        Log.println("Trying to start Play: PlayerID[" +
                player_id + "], RoomID[" + room_id + "]");
        
        if(play != null) {
            ResponsePlayInit response = new ResponsePlayInit();
            response.setPlayer(play.getPlayer(player_id));
            response.setNumber(play.getPlayer(player_id).getNumber());
            response.setStatus(status);
            //add response for self
            responses.add(response);
            //get opponent id
            try{
            int opp_id= PlayManager.getInstance().getPlayByPlayerID(client.getPlayer().getPlayer_id())
                .getOpponent(client.getPlayer()).getPlayer_id();
            
            /* this is meant for adding a response for all players in a match.
            for(int p_id : play.getPlayers().keySet()) {
                NetworkManager.addResponseForUser(p_id, response);
            }*/
            //send response to opponent
            GameServer.getInstance().getThreadByPlayerID(opp_id).send(response);
            }catch(Exception ex){
                Log.printf_e("waiting for opponent in game %d", room_id);
            }
            Log.println("Play created with players: " + play.getPlayers().keySet().toString());
        }
        
        
        
    }
    
    
    


}
