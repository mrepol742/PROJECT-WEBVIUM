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

package com.droidmj.webvium.search;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.telemetry.DiagnosticData;

public class SearchDatabase extends SQLiteOpenHelper {


    public SearchDatabase(Context context) {
        super(context, BuildConfiguration.Database.DATA_SEARCH, null, BuildConfiguration.Database.VERSION_SEARCH);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +
                BuildConfiguration.Database.TABLE_SEARCH +
                " ( " +
                BuildConfiguration.Database.ID +
                " INTEGER PRIMARY KEY, " +
                BuildConfiguration.Database.COL1_SEARCH +
                " TEXT )");
        if (BuildConfiguration.Application.isDevelopment)
            DiagnosticData.a("Webvium.onCreate =" + db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +
                BuildConfiguration.Database.TABLE_SEARCH);
        onCreate(db);
        if (BuildConfiguration.Application.isDevelopment)
            DiagnosticData.a("BuildConfiguration.onUpgrade()");
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
        if (BuildConfiguration.Application.isDevelopment)
            DiagnosticData.a("BuildConfiguration.onDowngrade()");
    }

}
