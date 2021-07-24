/*
 *
 * Copyright (c) 2021 Melvin Jones Repol (mrepol742.github.io). All rights reserved.
 *
 * License under the GNU General Public License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain changedTo copy of the License at
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

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.preference.PreferenceManager;

import com.mrepol742.webvium.app.Intents;
import com.mrepol742.webvium.app.Notifications;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.app.main.MainNotification;
import com.mrepol742.webvium.util.AwesomeToast;

import java.util.Objects;

/*
 * @PackageChangedBroadcastReceiver
 */
public class Pack extends BroadcastReceiver {

    @Override
    public void onReceive(Context a, Intent b) {
        if (PreferenceManager.getDefaultSharedPreferences(a).getBoolean("bcP", true) && Build.VERSION.SDK_INT < 29) {
            Intents.b(a, Back.class);
        }
        if (PreferenceManager.getDefaultSharedPreferences(a).getBoolean("maUU", false) && PreferenceManager.getDefaultSharedPreferences(a).getBoolean("asd71", false)) {
            Intents.a(a, Webv.class);
        }
        Intents.b(a, Upda.class);
        Intents.b(a, Noti.class);


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(a);
        MainNotification.b(a, a.getString(R.string.z79), a.getString(R.string.z80));
        android.app.Notification.Builder m = Notifications.a(a, a.getString(R.string.z79));
        m.setSmallIcon(R.drawable.a20);
        m.setContentTitle(a.getString(R.string.z77));
        m.setContentText(a.getString(R.string.z78));
        m.setColor(Resources.getColor(a, R.color.a));
        m.setAutoCancel(sp.getBoolean("eac", true));
        m.setDefaults(android.app.Notification.DEFAULT_ALL);
        if (Build.VERSION.SDK_INT < 26) {
            m.setPriority(android.app.Notification.PRIORITY_HIGH);
        }
        if (sp.getString("vy", "") == null) {
            m.setVisibility(android.app.Notification.VISIBILITY_PUBLIC);
        }
        if (Objects.requireNonNull(sp.getString("vy", "")).equals("1y")) {
            m.setVisibility(android.app.Notification.VISIBILITY_PRIVATE);
        }
        if (Objects.requireNonNull(sp.getString("vy", "")).equals("7y")) {
            m.setVisibility(android.app.Notification.VISIBILITY_PUBLIC);
        }
        if (Objects.requireNonNull(sp.getString("vy", "")).equals("30y")) {
            m.setVisibility(android.app.Notification.VISIBILITY_SECRET);
        }
        Intent j = new Intent(a, Webv.class);
        PendingIntent k = PendingIntent.getActivity(a, 1, j, PendingIntent.FLAG_UPDATE_CURRENT);
        m.setContentIntent(k);
        m.setLargeIcon(BitmapFactory.decodeResource(a.getResources(), R.drawable.a20));
        NotificationManager nmc = (NotificationManager) a.getSystemService(Context.NOTIFICATION_SERVICE);
        nmc.notify(Notifications.a, m.build());
    }
}