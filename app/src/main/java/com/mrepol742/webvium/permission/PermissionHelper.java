/*
 *
 * Copyright (c) 2021 Melvin Jones Repol (mrepol742.github.io). All rights reserved.
 *
 * License under the GNU General Public License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
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

// PERMISSIONS DATABASE

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mrepol742.webvium.PDMS;
import com.mrepol742.webvium.app.Sqlite;
import com.mrepol742.webvium.app.WebviumDatabase;
import com.mrepol742.webvium.security.Hash;

public class PermissionHelper implements WebviumDatabase {

    private static PermissionHelper d10;
    private final SQLiteDatabase sld;

    private PermissionHelper(Context ct) {
        PermissionDatabase d9 = new PermissionDatabase(ct);
        sld = d9.getWritableDatabase();
    }

    public static PermissionHelper getInstance(Context ctx) {
        if (d10 == null) {
            d10 = new PermissionHelper(ctx);
        }
        return d10;
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
            sld.delete(Sqlite.TABLE_PERMISSION, null, null);
        }
    }

    public void b(PermissionObjectDataModel w2) {
        if (sld != null && sld.isOpen()) {
            sld.delete(Sqlite.TABLE_PERMISSION,
                    Sqlite.COL1_PERMISSION +
                            " =? AND " +
                            Sqlite.COL2_PERMISSION +
                            " =? AND " +
                            Sqlite.COL3_PERMISSION +
                            " =? AND " +
                            Sqlite.COL4_PERMISSION +
                            " =? ", new String[]{Hash.a("SHA-1", w2.nm), Hash.a("SHA-1", w2.pm), w2.gt, w2.dnt});
        }
    }

    public void c(PermissionObjectDataModel w7) {
        if (sld != null && sld.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(Sqlite.COL1_PERMISSION, Hash.a("SHA-1", w7.nm));
            values.put(Sqlite.COL2_PERMISSION, Hash.a("SHA-1", w7.pm));
            values.put(Sqlite.COL3_PERMISSION, w7.gt);
            values.put(Sqlite.COL4_PERMISSION, w7.dnt);
            sld.insert(Sqlite.TABLE_PERMISSION, null, values);
        }
    }

    public void d(PDMS w7) {
        if (sld != null && sld.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(Sqlite.COL1_PERMISSION, w7.nm);
            values.put(Sqlite.COL2_PERMISSION, w7.pm);
            values.put(Sqlite.COL3_PERMISSION, w7.gt);
            values.put(Sqlite.COL4_PERMISSION, w7.dnt);
            sld.insert(Sqlite.TABLE_PERMISSION, null, values);
        }
    }

    public void i(PermissionObjectDataModel old, PermissionObjectDataModel w7) {
        if (sld != null && sld.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(Sqlite.COL1_PERMISSION, Hash.a("SHA-1", w7.nm));
            values.put(Sqlite.COL2_PERMISSION, Hash.a("SHA-1", w7.pm));
            values.put(Sqlite.COL3_PERMISSION, w7.gt);
            values.put(Sqlite.COL4_PERMISSION, w7.dnt);
            sld.update(Sqlite.TABLE_PERMISSION, values,
                    Sqlite.COL1_PERMISSION +
                            " LIKE ? AND " +
                            Sqlite.COL2_PERMISSION +
                            " LIKE ? AND " +
                            Sqlite.COL3_PERMISSION +
                            " LIKE ? AND " +
                            Sqlite.COL4_PERMISSION +
                            " LIKE ? ", new String[]{Hash.a("SHA-1", old.nm), Hash.a("SHA-1", old.pm), old.gt, old.dnt});
        }
    }
}