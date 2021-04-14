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

package com.mrepol742.webvium.bookmark;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mrepol742.webvium.app.BuildConfiguration;
import com.mrepol742.webvium.telemetry.DiagnosticData;

public class BookmarkDatabase extends SQLiteOpenHelper {

    public static final String DATA_BOOKMARK = "zx";
    public static final int VERSION_BOOKMARK = 1;
    public static final String TABLE_BOOKMARK = "A";
    public static final String COL1_BOOKMARK = "B";
    public static final String COL2_BOOKMARK = "C";

    public BookmarkDatabase(Context context) {
        super(context, DATA_BOOKMARK, null, VERSION_BOOKMARK);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +
                TABLE_BOOKMARK +
                " ( " +
                BuildConfiguration.DB_ID +
                " INTEGER PRIMARY KEY, " +
                COL1_BOOKMARK +
                " TEXT, " +
                COL2_BOOKMARK +
                " TEXT ) ");
        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("Webvium.onCreate =" + db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +
                TABLE_BOOKMARK);
        onCreate(db);
        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("Webvium.onUpgrade =" + db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("Webvium.onDowngrade =" + db);
    }

}
