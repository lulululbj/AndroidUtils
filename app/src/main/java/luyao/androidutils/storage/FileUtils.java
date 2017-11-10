package luyao.androidutils.storage;

import java.io.File;
import java.text.DecimalFormat;

/**
 * Created by luyao
 * on 2017/11/10 15:05
 */

public class FileUtils {

    public static String getFileSize(File file){
        return formatFileSize(getDirectorySize(file));
    }

    public static String formatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    public static long getDirectorySize(File dir) {
        if (dir == null) {
            return 0;
        }
        long size = 0;
        if (dir.isDirectory()) {
            try {
                File[] array = dir.listFiles();
                if (array != null) {
                    for (File file : array) {

                        if (file.isDirectory()) {
                            size += getDirectorySize(file);
                        } else {
                            size += file.length();
                        }
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        } else if (dir.isFile()) {
            size += dir.length();
        }
        return size;
    }

//    public static String formatFileSize(long size) {
//        if (size < 1024) {
//            return String.format("%s B", size);
//        } else if (size < 1024 * 1024) {
//            return String.format("%.1s KB", size / 1024.0f);
//        } else if (size < 1024 * 1024 * 1024) {
//            return String.format("%.1s MB", size / 1024.0f / 1024.0f);
//        } else {
//            return String.format("%.1s GB", size / 1024.0f / 1024.0f / 1024.0f);
//        }
//    }
}
