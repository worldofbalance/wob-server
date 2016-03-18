package lby.net.response;

// Java Imports
import java.util.List;

// Other Imports
import lby.core.world.Zone;
import shared.metadata.NetworkCode;
import shared.model.Player;
import shared.util.GamePacket;

public class ResponseEcosystem extends GameResponse {

    // Status Codes
    public final static short SUCCESS = 0;
    public final static short FAILED = 1;
    public final static short INVALID_PLAYER = 2;
    // Variables
    private short status;
    private int eco_id;
    private short type;
    private int score;
    private Player player;
    private List<Zone> zones;

    public ResponseEcosystem() {
        response_id = NetworkCode.ECOSYSTEM;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);
        
        if (status == SUCCESS) {
            packet.addInt32(eco_id);
            packet.addShort16(type);
            packet.addInt32(score);
            
            packet.addInt32(player.getID());
            packet.addString(player.getName());
            packet.addString(player.getColor().toRGB());

            String str = "";
            for (Zone zone : zones) {
                str += zone.getID() + "," + zone.getRow() + ","
                        + zone.getColumn() + "," + zone.getTerrainType() + ","
                        + zone.getVegetationCapacity() + ";";
            }
            packet.addString(str);
        }

        return packet.getBytes();
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public void setEcosystem(int eco_id, short type, int score) {
        this.eco_id = eco_id;
        this.type = type;
        this.score = score;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }
}
