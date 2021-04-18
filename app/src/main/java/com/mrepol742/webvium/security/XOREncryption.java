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

package com.mrepol742.webvium.security;

import com.mrepol742.webvium.annotation.release.Keep;

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
