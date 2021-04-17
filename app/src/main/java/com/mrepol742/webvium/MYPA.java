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

package com.mrepol742.webvium;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;

import com.mrepol742.webvium.app.main.MainReceiver;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.io.Files;
import com.mrepol742.webvium.io.StorageDirectory;
import com.mrepol742.webvium.telemetry.DiagnosticData;
import com.mrepol742.webvium.util.Hardware;
import com.mrepol742.webvium.util.U4;
import com.mrepol742.webvium.widget.Toast;

import java.util.Objects;

// @Class MyPackageReplaced
public class MYPA extends MainReceiver {

    @Override
    public void onReceive(Context a, Intent b) {
        if (Objects.requireNonNull(b.getAction()).equals("android.intent.action.MY_PACKAGE_REPLACED")) {
            Files.delete(StorageDirectory.getCacheDir(a) + "/log");
            try {
                DiagnosticData.a("\nStorage \nFiles: " + a.getFilesDir() + "\nCache: " + StorageDirectory.getCacheDir(a) + "\n\nBuild Log \nDevice: " + Hardware.c(a) + "\nNight: " + U4.a(Hardware.isNightMode(a)) + "\n\nPackage Update\nPackage Label: " + Package.c() + "\nPackage Name: " + Package.b() + "\nPackage Version Name: " + Package.e(a) + "\nPackage Version Code: " + Package.f(a));
            } catch (PackageManager.NameNotFoundException q) {
                DiagnosticData.a(q);
            }
            if (PreferenceManager.getDefaultSharedPreferences(a).getBoolean("bcP", true)) {
                Intents.b(a, BACK.class);
            }
            if (PreferenceManager.getDefaultSharedPreferences(a).getBoolean("asd71", false)) {
                Intents.a(a, MAIN.class);
            }
            Toast.b(a, a.getString(R.string.y61));
            Intent intent = new Intent(a, UPDA.class);
            intent.putExtra("sta", "non");
            a.startService(intent);
            Intent intent2 = new Intent(a, NOTI.class);
            intent2.putExtra("sta", "non");
            a.startService(intent2);
            Intent intent3 = new Intent(a, UPDA0.class);
            intent3.putExtra("sta", "non");
            a.startService(intent3);
        }
        super.onReceive(a, b);
    }

}