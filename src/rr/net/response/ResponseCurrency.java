package rr.net.response;

import rr.metadata.Constants;
import rr.util.GamePacket;


/**
 * Responds with a packet containing packet ID and a int32 containing an int
 * representing Client's currency.
 * This is not yet implemented in Client.
 * @author Joshua Rubin
 */
public class ResponseCurrency extends GameResponse{
    
    private int currency;
    
    void ResponseCurrency(){
        responseCode = Constants.SMSG_RRGETCURRENCY;
    }
    
    @Override
    public byte[] constructResponseInBytes() {
         GamePacket packet = new GamePacket(responseCode);
         packet.addInt32(currency);
         return packet.getBytes();
       
    }
    
    public int getCurrency(){
        return this.currency;
    }
    public void setCurrency(int currency){
        this.currency = currency;
    }
    
}
