package cos.net.request;

// Java Imports

import cos.core.world.Zone;
import cos.db.EcosystemDAO;
import cos.db.PlayerDAO;
import cos.db.world.WorldZoneDAO;
import cos.model.Ecosystem;
import cos.model.Player;
import cos.net.response.ResponseEcosystem;
import cos.util.DataReader;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

// Other Imports

public class RequestEcosystem extends GameRequest {

    private int world_id;
    private int player_id;

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        world_id = DataReader.readInt(dataInput);
        player_id = DataReader.readInt(dataInput);
    }

    @Override
    public void process() throws Exception {
        ResponseEcosystem response = new ResponseEcosystem();

        Player player = PlayerDAO.getPlayer(player_id);

        if (player != null) {
            Ecosystem ecosystem = EcosystemDAO.getEcosystem(world_id, player_id);

            if (ecosystem != null) {
                List<Zone> zones = WorldZoneDAO.getZoneList(world_id, player_id);

                if (!zones.isEmpty()) {
                    response.setStatus(ResponseEcosystem.SUCCESS);
                    response.setEcosystem(ecosystem.getID(), ecosystem.getType(), ecosystem.getScore());
                    response.setPlayer(player);
                    response.setZones(zones);
                } else {
                    response.setStatus(ResponseEcosystem.FAILED);
                }
            } else {
                response.setStatus(ResponseEcosystem.FAILED);
            }
        } else {
            response.setStatus(ResponseEcosystem.INVALID_PLAYER);
        }

        client.add(response);
    }
}
