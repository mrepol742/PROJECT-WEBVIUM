/*
 *
 *
 *
 * DROID MJ Property || Confidential
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.droidmj.webvium.bookmark;

// BOOKMARK DATABASE

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.droidmj.webvium.BDMS;
import com.droidmj.webvium.annotation.ObjectSerializability;
import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.app.WebviumDatabase;

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
            sld.delete(BookmarkDatabase.TABLE_BOOKMARK,
                    BookmarkDatabase.COL1_BOOKMARK +
                            "=? AND " +
                            BookmarkDatabase.COL2_BOOKMARK +
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
            sld.delete(BookmarkDatabase.TABLE_BOOKMARK, null, null);
        }
    }

    public void c(final String a, final String b) {
        if (sld != null && sld.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(BookmarkDatabase.COL1_BOOKMARK, a);
            values.put(BookmarkDatabase.COL2_BOOKMARK, b);
            sld.insert(BookmarkDatabase.TABLE_BOOKMARK, null, values);
        }
    }

    @ObjectSerializability
    public void d(BDMS a) {
        if (sld != null && sld.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(BookmarkDatabase.COL1_BOOKMARK, a.sg);
            values.put(BookmarkDatabase.COL2_BOOKMARK, a.sg0);
            sld.insert(BookmarkDatabase.TABLE_BOOKMARK, null, values);
        }
    }

    public void f(final String oldTitle, final String oldURl, final String newTitle, final String newUrl) {
        if (sld != null && sld.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(BookmarkDatabase.COL1_BOOKMARK, newTitle);
            values.put(BookmarkDatabase.COL2_BOOKMARK, newUrl);
            sld.update(BookmarkDatabase.TABLE_BOOKMARK, values,
                    BookmarkDatabase.COL1_BOOKMARK +
                            " LIKE ? AND " +
                            BookmarkDatabase.COL2_BOOKMARK +
                            " LIKE ?", new String[]{oldTitle, oldURl});
        }
    }
}