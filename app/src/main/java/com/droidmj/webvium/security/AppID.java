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

package com.droidmj.webvium.security;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import com.droidmj.webvium.annotation.release.Keep;
import com.droidmj.webvium.app.BuildConfiguration;

import java.util.UUID;

public class AppID {
    @Keep
    private AppID() {
    }

    @SuppressLint("HardwareIds")
    public static String getAppID(Context ct) {
        String a = "742" +
                Build.BOARD.length() % 10 +
                Build.BRAND.length() % 10 +
                Build.DEVICE.length() % 10 +
                Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 +
                Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 +
                Build.TYPE.length() % 10 +
                Build.USER.length() % 10 +
                BuildConfiguration.Application.downloadSize.length() % 20 +
                Boolean.toString(BuildConfiguration.Application.isDevelopment).length() % 30;
        String b = Settings.Secure.getString(ct.getContentResolver(), Settings.Secure.ANDROID_ID);
        BluetoothAdapter d = BluetoothAdapter.getDefaultAdapter();
        String c = "";
        if (d != null) {
            c = d.getAddress();
        }
        String id = a + b + c + UUID.randomUUID().toString();
        return Hash.a("SHA-1", id);
    }
}
