package luyao.lib.encrypt;

import org.junit.Test;

import luyao.lib.TransformUtils;

/**
 * Created by luyao
 * on 2018/6/29 9:43
 */
public class HashUtilsTest {

    @Test
    public void hash() {
        byte[] bytes = "Hello World!".getBytes();
        System.out.println(TransformUtils.byte2HexStr(HashUtils.hash(bytes, HashUtils.Hash.SHA1)));
        System.out.println(TransformUtils.byte2HexStr(HashUtils.hash(bytes, HashUtils.Hash.SHA256)));
        System.out.println(TransformUtils.byte2HexStr(HashUtils.hash(bytes, HashUtils.Hash.MD5)));

        System.out.println(HashUtils.hash("Hello World!", HashUtils.Hash.SHA1));
        System.out.println(HashUtils.hash("Hello World!", HashUtils.Hash.SHA256));
        System.out.println(HashUtils.hash("Hello World!", HashUtils.Hash.MD5));
    }

    @Test
    public void getFileHash() {
        String path = "D:/ChatUI.zip";
        System.out.println(HashUtils.getFileHash(path, HashUtils.Hash.MD5));
        System.out.println(HashUtils.getFileHash(path, HashUtils.Hash.SHA1));
        System.out.println(HashUtils.getFileHash(path, HashUtils.Hash.SHA256));

    }

}