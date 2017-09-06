package com.myloe.commons.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    /**
     * 简单的通过java的md5进行加密
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */

    public static String encryptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        //创建java中自带的MD5对象
        MessageDigest md5=MessageDigest.getInstance("MD5");

        //解决加密后的乱码，创建BASE64Encoder对象
        BASE64Encoder base64Encoder=new BASE64Encoder();
        //加密
        String resultPassword=base64Encoder.encode(md5.digest(password.getBytes("utf-8")));

        return resultPassword;
    }


    /**
     *  加密用户输入的密码后，进行密码比对
     * @param inputPassword 用户输入的密码
     * @param dbPassword    数据库中存储的密码
     * @return
     */
    public static boolean checkPassword(String inputPassword,String dbPassword) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //先用户输入的密码进行比对
        String md5InputPssword=encryptPassword(inputPassword);

        if(md5InputPssword.equals(dbPassword)){
            return true;
        }else {
            return false;
        }
    }
}
