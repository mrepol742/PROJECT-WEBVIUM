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

package com.mrepol742.webvium.download;

// HISTORY DATABASE

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mrepol742.webvium.DDMS;
import com.mrepol742.webvium.Sqlite;
import com.mrepol742.webvium.app.WebviumDatabase;

public class DownloadHelper implements WebviumDatabase {

    private static DownloadHelper d10;
    private final SQLiteDatabase sld;

    private DownloadHelper(Context ct) {
        DownloadDatabase d9 = new DownloadDatabase(ct);
        sld = d9.getWritableDatabase();
    }

    public static DownloadHelper getInstance(Context ctx) {
        if (d10 == null) {
            d10 = new DownloadHelper(ctx);
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
            sld.delete(Sqlite.TABLE_DOWNLOAD, null, null);
        }
    }

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
                            w2.c,
                            Long.toString(w2.d)});
        }
    }

    public void c(String a, String b, String c) {
        if (sld != null && sld.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(Sqlite.COL1_DOWNLOAD, a);
            values.put(Sqlite.COL2_DOWNLOAD, b);
            values.put(Sqlite.COL3_DOWNLOAD, c);
            values.put(Sqlite.COL4_DOWNLOAD, System.currentTimeMillis());
            sld.insert(Sqlite.TABLE_DOWNLOAD, null, values);
        }
    }

    public void d(DDMS w7) {
        if (sld != null && sld.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(Sqlite.COL1_DOWNLOAD, w7.a);
            values.put(Sqlite.COL2_DOWNLOAD, w7.b);
            values.put(Sqlite.COL3_DOWNLOAD, w7.c);
            values.put(Sqlite.COL4_DOWNLOAD, w7.d);
            sld.insert(Sqlite.TABLE_DOWNLOAD, null, values);
        }
    }

    public void i(String oldTitle, String oldURl, String oldSize, long oldTIme, String newTitle, String newUrl) {
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
                            " LIKE ?", new String[]{oldTitle, oldURl, oldSize, Long.toString(oldTIme)});
        }

    }

}