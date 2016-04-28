/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import PlayTime.PlayManager;
import PlayTime.PlayTimePlayer;
import db.PlayInfoDAO;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import metadata.Constants;
import metadata.GameRequestTable;
import utility.Log;
import utility.DataReader;

import net.Request.GameRequest;
import net.Response.GameResponse;

import model.Player;
import net.Request.RequestSDPosition;
import net.Response.ResponseSDDisconnect;
/**
 *
 * @author anu
 */
public class GameClient implements Runnable{
    
       // Client Variables
    private String session_id;
    private Socket clientSocket;
    private InputStream inputStream; // For use with incoming requests
    private OutputStream outputStream; // For use with outgoing responses
    private DataInputStream dataInputStream; // Stores incoming requests for use
    private Queue<GameResponse> updates; // Temporarily store responses for client
    private boolean isDone;
    private boolean isAlive = true;
    private Player player;
    RequestSDPosition lastPosRequest;
    //Queue<GameResponse> updates; // Temporarily store responses for client
    
    /**
     * Initialize the GameClient using the client socket and creating both input
     * and output streams.
     * 
     * @param session_id holds the unique identifier of this session
     * @param clientSocket holds reference of the socket being used
     * @throws IOException 
     */
    public GameClient(String session_id, Socket clientSocket) throws IOException {
        this.session_id = session_id;
        this.clientSocket = clientSocket;

        updates = new LinkedList<GameResponse>();

        inputStream = clientSocket.getInputStream();
        outputStream = clientSocket.getOutputStream();
        dataInputStream = new DataInputStream(inputStream);
        
        
    }
    
      public String getID() {
        return session_id;
    }
    
       public void newSession() {
        session_id = GameServer.createUniqueID();
        updates.clear();

        player = null;
    }
      /**
     * Holds the main loop that processes incoming requests by first identifying
     * its type, then interpret the following data in each determined request
     * class. Queued up responses created from each request class will be sent
     * after the request is finished processing.
     * 
     * The loop exits whenever the isPlaying flag is set to false. One of these
     * occurrences is triggered by a timeout. A timeout occurs whenever no
     * activity is picked up from the client such as being disconnected.
     */
    @Override
    public void run() {
        long lastActivity = System.currentTimeMillis();
        short requestCode = -1;

        while (!isDone) {
            try {
                // Extract the size of the package from the data stream
                short requestLength = DataReader.readShort(dataInputStream);

                if (requestLength > 0) {
                    lastActivity = System.currentTimeMillis();
                    // Separate the remaining package from the data stream
                    byte[] buffer = new byte[requestLength];
                    inputStream.read(buffer, 0, requestLength);
                    DataInputStream dataInput = new DataInputStream(new ByteArrayInputStream(buffer));
                    // Extract the request code number
                    requestCode = DataReader.readShort(dataInput);
                    // Determine the type of request
                    GameRequest request = GameRequestTable.get(requestCode);
                    // If the request exists, process like following:
                    if (request != null) {
                        request.setGameClient(this);
                        // Pass input stream to the request object
                        request.setDataInputStream(dataInput);
                        // Parse the input stream
                        request.parse();
                        // Interpret the data
                        request.doBusiness();
                        try {
                            // Retrieve any responses created by the request object
                            for (GameResponse response : request.getResponses()) {
                                // Transform the response into bytes and pass it into the output stream
                                send(response);
                            }
                        //When player disconnects from closing the client. (loses output dest.)
                        } catch (IOException ex) {
                            Log.printf_e("Client %s connection lost, attempting to reconnect.", session_id);
                            //returns -1 if the player had not opponent
                            int opp_id = PlayManager.getInstance().getPlayByPlayerID(player.getPlayer_id()).getOpponentID(player.getPlayer_id());
                            if(opp_id < 0){
                                ResponseSDDisconnect response = new ResponseSDDisconnect();
                                NetworkManager.addResponseForUser(opp_id, response);
                            }
                            
                            isDone = true;
                                                        
                        }
                    }
                //when player disconnects from a timeout.
                } else { 
                    // If there was no activity for the last moments, exit loop
                    if ((System.currentTimeMillis() - lastActivity) / 1000 >= Constants.TIMEOUT_SECONDS) {
                        isDone = true;
                    }
                }
            //incorrect request code. check Constants.
            } catch (Exception ex) {
                Log.printf_e("Request [%d] Error:", requestCode);
                Log.println_e(ex.getMessage());
                Log.println_e("---");
                ex.printStackTrace();
            }
        }

        if (player != null) {
            removePlayerData();
        }

        // Remove this GameClient from the server
        GameServer.getInstance().deletePlayerThreadOutOfActiveThreads(session_id);

        Log.printf("Client %s has ended", session_id);
    }

     public void removePlayerData() {
        GameServer.getInstance().removeActivePlayer(player.getPlayer_id());
        Log.printf("User '%s' has logged off.", player.getUsername());
    }
     
     public void send(GameResponse response) throws IOException {
        outputStream.write(response.constructResponseInBytes());
    }
   

    public void end() {
        isDone = true;
    }

    public int getUserID() {
        return player != null ? player.getPlayer_id() : -1;
    }

    public Player getPlayer() {
        return player;
    }

    public Player setPlayer(Player player) {
        return this.player = player;
    }
    
     public boolean addResponseForUpdate(GameResponse response) {
        return updates.add(response);
    }

}
