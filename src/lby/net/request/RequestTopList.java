package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

// Other Imports
import shared.db.PlayerDAO;
import shared.db.ScoreDAO;
import lby.net.response.ResponseTopList;

public class RequestTopList extends GameRequest {
    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        
    }

    @Override
    public void process() throws Exception {
        ResponseTopList response = new ResponseTopList();
        String names[] = new String[6];
        int scores[] = new int[6];
        List<String[]> scoreList = ScoreDAO.getBestEnvScore_2(0, 3);
        
        for (int i=0; i<6; i++) {
            names[i] = PlayerDAO.getPlayer(Integer.parseInt(scoreList.get(i)[0])).getName();
            scores[i] = Integer.parseInt(scoreList.get(i)[1]);
        }
        response.setData(names[0], scores[0], names[1], scores[1], names[2], scores[2],
                names[3], scores[3], names[4], scores[4], names[5], scores[5]);
        client.add(response);
    }
}