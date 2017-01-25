package lby.net.response;

// Other Imports
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

public class ResponseSpeciesKill extends GameResponse {

    private int organism_id;
    private int predator_id;
    private int amount;

    public ResponseSpeciesKill() {
        response_id = NetworkCode.SPECIES_KILL;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addInt32(predator_id);
        packet.addInt32(organism_id);
        packet.addShort16((short) amount);
        return packet.getBytes();
    }

    public void setOrganismID(int organism_id) {
        this.organism_id = organism_id;
    }

    public void setPredatorID(int predator_id) {
        this.predator_id = predator_id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
