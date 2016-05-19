package cos.net.response.shop;

// Other Imports
import cos.metadata.NetworkCode;
import cos.net.response.GameResponse;
import cos.util.GamePacket;

public class ResponseShopAction extends GameResponse {

    private short action;
    private short status;
    private int amount;
    private String itemList;

    public ResponseShopAction() {
        response_id = NetworkCode.SHOP_ACTION;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(action);
        packet.addShort16(status);

        if (status == 0) {
            packet.addInt32(amount);
        } else if (status == 2) {
            packet.addString(itemList);
        }

        return packet.getBytes();
    }

    public void setAction(short action) {
        this.action = action;
    }

    public void setStatus(int status) {
        this.status = (short) status;
    }

    public void setTotalSpent(int amount) {
        this.amount = amount;
    }

    public void setItems(String itemList) {
        this.itemList = itemList;
    }
}
