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

import java.util.Date;

public class Long {

    @Keep
    private Long() {

    }

    public static Date toDate(long lg) {
        return new Date(lg * 100);
    }
}
