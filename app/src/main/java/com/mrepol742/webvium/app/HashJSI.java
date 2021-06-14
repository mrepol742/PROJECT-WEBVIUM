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

package com.mrepol742.webvium.app;

import android.os.Build;
import android.webkit.JavascriptInterface;

import com.mrepol742.webvium.annotation.Keep;
import com.mrepol742.webvium.security.Hash;

import java.util.Arrays;

public class HashJSI {
    private final String[] algos = {"MD5", "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512"};

    @Keep
    public HashJSI() {

    }

    @JavascriptInterface
    public String algorithm(String algo, String text, boolean ran) {
        try {
            if (Arrays.toString(algos).contains(algo) && !text.isEmpty()) {
                if (algo.equals(algos[2]) && (Build.VERSION.SDK_INT < 22)) {
                    return "";
                }
                return Hash.a(algo, text, ran);
            }
        } catch (Exception en) {
            en.printStackTrace();
        }
        return "";
    }
}
