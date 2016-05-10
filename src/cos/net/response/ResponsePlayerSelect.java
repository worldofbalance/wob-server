package cos.net.response;

// Other Imports
import cos.metadata.NetworkCode;
import cos.model.Player;
import cos.util.GamePacket;

public class ResponsePlayerSelect extends GameResponse {

    // Status Codes
    public final static short SUCCESS = 0;
    public final static short FAILED = 1;
    // Variables
    private short status;
    private Player player;

    public ResponsePlayerSelect() {
        response_id = NetworkCode.PLAYER_SELECT;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);

        if (status == SUCCESS) {
            packet.addInt32(player.getID());
            packet.addString(player.getName());
            packet.addShort16(player.getLevel());
            packet.addInt32(player.getExperience());
            packet.addInt32(player.getCredits());
            packet.addString(player.getColor().toRGB());
            //jtc - otherwise fails on first login
            if (player.getLastPlayed() == null) {
                packet.addString("");
            } else {
                packet.addString(player.getLastPlayed());
            }
        }

        return packet.getBytes();
    }
    
    public void setStatus(short status) {
        this.status = status;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
