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

package com.mrepol742.webvium.app;

import com.mrepol742.webvium.annotation.release.Keep;
import com.mrepol742.webvium.util.U4;

@Keep
public class NoSuchObjectToReturn extends RuntimeException {
    private static final String sg = U4.a(E1.NoSuchObjectToReturn);

    public NoSuchObjectToReturn() {
        throw new java.lang.RuntimeException(sg);
    }

    public NoSuchObjectToReturn(String message) {
        throw new java.lang.RuntimeException(sg);
    }

    public NoSuchObjectToReturn(String message, Throwable cause) {
        throw new java.lang.RuntimeException(sg);
    }

    public NoSuchObjectToReturn(Throwable cause) {
        throw new java.lang.RuntimeException(sg);
    }

    protected NoSuchObjectToReturn(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        throw new java.lang.RuntimeException(sg);
    }

    protected enum E1 {
        NoSuchObjectToReturn,
        NoSuchStringToReturn, NoSuchSpannableStringBuilderToReturn, NoSuchItemToGet, UnsupportedActions;

        @Keep
        E1() {

        }
    }
}