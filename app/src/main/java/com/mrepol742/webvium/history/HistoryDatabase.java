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

package com.mrepol742.webvium.history;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mrepol742.webvium.app.BuildConfiguration;
import com.mrepol742.webvium.telemetry.DiagnosticData;

public class HistoryDatabase extends SQLiteOpenHelper {

    public static final String DATA_HISTORY = "qw";
    public static final int VERSION_HISTORY = 1;
    public static final String TABLE_HISTORY = "A";
    public static final String COL1_HISTORY = "B";
    public static final String COL2_HISTORY = "C";
    public static final String COL3_HISTORY = "D";

    public HistoryDatabase(Context context) {
        super(context, DATA_HISTORY, null, VERSION_HISTORY);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +
                TABLE_HISTORY +
                " ( " +
                BuildConfiguration.DB_ID +
                " INTEGER PRIMARY KEY, " +
                COL1_HISTORY +
                " TEXT, " +
                COL2_HISTORY +
                " TEXT, " +
                COL3_HISTORY +
                " TEXT )");
        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("Webvium.onCreate =" + db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +
                TABLE_HISTORY);
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
