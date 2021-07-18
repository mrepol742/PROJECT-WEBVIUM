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
import android.net.Uri;
import android.webkit.URLUtil;

import com.mrepol742.webvium.DDMS;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.app.Sqlite;
import com.mrepol742.webvium.app.WebviumDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
                            " =? AND " +
                            Sqlite.COL5_DOWNLOAD +
                            " =? ", new String[]{w2.b,
                            w2.a,
                            Integer.toString(w2.c),
                            w2.d,
                            w2.e});
        }
    }

    public void c(DownloadNewDataModel downloadNewDataModel) {
        if (sld != null && sld.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(Sqlite.COL1_DOWNLOAD, h(downloadNewDataModel.a));
            values.put(Sqlite.COL2_DOWNLOAD, downloadNewDataModel.b);
            values.put(Sqlite.COL3_DOWNLOAD, downloadNewDataModel.s);
            values.put(Sqlite.COL4_DOWNLOAD, f(downloadNewDataModel.d));
            values.put(Sqlite.COL5_DOWNLOAD, g());
            sld.insert(Sqlite.TABLE_DOWNLOAD, null, values);
        }
    }

    public void d(DDMS w7) {
        if (sld != null && sld.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(Sqlite.COL1_DOWNLOAD, w7.b);
            values.put(Sqlite.COL2_DOWNLOAD, w7.d);
            values.put(Sqlite.COL3_DOWNLOAD, w7.f);
            values.put(Sqlite.COL4_DOWNLOAD, w7.g);
            values.put(Sqlite.COL5_DOWNLOAD, w7.e4);
            sld.insert(Sqlite.TABLE_DOWNLOAD, null, values);
        }
    }

    private int f(String b) {
        if (b.startsWith("https://")) {
            return R.drawable.a15;
        } else if (b.startsWith("http://")) {
            return R.drawable.a16;
        } else if (b.startsWith("file://") || b.startsWith("content://")) {
            return R.drawable.a17;
        }
        return R.drawable.a8;
    }

    private String g() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyy | HHmm", Locale.US);
        return sdf.format(new Date());
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

    public void i(DownloadOldDataModel downloadOldDataModel, DownloadNewDataModel downloadNewDataModel) {
        if (sld != null && sld.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(Sqlite.COL1_DOWNLOAD, h(downloadNewDataModel.a));
            values.put(Sqlite.COL2_DOWNLOAD, downloadNewDataModel.b);
            values.put(Sqlite.COL3_DOWNLOAD, downloadNewDataModel.s);
            values.put(Sqlite.COL4_DOWNLOAD, f(downloadNewDataModel.d));
            values.put(Sqlite.COL5_DOWNLOAD, downloadOldDataModel.oldTime);
            sld.update(Sqlite.TABLE_DOWNLOAD, values,
                    Sqlite.COL1_DOWNLOAD +
                            " LIKE ? AND " +
                            Sqlite.COL2_DOWNLOAD +
                            " LIKE ? AND " +
                            Sqlite.COL3_DOWNLOAD +
                            " LIKE ? AND " +
                            Sqlite.COL4_DOWNLOAD +
                            " LIKE ? AND " +
                            Sqlite.COL5_DOWNLOAD +
                            " LIKE ? ", new String[]{downloadOldDataModel.oldTitle, downloadOldDataModel.oldUrl, String.valueOf(downloadOldDataModel.oldDrawable), downloadOldDataModel.oldTime, downloadOldDataModel.oldSize});
        }

    }

}