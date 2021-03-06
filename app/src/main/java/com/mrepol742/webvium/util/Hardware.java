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

package com.mrepol742.webvium.util;

import android.annotation.SuppressLint;
import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Configuration;

import com.mrepol742.webvium.annotation.Keep;

public class Hardware {
    @Keep
    private Hardware() {
    }

    public static boolean isTablet(Context context) {
        return (context.getApplicationContext().getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    @SuppressLint("SwitchIntDef")
    public static Object isNightMode(Context a) {
        UiModeManager ui = (UiModeManager) a.getSystemService(Context.UI_MODE_SERVICE);
        switch (ui.getNightMode()) {
            case UiModeManager.MODE_NIGHT_YES:
                return E1.Yes;
            case UiModeManager.MODE_NIGHT_NO:
                return E1.No;
            default:
            case UiModeManager.MODE_NIGHT_AUTO:
                return E1.Auto;
                // UiModeManager.MODE_NIGHT_CUSTOM == 3
            case 3:
                return E1.Custom;
        }
    }

    public enum E1 {
        Yes, No, Custom, Auto;

        @Keep
        E1() {
        }
    }
}
