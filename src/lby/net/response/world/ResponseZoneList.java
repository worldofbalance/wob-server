package lby.net.response.world;

// Java Imports
import java.util.List;

// Other Imports
import lby.core.world.Zone;
import shared.metadata.NetworkCode;
import shared.model.Player;
import lby.net.response.GameResponse;
import shared.util.GamePacket;

/**
 *
 * @author Ari
 */
public class ResponseZoneList extends GameResponse {

    // Status Codes
    public final static short SUCCESS = 0;
    public final static short FAILED = 1;
    // Variables
    private short status;
    private List<Player> players;
    private short height;
    private short width;
    private List<Zone> zones;

    public ResponseZoneList() {
        response_id = NetworkCode.ZONE_LIST;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);

        if (status == SUCCESS) {
            packet.addShort16((short) players.size());
            for (Player player : players) {
                packet.addInt32(player.getID());
                packet.addString(player.getName());
                packet.addString(player.getColor().toRGB());
            }
            
            packet.addShort16(height);
            packet.addShort16(width);

            String str = "";
            for (Zone zone : zones) {
                str += zone.getID() + "," + zone.getRow() + ","
                        + zone.getColumn() + "," + zone.getTerrainType() + ","
                        + zone.getVegetationCapacity() + "," + zone.getOwner() + ";";
            }
            packet.addString(str);
        }

        return packet.getBytes();
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public void setZonePlayers(List<Player> players) {
        this.players = players;
    }

    public void setZoneList(short height, short width, List<Zone> zoneList) {
        this.height = height;
        this.width = width;
        this.zones = zoneList;
    }
}
