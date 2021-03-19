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

package com.droidmj.webvium.util.cache;

import android.content.Context;
import android.graphics.Typeface;
import android.util.LruCache;

import com.droidmj.webvium.app.BuildConfiguration;

public class FontCache {
    public static FontCache U7;
    public LruCache<Integer, Typeface> cac;
    private Typeface main;

    private FontCache(Context ct) {
        if (cac == null) {
            cac = new LruCache<>(32);
            main = Typeface.createFromAsset(ct.getAssets(), BuildConfiguration.Font.MAVEN_PRO);
        }
    }

    public static FontCache getInstance(Context ct) {
        if (U7 == null) {
            U7 = new FontCache(ct);
        }
        return U7;
    }

    public Typeface a(int sg) {
        Typeface bp = cac.get(sg);
        if (bp == null) {
            bp = Typeface.create(main, sg);
            cac.put(sg, bp);
        }
        return bp;
    }

}