package luyao.lib.encrypt;

import org.junit.Test;

import luyao.lib.TransformUtils;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by luyao
 * on 2017/11/13 14:26
 */
public class TransformUtilsTest {

    private byte[] BYTES = {12, 'a', 23, 67, 123};
    private String HEX_STRING = "0C6117437B";
    private int INTEGER = 207689539;

    private short SHORT = 32767;
    private byte[] SHORT_BYTES = {-1, 127};

    private long LONG = 2223372036854775807L;
    private byte[] LONG_BYTES = {-1, -1, 67, 108, 22, 1, -37, 30};

    private String STRING = "Hello, Android!";
    private String HEX_STRING2 = "48656C6C6F2C20416E64726F696421";


    @Test
    public void bytes2Int() {
        int i = TransformUtils.bytes2Int(BYTES);
        assertEquals(INTEGER, i);
    }

    @Test
    public void int2Bytes() {
        byte[] b = TransformUtils.int2Bytes(INTEGER);
        for (int i = 0; i < 4; i++) {
            assertEquals(BYTES[i], b[i]);
        }
    }

    @Test
    public void byte2HexStr() {
        assertEquals(HEX_STRING, TransformUtils.byte2HexStr(BYTES));
    }

    @Test
    public void hexStr2Byte() {
        byte[] b = TransformUtils.hexStr2Byte(HEX_STRING);
        assertArrayEquals(BYTES, b);
    }

    @Test
    public void bytes2Short() {
        short s = TransformUtils.bytes2Short(SHORT_BYTES);
        assertEquals(SHORT, s);
    }

    @Test
    public void short2Bytes() {
        byte[] bytes = TransformUtils.short2Bytes(SHORT);
        assertArrayEquals(SHORT_BYTES, bytes);
    }

    @Test
    public void bytes2Long() {
        long l = TransformUtils.bytes2Long(LONG_BYTES);
        assertEquals(LONG, l);
    }

    @Test
    public void long2Bytes() {
        byte[] longBytes = TransformUtils.long2Bytes(LONG);
        assertArrayEquals(LONG_BYTES, longBytes);
    }

    @Test
    public void string2Hex() {
        String hex = TransformUtils.string2Hex(STRING);
        assertEquals(HEX_STRING2,hex);
    }

    @Test
    public void hex2String() {
        String normal = TransformUtils.hex2String(HEX_STRING2);
        assertEquals(STRING, normal);
    }
}