package lby.net.request.shop;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

// Other Imports

import shared.db.ShopDAO;
import lby.net.request.GameRequest;
import lby.net.response.shop.ResponseShop;

public class RequestShop extends GameRequest {

    @Override
    public void parse(DataInputStream dataInput) throws IOException {
    }

    @Override
    public void process() throws Exception {
        ResponseShop response = new ResponseShop();
        response.setShopList(ShopDAO.getItems("level:0,99"));
        client.add(response);
    }
}
