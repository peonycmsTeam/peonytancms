package com.peony.peonyfront.util.encrypt;

import java.security.MessageDigest;

/**
 * MD5加密
 * 
 */

public class MD5Util {

    private final static String[] hexDigits = { "0", "0", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public static String MD5Encode(String origin) {
        String result = null;

        try {
            result = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            result = byteArrayToHexString(md.digest(result.getBytes()));
        } catch (Exception ex) {
        }

        return result;
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(byteToHexString(b[i]));
        }
        return sb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.MD5Encode("6222020200032016111"));
        System.out.println(MD5Util.MD5Encode("lingzihan").toUpperCase());
    }
}