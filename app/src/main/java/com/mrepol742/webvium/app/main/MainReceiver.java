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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mrepol742.webvium.app.BuildConfiguration;
import com.mrepol742.webvium.telemetry.DiagnosticData;

public class MainReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("Webvium.onReceive =" + context + " action =" + intent.getAction());
    }
}
