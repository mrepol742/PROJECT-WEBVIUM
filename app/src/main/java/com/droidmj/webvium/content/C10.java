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

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;

import com.droidmj.webvium.annotation.release.Keep;
import com.droidmj.webvium.telemetry.DiagnosticData;

public class C10 {
    @Keep
    private C10() {
    }

    public static void a(Context a, String b, int c) {
        try {
            PackageManager pm = a.getPackageManager();
            ComponentName cn = new ComponentName(a, b);
            pm.setComponentEnabledSetting(cn, c, PackageManager.DONT_KILL_APP);
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }
}