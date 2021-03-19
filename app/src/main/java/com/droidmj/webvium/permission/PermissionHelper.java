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

package com.droidmj.webvium.permission;

// PERMISSIONS DATABASE

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.droidmj.webvium.PDMS;
import com.droidmj.webvium.annotation.ObjectSerializability;
import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.app.WebviumDatabase;

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
            sld.delete(BuildConfiguration.Database.TABLE_PERMISSION, null, null);
        }
    }

    public void b(PermissionObjectDataModel w2) {
        if (sld != null && sld.isOpen()) {
            sld.delete(BuildConfiguration.Database.TABLE_PERMISSION,
                    BuildConfiguration.Database.COL1_PERMISSION +
                            " =? AND " +
                            BuildConfiguration.Database.COL2_PERMISSION +
                            " =? AND " +
                            BuildConfiguration.Database.COL3_PERMISSION +
                            " =? AND " +
                            BuildConfiguration.Database.COL4_PERMISSION +
                            " =? ", new String[]{w2.nm, w2.pm, w2.gt, w2.dnt});
        }
    }

    public void c(PermissionObjectDataModel w7) {
        if (sld != null && sld.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(BuildConfiguration.Database.COL1_PERMISSION, w7.nm);
            values.put(BuildConfiguration.Database.COL2_PERMISSION, w7.pm);
            values.put(BuildConfiguration.Database.COL3_PERMISSION, w7.gt);
            values.put(BuildConfiguration.Database.COL4_PERMISSION, w7.dnt);
            sld.insert(BuildConfiguration.Database.TABLE_PERMISSION, null, values);
        }
    }

    @ObjectSerializability
    public void d(PDMS w7) {
        if (sld != null && sld.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(BuildConfiguration.Database.COL1_PERMISSION, w7.nm);
            values.put(BuildConfiguration.Database.COL2_PERMISSION, w7.pm);
            values.put(BuildConfiguration.Database.COL3_PERMISSION, w7.gt);
            values.put(BuildConfiguration.Database.COL4_PERMISSION, w7.dnt);
            sld.insert(BuildConfiguration.Database.TABLE_PERMISSION, null, values);
        }
    }

    public void i(PermissionObjectDataModel old, PermissionObjectDataModel w7) {
        if (sld != null && sld.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(BuildConfiguration.Database.COL1_PERMISSION, w7.nm);
            values.put(BuildConfiguration.Database.COL2_PERMISSION, w7.pm);
            values.put(BuildConfiguration.Database.COL3_PERMISSION, w7.gt);
            values.put(BuildConfiguration.Database.COL4_PERMISSION, w7.dnt);
            sld.update(BuildConfiguration.Database.TABLE_PERMISSION, values,
                    BuildConfiguration.Database.COL1_PERMISSION +
                            " LIKE ? AND " +
                            BuildConfiguration.Database.COL2_PERMISSION +
                            " LIKE ? AND " +
                            BuildConfiguration.Database.COL3_PERMISSION +
                            " LIKE ? AND " +
                            BuildConfiguration.Database.COL4_PERMISSION +
                            " LIKE ? ", new String[]{old.nm, old.pm, old.gt, old.dnt});
        }
    }
}