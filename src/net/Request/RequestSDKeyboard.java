/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Request;

import PlayTime.PlayManager;
import PlayTime.PlayTimePlayer;
import core.GameServer;
import java.io.IOException;
import net.Response.ResponseSDKeyboard;
import utility.DataReader;

/**
 *
 * @author anu
 */
public class RequestSDKeyboard extends GameRequest {
    
    private int keyCode,keyCombination;
    private int p_id;
    private  ResponseSDKeyboard responsekeyboard;
    
       
    public void parse() throws IOException {
        keyCode = DataReader.readInt(dataInput);
        keyCombination = DataReader.readInt(dataInput);
    }
    
    public void doBusiness() throws Exception {
        PlayTimePlayer player;
      //  System.out.println("key type:  " +  keytype + "key :  " + key);
        
        responsekeyboard = new ResponseSDKeyboard();
        responsekeyboard.setKeyCode(keyCode);
        responsekeyboard.setKeyCombination(keyCombination);
        
        p_id = PlayManager.manager.getRaceByPlayerID(client.getPlayer().getPlayer_id())
                .getOpponent(client.getPlayer()).getPlayer_id();
        
        player = PlayManager.manager.getRaceByPlayerID(p_id).getPlayers().get(p_id);
        
        GameServer.getInstance().getThreadByPlayerID(p_id).send(responsekeyboard);

    }
    
}
