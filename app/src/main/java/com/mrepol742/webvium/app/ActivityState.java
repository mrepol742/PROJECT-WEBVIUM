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

package com.mrepol742.webvium.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;

import com.mrepol742.webvium.annotation.Keep;

public class ActivityState {

    @Keep
    private ActivityState() {
    }

    public static void changedTo(Context a, String b, int c) {
        try {
            PackageManager pm = a.getPackageManager();
            ComponentName cn = new ComponentName(a, b);
            pm.setComponentEnabledSetting(cn, c, PackageManager.DONT_KILL_APP);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}