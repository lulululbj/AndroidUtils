package luyao.lib.encrypt;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by luyao
 * on 2018/7/10 9:51
 */
public class RC4UtilsTest {

    String origin = "Hello World!";
    byte[] originByte = {72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 33};
    String key = "abc";
    byte[] keyBytes = {97, 98, 99};

    @Test
    public void rc4() {


        byte[] encode = RC4Utils.rc4(origin, key);
        byte[] decode = RC4Utils.rc4(encode, keyBytes);
        assertEquals(origin, new String(decode));

    }

    @Test
    public void rc41() {
        byte[] encode = RC4Utils.rc4(originByte, keyBytes);
        byte[] decode = RC4Utils.rc4(encode, keyBytes);
        assertArrayEquals(origin.getBytes(), decode);
    }

    @Test
    public void rc42() {
        byte[] encode = RC4Utils.rc4(origin, keyBytes);
        byte[] decode = RC4Utils.rc4(encode, keyBytes);
        assertArrayEquals(origin.getBytes(), decode);
    }

    @Test
    public void rc43() {
    }

    @Test
    public void rc44() {
    }
}