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

package com.mrepol742.webvium.download;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;

import com.mrepol742.webvium.app.Sqlite;

@Deprecated
public class DownloadHelper {

    private static DownloadHelper d10;
    private final SQLiteDatabase sld;
    private final SharedPreferences sp;

    @Deprecated
    private DownloadHelper(Context ct) {
        DownloadDatabase d9 = new DownloadDatabase(ct);
        sp = PreferenceManager.getDefaultSharedPreferences(ct);
        sld = d9.getWritableDatabase();
    }

    @Deprecated
    public static DownloadHelper getInstance(Context ctx) {
        if (d10 == null) {
            d10 = new DownloadHelper(ctx);
        }
        return d10;
    }

    @Deprecated
    public SQLiteDatabase getReadableDatabase() {
        return sld;
    }

    @Deprecated
    public void delete() {
        if (sld != null && sld.isOpen()) {
            sld.delete(Sqlite.TABLE_DOWNLOAD, null, null);
        }
    }

    @Deprecated
    public void b(DownloadDataModel w2) {
        if (sld != null && sld.isOpen()) {
            sld.delete(Sqlite.TABLE_DOWNLOAD,
                    Sqlite.COL1_DOWNLOAD +
                            " =? AND " +
                            Sqlite.COL2_DOWNLOAD +
                            " =? AND " +
                            Sqlite.COL3_DOWNLOAD +
                            " =? AND " +
                            Sqlite.COL4_DOWNLOAD +
                            " =?" , new String[]{w2.a,
                            w2.b,
                            Long.toString(w2.c),
                            Long.toString(w2.d)});
        }
    }

    @Deprecated
    public void c(String a, String b, long c) {
        if (!sp.getBoolean("pDownload", false)) {
            if (sld != null && sld.isOpen()) {
                ContentValues values = new ContentValues();
                values.put(Sqlite.COL1_DOWNLOAD, a);
                values.put(Sqlite.COL2_DOWNLOAD, b);
                values.put(Sqlite.COL3_DOWNLOAD, c);
                values.put(Sqlite.COL4_DOWNLOAD, System.currentTimeMillis());
                sld.insert(Sqlite.TABLE_DOWNLOAD, null, values);
            }
        }
    }

    @Deprecated
    public void i(String oldTitle, String oldURl, long oldSize, long oldTIme, String newTitle, String newUrl) {
        if (sld != null && sld.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(Sqlite.COL1_DOWNLOAD, newTitle);
            values.put(Sqlite.COL2_DOWNLOAD, newUrl);
            values.put(Sqlite.COL3_DOWNLOAD, oldSize);
            values.put(Sqlite.COL4_DOWNLOAD, oldTIme);
            sld.update(Sqlite.TABLE_DOWNLOAD, values,
                    Sqlite.COL1_DOWNLOAD +
                            " LIKE ? AND " +
                            Sqlite.COL2_DOWNLOAD +
                            " LIKE ? AND " +
                            Sqlite.COL3_DOWNLOAD +
                            " LIKE ? AND " +
                            Sqlite.COL4_DOWNLOAD +
                            " LIKE ?", new String[]{oldTitle, oldURl, Long.toString(oldSize), Long.toString(oldTIme)});
        }

    }

    static class DownloadDatabase extends SQLiteOpenHelper {

        protected DownloadDatabase(Context context) {
            super(context, Sqlite.DATA_DOWNLOAD, null, Sqlite.VERSION_DOWNLOAD);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " +
                    Sqlite.TABLE_DOWNLOAD +
                    " ( " +
                    "_id" +
                    " INTEGER PRIMARY KEY, " +
                    Sqlite.COL1_DOWNLOAD +
                    " TEXT, " +
                    Sqlite.COL2_DOWNLOAD +
                    " TEXT, " +
                    Sqlite.COL3_DOWNLOAD +
                    " INTEGER, " +
                    Sqlite.COL4_DOWNLOAD +
                    " INTEGER)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " +
                    Sqlite.TABLE_DOWNLOAD);
            onCreate(db);
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

    }

}