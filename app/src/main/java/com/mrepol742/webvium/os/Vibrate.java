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

package com.mrepol742.webvium.os;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.preference.PreferenceManager;

import com.mrepol742.webvium.annotation.release.Keep;
import com.mrepol742.webvium.telemetry.DiagnosticData;

import java.util.Objects;

public class Vibrate {

    @Keep
    private Vibrate() {
    }

    public static void a(Context a, int b) {
        try {
            Vibrator v = (Vibrator) a.getSystemService(Context.VIBRATOR_SERVICE);
            if (!v.hasVibrator()) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 27) {
                b(v, b, VibrationEffect.DEFAULT_AMPLITUDE);
                if (Build.VERSION.SDK_INT >= 29) {
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(a.getApplicationContext());
                    if (Objects.equals(sp.getString("vbF", ""), "7y")) {
                        b(v, b, VibrationEffect.EFFECT_CLICK);
                    }
                    if (Objects.equals(sp.getString("vbF", ""), "30y")) {
                        b(v, b, VibrationEffect.EFFECT_DOUBLE_CLICK);
                    }
                    if (Objects.equals(sp.getString("vbF", ""), "60y")) {
                        b(v, b, VibrationEffect.EFFECT_HEAVY_CLICK);
                    }
                    if (Objects.equals(sp.getString("vbF", ""), "120y")) {
                        b(v, b, VibrationEffect.EFFECT_TICK);
                    }
                }
            } else {
                v.vibrate(b);
            }
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

    @TargetApi(27)
    private static void b(Vibrator v, int i, int eff) {
        v.vibrate(VibrationEffect.createOneShot(i, eff));
    }
}