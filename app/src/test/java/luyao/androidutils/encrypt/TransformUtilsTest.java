package luyao.androidutils.encrypt;

import org.junit.Test;

import luyao.androidutils.TransformUtils;

import static org.junit.Assert.*;

/**
 * Created by luyao
 * on 2017/11/13 14:26
 */
public class TransformUtilsTest {

    private byte[] b = {12, 'a', 23, 67, 123};
    private String str = "0C6117437B";

    @Test
    public void byte2HexStr() throws Exception {
        assertEquals(str, TransformUtils.byte2HexStr(b));
    }

    @Test
    public void hexStr2Byte() throws Exception {
        byte[] b2 = TransformUtils.hexStr2Byte(str);
        for (int i=0;i<b.length;i++){
            assertEquals(b[i],b2[i]);
        }
    }

}