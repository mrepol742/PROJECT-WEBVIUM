/*
 *
 * Copyright (c) 2021 Melvin Jones Repol (mrepol742.github.io). All rights reserved.
 *
 * License under the GNU General Public License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain changedTo copy of the License at
 *
 *     https://www.gnu.org/licenses/gpl-3.0.en.html
 *
 * Unless required by the applicable law or agreed in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mrepol742.webvium.search;

// SEARCH DATABASE

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;

import com.mrepol742.webvium.app.Sqlite;

public class SearchHelper {

    private static SearchHelper d2;
    private static String temp = "";
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

    public SQLiteDatabase getReadableDatabase() {
        return sld;
    }

    public void delete() {
        if (sld != null && sld.isOpen()) {
            sld.delete(Sqlite.TABLE_SEARCH, null, null);
        }
    }

    public void b(final String a) {
        if (sld != null && sld.isOpen()) {
            sld.delete(Sqlite.TABLE_SEARCH,
                    Sqlite.COL1_SEARCH +
                            " =? ", new String[]{a});
        }
    }

    public void c(final String a) {
        if (!sp.getBoolean("pSearch", false) && !temp.equals(a)) {
            if (sld != null && sld.isOpen()) {
                    temp = a;
                    ContentValues values = new ContentValues();
                    values.put(Sqlite.COL1_SEARCH, a);
                    sld.insert(Sqlite.TABLE_SEARCH, null, values);
            }
        }
    }

    public void f(final String oldData, final String newData) {
        if (sld != null && sld.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(Sqlite.COL1_SEARCH, newData);
            sld.update(Sqlite.TABLE_SEARCH, values,
                    Sqlite.COL1_SEARCH +
                            " LIKE ? ", new String[]{oldData});
        }
    }

    static class SearchDatabase extends SQLiteOpenHelper {

        protected SearchDatabase(Context context) {
            super(context, Sqlite.DATA_SEARCH, null, Sqlite.VERSION_SEARCH);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " +
                    Sqlite.TABLE_SEARCH +
                    " ( " +
                    "_id" +
                    " INTEGER PRIMARY KEY, " +
                    Sqlite.COL1_SEARCH +
                    " TEXT )");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " +
                    Sqlite.TABLE_SEARCH);
            onCreate(db);
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

    }

}
