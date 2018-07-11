package luyao.lib.encrypt;

/**
 * Created by luyao
 * on 2018/7/10 9:36
 */
public class RC4Utils {

    public static byte[] rc4(String content, byte[] key) {
//        Integer[] S = new Integer[256];
//        Character[] keySchedule = new Character[content.length()];
//        StringBuilder builder = new StringBuilder();
//
//        ksa(S, key);
//        rpga(S, keySchedule, content.length());
//
//        for (int i = 0; i < content.length(); ++i) {
//            builder.append((char) (content.charAt(i) ^ keySchedule[i]));
//        }
//        return builder.toString();
        return rc4(content.getBytes(), key);
    }

    public static byte[] rc4(byte[] content, byte[] key) {
        Integer[] S = new Integer[256];
        byte[] keySchedule = new byte[content.length];
        byte[] result = new byte[content.length];

        ksa(S, key);
        rpga(S, keySchedule, content.length);

        for (int i = 0; i < content.length; ++i) {
            result[i] = (byte) (content[i] ^ keySchedule[i]);
        }
        return result;
    }

    private static void ksa(Integer[] s, byte[] key) {
        for (int i = 0; i < 256; i++) {
            s[i] = i;
        }

        int j = 0;
        for (int i = 0; i < 256; ++i) {
            j = (j + s[i] + key[(i % key.length)]) % 256;
            swap(s, i, j);
        }
    }

    private static void rpga(Integer[] s, byte[] keySchedule, int plaintextLength) {
        int i = 0, j = 0;
        for (int k = 0; k < plaintextLength; ++k) {
            i = (i + 1) % 256;
            j = (j + s[i]) % 256;
            swap(s, i, j);
            keySchedule[k] = (byte) (s[(s[i] + s[j]) % 256]).intValue();
        }
    }

    private static void rpga(Integer[] s, Character[] keySchedule, int plaintextLength) {
        int i = 0, j = 0;
        for (int k = 0; k < plaintextLength; ++k) {
            i = (i + 1) % 256;
            j = (j + s[i]) % 256;
            swap(s, i, j);
            keySchedule[k] = (char) (s[(s[i] + s[j]) % 256]).intValue();
        }
    }

    private static void swap(Integer[] s, int i, int j) {
        Integer mTemp = s[i];
        s[i] = s[j];
        s[j] = mTemp;
    }
}
