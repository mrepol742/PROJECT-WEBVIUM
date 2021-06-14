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

import android.os.Build;

import com.mrepol742.webvium.annotation.Keep;
import com.mrepol742.webvium.app.NoSuchStringToReturn;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static java.security.MessageDigest.getInstance;

public class Hash {
    private static byte[] bytes;

    @Keep
    private Hash() {
    }

    public static String a(String a, String b) {
        return a(new HashDataModel(a, b, false, 0));
    }

    public static String a(String a, String b, boolean c) {
        return a(new HashDataModel(a, b, c, 1));
    }

    public static String a(HashDataModel w1) {
        try {
            MessageDigest md = getInstance(w1.sg);
            md.update(w1.e);
            if (w1.bn) {
                byte[] bytes = new byte[20];
                if (Build.VERSION.SDK_INT >= 26) {
                    SecureRandom.getInstanceStrong().nextBytes(bytes);
                } else {
                    SecureRandom random = new SecureRandom();
                    random.nextBytes(bytes);
                }
            } else if (w1.i == 0){
                bytes = md.digest(w1.sg1.getBytes());
            }
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