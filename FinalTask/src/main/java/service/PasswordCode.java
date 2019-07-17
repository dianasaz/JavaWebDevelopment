package service;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordCode {
    public static String CodeMD5(String st) {
        String md5Hex = DigestUtils.md5Hex(st);

        return md5Hex;
    }
}
