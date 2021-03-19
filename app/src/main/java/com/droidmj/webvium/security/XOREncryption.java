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

public class XOREncryption {
    @Keep
    private XOREncryption() {
    }

    public static String encryptDecrypt(String inputString, char xorKey) {
        StringBuilder outputString = new StringBuilder();
        int len = inputString.length();
        for (int i = 0; i < len; i++) {
            outputString.append((char) (inputString.charAt(i) ^ xorKey));
        }
        return outputString.toString();
    }
}
