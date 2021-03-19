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

package com.droidmj.webvium.util;

import com.droidmj.webvium.annotation.release.Keep;

import java.util.Random;

public class U1 {
    @Keep
    private U1() {
    }

    public static String a(int targetStringLength) {
        int leftLimit = 65;
        int rightLimit = 122;
        StringBuilder buffer = new StringBuilder(targetStringLength);
        int i = 0;
        Random random = new Random();
        while (i < targetStringLength) {
            int randomLimitedInt = leftLimit + (int) (random.nextInt() * (rightLimit - leftLimit + 1));
            if (randomLimitedInt <= 90 || randomLimitedInt >= 98) {
                buffer.append((char) randomLimitedInt);
            }
            i++;
        }
        return buffer.toString();
    }

    // Caesar cipher decrypt and encrypt
    public static String b(String text, int s) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) {
                char ch = (char) (((int) text.charAt(i) + s - 65) % 26 + 65);
                result.append(ch);
            } else {
                char ch = (char) (((int) text.charAt(i) + s - 97) % 26 + 97);
                result.append(ch);
            }
        }
        return result.toString();
    }
}