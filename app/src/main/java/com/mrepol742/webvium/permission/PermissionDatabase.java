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

package com.mrepol742.webvium.permission;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mrepol742.webvium.app.Sqlite;

@Deprecated
public class PermissionDatabase extends SQLiteOpenHelper {

    @Deprecated
    public PermissionDatabase(Context context) {
        super(context, Sqlite.DATA_PERMISSION, null, Sqlite.VERSION_PERMISSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +
                Sqlite.TABLE_PERMISSION +
                " ( " +
                "_id" +
                " INTEGER PRIMARY KEY, " +
                Sqlite.COL1_PERMISSION +
                " TEXT, " +
                Sqlite.COL2_PERMISSION +
                " TEXT, " +
                Sqlite.COL3_PERMISSION +
                " TEXT, " +
                Sqlite.COL4_PERMISSION +
                " TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +
                Sqlite.TABLE_PERMISSION);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
