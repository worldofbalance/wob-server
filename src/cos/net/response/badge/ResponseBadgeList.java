package cos.net.response.badge;

// Java Imports

import cos.core.Objective;
import cos.core.badge.Badge;
import cos.metadata.NetworkCode;
import cos.net.response.GameResponse;
import cos.util.GamePacket;

import java.util.List;

// Other Imports

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
