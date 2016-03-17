/**
 * @author Lobby Team
 */
package lby.net.response;

// Other Imports
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

/**
 * The ResponseLogin class contains information about the authentication
 * process.
 */
public class ResponseSeasonChange extends GameResponse {

    private short status;
    private int seasonCode;
    private int eventCode;

    public ResponseSeasonChange() {
        response_id = NetworkCode.SEASON_CHANGE;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);

        if (status == 0) { //season change, call season
            packet.addInt32(seasonCode);
        } else if (status == 1) { //natural event change, call natural event
            packet.addInt32(eventCode);
        } else if (status == 2) {
            //packet.addString();
        }

        return packet.getBytes();
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public void setSeasonCode(int seasonCode) {
        this.seasonCode = seasonCode;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }
}
