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

package com.mrepol742.webvium.download;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mrepol742.webvium.app.BuildConfiguration;
import com.mrepol742.webvium.telemetry.DiagnosticData;

public class DownloadDatabase extends SQLiteOpenHelper {

    public static final String DATA_DOWNLOAD = "jo";
    public static final int VERSION_DOWNLOAD = 1;
    public static final String TABLE_DOWNLOAD = "A";
    public static final String COL1_DOWNLOAD = "B";
    public static final String COL2_DOWNLOAD = "C";
    public static final String COL3_DOWNLOAD = "D";
    public static final String COL4_DOWNLOAD = "E";
    public static final String COL5_DOWNLOAD = "F";

    public DownloadDatabase(Context context) {
        super(context, DATA_DOWNLOAD, null, VERSION_DOWNLOAD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +
                TABLE_DOWNLOAD +
                " ( " +
                BuildConfiguration.DB_ID +
                " INTEGER PRIMARY KEY, " +
                COL1_DOWNLOAD +
                " TEXT, " +
                COL2_DOWNLOAD +
                " TEXT, " +
                COL3_DOWNLOAD +
                " INTEGER, " +
                COL4_DOWNLOAD +
                " TEXT, " +
                COL5_DOWNLOAD +
                " TEXT ) ");
        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("Webvium.onCreate =" + db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +
                TABLE_DOWNLOAD);
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
