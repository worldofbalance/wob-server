/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdv.net.Response;

import shared.metadata.NetworkCode;
import shared.util.GamePacket;

/**
 *
 * @author anu
 */
public class ResponseReSpawnPrey extends GameResponse{
    
  private int species_id;
  private int num_of_prey;
   
   
    public ResponseReSpawnPrey() {
        responseCode = NetworkCode.SD_RESPAWN;
    }
    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
         packet.addInt32(species_id);
         packet.addInt32(num_of_prey);
        return packet.getBytes();
    }

    public void setSpecies_id(int species_id) {
        this.species_id = species_id;
    }

    public void setNum_of_prey(int num_of_prey) {
        this.num_of_prey = num_of_prey;
    }

   
}
