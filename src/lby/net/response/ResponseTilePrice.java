
package lby.net.response;

// Other Imports
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

/**
 * @author Paul Broestl
 */
public class ResponseTilePrice extends GameResponse {
    
    public final static short SUCCESS = 0;
    public final static short FAILED = 1;
        // Variables
    private short status;
    private int zone_id;
    private int price;
    private boolean canBuy;
    
    public ResponseTilePrice(){
        response_id = NetworkCode.TILE_PRICE;
    }
    
        @Override
    public byte[] getBytes() {
        
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);
        
        packet.addInt32(zone_id);
        packet.addInt32(price);
        packet.addBoolean(canBuy);
        
        return packet.getBytes();
    }
    
    public void setStatus(short status) {
        this.status = status;
    }
    
    public void setZoneId(int id) {
        this.zone_id = id;
    }
    
    public void setPrice(int price){
        this.price = price;
    }
    
    public void setCanBuy(boolean buy){
        this.canBuy = buy;
    }
}
