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

package com.droidmj.webvium.history;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.droidmj.webvium.BuildConfig;
import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.telemetry.DiagnosticData;

public class HistoryDatabase extends SQLiteOpenHelper {

    public HistoryDatabase(Context context) {
        super(context, BuildConfiguration.Database.DATA_HISTORY, null, BuildConfiguration.Database.VERSION_HISTORY);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +
                BuildConfiguration.Database.TABLE_HISTORY +
                " ( " +
                BuildConfiguration.Database.ID +
                " INTEGER PRIMARY KEY, " +
                BuildConfiguration.Database.COL1_HISTORY +
                " TEXT, " +
                BuildConfiguration.Database.COL2_HISTORY +
                " TEXT, " +
                BuildConfiguration.Database.COL3_HISTORY +
                " TEXT )");
        if (BuildConfiguration.Application.isDevelopment)
            DiagnosticData.a("Webvium.onCreate =" + db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +
                BuildConfiguration.Database.TABLE_HISTORY);
        onCreate(db);
        if (BuildConfiguration.Application.isDevelopment)
            DiagnosticData.a("BuildConfiguration.onUpgrade =" + db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
        if (BuildConfiguration.Application.isDevelopment)
            DiagnosticData.a("BuildConfiguration.onDowngrade =" + db);
    }
}
