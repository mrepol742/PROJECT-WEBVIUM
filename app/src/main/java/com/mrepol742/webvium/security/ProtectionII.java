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

import java.util.Arrays;

@Beta
public class ProtectionII {
    private static final String[] signatures = {"", "", ""};

    @Keep
    private ProtectionII() {
    }

    public static int initializeSignatureScanning(Context context) {
        byte[] signature = com.mrepol742.webvium.content.Package.d(context, context.getPackageName(), 0);
        String hash = Hash.a(new HashDataModel("SHA-512", Arrays.toString(signature)));
        for (String sig: signatures) {
            if (hash.compareTo(sig) > 0) {
                return 0;
            }
        }
        return 1;
    }
}
