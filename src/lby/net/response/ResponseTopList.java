package lby.net.response;

// Other Imports
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

public class ResponseTopList extends GameResponse {

    private String p1, p2, p3;
    private int s1, s2, s3;

    public ResponseTopList() {
        response_id = NetworkCode.TOPLIST;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addString(p1);
        packet.addInt32(s1);
        packet.addString(p2);
        packet.addInt32(s2);
        packet.addString(p3);
        packet.addInt32(s3);

        return packet.getBytes();
    }
    
    public void setData(String p1, int s1, String p2, int s2, String p3, int s3){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }
}