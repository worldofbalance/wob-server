package cos.net.request;

import cos.db.MatchRecordDAO;
import cos.model.MatchRecord;
import cos.net.response.ResponseClashNotification;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Abhi on 7/10/16.
 */
public class RequestClashNotification extends GameRequest {
	@Override
	public void parse(DataInputStream dataInput) throws IOException {

	}

	@Override
	public void process() throws Exception {
		ResponseClashNotification response = new ResponseClashNotification();
		List<MatchRecord> matches = MatchRecordDAO.findMatchesByLastLogout(client.getPlayer().getID(), client.getAccount().getLastLogout());
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
