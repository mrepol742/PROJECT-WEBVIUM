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

import com.mrepol742.webvium.annotation.release.Keep;

import java.util.Arrays;

public class Base64 {
    @Keep
    private Base64() {
    }

    public static String decode(String a) {
        return new String(formatDecode(a.replaceAll(" ", "").getBytes(), android.util.Base64.DEFAULT));
    }

    public static String decode(char[] a) {
        return new String(formatDecode(Arrays.toString(a).replaceAll(" ", "").getBytes(), android.util.Base64.DEFAULT));
    }

    public static String encode(String a) {
        return new String(formatEncode(a.getBytes()));
    }

    public static String c(String a) {
        return new String(formatDecode(a.trim().getBytes(), android.util.Base64.URL_SAFE));
    }

    private static byte[] formatDecode(byte[] input, int flags) {
        return android.util.Base64.decode(input, 0, input.length, flags);
    }

    private static byte[] formatEncode(byte[] input) {
        return android.util.Base64.encode(input, 0, input.length, android.util.Base64.DEFAULT);
    }


}