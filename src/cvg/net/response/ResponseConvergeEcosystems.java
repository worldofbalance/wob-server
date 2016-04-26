/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg.net.response;

import java.util.List;
import shared.metadata.NetworkCode;
import cvg.ConvergeEcosystem;
import lby.net.response.GameResponse;
import shared.util.GamePacket;

/**
 *
 * @author justinacotter
 */
public class ResponseConvergeEcosystems extends GameResponse {

    private List<ConvergeEcosystem> ecosystemList;

    public ResponseConvergeEcosystems() {
        response_id = NetworkCode.CONVERGE_ECOSYSTEMS;
    }

    public void setConvergeEcosystems(List<ConvergeEcosystem> ecosystemList) {
        this.ecosystemList = ecosystemList;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16((short) ecosystemList.size());

        for (ConvergeEcosystem ecosystem : ecosystemList) {
            packet.addInt32(ecosystem.getEcosystemId());
            packet.addString(ecosystem.getDescription());
            packet.addInt32(ecosystem.getTimesteps());
            packet.addString(ecosystem.getConfigDefault());
            packet.addString(ecosystem.getConfigTarget());
            packet.addString32(ecosystem.getCsvDefault());
            packet.addString32(ecosystem.getCsvTarget());
        }

        return packet.getBytes();
    }
}
