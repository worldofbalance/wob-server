package lby.net.response.world;

// Other Imports
import lby.core.world.Zone;
import shared.metadata.NetworkCode;
import lby.net.response.GameResponse;
import shared.util.GamePacket;

public class ResponseZoneUpdate extends GameResponse {

    private short status;
    //private Tile tile;
    int tile_id;
    int tile_owner;

    public ResponseZoneUpdate() {
        response_id = NetworkCode.ZONE_UPDATE;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);

        if (status == 0) {
            packet.addInt32(tile_id);
            packet.addInt32(tile_owner);
            //packet.addString("Tile update successful");
//                        packet.addInt32(tile.getTerrainType());
//                        packet.addInt32(tile.getVegetationCapacity());
//                        packet.addInt32(tile.getXPosition());
//                        packet.addInt32(tile.getYPosition());
//                        packet.addInt32(tile.getZPosition());

        } else {
            packet.addString("Tile update failed");
        }

        return packet.getBytes();
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public void setTileId(int tile_id) {
        this.tile_id = tile_id;
    }

    public void setTileOwner(int tile_owner) {
        this.tile_owner = tile_owner;
    }
}
