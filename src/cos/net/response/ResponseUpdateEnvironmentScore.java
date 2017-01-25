package cos.net.response;

// Other Imports
import cos.metadata.NetworkCode;
import cos.util.GamePacket;

public class ResponseUpdateEnvironmentScore extends GameResponse {

    private int env_id;
    private int score;

    public ResponseUpdateEnvironmentScore(int env_id, int score) {
        response_id = NetworkCode.UPDATE_ENV_SCORE;
        
        this.env_id = env_id;
        this.score = score;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addInt32(env_id);
        packet.addInt32(score);

        return packet.getBytes();
    }
}
