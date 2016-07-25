/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cos.net.request;

import cos.db.BattleDAO;
import cos.db.DefenseConfigDAO;
import cos.db.PlayerDAO;
import cos.db.MatchRecordDAO;
import cos.model.Battle;
import cos.model.DefenseConfig;
import cos.model.Player;
import cos.model.MatchRecord;
import cos.net.response.ResponseClashEndBattle;
import cos.util.DataReader;
import cos.util.Log;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Sent when the client has finished a battle
 * @author lev
 */
public class RequestClashEndBattle extends GameRequest {

    /**
     * The result of the battle: win, lose or draw
     */
    Battle.Outcome outcome;
    String matchResult;

    /**
     * Reads the result from the input stream and fills the outcome
     * instance variable appropriately
     * @param dataInput the input stream
     * @throws IOException
     */
    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        int value = DataReader.readInt(dataInput);
        
        if (value == 0) {
            outcome = Battle.Outcome.WIN;
            matchResult = "WIN";
        } else if (value == 1) {
            outcome = Battle.Outcome.LOSE;
            matchResult = "LOSE";
        } else {
            outcome = Battle.Outcome.DRAW;
            matchResult = "DRAW";
        }
    }

    /**
     * Saves battle result in database
     * Awards the winner with virtual currency
     * Punishes the loser (attacker only) with fines in
     * virtual currency
     * Sends back the player's new virtual currency balance
     * @throws Exception
     */
    @Override
    public void process() throws Exception {
        Player p = client.getPlayer();

        //record state in db
        Battle battle = BattleDAO.findActiveByPlayer(p.getID());
        battle.outcome = outcome;
        battle.timeEnded = new Date();
        BattleDAO.save(battle);

        DefenseConfig df = DefenseConfigDAO.findByDefenseConfigId(battle.defenseConfigId);
        Player defender = PlayerDAO.getPlayer(df.playerId);

        //record game result in DB
        MatchRecord matchRecord = new MatchRecord();
        matchRecord.playerId = p.getID();
        matchRecord.opponentId = df.playerId;
        matchRecord.matchResult = matchResult;
        matchRecord.playedOn = new Date();
        Log.console(String.valueOf(matchRecord.playerId));
        Log.console(String.valueOf(matchRecord.opponentId));
        MatchRecordDAO.create(matchRecord);

        int attackerCredits = PlayerDAO.getPlayer(p.getID()).getCredits();
        int defenderCredits = defender.getCredits();

        switch (outcome){
            case WIN:
                attackerCredits += 50;
                break;
            case LOSE:
                defenderCredits += 25;
                break;
            case DRAW:
                break;
        }

        PlayerDAO.updateCredits(p.getID(), attackerCredits);
        p.setCredits(attackerCredits);
        PlayerDAO.updateCredits(defender.getID(), defenderCredits);

        ResponseClashEndBattle response = new ResponseClashEndBattle();
        response.setCredits(attackerCredits);
        client.add(response);
    }
    
}
