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

package com.droidmj.webvium.io;

import android.content.Context;
import android.os.Environment;

import com.droidmj.webvium.annotation.release.Keep;
import com.droidmj.webvium.app.UnsupportedActions;

import java.io.File;

public class StorageDirectory {

    @Keep
    private StorageDirectory() {

    }

    public static String a() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static String getWebviumDir() {
        return a() + "/Webvium";
    }

    public static String getDownloadDir() {
        return getWebviumDir() + "/Downloads";
    }

    public static String getScreenshotDir() {
        return getWebviumDir() + "/Screenshots";
    }

    public static String getToolsDir() {
        return getWebviumDir() + "/Tools";
    }

    public static String getClasses(Context context) {
        return getFileDir(context) + "/b";
    }

    public static String getVideoPoster(Context context) {
        return getFileDir(context) + "/e";
    }

    public static String getBackground(Context context) {
        return getFileDir(context) + "/a";
    }

    public static File getCacheDir(Context context) {
        File fe = context.getCacheDir();
        if (fe.getAbsolutePath().contains("com.droidmj.webvium")) {
            return fe;
        }
        throw new UnsupportedActions();
    }

    public static File getFileDir(Context context) {
        File fe = context.getFilesDir();
        if (fe.getAbsolutePath().contains("com.droidmj.webvium")) {
            return fe;
        }
        throw new UnsupportedActions();
    }

    public static class Backup {

        public static String getBackupDir() {
            return getWebviumDir() + "/Backup";
        }

        public static String getApplicationDir() {
            return getBackupDir() + "/Application";
        }

        public static String getDatabasesDir() {
            return getBackupDir() + "/Databases";
        }
    }


}