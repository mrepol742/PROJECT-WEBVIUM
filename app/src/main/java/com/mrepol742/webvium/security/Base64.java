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

import com.mrepol742.webvium.annotation.Keep;

public class Base64 {

    @Keep
    private Base64() {
    }

    public static String decode(String a) {
        return new String(formatDecode(a.trim().getBytes()));
    }

    public static String encode(String a) {
        return new String(formatEncode(a.getBytes()));
    }

    private static byte[] formatDecode(byte[] input) {
        return android.util.Base64.decode(input, 0, input.length, android.util.Base64.DEFAULT);
    }

    private static byte[] formatEncode(byte[] input) {
        return android.util.Base64.encode(input, 0, input.length, android.util.Base64.DEFAULT);
    }


}