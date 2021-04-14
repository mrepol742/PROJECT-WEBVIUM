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

package com.mrepol742.webvium.util;

import com.mrepol742.webvium.annotation.release.Keep;

import java.util.Arrays;

public class U5 {
    @Keep
    private U5() {
    }

    public static String a(String sg) {
        for (String sg5 : Inapproriate.inapproriateData) {
            if (sg.toLowerCase().trim().contains(sg5)) {
                StringBuilder sb = new StringBuilder();
                int length = sg5.length();
                for (int i = 0; i < length; i++) {
                    sb.append("*");
                }
                return a(sg.replaceAll(sg5, sb.toString()));
            }
        }
        return sg;
    }

    public static int b(String sg, char r) {
        return sg.length() - sg.replace(Character.toString(r), "").length();
    }

    public static String c(char[] str, int n) {
        int index = 0;
        for (int i = 0; i < n; i++) {
            int j;
            for (j = 0; j < i; j++) {
                if (str[i] == str[j]) {
                    break;
                }
            }
            if (j == i) {
                str[index++] = str[i];
            }
        }
        return String.valueOf(Arrays.copyOf(str, index));
    }

}
