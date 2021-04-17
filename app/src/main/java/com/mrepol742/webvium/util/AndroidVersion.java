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

import com.mrepol742.webvium.annotation.Test;
import com.mrepol742.webvium.annotation.release.Keep;
import com.mrepol742.webvium.app.NoSuchStringToReturn;

@Test
public class AndroidVersion {
    @Keep
    private AndroidVersion() {}

    public static String getVersionName(int it) {
        if (it >= 21) {
            if (it >= 23) {
                if (it >= 24) {
                    if (it >= 26) {
                        if (it >= 28) {
                            if (it >= 29) {
                                if (it >= 30) {
                                    if (it >= 31) {
                                        return Integer.toString(it);
                                    }
                                    return "R";
                                }
                                return "Q";
                            }
                            return "Pie";
                        }
                        return "Oreo";
                    }
                    return "Nougat";
                }
                return "Marshmallow";
            }
            return "Lollipop";
        }
        throw new NoSuchStringToReturn();
    }

}
