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

package com.mrepol742.webvium.search;

// SEARCH DATABASE

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;

import com.mrepol742.webvium.app.WebviumDatabase;
import com.mrepol742.webvium.util.Inapproriate;

public class SearchHelper implements WebviumDatabase {

    private static SearchHelper d2;
    private final SharedPreferences sp;
    private final SQLiteDatabase sld;
    private static String temp;

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
        if (!sp.getBoolean("pSearch", false) && !temp.equals(a)) {
            if (sld != null && sld.isOpen()) {
                if (Inapproriate.isInapproriate(a.toLowerCase())) {
                    temp = a;
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
