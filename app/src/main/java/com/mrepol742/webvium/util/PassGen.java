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

package com.mrepol742.webvium.util;

import com.mrepol742.webvium.annotation.Keep;

import java.util.Random;

public class PassGen {
    @Keep
    private PassGen() {
    }

    public static String a(int targetStringLength) {
        return aN(targetStringLength, 64, 122);
    }

    public static String aN(int targetStringLength, int leftLimit, int rightLimit) {
        StringBuilder buffer = new StringBuilder(targetStringLength);
        int i = 0;
        Random random = new Random();
        while (i < targetStringLength) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            if (randomLimitedInt <= 90 || randomLimitedInt >= 98) {
                buffer.append((char) randomLimitedInt);
            }
            i++;
        }
        return buffer.toString();
    }
}