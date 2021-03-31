/*
 *
 *
 *
 * DROID MJ Property || Confidential
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.droidmj.webvium.util;

import com.droidmj.webvium.annotation.release.Keep;
import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.app.UnsupportedActions;
import com.droidmj.webvium.telemetry.DiagnosticData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Zip {
    @Keep
    private Zip() {
    }

    public static boolean compress(String filePath, String i) {
        try {
            File file = new File(filePath);
            FileOutputStream fos = new FileOutputStream(i);
            ZipOutputStream zos = new ZipOutputStream(fos);
            zos.putNextEntry(new ZipEntry(file.getName()));
            byte[] bytes = getBytes(file);
            zos.write(bytes, 0, bytes.length);
            zos.closeEntry();
            zos.close();
            return true;
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
        return false;
    }

    private static byte[] getBytes(File file) throws UnsupportedActions, IOException {
        try (RandomAccessFile f = new RandomAccessFile(file, "r")) {
            long longLength = f.length();
            int length = (int) longLength;
            if (length != longLength)
                throw new UnsupportedActions();
            if (BuildConfiguration.isDevelopment) {
                DiagnosticData.a("FILE SIZE >= 2 GB");
            }
            byte[] data = new byte[length];
            f.readFully(data);
            return data;
        }
    }

    public static boolean decompress(ZipInputStream zos, File file) {
        try {
            byte[] buffer = new byte[1024];
            ZipEntry ze = zos.getNextEntry();
            while (ze != null) {
                FileOutputStream fos = new FileOutputStream(file);
                int len;
                while ((len = zos.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                ze = zos.getNextEntry();
            }
            zos.closeEntry();
            zos.close();
            return true;
        } catch (IOException ex) {
            DiagnosticData.a(ex);
        }
        return false;
    }

    public static boolean decompress(String filePath, String i) throws FileNotFoundException {
        return decompress(new ZipInputStream(new FileInputStream(filePath)), new File(i));
    }

    public static boolean decompress(InputStream is, String path) {
        return decompress(new ZipInputStream(is), new File(path));
    }
}