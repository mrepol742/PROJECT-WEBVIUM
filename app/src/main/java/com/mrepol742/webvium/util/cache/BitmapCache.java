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

package com.mrepol742.webvium.util.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

public class BitmapCache {
    private static BitmapCache U6;
    private LruCache<String, Bitmap> cac;

    private BitmapCache() {
        if (cac == null) {
            cac = new LruCache<>(32);
        }
    }

    public static BitmapCache getInstance() {
        if (U6 == null) {
            U6 = new BitmapCache();
        }
        return U6;
    }

    public Bitmap a(String sg) {
        Bitmap bp = cac.get(sg);
        if (bp == null) {
            bp = BitmapFactory.decodeFile(sg);
            cac.put(sg, bp);
        }
        return bp;
    }

    public void b(String sg) {
        cac.put(sg, BitmapFactory.decodeFile(sg));
    }

}
