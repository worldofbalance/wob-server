package cos.util;

// Java Imports
import java.io.DataInputStream;
import java.io.IOException;

/**
 * The DataReader class facilitates reading data from data streams. Incoming
 * data must have its bits shifted and reversed before the server can properly
 * read it.
 */
public class DataReader {

    /**
     * Read a single short from the input stream. Used for Python client
     * PyDatagram decoding.
     *
     * @param in references the input stream
     * @return the value read from the input stream
     * @throws IOException
     */
    public static short readShort(DataInputStream in) throws IOException {
        return in.available() > 0 ? Short.reverseBytes(in.readShort()) : -1;
    }

    /**
     * Read a single int from the input stream. Used for Python client
     * PyDatagram decoding.
     *
     * @param in references the input stream
     * @return the value read from the input stream
     * @throws IOException
     */
    public static int readInt(DataInputStream in) throws IOException {
        return in.available() > 0 ? Integer.reverseBytes(in.readInt()) : -1;
    }

    /**
     * Read a single boolean from the input stream. Used for Python client
     * PyDatagram decoding.
     *
     * @param in references the input stream
     * @return the value read from the input stream
     * @throws IOException
     */
    public static boolean readBoolean(DataInputStream in) throws IOException {
        return in.available() > 0 ? in.readBoolean() : false;
    }

    /**
     * Read a single string from the input stream. Used for Python client
     * PyDatagram decoding.
     *
     * @param in references the input stream
     * @return the value read from the input stream
     * @throws IOException
     */
    public static String readString(DataInputStream in) throws IOException {
        short length = readShort(in);
        byte[] aString = new byte[length];
        in.read(aString, 0, length);
        return new String(aString);
    }

    /**
     * Read a single float from the input stream. Used for Python client
     * PyDatagram decoding.
     *
     * @param in references the input stream
     * @return the value read from the input stream
     * @throws IOException
     */
    public static float readFloat(DataInputStream in) throws IOException {
        return in.available() > 0 ? Float.intBitsToFloat(Integer.reverseBytes(in.readInt())) : -1;
    }
}
