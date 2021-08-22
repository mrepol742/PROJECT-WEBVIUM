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

package com.mrepol742.webvium.history;

// HISTORY DATABASE

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.webkit.URLUtil;

import com.mrepol742.webvium.app.Sqlite;

@Deprecated
public class HistoryHelper {

    private static HistoryHelper d1;
    private final SharedPreferences sp;
    private final SQLiteDatabase sld;

    @Deprecated
    private HistoryHelper(Context ct) {
        sp = PreferenceManager.getDefaultSharedPreferences(ct);
        HistoryDatabase d5 = new HistoryDatabase(ct);
        sld = d5.getWritableDatabase();
    }

    @Deprecated
    public static HistoryHelper getInstance(Context ctx) {
        if (d1 == null) {
            d1 = new HistoryHelper(ctx);
        }
        return d1;
    }

    @Deprecated
    public SQLiteDatabase getReadableDatabase() {
        return sld;
    }

    @Deprecated
    public void delete() {
        if (sld != null && sld.isOpen()) {
            sld.delete(Sqlite.TABLE_HISTORY, null, null);
        }
    }

    @Deprecated
    public void b(final String a, final String b, final long s) {
        if (sld != null && sld.isOpen()) {
            sld.delete(Sqlite.TABLE_HISTORY,
                    Sqlite.COL1_HISTORY +
                            " =? AND " +
                            Sqlite.COL2_HISTORY +
                            " =? AND " +
                            Sqlite.COL3_HISTORY +
                            " =? ", new String[]{b, a, Long.toString(s)});
        }
    }

    @Deprecated
    public void c(String a, String b, long c) {
        if (!sp.getBoolean("pHistory", false)) {
            if (sld != null && sld.isOpen()) {
                ContentValues values = new ContentValues();
                values.put(Sqlite.COL1_HISTORY, h(a));
                values.put(Sqlite.COL2_HISTORY, b);
                values.put(Sqlite.COL3_HISTORY, c);
                sld.insert(Sqlite.TABLE_HISTORY, null, values);
            }
        }
    }

    private String h(String sg) {
        if (URLUtil.isValidUrl(sg)) {
            try {
                Uri uri = Uri.parse(sg);
                return uri.getHost();
            } catch (Exception en) {
                en.printStackTrace();
            }
            return sg;
        }
        return sg;
    }

    @Deprecated
    public void i(String oldTitle, String oldURl, long oldTIme, String newTitle, String newUrl) {
        if (sld != null && sld.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(Sqlite.COL1_HISTORY, h(newTitle));
            values.put(Sqlite.COL2_HISTORY, newUrl);
            values.put(Sqlite.COL3_HISTORY, oldTIme);
            sld.update(Sqlite.TABLE_HISTORY, values,
                    Sqlite.COL1_HISTORY +
                            " LIKE ? AND " +
                            Sqlite.COL2_HISTORY +
                            " LIKE ? AND " +
                            Sqlite.COL3_HISTORY +
                            " LIKE ?", new String[]{oldTitle, oldURl, Long.toString(oldTIme)});
        }
    }

    static class HistoryDatabase extends SQLiteOpenHelper {

        protected HistoryDatabase(Context context) {
            super(context, Sqlite.DATA_HISTORY, null, Sqlite.VERSION_HISTORY);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " +
                    Sqlite.TABLE_HISTORY +
                    " ( " +
                    "_id" +
                    " INTEGER PRIMARY KEY, " +
                    Sqlite.COL1_HISTORY +
                    " TEXT, " +
                    Sqlite.COL2_HISTORY +
                    " TEXT, " +
                    Sqlite.COL3_HISTORY +
                    " INTEGER)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " +
                    Sqlite.TABLE_HISTORY);
            onCreate(db);
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

}