package cos.net.response;

// Other Imports
import cos.metadata.NetworkCode;
import cos.util.GamePacket;

public class ResponseChart extends GameResponse {

    private short type;
    private String csv;

    public ResponseChart() {
        response_id = NetworkCode.CHART;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(type);
        packet.addString(csv);

        return packet.getBytes();
    }

    public void setCSV(String csv) {
        this.csv = csv;
    }

    public void setType(int type) {
        this.type = (short) type;
    }
}
