package cos.net.request;

import cos.db.MatchRecordDAO;
import cos.model.MatchRecord;
import cos.net.response.ResponseClashPlayerHistory;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Abhi on 7/4/16.
 */
public class RequestClashPlayerHistory extends GameRequest {
	@Override
	public void parse(DataInputStream dataInput) throws IOException {

	}

	@Override
	public void process() throws Exception {
		ResponseClashPlayerHistory response = new ResponseClashPlayerHistory();

		List<MatchRecord> matches = MatchRecordDAO.findMatchesByPlayerId(client.getPlayer().getID());
		matches.addAll(MatchRecordDAO.findMatchesByOpponentId(client.getPlayer().getID()));
		for(MatchRecord matchRecord : matches){
			// Players don't need to be active to initialize a battle.
			// Don't display yourself in the list of potential opponents
			if(matchRecord.playerName != null ) {
				response.addGame(matchRecord);
			}
		}
		client.add(response);
	}
}
