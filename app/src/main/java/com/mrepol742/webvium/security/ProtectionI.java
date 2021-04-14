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

package com.mrepol742.webvium.security;

import android.content.Context;

import com.mrepol742.webvium.annotation.Beta;
import com.mrepol742.webvium.annotation.release.Keep;
import com.mrepol742.webvium.app.BuildConfiguration;
import com.mrepol742.webvium.util.Debug;

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
