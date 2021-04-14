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

package com.mrepol742.webvium.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.mrepol742.webvium.annotation.release.Keep;

public class Debug {

    @Keep
    private Debug() {
    }

    public static int isDebuggable(Context context) {
        return context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE;
    }

    // result if (isDebuggable(this) != 0) {}

}
