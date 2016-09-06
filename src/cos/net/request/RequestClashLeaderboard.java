package cos.net.request;

import cos.db.LeaderboardDAO;
import cos.model.Leaderboard;
import cos.net.response.ResponseClashLeaderboard;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Abhi on 7/10/16.
 */
public class RequestClashLeaderboard extends GameRequest {
	@Override
	public void parse(DataInputStream dataInput) throws IOException {

	}

	@Override
	public void process() throws Exception {
		ResponseClashLeaderboard response = new ResponseClashLeaderboard();

		List<Leaderboard> leaderboards = LeaderboardDAO.findMatchesByMatchResult();
		for(Leaderboard leaderboard : leaderboards){
			if(leaderboard.playerName != null ) {
				response.addLeaderboard(leaderboard);
			}
		}
		client.add(response);
	}
}
