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

package com.mrepol742.webvium.bookmark;

// BOOKMARK DATABASE

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mrepol742.webvium.BDMS;
import com.mrepol742.webvium.Sqlite;
import com.mrepol742.webvium.app.WebviumDatabase;

public class BookmarkHelper implements WebviumDatabase {

    private static BookmarkHelper d3;
    private final SQLiteDatabase sld;

    private BookmarkHelper(Context ct) {
        BookmarkDatabase d8 = new BookmarkDatabase(ct);
        sld = d8.getWritableDatabase();
    }

    public static BookmarkHelper getInstance(Context ctx) {
        if (d3 == null) {
            d3 = new BookmarkHelper(ctx);
        }
        return d3;
    }

    public void b(final String a, final String b) {
        if (sld != null && sld.isOpen()) {
            sld.delete(Sqlite.TABLE_BOOKMARK,
                    Sqlite.COL1_BOOKMARK +
                            "=? AND " +
                            Sqlite.COL2_BOOKMARK +
                            "=? ", new String[]{b, a});
        }
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
            sld.delete(Sqlite.TABLE_BOOKMARK, null, null);
        }
    }

    public void c(final String a, final String b) {
        if (sld != null && sld.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(Sqlite.COL1_BOOKMARK, a);
            values.put(Sqlite.COL2_BOOKMARK, b);
            sld.insert(Sqlite.TABLE_BOOKMARK, null, values);
        }
    }

    public void d(BDMS a) {
        if (sld != null && sld.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(Sqlite.COL1_BOOKMARK, a.sg);
            values.put(Sqlite.COL2_BOOKMARK, a.sg0);
            sld.insert(Sqlite.TABLE_BOOKMARK, null, values);
        }
    }

    public void f(final String oldTitle, final String oldURl, final String newTitle, final String newUrl) {
        if (sld != null && sld.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(Sqlite.COL1_BOOKMARK, newTitle);
            values.put(Sqlite.COL2_BOOKMARK, newUrl);
            sld.update(Sqlite.TABLE_BOOKMARK, values,
                    Sqlite.COL1_BOOKMARK +
                            " LIKE ? AND " +
                            Sqlite.COL2_BOOKMARK +
                            " LIKE ?", new String[]{oldTitle, oldURl});
        }
    }
}