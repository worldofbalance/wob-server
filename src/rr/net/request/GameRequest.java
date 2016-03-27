package rr.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// Other Imports
import rr.core.GameClient;
import rr.net.response.GameResponse;

/**
 * The GameRequest class is an abstract class used as a basis for storing
 * request information.
 */
public abstract class GameRequest {

    protected GameClient client;
    protected DataInputStream dataInput;
    protected List<GameResponse> responses;
    protected int request_id;

    public GameRequest() {
        responses = new ArrayList<GameResponse>();
    }

    public int getID() {
        return request_id;
    }

    public int setID(int request_id) {
        return this.request_id = request_id;
    }

    public void setDataInputStream(DataInputStream dataInput) {
        this.dataInput = dataInput;
    }

    public void setGameClient(GameClient client) {
        this.client = client;
    }

    /**
     * Parse the request from the input stream.
     * 
     * @throws IOException 
     */
    public abstract void parse() throws IOException;

    /**
     * Interpret the information from the request.
     * 
     * @throws Exception 
     */
    public abstract void doBusiness() throws Exception;

    /**
     * Get the responses generated from the request.
     * 
     * @return the responses
     */
    public List<GameResponse> getResponses() {
        return responses;
    }

    @Override
    public String toString() {
        String str = "";

        str += "-----" + "\n";
        str += getClass().getName() + "\n";
        str += "\n";

        for (Field field : getClass().getDeclaredFields()) {
            try {
                str += field.getName() + " - " + field.get(this) + "\n";
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        str += "-----";

        return str;
    }
}