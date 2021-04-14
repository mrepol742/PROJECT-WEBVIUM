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

import java.io.Serializable;

@ObjectSerializability
public class BDMS implements Serializable {
    public final String sg;
    public final String sg0;

    public BDMS(String sg, String sg0) {
        this.sg = sg;
        this.sg0 = sg0;
    }


}
