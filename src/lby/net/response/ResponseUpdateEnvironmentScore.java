package lby.net.response;

// Other Imports
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

public class ResponseUpdateEnvironmentScore extends GameResponse {

    private int env_id;
    private int score;
    private int highScore;

    public ResponseUpdateEnvironmentScore(int env_id, int score, int highScore) {
        response_id = NetworkCode.UPDATE_ENV_SCORE;
        
        this.env_id = env_id;
        this.score = score;
        this.highScore = highScore;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addInt32(env_id);
        packet.addInt32(score);
        packet.addInt32(highScore);

        return packet.getBytes();
    }
}
