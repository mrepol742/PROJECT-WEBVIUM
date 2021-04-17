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

import com.mrepol742.webvium.annotation.release.Keep;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ObjectHash {

    @Keep
    private ObjectHash() {
    }

    public static String getObjectHash(String value) {
        try {
            int it = Integer.parseInt(value);
            return generate(it);
        } catch (NumberFormatException numberFormatException) {
            return generate(value);
        }
    }

    private static String generate(Object object) {
        String a = "742";
        String b = Integer.toString(object.hashCode());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyymmsshh", Locale.US);
        String currentDate = sdf.format(date);
        String total = a + b + currentDate;
        return Hash.a(new HashDataModel("SHA-1", total));
    }


}