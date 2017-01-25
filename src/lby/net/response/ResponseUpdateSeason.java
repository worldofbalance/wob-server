package lby.net.response;

// Other Imports
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

/**
 *
 * @author Zi sheng Wu
 */
public class ResponseUpdateSeason extends GameResponse {

    private short season_id;

    public ResponseUpdateSeason() {
        response_id = NetworkCode.UPDATE_SEASON;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(season_id);
        return packet.getBytes();
    }

    public void setSeasonID(short seasonID) {
        this.season_id = seasonID;
    }
}
