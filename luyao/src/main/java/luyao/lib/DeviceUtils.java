package luyao.lib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Locale;

import static android.Manifest.permission.READ_PHONE_STATE;

/**
 * Created by luyao
 * on 2018/10/9 13:19
 */
public class DeviceUtils {

    public static String getBrand() {
        return Build.BRAND;
    }

    public static String getDisplay() {
        return Build.DISPLAY;
    }

    public static String getProduct() {
        return Build.PRODUCT;
    }

    public static String getDevice() {
        return Build.DEVICE;
    }

    public static String getBoard() {
        return Build.BOARD;
    }

    public static String getModel() {
        return Build.MODEL;
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getBootloader() {
        return Build.BOOTLOADER;
    }

    public static String getHardware() {
        return Build.HARDWARE;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static String[] getSupportedAbis() {
        return Build.SUPPORTED_ABIS;
    }

    public static String getRadioVersion() {
        return Build.getRadioVersion();
    }

    public static int getSdkVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static String getVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getKernelVersion() {
        Process process = null;
        String kernelVersion = "";
        try {
            process = Runtime.getRuntime().exec("cat /proc/version");
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream outs = process.getInputStream();
        InputStreamReader isrout = new InputStreamReader(outs);
        BufferedReader brout = new BufferedReader(isrout, 8 * 1024);


        StringBuilder result = new StringBuilder();
        String line;
        try {
            while ((line = brout.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (!result.toString().equals("")) {
                String Keyword = "version ";
                int index = result.indexOf(Keyword);
                line = result.substring(index + Keyword.length());
                index = line.indexOf(" ");
                kernelVersion = line.substring(0, index);
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return kernelVersion;
    }

    public static int getSDK() {
        return Build.VERSION.SDK_INT;
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    @RequiresPermission(READ_PHONE_STATE)
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager == null) return "";
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? telephonyManager.getImei() : telephonyManager.getDeviceId();
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getMEID(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager == null) return "";
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? telephonyManager.getMeid() : telephonyManager.getDeviceId();
    }

    @SuppressLint("MissingPermission")
    public static String getSN() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? Build.getSerial() : Build.SERIAL;
    }


    public static String getMacAddress() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            if (!networkInterface.getName().equalsIgnoreCase("wlan0")) continue;
            byte[] addresses = networkInterface.getHardwareAddress();
            if (addresses == null || addresses.length == 0) return "";
            StringBuilder builder = new StringBuilder();
            for (byte b : addresses) {
                builder.append(String.format(Locale.getDefault(), "%02x:", b));
            }
            return builder.deleteCharAt(builder.length() - 1).toString();
        }
        return "";
    }


}
