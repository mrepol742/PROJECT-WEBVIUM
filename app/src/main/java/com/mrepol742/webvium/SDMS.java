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
public class SDMS implements Serializable {
    public String sg;

    public SDMS(String sg) {
        this.sg = sg;
    }
}
