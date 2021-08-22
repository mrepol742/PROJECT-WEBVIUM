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

package com.mrepol742.webvium.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.webkit.URLUtil;

import com.mrepol742.webvium.history.HistoryDataModel;

public class Sqlite extends SQLiteOpenHelper {
    public static final String DATA_DOWNLOAD = "a1";
    public static final int VERSION_DOWNLOAD = 1;
    public static final String TABLE_DOWNLOAD = "a2";
    public static final String COL1_DOWNLOAD = "a3";
    public static final String COL2_DOWNLOAD = "a4";
    public static final String COL3_DOWNLOAD = "a5";
    public static final String COL4_DOWNLOAD = "a6";

    public static final String DATA_HISTORY = "b1";
    public static final int VERSION_HISTORY = 1;
    public static final String TABLE_HISTORY = "b2";
    public static final String COL1_HISTORY = "b3";
    public static final String COL2_HISTORY = "b4";
    public static final String COL3_HISTORY = "b5";

    public static final String DATA_SEARCH = "d1";
    public static final int VERSION_SEARCH = 1;
    public static final String TABLE_SEARCH = "d2";
    public static final String COL1_SEARCH = "d3";

    public static final String DATA_BOOKMARK = "e1";
    public static final int VERSION_BOOKMARK = 1;
    public static final String TABLE_BOOKMARK = "e2";
    public static final String COL1_BOOKMARK = "e3";
    public static final String COL2_BOOKMARK = "e4";

    public static Sqlite sqlite;

    public static Sqlite getInstance(Context context) {
        if (sqlite == null) {
            sqlite = new Sqlite(context);
        }
        return sqlite;
    }

    protected Sqlite(Context context) {
        super(context, "webvium.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +
                Sqlite.TABLE_BOOKMARK +
                " ( " +
                "_id" +
                " INTEGER PRIMARY KEY, " +
                Sqlite.COL1_BOOKMARK +
                " TEXT, " +
                Sqlite.COL2_BOOKMARK +
                " TEXT ) ");
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
                Sqlite.TABLE_DOWNLOAD);
        db.execSQL("DROP TABLE IF EXISTS " +
                Sqlite.TABLE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " +
                Sqlite.TABLE_SEARCH);
        db.execSQL("DROP TABLE IF EXISTS " +
                Sqlite.TABLE_BOOKMARK);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
