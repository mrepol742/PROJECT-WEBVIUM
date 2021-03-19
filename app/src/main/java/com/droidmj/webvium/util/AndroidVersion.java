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

import com.droidmj.webvium.annotation.Test;
import com.droidmj.webvium.annotation.release.Keep;
import com.droidmj.webvium.app.NoSuchStringToReturn;

@Test
public class AndroidVersion {
    @Keep
    private AndroidVersion() {}

    public static String getVersionName(int it) {
        if (it >= 21) {
            if (it >= 23) {
                if (it >= 24) {
                    if (it >= 26) {
                        if (it >= 28) {
                            if (it >= 29) {
                                if (it >= 30) {
                                    if (it >= 31) {
                                        return Integer.toString(it);
                                    }
                                    return "R";
                                }
                                return "Q";
                            }
                            return "Pie";
                        }
                        return "Oreo";
                    }
                    return "Nougat";
                }
                return "Marshmallow";
            }
            return "Lollipop";
        }
        throw new NoSuchStringToReturn();
    }

}
