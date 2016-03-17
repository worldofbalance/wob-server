package lby.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Other Imports
import shared.db.ScoreDAO;
import shared.model.Ecosystem;
import lby.net.response.ResponseHighScore;
import shared.util.DataReader;

public class RequestHighScore extends GameRequest {

    private short type;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        type = DataReader.readShort(dataInput);
    }

    @Override
    public void process() throws Exception {
        ResponseHighScore response = new ResponseHighScore();
        response.setType(type);

        if (type == 0) {
            Ecosystem ecosystem = client.getPlayer().getEcosystem();

            List<String[]> scoreList = new ArrayList<String[]>();
            scoreList.add(new String[]{client.getAccount().getUsername(), String.valueOf(ecosystem.getHighEnvScore())});
            response.setEnvScore(scoreList);

            List<String[]> totalScoreList = new ArrayList<String[]>();
            totalScoreList.add(new String[]{client.getAccount().getUsername(), String.valueOf(ecosystem.getAccumulatedEnvScore())});
            response.setTotalEnvScore(totalScoreList);

            List<String[]> currentScoreList = new ArrayList<String[]>();
            currentScoreList.add(new String[]{client.getAccount().getUsername(), String.valueOf(ecosystem.getScore())});
            response.setCurrentEnvScore(currentScoreList);
        } else {
            List<String> patternList = new ArrayList<String>();
            String username = client.getAccount().getUsername();

            if (username.startsWith("wbtester")) {
                patternList.add("wbtester");
            } else if (username.startsWith("wbuser")) {
                patternList.add("wbuser");
            }

            response.setEnvScore(ScoreDAO.getBestEnvScore(0, 3, patternList));
            response.setTotalEnvScore(ScoreDAO.getBestTotalEnvScore(0, 3, patternList));
            response.setCurrentEnvScore(ScoreDAO.getBestCurrentEnvScore(0, 3, patternList));
        }

        client.add(response);
    }
}
