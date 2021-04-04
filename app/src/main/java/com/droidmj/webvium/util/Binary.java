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

public class Binary {

    @Keep
    private Binary() {

    }

    public static String toString(String var) {
        return Character.toString((char) Integer.parseInt(var, 2));
    }

    public static String toString(int var) {
        return toString(Integer.toString(var));
    }


}
