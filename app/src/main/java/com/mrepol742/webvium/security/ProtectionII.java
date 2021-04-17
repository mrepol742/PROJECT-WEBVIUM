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

package com.mrepol742.webvium.security;

import android.content.Context;

import com.mrepol742.webvium.annotation.Beta;
import com.mrepol742.webvium.annotation.release.Keep;

import java.util.Arrays;

@Beta
public class ProtectionII {
    private static final String[] signatures = {"", "", ""};

    @Keep
    private ProtectionII() {
    }

    public static int initializeSignatureScanning(Context context) {
        byte[] signature = com.mrepol742.webvium.content.Package.d(context, context.getPackageName(), 0);
        String hash = Hash.a(new HashDataModel("SHA-512", Arrays.toString(signature)));
        for (String sig: signatures) {
            if (hash.compareTo(sig) > 0) {
                return 0;
            }
        }
        return 1;
    }
}
