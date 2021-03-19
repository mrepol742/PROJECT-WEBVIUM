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

import com.droidmj.webvium.EDIT;
import com.droidmj.webvium.annotation.release.Keep;

public class U3 {
    @Keep
    private U3() {
    }

    public static boolean a(EDIT a) {
        String b = a.getText().toString().trim();
        return !b.isEmpty();
    }

    public static boolean b(String a) {
        String b = a.trim();
        return !b.isEmpty();
    }

    public static boolean c(String a, String b) {
        return a.startsWith(b);
    }

    public static boolean d(String a, String b) {
        return a.endsWith(b);
    }

    public static boolean e(String a, String b) {
        return !a.equals(b);
    }

    public static boolean e(String a, char[] b) {
        return !a.equals(String.valueOf(b));
    }
}