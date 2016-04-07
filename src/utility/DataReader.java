/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author anu
 *  The DataReader class facilitates reading data from data streams. Incoming
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
        if (in.available() > 0) {
            return Short.reverseBytes(in.readShort());
        }
        return -1;
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
        if (in.available() > 0) {
            return Integer.reverseBytes(in.readInt());
        }
        return -1;
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
        if (in.available() > 0) {
            return in.readBoolean();
        }
        return false;
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
        return (new String(aString));
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
        if (in.available() > 0) {
            return Float.intBitsToFloat(Integer.reverseBytes(in.readInt()));
        }

        return -1;
    }

    
}
