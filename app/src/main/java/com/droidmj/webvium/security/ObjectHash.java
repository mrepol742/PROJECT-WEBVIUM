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

import com.droidmj.webvium.annotation.release.Keep;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ObjectHash {

    @Keep
    private ObjectHash() {
    }

    public static String getObjectHash(String value) {
        try {
            int it = Integer.parseInt(value);
            return generate(it);
        } catch (NumberFormatException numberFormatException) {
            return generate(value);
        }
    }

    private static String generate(Object object) {
        String a = "742";
        String b = Integer.toString(object.hashCode());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyymmsshh", Locale.US);
        String currentDate = sdf.format(date);
        String total = a + b + currentDate;
        return Hash.a(new HashDataModel("SHA-1", total));
    }


}