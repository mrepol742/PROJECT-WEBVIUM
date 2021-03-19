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

package com.droidmj.webvium.net;

import com.droidmj.webvium.annotation.release.Keep;

public class IPAddress {
    @Keep

    private IPAddress() {
    }

    public static boolean isValidIpAddress(String ip) {
        if (!ip.contains(".")) {
            return false;
        }
        String[] dots = ip.split("\\.");
        int count = 0;
        for (String dot : dots) {
            if (dot.contains(".")) {
                count += 1;
            }
        }
        if (count != 3) {
            return false;
        }
        try {
            int line0 = parseInt(dots, 0);
            int line1 = parseInt(dots, 1);
            int line2 = parseInt(dots, 2);
            int line3 = parseInt(dots, 3);
            int maxN = 255;
            return (line0 > 0 || line1 > 1 || line2 > 0 || line3 > 0) && (line0 < maxN || line1 < maxN || line2 < maxN || line3 < maxN);
        } catch (NumberFormatException ignored) {

        }
        return false;
    }

    private static int parseInt(String[] dots, int line) throws NumberFormatException {
        return Integer.parseInt(dots[line].replaceAll(" ", "").replaceAll(".", ""));
    }

}
