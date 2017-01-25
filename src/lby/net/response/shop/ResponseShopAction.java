package lby.net.response.shop;

// Other Imports
import shared.metadata.NetworkCode;
import lby.net.response.GameResponse;
import shared.util.GamePacket;
import shared.util.Log;

public class ResponseShopAction extends GameResponse {

    private short action;
    private short status;
    private int newCredits = -1;
    private int totalSpent;
    private String itemList;

    public ResponseShopAction() {
        response_id = NetworkCode.SHOP_ACTION;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(action);
        packet.addShort16(status);
        packet.addInt32(newCredits);
        Log.println("ResponseShopAction.getBytes: a/c/newCredits: " + action + " " + status + " " + newCredits);
        
//        if (status == 0) {
//            packet.addInt32(newCredits);
//        } else if (status == 2) {
//           packet.addString(itemList);
//        }

        return packet.getBytes();
    }

    public void setAction(short action) {
        this.action = action;
    }

    public void setStatus(int status) {
        this.status = (short) status;
    }

    public void setNewCredits(int newCredits) {
        this.newCredits = newCredits;
    }
    
    public void setTotalSpent(int totalSpent) {
        this.totalSpent = totalSpent;
    }

    public void setItems(String itemList) {
        this.itemList = itemList;
    }
}
