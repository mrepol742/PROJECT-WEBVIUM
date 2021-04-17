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

package com.mrepol742.webvium.app;

import android.database.sqlite.SQLiteDatabase;

import com.mrepol742.webvium.annotation.release.Keep;
import com.mrepol742.webvium.telemetry.DiagnosticData;

public class W6 {

    @Keep
    private W6() {
    }

    public static void a() {
        System.exit(0);
    }

    public static void b() {

        int a = SQLiteDatabase.releaseMemory();
        if (BuildConfiguration.isDevelopment)
            DiagnosticData.a("Webvium.SQLiteDatabase release memory =" + a);
    }

    public static void c() {
        System.gc();
    }
}