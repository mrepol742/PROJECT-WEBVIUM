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

package com.droidmj.webvium.manifest;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Service;
import android.content.pm.PackageManager;
import android.os.Build;

import com.droidmj.webvium.annotation.release.Keep;

public class Permission {
    public static final String LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String CAMERA = Manifest.permission.CAMERA;
    public static final String MICROPHONE = Manifest.permission.RECORD_AUDIO;
    public static final String PHONE = Manifest.permission.CALL_PHONE;

    @Keep
    private Permission() {
    }

    public static boolean check(Activity ay, String sg, int it) {
        if (Build.VERSION.SDK_INT >= 29 && STORAGE.equals(sg)) {
            return false;
        } else if (Build.VERSION.SDK_INT >= 23) {
            if (ay.checkSelfPermission(sg) == PackageManager.PERMISSION_GRANTED) {
                return true;
            }
            ay.requestPermissions(new String[]{sg}, it);
            return false;
        }
        return true;
    }

    public static boolean checkOnly(Activity ay, String sg) {
        if (Build.VERSION.SDK_INT >= 29  && STORAGE.equals(sg)) {
            return false;
        } else if (Build.VERSION.SDK_INT >= 23) {
            return ay.checkSelfPermission(sg) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean checkOnly(Service ay, String sg) {
        if ((Build.VERSION.SDK_INT >= 29  && STORAGE.equals(sg)) || Build.VERSION.SDK_INT < 23) {
            return false;
        }
        return ay.checkSelfPermission(sg) != PackageManager.PERMISSION_GRANTED;
    }
}