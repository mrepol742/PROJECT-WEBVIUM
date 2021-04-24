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

package com.mrepol742.webvium.content;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;

import com.mrepol742.webvium.annotation.release.Keep;

@SuppressWarnings("ALL")
public class Package {
    @Keep
    private Package() {
    }

    public static boolean a(String a, PackageManager b) {
        boolean c = true;
        try {
            b.getPackageInfo(a, 0);
        } catch (NameNotFoundException e) {
            c = false;
        }
        return c;
    }

    public static String b() {
        return "com.mrepol742.webvium";
    }

    public static String c() {
        return "Webvium";
    }

    public static byte[] d(Context a, String b, int it) {
        try {
            PackageInfo packageInfo = a.getPackageManager().getPackageInfo(b, it);
            return packageInfo.signatures[0].toByteArray();
        } catch (NameNotFoundException ignored) {
        }
        return null;
    }

    public static String e(Context a) throws NameNotFoundException {
        return a.getPackageManager().getPackageInfo(b(), 0).versionName;
    }

    public static int f(Context a) throws NameNotFoundException {
        PackageInfo pi = a.getPackageManager().getPackageInfo(b(), 0);
        if (Build.VERSION.SDK_INT >= 28) {
            return (int) pi.getLongVersionCode();
        }
        return pi.versionCode;
    }

    public static long g(Context a) throws NameNotFoundException {
        return a.getPackageManager().getPackageInfo(b(), 0).firstInstallTime;
    }

}