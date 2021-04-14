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

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;

import com.mrepol742.webvium.app.BuildConfiguration;
import com.mrepol742.webvium.telemetry.DiagnosticData;

public class MainWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("Webvium.onUpdate =" + context);
    }
}