package lby.net.response;

// Java Imports
import java.util.List;

// Other Imports
import shared.metadata.NetworkCode;
import shared.model.Player;
import shared.util.GamePacket;

public class ResponsePlayers extends GameResponse {

    private List<Player> playerList;

    public ResponsePlayers() {
        response_id = NetworkCode.PLAYERS;
    }

    public void setPlayers(List<Player> playerList) {
        this.playerList = playerList;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16((short) playerList.size());

        for (Player player : playerList) {
            packet.addInt32(player.getID());
            packet.addString(player.getName());
            packet.addString(player.getColor().toRGB());
        }

        return packet.getBytes();
    }
}
