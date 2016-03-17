package lby.net.response.shop;

// Java Imports
import java.util.List;

// Other Imports

import shared.metadata.NetworkCode;
import shared.model.ShopItem;
import lby.net.response.GameResponse;
import shared.util.GamePacket;

public class ResponseShop extends GameResponse {

    private List<ShopItem> shopList;

    public ResponseShop() {
        response_id = NetworkCode.SHOP;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16((short) shopList.size());

        for (ShopItem item : shopList) {
            packet.addInt32(item.getID());
            packet.addShort16((short) item.getLevel());
            packet.addString(item.getName());
            packet.addShort16((short) item.getPrice());
            packet.addString(item.getDescription());

            packet.addShort16((short) item.getExtraArgs().size());
            for (String s : item.getExtraArgs()) {
                packet.addString(s);
            }

            packet.addString(item.getCategoryListAsString());
            packet.addString(item.getTagListAsString());
        }

        return packet.getBytes();
    }

    public void setShopList(List<ShopItem> shopList) {
        this.shopList = shopList;
    }
}
