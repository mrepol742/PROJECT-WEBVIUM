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

package com.mrepol742.webvium.app.main;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.mrepol742.webvium.app.BuildConfiguration;
import com.mrepol742.webvium.telemetry.DiagnosticData;

public class MainService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("Webvium.onCreate =" + this);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (BuildConfiguration.isDevelopment) {
            DiagnosticData.a("Webvium.onStartCommand =" + this);
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("Webvium.onDestroy =" + this);
    }

    public void s1() {
        stopSelf();
        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("Webvium.stopSelf =" + this);
    }

    public void s2() {
        stopForeground(true);
        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("Webvium.stopForeground =" + this);
    }
}
