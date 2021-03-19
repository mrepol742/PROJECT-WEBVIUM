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

package com.droidmj.webvium;

import android.content.Context;
import android.os.Build;
import android.preference.Preference;
import android.util.AttributeSet;

import com.droidmj.webvium.annotation.release.Keep;

// @Class Pref
public class PREF extends Preference {
    @Keep
    public PREF(Context context) {
        super(context);
        a();
    }

    @Keep
    public PREF(Context context, AttributeSet attrs) {
        super(context, attrs);
        a();
    }

    @Keep
    public PREF(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        a();
    }

    protected void a() {
        if (Build.VERSION.SDK_INT < 26) {
            setIcon(R.drawable.t);
        } else {
            setIconSpaceReserved(true);
        }
    }

}
