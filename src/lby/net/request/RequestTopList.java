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
        String names[] = new String[3];
        int scores[] = new int[3];
        List<String[]> scoreList = ScoreDAO.getBestEnvScore_2(0, 3);
        
        for (int i=0; i<3; i++) {
            names[i] = PlayerDAO.getPlayer(Integer.parseInt(scoreList.get(i)[0])).getName();
            scores[i] = Integer.parseInt(scoreList.get(i)[1]);
        }
        response.setData(names[0], scores[0], names[1], scores[1], names[2], scores[2]);
        client.add(response);
    }
}