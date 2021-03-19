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

package com.droidmj.webvium.util;

import com.droidmj.webvium.annotation.release.Keep;

import java.util.Arrays;

@Deprecated
public class Base64 {
    @Keep
    private Base64() {
    }

    @Deprecated
    public static String a(String a) {
        byte[] b = d(a.replaceAll(" ", "").getBytes(), android.util.Base64.DEFAULT);
        return new String(b);
    }

    @Deprecated
    public static String a(char[] a) {
        byte[] b = d(Arrays.toString(a).replaceAll(" ", "").getBytes(), android.util.Base64.DEFAULT);
        return new String(b);
    }

    @Deprecated
    public static String b(String a) {
        byte[] b = e(a.getBytes());
        return new String(b);
    }

    @Deprecated
    public static String c(String a) {
        byte[] b = d(a.trim().getBytes(), android.util.Base64.URL_SAFE);
        return new String(b);
    }

    @Deprecated
    private static byte[] d(byte[] input, int flags) {
        return android.util.Base64.decode(input, 0, input.length, flags);
    }

    @Deprecated
    private static byte[] e(byte[] input) {
        return android.util.Base64.encode(input, 0, input.length, android.util.Base64.DEFAULT);
    }


}