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

package com.mrepol742.webvium.security.against;

import com.mrepol742.webvium.annotation.release.Keep;

public class AntiProtectionII {

    @Keep
    private AntiProtectionII() {
    }

    // TODO
    // get the object objectHash from where it come from this will be use to check if the protectionII
    // was been tampered since the signature that get converted to bytearray wasnt come from
    // package info hence, string. by detecting if it from string or packafe info we can determined
    // if the protectionII was been infiltrated.
    public static int scanning(String sg) {

        return 0;
    }
}
