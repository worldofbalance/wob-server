/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cos.net.request;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import shared.db.PlayerDAO;
import cos.db.AttackConfigDAO;
import cos.db.BattleDAO;
import cos.db.DefenseConfigDAO;
import shared.model.Player;
import cos.model.AttackConfig;
import cos.model.Battle;
import cos.model.DefenseConfig;
import lby.net.request.GameRequest;
import cos.net.response.ResponseClashInitiateBattle;
import shared.util.DataReader;

/**
 * Sent when the player initiates a battle on the client
 * @author lev
 */
public class RequestClashInitiateBattle extends GameRequest{

    /**
     * The id of the player attacked
     */
    private int playerToAttack;

    /**
     * The list of species with which the attack is made
     */
    private ArrayList<Integer> attackConfig;

    /**
     * Reads in the data about the attack from the input sent by the
     * client
     * @param dataInput the input stream
     * @throws IOException
     */
    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        playerToAttack = DataReader.readInt(dataInput);
        attackConfig = new ArrayList<Integer>();
        int count = DataReader.readInt(dataInput);
        for(int i = 0; i < count; i++){
            attackConfig.add(DataReader.readInt(dataInput));
        }
    }

    /**
     * Checks if the attack configuration sent is valid
     * Adds the validity flag to the response
     * if valid, saves the battle to the database
     * adds the response to the queue to be sent back to the client
     * @throws Exception
     */
    @Override
    public void process() throws Exception {
        ResponseClashInitiateBattle response = new ResponseClashInitiateBattle();

        Player player = client.getPlayer();

        // Subtract the cost of attacking from the player's credits
        int currentCredits = PlayerDAO.getPlayer(player.getID()).getCredits();

        if (attackConfig.size() > 5 || currentCredits < 10) {
            response.setValid(false);
        } else {
            response.setValid(true);

            // Charge the player the cost of attacking
            currentCredits -= 10;
            player.setCredits(currentCredits);
            PlayerDAO.updateCredits(player.getID(), currentCredits);

            DefenseConfig target = DefenseConfigDAO.findByPlayerId(playerToAttack);

            AttackConfig atk = new AttackConfig();
            atk.createdAt = new Date();
            atk.playerId = this.client.getPlayer().getID();
            atk.speciesIds = attackConfig;
            AttackConfigDAO.create(atk);

            Battle battle = new Battle();
            battle.defenseConfigId = target.id;
            battle.attackConfigId = atk.id;
            battle.timeStarted = new Date();
            BattleDAO.create(battle);
        }
        client.add(response);
    }
    
}
