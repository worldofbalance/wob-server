/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lby.net.request;

import java.io.DataInputStream;
import java.io.IOException;
import lby.GameRoomManager;
import shared.util.Log;

/**
 *
 * @author yanxing wang
 */
public class RequestBackToLobby extends GameRequest {

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
    }

    @Override
    public void process() throws Exception {
        Log.println("The client is requesting for quiting...");
        GameRoomManager.getInstance().onClientQuit(client);
    }   
}
