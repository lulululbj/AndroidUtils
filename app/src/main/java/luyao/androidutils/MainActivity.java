package luyao.androidutils;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;

import luyao.lib.encrypt.RC4Utils;
import luyao.lib.storage.FileUtils;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File file = new File(Environment.getExternalStorageDirectory().getPath());
        Log.e("cache", FileUtils.getFileSize(file));

        String data = "hello";
        String key = "123456";

        byte[] enc = RC4Utils.rc4Native(data.getBytes(), key.getBytes(), data.getBytes().length, key.length());
        byte[] dec = RC4Utils.rc4Native(enc, key.getBytes(), enc.length, key.length());

        Log.e("jni", new String(dec));
    }
}
