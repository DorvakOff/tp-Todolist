package fr.iutblagnac.devmobtp2p2.utils;

public class StringUtils {

    public static String truncate(String s, int maxLength) {
        if (s == null) {
            return "";
        }
        if (s.length() <= maxLength) {
            return s;
        }
        return s.substring(0, maxLength - 3) + "...";
    }
}
