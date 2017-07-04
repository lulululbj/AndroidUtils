package luyao.androidutils;

import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import luyao.androidutils.encrtpy.AesUtils;

import static org.junit.Assert.assertEquals;

/**
 * Created by Lu
 * on 2017/7/1 17:21
 */

public class AesTest {

    private String plainText = "luyao";
    byte[] key = null;
    byte[] iv = null;

    @Before
    public void setUp() {
        try {
            key = AesUtils.initKey(128);
            iv = key;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void aesDefault() {
        try {
            byte[] encrypt = AesUtils.encrypt(plainText.getBytes(), key);
            byte[] decrypt = AesUtils.decrypt(encrypt, key);
            assertEquals(plainText, new String(decrypt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void aesCFB() {
        try {
            byte[] encrypt = AesUtils.encrypt(plainText.getBytes(), key, iv, "AES/CFB/NOPADDING");
            byte[] decrypt = AesUtils.decrypt(encrypt, key, iv, "AES/CFB/NOPADDING");
            assertEquals(plainText, new String(decrypt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void aesCBC(){
        try {
            byte[] encrypt = AesUtils.encrypt(plainText.getBytes(), key, iv, "AES/CBC/PKCS5Padding");
            byte[] decrypt = AesUtils.decrypt(encrypt, key, iv, "AES/CBC/PKCS5Padding");
            assertEquals(plainText, new String(decrypt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void aesECB(){
        try {
            byte[] encrypt = AesUtils.encrypt(plainText.getBytes(), key, iv, "AES/ECB/PKCS5Padding");
            byte[] decrypt = AesUtils.decrypt(encrypt, key, iv, "AES/ECB/PKCS5Padding");
            assertEquals(plainText, new String(decrypt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void aesFile(){
        String sourceFIle="G:/test.txt";
        String encryptFile="G:/encrypt.txt";
        String decryptFile="G:/decrypt.txt";
        try {
            byte[] key=AesUtils.initKey(128);
            AesUtils.encryptFile(key,key,sourceFIle,encryptFile);
            AesUtils.decryptFile(key,key,encryptFile,decryptFile);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
