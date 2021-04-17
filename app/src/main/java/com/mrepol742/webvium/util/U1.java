/*
 *
 * Created by Melvin Jones Repol on 4/17/21 10:27 AM
 * Copyright (c) 2021 . All rights reserved. Melvin Jones Repol(mrepol742.github.io)
 * Last modified 4/17/21 10:26 AM
 *
 *  License under the GNU General Public License, Version 3.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *  https://www.gnu.org/licenses/gpl-3.0.en.html
 *  Unless required by the applicable law or agreed in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.mrepol742.webvium.util;

import com.mrepol742.webvium.annotation.release.Keep;

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
        int length = text.length();
        for (int i = 0; i < length; i++) {
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