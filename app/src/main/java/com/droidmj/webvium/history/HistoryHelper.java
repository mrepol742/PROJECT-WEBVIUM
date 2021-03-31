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

// HISTORY DATABASE

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.webkit.URLUtil;

import com.droidmj.webvium.HDMS;
import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.app.WebviumDatabase;
import com.droidmj.webvium.telemetry.DiagnosticData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HistoryHelper implements WebviumDatabase {

    private static HistoryHelper d1;
    private final SharedPreferences sp;
    private final SQLiteDatabase sld;

    private HistoryHelper(Context ct) {
        sp = PreferenceManager.getDefaultSharedPreferences(ct);
        HistoryDatabase d5 = new HistoryDatabase(ct);
        sld = d5.getWritableDatabase();
    }

    public static HistoryHelper getInstance(Context ctx) {
        if (d1 == null) {
            d1 = new HistoryHelper(ctx);
        }
        return d1;
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return sld;
    }

    @Override
    public void finish() {
        if (sld != null && sld.isOpen()) {
            sld.close();
        }
    }

    @Override
    public void delete() {
        if (sld != null && sld.isOpen()) {
            sld.delete(HistoryDatabase.TABLE_HISTORY, null, null);
        }
    }

    public void b(final String a, final String b, final String s) {
        if (sld != null && sld.isOpen()) {
            sld.delete(HistoryDatabase.TABLE_HISTORY,
                    HistoryDatabase.COL1_HISTORY +
                            " =? AND " +
                            HistoryDatabase.COL2_HISTORY +
                            " =? AND " +
                            HistoryDatabase.COL3_HISTORY +
                            " =? ", new String[]{b, a, s});
        }
    }

    public void c(final String a, final String b) {
        if (!sp.getBoolean("pHistory", false)) {
            if (sld != null && sld.isOpen()) {
                //if (HDMS.b(a.toLowerCase())) {
                // if (HDMS.b(b.toLowerCase())) {
                ContentValues values = new ContentValues();
                values.put(HistoryDatabase.COL1_HISTORY, h(a));
                values.put(HistoryDatabase.COL2_HISTORY, b);
                values.put(HistoryDatabase.COL3_HISTORY, g());
                sld.insert(HistoryDatabase.TABLE_HISTORY, null, values);
            }

        }
    }

    public void d(HDMS HDMS) {
        if (!sp.getBoolean("pHistory", false)) {
            if (sld != null && sld.isOpen()) {
                //if (HDMS.b(a.toLowerCase())) {
                // if (HDMS.b(b.toLowerCase())) {
                ContentValues values = new ContentValues();
                values.put(HistoryDatabase.COL1_HISTORY, h(HDMS.ls));
                values.put(HistoryDatabase.COL2_HISTORY, HDMS.ls0);
                values.put(HistoryDatabase.COL3_HISTORY, HDMS.ls2);
                sld.insert(HistoryDatabase.TABLE_HISTORY, null, values);
            }

        }
    }

    private String g() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyy | HHmm", Locale.US);
        return sdf.format(new Date());
    }

    private String h(String sg) {
        if (URLUtil.isValidUrl(sg)) {
            try {
                Uri uri = Uri.parse(sg);
                return uri.getHost();
            } catch (Exception en) {
                DiagnosticData.a(en);
            }
            return sg;
        }
        return sg;
    }

    public void i(final String oldTitle, final String oldURl, final String oldTIme, final String newTitle, final String newUrl) {
        if (sld != null && sld.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(HistoryDatabase.COL1_HISTORY, h(newTitle));
            values.put(HistoryDatabase.COL2_HISTORY, newUrl);
            values.put(HistoryDatabase.COL3_HISTORY, oldTIme);
            sld.update(HistoryDatabase.TABLE_HISTORY, values,
                    HistoryDatabase.COL1_HISTORY +
                            " LIKE ? AND " +
                            HistoryDatabase.COL2_HISTORY +
                            " LIKE ? AND " +
                            HistoryDatabase.COL3_HISTORY +
                            " LIKE ?", new String[]{oldTitle, oldURl, oldTIme});
        }
    }

}