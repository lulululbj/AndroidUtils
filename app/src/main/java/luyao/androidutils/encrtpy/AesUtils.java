package luyao.androidutils.encrtpy;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Aes加解密相关
 * Created by Lu
 * on 2017/7/1 15:40
 */

public class AesUtils {

    public static final String KEY_ALGORITHM = "AES";
    public static final String CIPHER_ALGORITHM_DEFAULT = "AES";

    /**
     * 转换秘钥
     *
     * @param key 二进制秘钥
     * @return 秘钥
     */
    private static Key toKey(byte[] key) {
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }

    /**
     * 生成秘钥
     *
     * @param size 秘钥长度 128,192 or 256
     * @return 秘钥
     * @throws NoSuchAlgorithmException
     */
    public static byte[] initKey(int size) throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        kg.init(size);
        SecretKey secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }

    public static byte[] encrypt(byte[] data, byte[] key) {
        return encrypt(data, key, CIPHER_ALGORITHM_DEFAULT);
    }

    public static byte[] encrypt(byte[] data, byte[] key, String cipherAlgotirhm) {
        return encrypt(data, key, null, cipherAlgotirhm);
    }

    /**
     * @param data            待加密数据
     * @param key             加密秘钥
     * @param iv              初始化向量 CFB,CBC模式下需要
     * @param cipherAlgotirhm 算法/工作模式/填充模式  例如：AES/CFB/NOPADDING
     * @return 加密数据
     */
    public static byte[] encrypt(byte[] data, byte[] key, byte[] iv, String cipherAlgotirhm) {
        try {
            Key k = toKey(key);
            Cipher cipher = Cipher.getInstance(cipherAlgotirhm);
            String CipherAlgotirhm = cipherAlgotirhm.toUpperCase();
            if (CipherAlgotirhm.contains("CFB") ||
                    CipherAlgotirhm.contains("CBC"))
                cipher.init(Cipher.ENCRYPT_MODE, k, new IvParameterSpec(iv));
            else
                cipher.init(Cipher.ENCRYPT_MODE, k);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] decrypt(byte[] data, byte[] key) {
        return decrypt(data, key, CIPHER_ALGORITHM_DEFAULT);
    }

    public static byte[] decrypt(byte[] data, byte[] key, String cipherAlgotirhm) {
        return decrypt(data, key, null, cipherAlgotirhm);
    }

    /**
     * @param data            加密数据
     * @param key             解密秘钥
     * @param iv              初始化向量 CFB,CBC模式下需要
     * @param cipherAlgotirhm 算法/工作模式/填充模式  例如：AES/CFB/NOPADDING
     * @return 原始数据
     */
    public static byte[] decrypt(byte[] data, byte[] key, byte[] iv, String cipherAlgotirhm) {
        try {
            Key k = toKey(key);
            Cipher cipher = Cipher.getInstance(cipherAlgotirhm);
            String CipherAlgotirhm = cipherAlgotirhm.toUpperCase();
            if (CipherAlgotirhm.contains("CFB") ||
                    CipherAlgotirhm.contains("CBC"))
                cipher.init(Cipher.DECRYPT_MODE, k, new IvParameterSpec(iv));
            else
                cipher.init(Cipher.DECRYPT_MODE, k);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
