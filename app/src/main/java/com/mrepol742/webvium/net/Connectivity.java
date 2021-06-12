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

package com.mrepol742.webvium.net;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;

import com.mrepol742.webvium.annotation.Keep;

public class Connectivity {
    @Keep
    private Connectivity() {
    }

    public static boolean isThereAnyInternetConnection(Context a) {
        ConnectivityManager cm = (ConnectivityManager) a.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= 23) {
            NetworkCapabilities nc = cm.getNetworkCapabilities(cm.getActiveNetwork());

            if (Build.VERSION.SDK_INT >= 26) {
                return !e(nc);
            }
            return !d(nc);
        }
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null) {
            return ni.getType() != ConnectivityManager.TYPE_WIFI && ni.getType() != ConnectivityManager.TYPE_MOBILE && ni.getType() != ConnectivityManager.TYPE_ETHERNET && ni.getType() != ConnectivityManager.TYPE_WIMAX;
        }
        return true;
    }

    public static boolean isAirplaneMode(Context a) {
        return Settings.Global.getInt(a.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) == 0;
    }

    private static boolean d(NetworkCapabilities nc) {
        if (nc != null) {
            return nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
        }
        return false;
    }

    @TargetApi(26)
    private static boolean e(NetworkCapabilities nc) {
        if (nc != null) {
            return nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE);
        }
        return false;
    }
}