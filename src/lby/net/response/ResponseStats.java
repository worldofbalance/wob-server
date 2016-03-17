package lby.net.response;

// Java Imports
import java.util.List;

// Other Imports
import shared.metadata.NetworkCode;
import shared.model.Stat;
import shared.util.GamePacket;

public class ResponseStats extends GameResponse {

    private List<Stat> statList;

    public ResponseStats() {
        response_id = NetworkCode.STATISTICS;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16((short) statList.size());

        for (Stat stat : statList) {
            packet.addShort16((short) stat.getMonth());
            packet.addString(stat.getSpeciesName());
            packet.addString(stat.getType());
            packet.addShort16((short) stat.getAmount());
        }

        return packet.getBytes();
    }

    public void setStats(List<Stat> statList) {
        this.statList = statList;
    }
}
