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

package com.droidmj.webvium.content;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;

import com.droidmj.webvium.annotation.release.Keep;

public class Package {
    @Keep
    private Package() {
    }

    public static boolean a(String a, PackageManager b) {
        boolean c = true;
        try {
            b.getPackageInfo(a, 0);
        } catch (NameNotFoundException e) {
            c = false;
        }
        return c;
    }

    public static String b() {
        return "com.droidmj.webvium";
    }

    public static String c() {
        return "Webvium";
    }

    public static byte[] d(Context a, String b, int it) {
        try {
            PackageInfo packageInfo = a.getPackageManager().getPackageInfo(b, it);
            return packageInfo.signatures[0].toByteArray();
        } catch (NameNotFoundException ignored) {
        }
        return null;
    }

    public static String e(Context a) throws NameNotFoundException {
        return a.getPackageManager().getPackageInfo(b(), 0).versionName;
    }

    public static int f(Context a) throws NameNotFoundException {
        PackageInfo pi = a.getPackageManager().getPackageInfo(b(), 0);
        if (Build.VERSION.SDK_INT >= 28) {
            return (int) pi.getLongVersionCode();
        }
        return pi.versionCode;
    }

    public static long g(Context a) throws NameNotFoundException {
        return a.getPackageManager().getPackageInfo(b(), 0).firstInstallTime;
    }

    public static long h(Context a) throws NameNotFoundException {
        return a.getPackageManager().getPackageInfo(b(), 0).lastUpdateTime;
    }

}