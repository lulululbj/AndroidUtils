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
        System.out.println(TransformUtils.byte2HexStr(HashUtils.md5(bytes)));
        System.out.println(TransformUtils.byte2HexStr(HashUtils.sha1(bytes)));
        System.out.println(TransformUtils.byte2HexStr(HashUtils.sha256(bytes)));

        System.out.println(HashUtils.md5("Hello World!"));
        System.out.println(HashUtils.sha1("Hello World!"));
        System.out.println(HashUtils.sha256("Hello World!"));
    }

    @Test
    public void getFileHash() {
        String path = "D:/ChatUI.zip";
        System.out.println(HashUtils.getFileHash(path, HashUtils.Hash.MD5));
        System.out.println(HashUtils.getFileHash(path, HashUtils.Hash.SHA1));
        System.out.println(HashUtils.getFileHash(path, HashUtils.Hash.SHA256));

    }

}