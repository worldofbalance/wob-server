package lby.net.request.shop;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// Other Imports
import lby.core.world.World;
import lby.net.request.GameRequest;
import lby.net.response.shop.ResponseShopAction;
import shared.util.DataReader;

public class RequestShopAction extends GameRequest {

    private short action;
    private Map<Integer, Integer> itemList = new HashMap<Integer, Integer>();

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
        action = DataReader.readShort(dataInput);

        int size = DataReader.readShort(dataInput);

        for (int i = 0; i < size; i++) {
            int item_id = DataReader.readInt(dataInput);
            int amount = DataReader.readInt(dataInput);

            itemList.put(item_id, amount);
        }
    }

    @Override
    public void process() throws Exception {
        World world = client.getPlayer().getWorld();

        if (world != null) {
            ResponseShopAction response = new ResponseShopAction();
            int totalSpent = world.createShopOrder(itemList, client.getPlayer());

            if (totalSpent > -1) {
                response.setStatus(0);
                response.setTotalSpent(totalSpent);
            } else {
                response.setStatus(1);
            }

            client.add(response);
        }
    }
}
