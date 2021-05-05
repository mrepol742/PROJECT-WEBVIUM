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

import android.content.Intent;
import android.content.SharedPreferences;

import com.mrepol742.webvium.annotation.Development;
import com.mrepol742.webvium.annotation.Test;
import com.mrepol742.webvium.app.main.MainService;
import com.mrepol742.webvium.net.Connectivity;
import com.mrepol742.webvium.util.Log;
import com.mrepol742.webvium.util.Base64;
import com.mrepol742.webvium.util.Stream;

// @Class UpdateService
public class UPDA0 extends MainService {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent a, int c, int d) {
        if (Connectivity.isThereAnyInternetConnection(this)) {
            if (a.getStringExtra("sta") == null || a.getStringExtra("sta").isEmpty())
                s1();
        }
        e();
        s1();
        return super.onStartCommand(a, c, d);
    }

    @Test
    @Development
    private void e() {
        Runnable runnable = () -> {
            try {
                SharedPreferences sharedPreferences = getSharedPreferences("b", 0);
                String sg = Stream.f(Base64.decode(sharedPreferences.getString(WELC.TEMP_WEBVIUM_URLS, ""))+ "?raw=true", "404");
                if (sg.equals("404")) {
                    return;
                }
                String[] arr = sg.trim().split(";");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String[] abc = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
                editor.clear();
                int length = arr.length;
                for (int i = 0; i < length; i++) {
                    editor.putString(abc[i], arr[i] + abc[i]);
                }
                editor.apply();
            } catch (Exception en) {
                Log.a(en);
            }
        };
        new Thread(runnable).start();
    }

}
