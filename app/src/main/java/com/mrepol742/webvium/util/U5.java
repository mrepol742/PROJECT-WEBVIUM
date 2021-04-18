/*
 *
 * Copyright (c) 2021 Melvin Jones Repol (mrepol742.github.io). All rights reserved.
 *
 * License under the GNU General Public License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
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
