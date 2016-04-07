/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author anu
 */
public class Log {
  

    // Time-Related Variables
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static boolean displayTime = false;

    /**
     * Appends a timestamp at the beginning of the string.
     * 
     * For Example:
     * [2012-12-21 00:00:00]
     *   Insert String Here
     * 
     * @param string contains the string to be modified
     * @return the same string, but with a leading timestamp
     */
    private static String format(String string) {
        if (displayTime) {
            string = String.format("[%s]\n  %s", sdf.format(new Date()), string.replaceAll("\n", "\n  "));
        }

        return string;
    }

    public static void printf(String string, Object... os) {
        System.out.printf(format(string) + "\n", os);
    }

    public static void println(String string) {
        System.out.println(format(string));
    }

    public static void printf_e(String string, Object... os) {
        System.err.printf(format(string) + "\n", os);
    }

    public static void println_e(String string) {
        System.err.println(format(string));
    }

 
}
