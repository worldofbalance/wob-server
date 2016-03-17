/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cos.net.response;

import shared.metadata.NetworkCode;
import cos.model.Species;
import lby.net.response.GameResponse;
import shared.util.GamePacket;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores the list of species in Clash of Species to be sent back
 * to the client
 * @author lev
 */
public class ResponseClashSpeciesList extends GameResponse {
    /**
     * The list of species
     */
    private List<Species> speciesList;

    public ResponseClashSpeciesList() {
        response_id = NetworkCode.CLASH_SPECIES_LIST;
        speciesList = new ArrayList<Species>();
    }

    /**
     * Sets the list of species
     * @param list
     */
    public void setSpeciesList(List<Species> list) {
        speciesList = list;
    }

    /**
     * Generates a byte array in the following format:
     *  id of this response (short)
     *  # of species returned (int)
     *  for each species: id (int)
     *                  name (string)
     *                  price (int)
     *                  type (int, from a predetermined set)
     *                  description (string)
     *                  attack points, i.e. damage (int)
     *                  hit points (int)
     *                  movement speed (int)
     *                  attack speed (int)
     * @return the byte array
     */
    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addInt32(speciesList.size());
        //System.out.println("send " + speciesList.size() + " species");
        for (Species sp : speciesList) {
            packet.addInt32(sp.speciesId);
            packet.addString(sp.name);
            packet.addInt32(sp.price);
            packet.addInt32(sp.type.getValue());
            packet.addString(sp.description);
            packet.addInt32(sp.attackPoints);
            packet.addInt32(sp.hitPoints);
            packet.addInt32(sp.movementSpeed);
            packet.addInt32(sp.attackSpeed);
        }
        return packet.getBytes();
    }
}
