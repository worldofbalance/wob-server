
package net.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import metadata.Constants;
import metadata.NetworkCode;
import model.Prey;
import utility.GamePacket;

/**
 * a Response to update other non-host players of NPC fish movements.
 * @author Karl
 */
public class ResponseNpcFishPosition extends GameResponse{

    private HashMap<Integer, Prey> fishMap; //preyid -> prey object

    public ResponseNpcFishPosition(){
        this.responseCode = NetworkCode.SD_NPCPOSITION;
    }
    
    @Override
    public byte[] constructResponseInBytes() {
        Prey fish;
        GamePacket packet = new GamePacket(responseCode);
        packet.addInt32(fishMap.size());    //number of fish in response
        //adds the fish in the hashmap for each entry: id,x,y,rotation
        for(Map.Entry<Integer, Prey> entry : fishMap.entrySet()){
            fish = entry.getValue();
            packet.addInt32(fish.getPrey_id());
            packet.addInt32(fish.getSpecies_id());
            packet.addFloat(fish.getX());
            packet.addFloat(fish.getY());
            packet.addFloat(fish.getRotation());
        }
        return packet.getBytes();
    }
    
    public void setNpcFishMap(HashMap<Integer, Prey> map){
        this.fishMap = map;
    }
    
    
    
    
}
