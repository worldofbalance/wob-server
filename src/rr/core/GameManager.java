///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package core;
////import model.Game;
//import java.util.HashMap;
//import java.util.ArrayList;
//import java.util.List;
//import utility.Log;
//
///**
// *
// * @author markfavis
// */
//public class GameManager {
//    
//    protected List<Game> games;
////    protected HashMap<Games> games  // this is the preferred collection of games
//    protected short numberOfGamesBeingPlayed;
//    
//    private final short MAX_NUMBER_OF_PLAYERS = 2;
//    
//    GameManager(){
//        games = new ArrayList<Game>();
//        numberOfGamesBeingPlayed = 0;
//        
//    }
//    
//    //Adds a client to the correct game out of a list of games and makes sure there is a game for the client to be added to.
//    public void addClientToGame(GameClient client) {
//        noCurrentGames();
//        PlaceClient(client);
//        createNewGame();
//    }
//    
//    //checks to see if any games exisit and if not creates one;
//    void noCurrentGames() {
//        if (games.isEmpty()){
//            games.add(new Game(null, null));
//        
//            //debugging
//            Log.printf("No games set up, creating a new game.");
//        }
//    }
//    
//    //Places the client into the open game with the correct player position and sets the opponent of each client as the other client when there is enough clients in game.
//    void PlaceClient(GameClient client) {     
//        Game game = games.get(games.size()-1);
//        switch (game.getNumberOfPlayersInGame()){
//            case 0: game.setClient1(client);
//                    //debugging
//                    Log.printf("Setting first player in a Game.");
//                    break;
//            case 1: game.setClient2(client);
//                    game.setOpponentsOfGame();
//                    game.setStateOn();
//                    //debugging
//                    Log.printf("Setting second player in a Game and setting opponents.");
//                    break;
//        }
//    }    
//    
//    // Checks to see if the last game in the list is full and if so creates a new one.
//    void createNewGame() {
//        if (games.get(games.size()-1).getNumberOfPlayersInGame() == MAX_NUMBER_OF_PLAYERS){
//            games.add(new Game(null, null));
//        }
//    }
//}
//
