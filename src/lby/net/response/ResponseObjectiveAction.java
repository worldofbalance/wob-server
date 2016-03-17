package lby.net.response;

// Other Imports
import shared.core.Objective;
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

public class ResponseObjectiveAction extends GameResponse {

    private short action;
    private Objective objective;

    public ResponseObjectiveAction() {
        response_id = NetworkCode.OBJECTIVE_ACTION;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(action);
        packet.addInt32(objective.getID());

        if (action == 0) { // Create
            packet.addString(objective.getName());
            packet.addInt32((Integer) objective.getTarget());
            packet.addString(objective.getUnit());
        } else if (action == 1) { // Update
            packet.addInt32((Integer) objective.getAmount());
            packet.addBoolean(objective.isDone());
        }

        return packet.getBytes();
    }

    public void setAction(int action) {
        this.action = (short) action;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }
}
