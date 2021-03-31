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

package com.droidmj.webvium.security;

import android.content.Context;

import com.droidmj.webvium.annotation.Beta;
import com.droidmj.webvium.annotation.release.Keep;
import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.util.Debug;

@Beta
public class ProtectionI {

    @Keep
    private ProtectionI() {
    }

    public static int initializeProtectionScanning(Context context) {
        if (Debug.isDebuggable(context) != 0) {
            if (BuildConfiguration.isDevelopment) {
                return 0;
            } else {
                return 1;
            }
        }

        return 0;
    }
}
