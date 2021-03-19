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

package com.droidmj.webvium.security;

import com.droidmj.webvium.annotation.release.Keep;
import com.droidmj.webvium.app.NoSuchStringToReturn;
import com.droidmj.webvium.telemetry.DiagnosticData;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.security.MessageDigest.getInstance;

public class Hash {
    @Keep
    private Hash() {
    }

    public static String a(String a, String b) {
        return a(new HashDataModel(a, b));
    }

    public static String a(HashDataModel w1) {
        try {
            MessageDigest md = getInstance(w1.sg);
            md.update(w1.e);
            byte[] bytes = md.digest(w1.sg1.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte be2 : bytes) {
                sb.append(Integer.toString((be2 & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ne) {
            DiagnosticData.a(ne);
        }
        throw new NoSuchStringToReturn();
    }

}