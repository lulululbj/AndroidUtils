package luyao.androidutils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.util.Arrays;

import luyao.lib.DeviceUtils;
import luyao.lib.encrypt.RC4Utils;
import luyao.lib.storage.FileUtils;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File file = new File(Environment.getExternalStorageDirectory().getPath());
        Log.e("cache", FileUtils.getFileSize(file));

        String data = "你好";
        String key = "123456";

        byte[] enc = RC4Utils.rc4(data.getBytes(), key.getBytes());
        Log.e("jni enc", Arrays.toString(enc));
        byte[] dec = RC4Utils.rc4Native(enc, key.getBytes());
//        byte[] dec2 = RC4Utils.rc4(dec, key.getBytes());

        Log.e("jni enc", Arrays.toString(dec));
        Log.e("jni", new String(dec));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        String imei = DeviceUtils.getIMEI(getApplicationContext());
        Log.e("imei", imei);
    }
}
