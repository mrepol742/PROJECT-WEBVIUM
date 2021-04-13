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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    @Keep
    private Regex() {}

    public static boolean containsSpecialChar(String string) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(string);
        return m.find();
    }

}
