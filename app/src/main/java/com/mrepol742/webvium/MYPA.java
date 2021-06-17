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

package com.mrepol742.webvium;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.preference.PreferenceManager;

import com.mrepol742.webvium.app.main.MainReceiver;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.widget.Toast;

import java.util.Objects;

// @Class MyPackageReplaced
public class MYPA extends MainReceiver {

    @Override
    public void onReceive(Context a, Intent b) {
        if (Objects.requireNonNull(b.getAction()).equals("android.intent.action.MY_PACKAGE_REPLACED")) {
            if (PreferenceManager.getDefaultSharedPreferences(a).getBoolean("bcP", true) && Build.VERSION.SDK_INT < 29) {
                Intents.b(a, BACK.class);
            }
            if (PreferenceManager.getDefaultSharedPreferences(a).getBoolean("maUU", false) && PreferenceManager.getDefaultSharedPreferences(a).getBoolean("asd71", false)) {
                Intents.a(a, MAIN.class);
            }
            Toast.f(a, a.getString(R.string.y61), 2);
            Intent intent = new Intent(a, UPDA.class);
            intent.putExtra("sta", "non");
            a.startService(intent);
            Intent intent2 = new Intent(a, NOTI.class);
            intent2.putExtra("sta", "non");
            a.startService(intent2);
        }
        super.onReceive(a, b);
    }

}