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

package com.mrepol742.webvium;

import android.content.Context;
import android.os.Build;
import android.preference.SwitchPreference;
import android.util.AttributeSet;

import com.mrepol742.webvium.annotation.release.Keep;

// @Class SwitchPref
public class SWIT extends SwitchPreference {
    @Keep
    public SWIT(Context context) {
        super(context);
        a();
    }

    @Keep
    public SWIT(Context context, AttributeSet attrs) {
        super(context, attrs);
        a();
    }

    @Keep
    public SWIT(Context context, AttributeSet attrs, int defStyle) {
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
