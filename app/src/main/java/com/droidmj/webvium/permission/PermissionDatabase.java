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

package com.droidmj.webvium.permission;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.droidmj.webvium.BuildConfig;
import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.telemetry.DiagnosticData;

public class PermissionDatabase extends SQLiteOpenHelper {

    public PermissionDatabase(Context context) {
        super(context, BuildConfiguration.Database.DATA_PERMISSION, null, BuildConfiguration.Database.VERSION_PERMISSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +
                BuildConfiguration.Database.TABLE_PERMISSION +
                " ( " +
                BuildConfiguration.Database.ID +
                " INTEGER PRIMARY KEY, " +
                BuildConfiguration.Database.COL1_PERMISSION +
                " TEXT, " +
                BuildConfiguration.Database.COL2_PERMISSION +
                " TEXT, " +
                BuildConfiguration.Database.COL3_PERMISSION +
                " TEXT, " +
                BuildConfiguration.Database.COL4_PERMISSION +
                " TEXT )");
        if (BuildConfiguration.Application.isDevelopment)
            DiagnosticData.a("Webvium.onCreate()");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +
                BuildConfiguration.Database.TABLE_PERMISSION);
        onCreate(db);
        if (BuildConfiguration.Application.isDevelopment)
            DiagnosticData.a("Webvium.onUpgrade()");
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
        if (BuildConfiguration.Application.isDevelopment)
            DiagnosticData.a("Webvium.onDowngrade()");
    }
}
