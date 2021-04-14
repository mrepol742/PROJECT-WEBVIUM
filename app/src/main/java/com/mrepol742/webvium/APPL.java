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

package com.mrepol742.webvium;
// WEBVIEW

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mrepol742.webvium.app.BuildConfiguration;
import com.mrepol742.webvium.app.W6;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.os.StrictMode;
import com.mrepol742.webvium.telemetry.DiagnosticData;
import com.mrepol742.webvium.util.Hardware;

// @class Application
// NOTE: This class must be instantiated in fraction of a second so this won't cause any slow loading of
// activities, broadcast receivers, and services

public class APPL extends Application {
    private SharedPreferences sp;

    @Override
    public void onCreate() {
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        if (BuildConfiguration.isDevelopment || (getSharedPreferences("wv,", 0).getBoolean("webDa", false) && sp.getBoolean("stM12", false))) {
            StrictMode.b();
        }
        super.onCreate();
        try {

            if (BuildConfiguration.isDevelopment || (getSharedPreferences("wv,", 0).getBoolean("webDa", false) && sp.getBoolean("og67", false))) {
                DiagnosticData.getInstance(getApplicationContext());
            }
            if (BuildConfiguration.isDevelopment) {
                DiagnosticData.a("APN.onCreate =" + this);
            }
            if (sp.getBoolean("autoUpdate55", false)) {
                boolean bn = Hardware.isNightMode(this) == Hardware.E1.Yes;
                if (bn) {
                    if (!sp.getBoolean("autoUpdate", false)) {
                        g(true);
                    }
                } else {
                    if (!sp.getBoolean("autoUpdate", false)) {
                        g(false);
                    }
                }
            }
        } catch (Exception ex5) {
            DiagnosticData.a(ex5);
        }
    }

    private void g(boolean bn) {
        SharedPreferences.Editor se = sp.edit();
        se.putBoolean("autoUpdate", bn);
        se.apply();
    }

    @Override
    public void onTrimMemory(int i) {
        if (i == ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL || i == ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE || i == ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW) {
            W6.b();
            W6.c();
            if (BuildConfiguration.isDevelopment)
                DiagnosticData.a(Package.c() + ".onTrimMemory =" + this + " value = " + i);
        }
        super.onTrimMemory(i);
    }
}

