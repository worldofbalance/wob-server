package cos.net.response;

// Other Imports

import cos.metadata.NetworkCode;
import cos.model.SpeciesGroup;
import cos.util.GamePacket;

public class ResponseSpeciesCreate extends GameResponse {

    private short status;
    private int eco_id;
    private SpeciesGroup group;

    public ResponseSpeciesCreate(short status, int eco_id, SpeciesGroup group) {
        response_id = NetworkCode.SPECIES_CREATE;
        
        this.status = status;
        this.eco_id = eco_id;
        this.group = group;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);
        packet.addInt32(eco_id);
        packet.addInt32(group.getID());
        packet.addInt32(group.getSpecies().getID());
        packet.addString(group.getSpecies().getSpeciesType().getName());
        packet.addInt32(group.getSpecies().getSpeciesType().getModelID());
        packet.addInt32(group.getBiomass());
        packet.addFloat(group.getPosition().getX());
        packet.addFloat(group.getPosition().getY());
        packet.addFloat(group.getPosition().getZ());
        packet.addInt32(group.getUserID());

        return packet.getBytes();
    }
}
