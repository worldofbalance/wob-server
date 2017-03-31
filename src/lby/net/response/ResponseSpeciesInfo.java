package lby.net.response;

// Other Imports
import cos.util.Log;
import java.util.ArrayList;
import shared.metadata.NetworkCode;
import shared.util.GamePacket;
import sun.security.ssl.Debug;

public class ResponseSpeciesInfo extends GameResponse {
    
    private int zoneX, zoneY;
    private ArrayList<Integer> speciesList;    

    public ResponseSpeciesInfo(int zoneX, int zoneY, ArrayList<Integer> speciesList) {
        response_id = NetworkCode.SPECIES_INFO;
        
        this.zoneX = zoneX;
        this.zoneY = zoneY;
        this.speciesList = speciesList;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addInt32(zoneX);
        packet.addInt32(zoneY); 
        packet.addInt32(speciesList.size());
        
        for (int idx = 0; idx < speciesList.size(); idx++) {
            packet.addInt32(speciesList.get(idx));
        }
        // Log.println("ResponseSpeciesInfo, zoneX, zoneY, count = " + zoneX + " " + zoneY + " " + speciesList.size());

        return packet.getBytes();
    }
}
