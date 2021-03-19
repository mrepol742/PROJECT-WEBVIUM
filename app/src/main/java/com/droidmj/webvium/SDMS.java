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
public class SDMS implements Serializable {
    public String sg;

    public SDMS(String sg) {
        this.sg = sg;
    }
}
