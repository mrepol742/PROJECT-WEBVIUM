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

package com.mrepol742.webvium.os;

import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.VmPolicy;

import com.mrepol742.webvium.annotation.release.Keep;
import com.mrepol742.webvium.util.Log;

public class StrictMode {
    @Keep
    private StrictMode() {
    }

    public static void a() {
        //android.os.StrictMode.setThreadPolicy(new ThreadPolicy.Builder().detectNetwork().penaltyLog().build());
    }

    public static void b() {
       // android.os.StrictMode.setThreadPolicy(new ThreadPolicy.Builder().detectAll().penaltyLog().build());
       // android.os.StrictMode.setVmPolicy(new VmPolicy.Builder().detectAll().penaltyLog().build());
    }

}
