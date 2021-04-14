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

package com.mrepol742.webvium.search;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mrepol742.webvium.app.BuildConfiguration;
import com.mrepol742.webvium.telemetry.DiagnosticData;

public class SearchDatabase extends SQLiteOpenHelper {

    public static final String DATA_SEARCH = "as";
    public static final int VERSION_SEARCH = 1;
    public static final String TABLE_SEARCH = "A";
    public static final String COL1_SEARCH = "B";
    
    public SearchDatabase(Context context) {
        super(context, DATA_SEARCH, null, VERSION_SEARCH);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +
                TABLE_SEARCH +
                " ( " +
                BuildConfiguration.DB_ID +
                " INTEGER PRIMARY KEY, " +
                COL1_SEARCH +
                " TEXT )");
        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("Webvium.onCreate =" + db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +
                TABLE_SEARCH);
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
