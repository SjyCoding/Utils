package com.sforits.Utils.String;

import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author：sunforits
 * @E-mail：sforits@gmail.com
 * @Date：2020/5/30-20:38
 */
public class MD5 {
    /**
     * MD5盐值加密
     *
     * @param str
     * @return
     */
    public static String code(String str) {
        int i;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] byteDigest = md.digest();
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Test
    public void m(){
        System.out.println(MD5.code("1234"));
    }

}
