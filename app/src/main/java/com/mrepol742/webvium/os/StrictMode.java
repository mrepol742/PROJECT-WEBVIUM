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

package com.mrepol742.webvium.os;

import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.VmPolicy;

import com.mrepol742.webvium.annotation.release.Keep;
import com.mrepol742.webvium.app.BuildConfiguration;
import com.mrepol742.webvium.telemetry.DiagnosticData;

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
