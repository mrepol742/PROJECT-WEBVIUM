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

package com.mrepol742.webvium.app.main;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.mrepol742.webvium.app.BuildConfiguration;
import com.mrepol742.webvium.telemetry.DiagnosticData;

public class MainService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("Webvium.onCreate =" + this);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (BuildConfiguration.isDevelopment) {
            DiagnosticData.a("Webvium.onStartCommand =" + this);
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("Webvium.onDestroy =" + this);
    }

    public void s1() {
        stopSelf();
        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("Webvium.stopSelf =" + this);
    }

    public void s2() {
        stopForeground(true);
        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("Webvium.stopForeground =" + this);
    }
}
