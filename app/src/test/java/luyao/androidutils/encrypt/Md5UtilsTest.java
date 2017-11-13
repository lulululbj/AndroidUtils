package luyao.androidutils.encrypt;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by luyao
 * on 2017/11/13 14:49
 */
public class Md5UtilsTest {

    @Test
    public void md5() throws Exception {
        String data = "sunluyao";
        assertEquals(true, Md5Utils.md5(data).equalsIgnoreCase("417e65f550705c4085506685c65f09a9"));
    }

    @Test
    public void getFileMd5() throws Exception {
        String md5 = Md5Utils.getFileMd5("E:/luyao/demo/demo.zip");
        assertEquals("334526620910365488287277141767848353304", md5);
    }

}