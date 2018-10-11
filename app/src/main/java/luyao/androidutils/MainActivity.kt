package luyao.androidutils

import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import luyao.lib.encrypt.RC4Utils
import luyao.lib.storage.FileUtils
import java.io.File
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val file = File(Environment.getExternalStorageDirectory().path)
        Log.e("cache", FileUtils.getFileSize(file))

        val data = "你好"
        val key = "123456"

        val enc = RC4Utils.rc4(data.toByteArray(), key.toByteArray())
        Log.e("jni enc", Arrays.toString(enc))
        val dec = RC4Utils.rc4Native(enc, key.toByteArray())
        //        byte[] dec2 = RC4Utils.rc4(dec, key.getBytes());

        Log.e("jni enc", Arrays.toString(dec))
        Log.e("jni", String(dec))
    }
}
