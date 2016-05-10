package cos.net.response;

/**
 * The GameResponse class is an abstract class used as a basis for storing
 * response information.
 */
public abstract class GameResponse {

    protected short response_id;
    protected byte[] bytes; // Response information stored as bytes

    public short getID() {
        return response_id;
    }
    
    /**
     * Convert the response into bytes format.
     * 
     * @return the response as bytes
     */
    public abstract byte[] getBytes();
}