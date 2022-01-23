package utils;
import java.security.SecureRandom;


public class StringHelper {
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    public static String getAlphanumericStringWithLength(int length)  {
        StringBuilder sb = new StringBuilder(length);
        for(int i = 0; i < length; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}
