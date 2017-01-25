package rr.net.request;

//package networking.request;
//
//// Java Imports
//import java.io.IOException;
//
//// Other Imports
//import networking.response.GameResponse;
//import networking.response.ResponseGameState;
//import utility.DataReader;
//import utility.Log;
//
///**
// * The RequestHeartbeat class is mainly used to release all pending responses
// * the client. Also used to keep the connection alive.
// */
//public class RequestGameState extends GameRequest {
//    
//    private ResponseGameState responseGameState;
//
//    public RequestGameState() {
//        responses.add(responseGameState = new ResponseGameState());
//    }
//
//    @Override
//    public void parse() throws IOException {
//    }
//
//    @Override
//    public void doBusiness() throws Exception {
//        responseGameState.setGameState(client.getGameState());
//        
//    }
//}