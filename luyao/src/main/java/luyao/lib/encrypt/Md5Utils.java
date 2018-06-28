package luyao.lib.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import luyao.lib.TransformUtils;


/**
 * Created by luyao
 * on 2017/11/13 14:14
 */

public class Md5Utils {

    private static final String ALGORITHM = "MD5";

    /**
     * md5 加密
     *
     * @param data 原数据
     * @return 原数据 md5 值
     */
    public static byte[] md5Byte(byte[] data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            return messageDigest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * md5 加密
     *
     * @param data 原数据
     * @return 原数据 md5 值字符串
     */
    public static String md5(byte[] data) {
        byte[] result = md5Byte(data);
        return result == null ? "" : TransformUtils.byte2HexStr(result);
    }

    /**
     * md5 加密
     *
     * @param data 原数据
     * @return 原数据 md5 值字符串
     */
    public static String md5(String data) {
        return md5(data.getBytes());
    }

    /**
     * 获取文件 md5 值
     *
     * @param filePath 文件路径
     * @return 文件 md5 值
     */
    public static String getFileMd5(String filePath) {
        return getFileMd5(new File(filePath));
    }

    /**
     * 获取文件 md5 值
     *
     * @param file 文件
     * @return 文件 md5 值
     */
    public static String getFileMd5(File file) {
        if (!file.exists() || !file.isFile()) return "";
        FileInputStream fin;
        MessageDigest messageDigest;
        byte buffer[] = new byte[1024];
        int len;
        try {
            messageDigest = MessageDigest.getInstance(ALGORITHM);
            fin = new FileInputStream(file);
            while ((len = fin.read(buffer, 0, 1024)) != -1) {
                messageDigest.update(buffer, 0, len);
            }
            fin.close();
            BigInteger bigInt = new BigInteger(1, messageDigest.digest());
            return bigInt.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
