package luyao.lib.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import luyao.lib.TransformUtils;

/**
 * Created by luyao
 * on 2018/3/28 10:00
 */
public class HashUtils {

    public enum Hash {

        MD5("MD5"),

        SHA1("SHA-1"),

        SHA256("SHA-256");

        private String algorithm;

        Hash(String algorithm) {
            this.algorithm = algorithm;
        }

        public String getAlgorithm() {
            return algorithm;
        }
    }

    public static byte[] hash(byte[] data, Hash algorithm) {
        byte[] result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm.getAlgorithm());
            result = digest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String hash(String data, Hash algorithm) {
        byte[] result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm.getAlgorithm());
            result = digest.digest(TransformUtils.hexStr2Bytes(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TransformUtils.byte2HexStr(result);
    }


}
