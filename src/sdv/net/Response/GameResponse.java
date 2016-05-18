/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdv.net.Response;

/**
 *
 * @author anu
 *  The GameResponse class is an abstract class used as a basis for storing
 * response information.
 */

// Java Imports
import java.lang.reflect.Field;

public abstract class GameResponse {

    protected byte[] responseInBytes; // Response information stored as bytes
    protected short responseCode;

    /**
     * Convert the response into bytes format.
     * 
     * @return the response as bytes
     */
    public abstract byte[] constructResponseInBytes();

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
