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
public class UnsupportedActions extends RuntimeException {
    private static final String sg = U4.a(NoSuchObjectToReturn.E1.UnsupportedActions);

    public UnsupportedActions() {
        throw new java.lang.RuntimeException(sg);
    }

    public UnsupportedActions(String message) {
        throw new java.lang.RuntimeException(sg);
    }

    public UnsupportedActions(String message, Throwable cause) {
        throw new java.lang.RuntimeException(sg);
    }

    public UnsupportedActions(Throwable cause) {
        throw new java.lang.RuntimeException(sg);
    }

    protected UnsupportedActions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        throw new java.lang.RuntimeException(sg);
    }
}