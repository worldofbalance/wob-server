/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lby;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.util.ConfFileParser;
import shared.util.Log;

/**
 *
 * @author yanxingwang
 */
public class MiniGameServers {
    
    private static MiniGameServers sServers;
    private final Map<String, MiniGame> miniGames = new HashMap<>();
    private int minigamesPort;
    
    public static MiniGameServers getInstance() {
        if (sServers == null) {
            sServers = new MiniGameServers();
        }
        return sServers;
    }
    
    public MiniGameServers() {
        minigamesPort = 20038;
        initMiniGames();
        
        int totalAvailables = 0;
        for (MiniGame mg : miniGames.values()) {
            if ( mg.isAvailable() ) {
                ++totalAvailables;
            }
        }
        Log.printf("All mini games initialized: %d/%d avaiable", totalAvailables, miniGames.size());
    }
    
    public void runServers() {
        try {
            for (MiniGame g : this.miniGames.values()) {
                g.run();
            }
        } catch (IOException ex) {
            Logger.getLogger(MiniGameServers.class.getName()).log(Level.SEVERE, null, ex);
        }            
    }
    
    private void initMiniGames() {
        ConfFileParser confFileParser = new ConfFileParser("conf/minigames.conf");
        Map<String, String> minigamesConfig = confFileParser.parse();
        
        minigamesConfig.entrySet().stream().map(
            (entry) -> { return entry; }
        ).forEach((entry) -> {
            initMiniGame(entry.getKey(), entry.getValue());
        });
    }
    
    private void initMiniGame(String name, String location) {
        MiniGame game = new MiniGame(name);
        game.setAsMultiPlayerGame(location, minigamesPort++);
        miniGames.put(game.getName(), game);
    }
}
