package cos.net.response;

import cos.metadata.NetworkCode;
import cos.model.Leaderboard;
import cos.util.GamePacket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhi on 7/10/16.
 */
public class ResponseClashLeaderboard extends GameResponse {
	List<Leaderboard> leaderboards = new ArrayList<Leaderboard>();
	public ResponseClashLeaderboard(){
		response_id = NetworkCode.CLASH_LEADERBOARD;
	}

	public void addLeaderboard(Leaderboard leaderboard){
		leaderboards.add(leaderboard);
	}
	@Override
	public byte[] getBytes() {
		GamePacket packet = new GamePacket(response_id);
		packet.addInt32(leaderboards.size());
		for(Leaderboard leaderboard : leaderboards) {
			packet.addString(leaderboard.playerName);
			packet.addInt32(leaderboard.wins);
			packet.addInt32(leaderboard.loses);
		}
		return packet.getBytes();
	}
}
