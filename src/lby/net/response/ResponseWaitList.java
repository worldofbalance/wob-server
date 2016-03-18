/*
 * WaitingList protocol
 * Fetch the waiting list for a specific game
 * request parameters:
 * gameType - which game
 * response parameters:
 * series of strings (player name) and ints (player id) terminated by an empty string and id of -1
 */

package lby.net.response;

// Other Imports
import shared.metadata.NetworkCode;
import shared.util.GamePacket;
import java.util.Queue;
import java.util.LinkedList;

public class ResponseWaitList extends GameResponse {

    private Queue<String> nameQueue = new LinkedList<String>();
    private Queue<Integer> idQueue = new LinkedList<Integer>();
    
    public ResponseWaitList() {
        response_id = NetworkCode.WAITFORGAME;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        
        while (!nameQueue.isEmpty() && !idQueue.isEmpty()) {
            packet.addString(nameQueue.remove());
            packet.addInt32(idQueue.remove());
        }
        
        packet.addString("");
        packet.addInt32(-1);

        return packet.getBytes();
    }

    public void addName(String name) {
        nameQueue.add(name);
    }
    
    public void addId(int id) {
        idQueue.add(id);
    }
}
