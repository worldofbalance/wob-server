/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lby.net.request;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;
import lby.net.response.ResponseStats;
import lby.net.response.ResponseTopList;
import shared.db.PlayerDAO;
import shared.db.ScoreDAO;
import shared.db.StatsDAO;

/**
 *
 * @author guanmingpan
 */
public class RequestStatus extends GameRequest {
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
        
        if (client.getPlayer().getWorld() != null) {
            int player_id = client.getAccount().getID();
            int eco_id = client.getPlayer().getEcosystem().getID();
            //response.setDailyList(StatsDAO.getStats(month_start, month_end, player_id, eco_id));
            client.add(response);
        }
        client.add(response);
    }
}