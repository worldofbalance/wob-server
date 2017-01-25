package cos.net.response;

// Java Imports

import cos.metadata.NetworkCode;
import cos.util.GamePacket;

import java.util.Map;
import java.util.Map.Entry;

// Other Imports

public class ResponsePrediction extends GameResponse {

    private short status;
    private Map<Integer, Integer> results;

    public ResponsePrediction() {
        response_id = NetworkCode.PREDICTION;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);
        packet.addShort16((short) results.size());

        for (Entry<Integer, Integer> entry : results.entrySet()) {
            packet.addInt32(entry.getKey());
            packet.addInt32(entry.getValue());
        }

        return packet.getBytes();
    }
    
    public void setStatus(short status) {
        this.status = status;
    }

    public void setResults(Map<Integer, Integer> results) {
        this.results = results;
    }
}
