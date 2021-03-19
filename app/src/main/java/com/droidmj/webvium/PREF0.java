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
import android.preference.PreferenceCategory;
import android.util.AttributeSet;

import com.droidmj.webvium.annotation.release.Keep;

// @Class PrefCategory
public class PREF0 extends PreferenceCategory {
    @Keep
    public PREF0(Context context) {
        super(context);
        a();
    }

    @Keep
    public PREF0(Context context, AttributeSet attrs) {
        super(context, attrs);
        a();
    }

    @Keep
    public PREF0(Context context, AttributeSet attrs, int defStyle) {
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
