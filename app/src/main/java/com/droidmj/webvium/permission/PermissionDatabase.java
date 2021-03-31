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

import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.telemetry.DiagnosticData;

public class PermissionDatabase extends SQLiteOpenHelper {

    public static final String DATA_PERMISSION = "po";
    public static final int VERSION_PERMISSION = 1;
    public static final String TABLE_PERMISSION = "A";
    public static final String COL1_PERMISSION = "B";
    public static final String COL2_PERMISSION = "C";
    public static final String COL3_PERMISSION = "D";
    public static final String COL4_PERMISSION = "E";

    public PermissionDatabase(Context context) {
        super(context, DATA_PERMISSION, null, VERSION_PERMISSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +
                TABLE_PERMISSION +
                " ( " +
                BuildConfiguration.DB_ID +
                " INTEGER PRIMARY KEY, " +
                COL1_PERMISSION +
                " TEXT, " +
                COL2_PERMISSION +
                " TEXT, " +
                COL3_PERMISSION +
                " TEXT, " +
                COL4_PERMISSION +
                " TEXT )");
        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("Webvium.onCreate()");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +
                TABLE_PERMISSION);
        onCreate(db);
        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("Webvium.onUpgrade()");
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("Webvium.onDowngrade()");
    }
}
