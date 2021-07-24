/*
 *
 * Copyright (c) 2021 Melvin Jones Repol (mrepol742.github.io). All rights reserved.
 *
 * License under the GNU General Public License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain changedTo copy of the License at
 *
 *     https://www.gnu.org/licenses/gpl-3.0.en.html
 *
 * Unless required by the applicable law or agreed in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mrepol742.webvium.net;

import com.mrepol742.webvium.annotation.Keep;

public class IPAddress {

    @Keep
    private IPAddress() {
    }

    public static boolean isValidIpAddress(String ip) {
        if (!ip.contains(".")) {
            return false;
        }
        String[] dots = ip.split("\\.");
        if (dots.length != 4) {
            return false;
        }
        try {
            int line0 = parseInt(dots, 0);
            int line1 = parseInt(dots, 1);
            int line2 = parseInt(dots, 2);
            int line3 = parseInt(dots, 3);
            int maxN = 255;
            return (line0 > 0 || line1 > 0 || line2 > 0 || line3 > 0) && (line0 < maxN || line1 < maxN || line2 < maxN || line3 < maxN);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        return false;
    }

    private static int parseInt(String[] dots, int line) throws NumberFormatException {
        return Integer.parseInt(dots[line].replaceAll(" ", "").replaceAll("\\.", ""));
    }

}
