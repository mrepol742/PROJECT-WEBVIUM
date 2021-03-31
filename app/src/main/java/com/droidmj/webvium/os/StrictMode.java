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

package com.droidmj.webvium.os;

import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.VmPolicy;

import com.droidmj.webvium.annotation.release.Keep;
import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.telemetry.DiagnosticData;

public class StrictMode {
    @Keep
    private StrictMode() {
    }

    public static void a() {
        android.os.StrictMode.setThreadPolicy(new ThreadPolicy.Builder().detectNetwork().penaltyLog().build());
        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("StrictMode Network | ThreadPolicy |  Initialize");
    }

    public static void b() {
        android.os.StrictMode.setThreadPolicy(new ThreadPolicy.Builder().detectAll().penaltyLog().build());
        android.os.StrictMode.setVmPolicy(new VmPolicy.Builder().detectAll().penaltyLog().build());
        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("StrictMode Initialize");
    }

}
