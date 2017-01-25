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
            System.out.println("Description: ");
            packet.addString(ecosystem.getDescription());
            packet.addInt32(ecosystem.getTimesteps());
            System.out.println("Config_defaut: ");
            packet.addString(ecosystem.getConfigDefault());
            System.out.println("Config_target: ");
            packet.addString(ecosystem.getConfigTarget());
            System.out.println("csv_default: ");
            packet.addString(ecosystem.getCsvDefault());
            System.out.println("csv_target: ");
            packet.addString(ecosystem.getCsvTarget());
        }

        return packet.getBytes();
    }
}
