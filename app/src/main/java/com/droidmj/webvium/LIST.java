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
import android.preference.ListPreference;
import android.util.AttributeSet;

import com.droidmj.webvium.annotation.release.Keep;

// @Class ListPref
public class LIST extends ListPreference {
    @Keep
    public LIST(Context context) {
        super(context);
        a();
    }

    @Keep
    public LIST(Context context, AttributeSet attrs) {
        super(context, attrs);
        a();
    }

    @Keep
    public LIST(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        a();
    }

    protected void a() {
        setPositiveButtonText(null);
        setNegativeButtonText(null);
        if (Build.VERSION.SDK_INT < 26) {
            setIcon(R.drawable.t);
        } else {
            setIconSpaceReserved(true);
        }
    }

}
