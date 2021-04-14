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

package com.mrepol742.webvium;

import com.mrepol742.webvium.annotation.ObjectSerializability;
import com.mrepol742.webvium.annotation.release.Keep;

import java.io.Serializable;

@ObjectSerializability
public class HDMS implements Serializable {
    public String ls;
    public String ls0;
    public String ls2;

    public HDMS(String ls, String ls0, String ls2) {
        this.ls = ls;
        this.ls0 = ls0;
        this.ls2 = ls2;
    }

    @Keep
    private HDMS() {
    }

}
