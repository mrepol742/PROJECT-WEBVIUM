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

import java.io.Serializable;

@ObjectSerializability
public class PDMS implements Serializable {
    public final String nm;
    public final String pm;
    public final String gt;
    public final String dnt;

    public PDMS(String nm, String pm, String gt, String dnt) {
        this.nm = nm;
        this.pm = pm;
        this.gt = gt;
        this.dnt = dnt;
    }
}
