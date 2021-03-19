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

package com.droidmj.webvium.app;

import android.database.sqlite.SQLiteDatabase;

public interface WebviumDatabase {
    SQLiteDatabase getReadableDatabase();

    void finish();

    void delete();
}
