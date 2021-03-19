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

package com.droidmj.webvium.app.main;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.telemetry.DiagnosticData;

public class MainService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        if (BuildConfiguration.Application.isDevelopment)
            DiagnosticData.a("Webvium.onCreate =" + this);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (BuildConfiguration.Application.isDevelopment) {
            DiagnosticData.a("BuildConfiguration.onStartCommand =" + this);
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (BuildConfiguration.Application.isDevelopment)
            DiagnosticData.a("Webvium.onDestroy =" + this);
    }

    public void s1() {
        stopSelf();
        if (BuildConfiguration.Application.isDevelopment)
            DiagnosticData.a("BuildConfiguration.stopSelf =" + this);
    }

    public void s2() {
        stopForeground(true);
        if (BuildConfiguration.Application.isDevelopment)
            DiagnosticData.a("BuildConfiguration.stopForeground =" + this);
    }
}
