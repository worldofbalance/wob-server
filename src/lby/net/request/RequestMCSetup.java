/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lby.net.request;

import java.io.DataInputStream;
import java.io.IOException;
import lby.GameRoom;
import lby.GameRoomManager;
import lby.net.response.ResponseMCSetup;
import shared.util.DataReader;
import shared.util.Log;

/**
 *
 * @author yanxing wang
 */
public class RequestMCSetup extends GameRequest {
    int gameID = 0;
    short totalPlayers;
    short numRounds;
    short secPerRound;
    short betAmt;
    short ecoNum;
    short helps;
    
    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        gameID = DataReader.readInt(dataInput);
        totalPlayers = DataReader.readShort(dataInput);
        numRounds = DataReader.readShort(dataInput);
        secPerRound = DataReader.readShort(dataInput);
        betAmt = DataReader.readShort(dataInput);
        ecoNum = DataReader.readShort(dataInput);
        helps = DataReader.readShort(dataInput);
    }

    @Override
    public void process() throws Exception {
        GameRoom room;
        room = GameRoomManager.getInstance().createRoomWithClient(client);
        room.setGameID(gameID);
        room.setTotalPlayers(totalPlayers);
        room.setNumRounds(numRounds);
        room.setSecPerRound(secPerRound);
        room.setBetAmt(betAmt);
        room.setEcoNum(ecoNum);
        room.setHelps(helps);
        
        ResponseMCSetup response = new ResponseMCSetup();     
        response.setStatus(room.isFull() ? (short)0 : (short)1);
        response.setID(room.getID());
        response.setGameID(gameID);
        
        room.sendResponse(response);   // Sends reponse to all clients 
    }
}
