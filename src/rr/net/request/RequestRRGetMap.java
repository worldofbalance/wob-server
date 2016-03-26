/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rr.net.request;

import java.io.IOException;
import rr.net.response.ResponseRRGetMap;
import rr.race.RaceManager;
import shared.util.DataReader;

/**
 *
 * @author markfavis
 */
public class RequestRRGetMap extends GameRequest {

    private ResponseRRGetMap getMap;
    
    public RequestRRGetMap(){
        responses.add(getMap = new ResponseRRGetMap());
    }

    @Override
    public void parse() throws IOException {
        // Nothing to parse;
    }

    @Override
    public void doBusiness() throws Exception {
        int thisPlayerID = this.client.getPlayer().getID();
        int mapID = RaceManager.manager.getRaceByPlayerID(thisPlayerID).getMapID();
        
        getMap.setRandomNumber(mapID);
    }
}
