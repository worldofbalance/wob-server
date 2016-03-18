package shared.util;

// Java Imports
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Functions {

    private Functions() {
    }

    public static int clamp(int value, int min, int max) {
        if (value < min) {
            value = min;
        } else if (value > max) {
            value = max;
        }

        return value;
    }

    public static float clamp(float value, float min, float max) {
        if (value < min) {
            value = min;
        } else if (value > max) {
            value = max;
        }

        return value;
    }

    public static int clamp01(int value) {
        if (value < 0) {
            value = 0;
        } else if (value > 1) {
            value = 1;
        }

        return value;
    }

    public static float clamp01(float value) {
        if (value < 0) {
            value = 0;
        } else if (value > 1) {
            value = 1;
        }

        return value;
    }

    public static String getMD5(byte[] input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input);

            byte[] mdbytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Log.println_e(ex.getMessage());
        }

        return null;
    }
}
