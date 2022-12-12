package com.example.authorization.util;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/*────────────────────────────────────────────────────────────────────
    작성자 : 고의동
    작성일 : 2022-06-22
    기능   : AES256암호화를 위한 Encryption 및 Decryption 처리를 진행
 ────────────────────────────────────────────────────────────────────*/
public class AES256 {
    public static String alg = "AES/CBC/PKCS5Padding";
    private final String key = "U!8cQv+63Q*Yry$6F#%2+!1)!E37In%^";
    private final String iv  = "abcdef1234567890"; // 16byte

    private final Logger log = (Logger) LoggerFactory.getLogger(AES256.class);

    /*────────────────────────────────────────────────────────────────────
        암호화 모듈
     ────────────────────────────────────────────────────────────────────*/
    public String encrypt(String text) throws Exception {
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);
        byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /*────────────────────────────────────────────────────────────────────
        복호화 모듈
     ────────────────────────────────────────────────────────────────────*/
    public String decrypt(String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);
        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, "UTF-8");
    }
}