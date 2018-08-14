package luyao.lib.encrypt;

import luyao.lib.TransformUtils;

/**
 * Created by luyao
 * on 2018/7/10 9:36
 */
public class RC4Utils {

    public static byte[] rc4(String content, String key) {
        return rc4(content, key.getBytes());
    }

    public static byte[] rc4(String content, byte[] key) {
        return rc4(content, TransformUtils.bytes2Ints(key));
    }

    private static byte[] rc4(String content, int[] key) {
        return rc4(content.getBytes(), key);
    }

    public static byte[] rc4(byte[] content, byte[] key) {
        return rc4(content, TransformUtils.bytes2Ints(key));
    }

    private static byte[] rc4(byte[] content, int[] key) {
        char[] S = new char[256];
        char[] keySchedule = new char[content.length];
        byte[] result = new byte[content.length];

        ksa(S, key);
        rpga(S, keySchedule, content.length);

        for (int i = 0; i < content.length; ++i) {
            result[i] = (byte) (content[i] ^ keySchedule[i]);
        }
        return result;
    }

    private static void ksa(char[] S, int[] key) {
        for (int i = 0; i < 256; i++) {
            S[i] = (char) i;
        }

        int j = 0;
        for (int i = 0; i < 256; ++i) {
            j = (j + S[i] + key[(i % key.length)]) % 256;
            swap(S, i, j);
        }
    }

    private static void rpga(char[] s, char[] keySchedule, int plaintextLength) {
        int i = 0, j = 0;
        for (int k = 0; k < plaintextLength; ++k) {
            i = (i + 1) % 256;
            j = (j + s[i]) % 256;
            swap(s, i, j);
            keySchedule[k] = s[(s[i] + s[j]) % 256];

        }
    }

    private static void swap(char[] s, int i, int j) {
        char mTemp = s[i];
        s[i] = s[j];
        s[j] = mTemp;
    }

    static {
        System.loadLibrary("native");
    }

    public static native byte[] rc4Native(byte[] content, byte[] key,int dataLe,int keyLen);
}
