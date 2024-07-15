package com.rems.boot.utils;

import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Author qinj
 * @Date 2024/7/15
 * @Description MD5加密 工具类
 * @Version 1.0
 */
public class MD5Util {

    public static String md5(String str){
        return DigestUtils.md5Hex(str);
    }

    /**
     * 加密
     * 生成盐和加盐后的MD5码，并将盐混入到MD5码中,对MD5密码进行加强
     **/
    public static String generateSaltPassword(String password) {
        Random random = new Random();

        // 生成一个16位的随机数，也就是盐
        StringBuilder stringBuilder = new StringBuilder(16);
        stringBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        int len = stringBuilder.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                stringBuilder.append("0");
            }
        }
        // 生成盐
        String salt = stringBuilder.toString();
        //将盐加到明文中，并生成新的MD5码
        password = md5(password + salt);
        //将盐混到新生成的MD5码中，之所以这样做是为了后期解密，校验密码
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }

    /**
     * 解密
     * 将混入MD5加密的密文中的盐取出来，然后将传来的密码按照此盐进行MD5加密，再比较
     **/
    public static boolean verifySaltPassword(String password, String md) {
        //先从MD5码中取出之前加的盐和加盐后生成的MD5码
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md.charAt(i);
            cs1[i / 3 * 2 + 1] = md.charAt(i + 2);
            cs2[i / 3] = md.charAt(i + 1);
        }
        String salt = new String(cs2);
        //比较二者是否相同
        return md5(password + salt).equals(new String(cs1));
    }

}

