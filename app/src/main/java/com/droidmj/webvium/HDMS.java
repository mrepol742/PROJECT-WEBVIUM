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

package com.droidmj.webvium;

import com.droidmj.webvium.annotation.ObjectSerializability;
import com.droidmj.webvium.annotation.release.Keep;

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
