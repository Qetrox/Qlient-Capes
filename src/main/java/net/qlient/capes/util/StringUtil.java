package net.qlient.capes.util;

public class StringUtil {

    public static String UUIDWithoutDashes(String UUID) {
        return UUID.replace("-", "");
    }


}