
package net.Request;

import PlayTime.Play;
import PlayTime.PlayManager;
import PlayTime.PreySpawning;
import core.GameServer;
import java.io.IOException;
import metadata.Constants;
import net.Response.ResponseDestroyPrey;
import net.Response.ResponseReSpawnPrey;
import utility.DataReader;
import utility.Log;

/**
 * request from client to destroy a prey in the map on the server.
 * a response will be sent to the opponent to let them know this change.
 * @author Karl
 */
public class RequestDestroyPrey extends GameRequest{
    
    private int prey_id; //the prey being destroyed.
    private int species_id; // species being destroyed
    private int player_id; //the opponent player to send an update to.
    private ResponseDestroyPrey response = new ResponseDestroyPrey();
    private ResponseReSpawnPrey response2 = new ResponseReSpawnPrey();
    
    public RequestDestroyPrey(){}
    
    public void parse() throws IOException{
        
        species_id = DataReader.readInt(dataInput);
        prey_id = DataReader.readInt(dataInput);
    }
    
    public void doBusiness() throws Exception{
        
        response.setPreyId(prey_id);
        Play play = PlayManager.getInstance().getPlayByPlayerID(client.getPlayer().getPlayer_id());
        // decrease number on destroy
        if(species_id==1){
            play.species1--;
        }
        else if(species_id==2){
            play.species2--;
        }
        else if(species_id==3){
            play.species3--;
        }
        //The playerID of the opponent of the player who sent the request
        player_id = PlayManager.manager.getPlayByPlayerID(client.getPlayer().getPlayer_id())
                .getOpponent(client.getPlayer()).getPlayer_id();
        //sending the eaten preyID to the opponent, for them to update their view.
        GameServer.getInstance().getThreadByPlayerID(player_id).send(response);
        
        // check total number of fishes alive and send request to host client to spawn fishes
        if((play.species1+ play.species2 + play.species3)< Constants.MIN_PREY){
            if(play.species1<3){
                response2.setSpecies_id(1);
                response2.setNum_of_prey(4);
                play.species1 += 4;
                GameServer.getInstance().getThreadByPlayerID(play.HOST_client_id).send(response2);
            }
            else if(play.species2<3){
                 response2.setSpecies_id(2);
                response2.setNum_of_prey(4);
                play.species2 += 4;
                GameServer.getInstance().getThreadByPlayerID(play.HOST_client_id).send(response2);
            }
            else if(play.species3<3){
                 response2.setSpecies_id(3);
                response2.setNum_of_prey(3);
                play.species3 += 3;
                GameServer.getInstance().getThreadByPlayerID(play.HOST_client_id).send(response2);
            }
            
        }
    }
    
}
