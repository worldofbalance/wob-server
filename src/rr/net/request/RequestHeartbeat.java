package rr.net.request;

// Java Imports
import java.io.IOException;

// Other Imports
import rr.net.response.GameResponse;
import shared.util.Log;
//import networking.response.ResponseHeartbeat;
//import utility.DataReader;

/**
 * The RequestHeartbeat class is mainly used to release all pending responses
 * the client. Also used to keep the connection alive.
 * Parses no data and returns an empty GameResponse packet 
 */
public class RequestHeartbeat extends GameRequest {

//    private int playerX;
//    private int playerY;
//    private int playerDistanceTraveled;
//    private short gameover;
//    private ResponseHeartbeat responseHeartbeat;

    public RequestHeartbeat() {
    }

    @Override
    public void parse() throws IOException {
    }

    @Override
    public void doBusiness() throws Exception {
        for (GameResponse response : client.getUpdates()) {
            try {
                client.send(response);
            } catch (IOException ex) {
                Log.println_e(ex.getMessage());
            }
        }

//        World world = client.getActiveObject(World.class);
//
//        if (world != null) {
//            GameEngine gameEngine = world.getGameEngine();
//            if (gameEngine != null) {
//                gameEngine.run();
//            }
//        }
    }
    
}


//    private int playerX;
//    private int playerY;
//    private int playerDistanceTraveled;
//    private short gameover;
//    private ResponseHeartbeat responseHeartbeat;
//
//    public RequestHeartbeat() {
//        responses.add(responseHeartbeat = new ResponseHeartbeat());
//    }
//
//    @Override
//    public void parse() throws IOException {
//        playerX = DataReader.readInt(dataInput);
//        playerY = DataReader.readInt(dataInput);
//        playerDistanceTraveled = DataReader.readInt(dataInput);
//        gameover =  DataReader.readShort(dataInput);
//    }
//
//    @Override
//    public void doBusiness() throws Exception {
//        // Update this client's game data.
//        client.setX(playerX);
//        client.setY(playerY);
//        client.setDistanceTraveled(playerDistanceTraveled);
//        client.setGameover(gameover);
//        // For sending this players data to the client of this player's opponent
//        responseHeartbeat.setOpponentX(client.getOpponent().getClient().getX());
//        responseHeartbeat.setOpponentY(client.getOpponent().getClient().getY());
//        responseHeartbeat.setOpponentDistanceTraveled(client.getOpponent().getClient().getDistanceTraveled());
//        responseHeartbeat.setOpponentGameover(client.getOpponent().getClient().getGameover());
//    }
//}
