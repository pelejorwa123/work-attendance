package com.pele.common.utils;


import org.springframework.util.DigestUtils;

public class SecurityUtils {
    public static String encryptPassword(String password){
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
