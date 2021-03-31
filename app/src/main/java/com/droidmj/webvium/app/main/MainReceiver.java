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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.telemetry.DiagnosticData;

public class MainReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("Webvium.onReceive =" + context + " action =" + intent.getAction());
    }
}
