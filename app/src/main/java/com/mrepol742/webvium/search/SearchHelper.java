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

// SEARCH DATABASE

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;

import com.mrepol742.webvium.app.WebviumDatabase;

public class SearchHelper implements WebviumDatabase {

    private static SearchHelper d2;
    private final SharedPreferences sp;
    private final SQLiteDatabase sld;

    public SearchHelper(Context ct) {
        SearchDatabase d7 = new SearchDatabase(ct);
        sp = PreferenceManager.getDefaultSharedPreferences(ct);
        sld = d7.getWritableDatabase();
    }

    public static SearchHelper getInstance(Context ctx) {
        if (d2 == null) {
            d2 = new SearchHelper(ctx);
        }
        return d2;
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
            sld.delete(SearchDatabase.TABLE_SEARCH, null, null);
        }
    }

    public void b(final String a) {
        if (sld != null && sld.isOpen()) {
            sld.delete(SearchDatabase.TABLE_SEARCH,
                    SearchDatabase.COL1_SEARCH +
                            " =? ", new String[]{a});
        }
    }

    public void c(final String a) {
        if (!sp.getBoolean("pSearch", false)) {
            if (sld != null && sld.isOpen()) {
                if (com.mrepol742.webvium.util.Inapproriate.isInapproriate(a.toLowerCase())) {
                    ContentValues values = new ContentValues();
                    values.put(SearchDatabase.COL1_SEARCH, a);
                    sld.insert(SearchDatabase.TABLE_SEARCH, null, values);
                }
            }
        }
    }

    public void f(final String oldData, final String newData) {
        if (sld != null && sld.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(SearchDatabase.COL1_SEARCH, newData);
            sld.update(SearchDatabase.TABLE_SEARCH, values,
                    SearchDatabase.COL1_SEARCH +
                            " LIKE ? ", new String[]{oldData});
        }
    }
}
