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

package com.mrepol742.webvium.app;

import android.database.sqlite.SQLiteDatabase;

import com.mrepol742.webvium.annotation.release.Keep;
import com.mrepol742.webvium.telemetry.DiagnosticData;

public class W6 {

    @Keep
    private W6() {
    }

    public static void a() {
        System.exit(0);
    }

    public static void b() {

        int a = SQLiteDatabase.releaseMemory();
        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("Webvium.SQLiteDatabase release memory =" + a);
    }

    public static void c() {
        System.gc();
    }
}