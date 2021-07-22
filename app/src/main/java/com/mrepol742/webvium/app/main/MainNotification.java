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

package com.mrepol742.webvium.app.main;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Objects;

@SuppressWarnings("ALL")
public class MainNotification {

    public static void a(Context ctx, int notifyId) {
        NotificationManager nMgr = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        nMgr.cancel(notifyId);
    }

    public static void a(Context ctx) {
        NotificationManager nMgr = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        nMgr.cancelAll();
    }

    @TargetApi(26)
    public static void b(Context ctx, String a, String b) {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ctx);
        NotificationChannel nc = new NotificationChannel(a, a, NotificationManager.IMPORTANCE_DEFAULT);
        try {
            if (Objects.equals(sp.getString("py", ""), "7x")) {
                nc = new NotificationChannel(a, a, NotificationManager.IMPORTANCE_HIGH);
            }
            if (Objects.equals(sp.getString("py", ""), "30x")) {
                nc = new NotificationChannel(a, a, NotificationManager.IMPORTANCE_LOW);
            }
            if (Objects.equals(sp.getString("py", ""), "60x")) {
                nc = new NotificationChannel(a, a, NotificationManager.IMPORTANCE_MAX);
            }
            if (Objects.equals(sp.getString("py", ""), "120x")) {
                nc = new NotificationChannel(a, a, NotificationManager.IMPORTANCE_MIN);
            }
            if (Objects.equals(sp.getString("py", ""), "240x")) {
                nc = new NotificationChannel(a, a, NotificationManager.IMPORTANCE_NONE);
            }
            if (Objects.equals(sp.getString("py", ""), "480x")) {
                nc = new NotificationChannel(a, a, NotificationManager.IMPORTANCE_UNSPECIFIED);
            }
            nc.setDescription(b);
            NotificationManager nm = ctx.getSystemService(NotificationManager.class);
            nm.createNotificationChannel(nc);
        } catch (Exception ec) {
            ec.printStackTrace();
        }
    }

    @TargetApi(26)
    public static void c(Context c, String a, String b) {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        try {
            NotificationChannel nc = new NotificationChannel(a, a, NotificationManager.IMPORTANCE_MIN);
            nc.setDescription(b);
            NotificationManager nm = c.getSystemService(NotificationManager.class);
            nm.createNotificationChannel(nc);
        } catch (Exception ec) {
            ec.printStackTrace();
        }
    }
}