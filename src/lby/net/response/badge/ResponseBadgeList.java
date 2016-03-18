package lby.net.response.badge;

// Java Imports
import java.util.List;

// Other Imports

import lby.core.badge.Badge;
import shared.core.Objective;
import shared.metadata.NetworkCode;
import lby.net.response.GameResponse;
import shared.util.GamePacket;

public class ResponseBadgeList extends GameResponse {

    private short status;
    private List<Badge> badgeList;

    public ResponseBadgeList() {
        response_id = NetworkCode.BADGE_LIST;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);

        if (status == 0) {
            packet.addShort16((short) badgeList.size());

            for (Badge badge : badgeList) {
                packet.addInt32(badge.getID());
                packet.addString(badge.getName());
                packet.addShort16((short) badge.getAmount());

                Objective objective = badge.getObjective();
                packet.addInt32(objective.getID());
                packet.addString(objective.getName());
                packet.addShort16((short) objective.getAmount());
                packet.addShort16((short) objective.getTarget());
            }
        }

        return packet.getBytes();
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public void setBadgeList(List<Badge> badgeList) {
        this.badgeList = badgeList;
    }
}
