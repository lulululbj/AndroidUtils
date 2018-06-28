package luyao.lib.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
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

    private static final String KEY_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM_DEFAULT = "AES";
    private static final String AES_CFB_NOPADDING = "AES/CFB/NOPADDING";

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

    /**
     * 初始化Cipher
     *
     * @param mode            Cipher.ENCRYPT_MODE or Cipher.DECRYPT_MODE
     * @param key             加密秘钥
     * @param iv              初始化向量
     * @param cipherAlgotirhm 算法/工作模式/填充模式  例如：AES/CFB/NOPADDING
     * @return Cipher
     */
    private static Cipher initCipher(int mode, byte[] key, byte[] iv, String cipherAlgotirhm) {
        try {
            Key k = toKey(key);
            Cipher cipher = Cipher.getInstance(cipherAlgotirhm);
            String CipherAlgotirhm = cipherAlgotirhm.toUpperCase();
            if (CipherAlgotirhm.contains("CFB") ||
                    CipherAlgotirhm.contains("CBC"))
                cipher.init(mode, k, new IvParameterSpec(iv));
            else
                cipher.init(mode, k);
            return cipher;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

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
            Cipher cipher = initCipher(Cipher.ENCRYPT_MODE, key, iv, cipherAlgotirhm);
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
     * @param cipherAlgorithm 算法/工作模式/填充模式  例如：AES/CFB/NOPADDING
     * @return 原始数据
     */
    public static byte[] decrypt(byte[] data, byte[] key, byte[] iv, String cipherAlgorithm) {
        try {
            Cipher cipher = initCipher(Cipher.DECRYPT_MODE, key, iv, cipherAlgorithm);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件加解密
     *
     * @param mode           Cipher.ENCRYPT_MODE or Cipher.DECRYPT_MODE 加密/解密
     * @param key            加解密秘钥
     * @param iv             初始化向量
     * @param sourceFilePath 源文件路径
     * @param destFilePath   目标文件路径
     * @return boolean
     */
    private static boolean handleFile(int mode, byte[] key, byte[] iv, String sourceFilePath, String destFilePath) {

        File sourceFile = new File(sourceFilePath);
        File destFile = new File(destFilePath);

        try {
            if (sourceFile.exists() && sourceFile.isFile()) {
                if (!destFile.getParentFile().exists())
                    destFile.getParentFile().mkdirs();
                destFile.createNewFile();

                InputStream in = new FileInputStream(sourceFile);
                OutputStream out = new FileOutputStream(destFile);
                Cipher cipher = initCipher(mode, key, iv, AES_CFB_NOPADDING);
                CipherInputStream cin = new CipherInputStream(in, cipher);

                byte[] b = new byte[1024];
                int read = 0;
                while ((read = cin.read(b)) != -1) {
                    out.write(b, 0, read);
                    out.flush();
                }

                cin.close();
                in.close();
                out.close();

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 文件加密
     */
    public static boolean encryptFile(byte[] key, byte[] iv, String sourceFilePath, String destFilePath) {
        return handleFile(Cipher.ENCRYPT_MODE, key, iv, sourceFilePath, destFilePath);
    }

    /**
     * 文件解密
     */
    public static boolean decryptFile(byte[] key, byte[] iv, String sourceFilePath, String destFilePath) {
        return handleFile(Cipher.DECRYPT_MODE, key, iv, sourceFilePath, destFilePath);
    }
}
