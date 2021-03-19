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

package com.droidmj.webvium.bookmark;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.telemetry.DiagnosticData;

public class BookmarkDatabase extends SQLiteOpenHelper {
    public BookmarkDatabase(Context context) {
        super(context, BuildConfiguration.Database.DATA_BOOKMARK, null, BuildConfiguration.Database.VERSION_BOOKMARK);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +
                BuildConfiguration.Database.TABLE_BOOKMARK +
                " ( " +
                BuildConfiguration.Database.ID +
                " INTEGER PRIMARY KEY, " +
                BuildConfiguration.Database.COL1_BOOKMARK +
                " TEXT, " +
                BuildConfiguration.Database.COL2_BOOKMARK +
                " TEXT ) ");
        if (BuildConfiguration.Application.isDevelopment)
            DiagnosticData.a("Webvium.onCreate =" + db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +
                BuildConfiguration.Database.TABLE_BOOKMARK);
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
