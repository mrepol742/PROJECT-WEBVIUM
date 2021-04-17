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

package com.mrepol742.webvium.manifest;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Service;
import android.content.pm.PackageManager;
import android.os.Build;

import com.mrepol742.webvium.annotation.release.Keep;

public class Permission {
    public static final String LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String CAMERA = Manifest.permission.CAMERA;
    public static final String MICROPHONE = Manifest.permission.RECORD_AUDIO;
    public static final String PHONE = Manifest.permission.CALL_PHONE;

    @Keep
    private Permission() {
    }

    public static boolean check(Activity ay, String sg, int it) {
        if (Build.VERSION.SDK_INT >= 29 && STORAGE.equals(sg)) {
            return false;
        } else if (Build.VERSION.SDK_INT >= 23) {
            if (ay.checkSelfPermission(sg) == PackageManager.PERMISSION_GRANTED) {
                return true;
            }
            ay.requestPermissions(new String[]{sg}, it);
            return false;
        }
        return true;
    }

    public static boolean checkOnly(Activity ay, String sg) {
        if (Build.VERSION.SDK_INT >= 29  && STORAGE.equals(sg)) {
            return false;
        } else if (Build.VERSION.SDK_INT >= 23) {
            return ay.checkSelfPermission(sg) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean checkOnly(Service ay, String sg) {
        if ((Build.VERSION.SDK_INT >= 29  && STORAGE.equals(sg)) || Build.VERSION.SDK_INT < 23) {
            return false;
        }
        return ay.checkSelfPermission(sg) != PackageManager.PERMISSION_GRANTED;
    }
}