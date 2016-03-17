package lby.net.response;

// Other Imports
import shared.metadata.NetworkCode;
import shared.util.GamePacket;

/**
 * The ResponseLogin class contains information about the authentication
 * process.
 */
public class ResponseLogin extends GameResponse {

    // Status Codes
    public final static short SUCCESS = 0;
    public final static short FAILED = 1;
    public final static short IN_USE = 2;
    public final static short LOGGED_IN = 4;
    // Variables
    private short status;
    private int account_id;
    private String username;
    private String last_logout;

    public ResponseLogin() {
        response_id = NetworkCode.LOGIN;
    }

    @Override
    public byte[] getBytes() {
        GamePacket packet = new GamePacket(response_id);
        packet.addShort16(status);

        if (status == SUCCESS) {
            packet.addInt32(account_id);
            packet.addString(username);
            packet.addString(last_logout);
        }

        return packet.getBytes();
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public void setAccount(int account_id, String username, String last_logout) {
        this.account_id = account_id;
        this.username = username;
        this.last_logout = last_logout == null ? "Never" : last_logout;
    }
}
