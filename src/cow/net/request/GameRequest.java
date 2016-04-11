package cow.net.request;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.Field;

// Other Imports
import cow.core.GameClient;
import shared.util.Log;

/**
 * The GameRequest class is an abstract class used as a basis for storing
 * request information.
 */
public abstract class GameRequest extends lby.net.request.GameRequest{

    protected int request_id;
    protected GameClient client;

    public int getID() {
        return request_id;
    }

    public int setID(int request_id) {
        return this.request_id = request_id;
    }

    
    public GameClient setGameClient(GameClient client) {
        return this.client = client;
    }

    /**
     * Parse the request from the input stream.
     *
     * @param dataInput
     * @throws IOException
     */
    public abstract void parse(DataInputStream dataInput) throws IOException;

    /**
     * Interpret the information from the request.
     *
     * @throws Exception
     */
    public abstract void process() throws Exception;

    @Override
    public String toString() {
        String str = "";

        str += "-----" + "\n";
        str += getClass().getName() + "\n";
        str += "\n";

        for (Field field : getClass().getDeclaredFields()) {
            try {
                str += field.getName() + " - " + field.get(this) + "\n";
            } catch (IllegalArgumentException ex) {
                Log.println_e(ex.getMessage());
            } catch (IllegalAccessException ex) {
                Log.println_e(ex.getMessage());
            }
        }

        str += "-----";

        return str;
    }
}
