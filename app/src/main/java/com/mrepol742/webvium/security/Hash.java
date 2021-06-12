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
import com.mrepol742.webvium.app.NoSuchStringToReturn;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.security.MessageDigest.getInstance;

public class Hash {
    @Keep
    private Hash() {
    }

    public static String a(String a, String b) {
        return a(new HashDataModel(a, b));
    }

    public static String a(HashDataModel w1) {
        try {
            MessageDigest md = getInstance(w1.sg);
            md.update(w1.e);
            byte[] bytes = md.digest(w1.sg1.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte be2 : bytes) {
                sb.append(Integer.toString((be2 & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ne) {
            ne.printStackTrace();
        }
        throw new NoSuchStringToReturn();
    }

}