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

import android.app.AlarmManager;
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

import java.util.Calendar;

/*
 * @BootCompletedBroadcastReceiver
 */
public class Boot extends BroadcastReceiver {

    @Override
    public void onReceive(Context a, Intent b) {
        if (b.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(a);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 6);
            AlarmManager alarmMgr = (AlarmManager) a.getSystemService(Context.ALARM_SERVICE);
            if (sp.getBoolean("qckS", false)) {
                MainNotification.b(a, a.getString(R.string.l33), a.getString(R.string.y18));
                android.app.Notification.Builder m = Notifications.a(a, a.getString(R.string.l33));
                m.setSmallIcon(R.drawable.a18);
                m.setContentTitle(a.getString(R.string.l33));
                m.setContentText(a.getString(R.string.n39));
                m.setOngoing(true);
                m.setColor(Resources.getColor(a, R.color.a));
                m.setAutoCancel(false);
                Intent j11 = new Intent(a, Sear.class);
                PendingIntent k567 = PendingIntent.getActivity(a, 0, j11, PendingIntent.FLAG_UPDATE_CURRENT);
                m.setContentIntent(k567);
                if (Build.VERSION.SDK_INT <= 26) {
                    m.setPriority(android.app.Notification.PRIORITY_LOW);
                }
                m.setVisibility(android.app.Notification.VISIBILITY_PUBLIC);
                m.setLargeIcon(BitmapFactory.decodeResource(a.getResources(), R.mipmap.b));
                NotificationManager nmc = (NotificationManager) a.getSystemService(Context.NOTIFICATION_SERVICE);
                nmc.notify(Notifications.d, m.build());
            }
            if (sp.getBoolean("acu", true)) {
                PendingIntent it = PendingIntent.getService(a, 0, new Intent(a, Upda.class), PendingIntent.FLAG_UPDATE_CURRENT);
                alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, it);
            }
            if (sp.getBoolean("pnd", true)) {
                PendingIntent it = PendingIntent.getService(a, 0, new Intent(a, Noti.class), PendingIntent.FLAG_UPDATE_CURRENT);
                alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, it);
            }
            if (sp.getBoolean("fltWeb", false)) {
                MainNotification.c(a, a.getString(R.string.l32), a.getString(R.string.l34));
                android.app.Notification.Builder m = Notifications.a(a, a.getString(R.string.l32));
                m.setSmallIcon(R.drawable.a18);
                m.setContentTitle(a.getString(R.string.l32));
                m.setContentText(a.getString(R.string.n40));
                m.setOngoing(true);
                m.setColor(Resources.getColor(a, R.color.a));
                m.setAutoCancel(false);
                Intent j11 = new Intent(a, Webv.class);
                PendingIntent k567 = PendingIntent.getActivity(a, 0, j11, PendingIntent.FLAG_UPDATE_CURRENT);
                m.setContentIntent(k567);
                if (Build.VERSION.SDK_INT <= 26) {
                    m.setPriority(android.app.Notification.PRIORITY_MIN);
                }
                m.setVisibility(android.app.Notification.VISIBILITY_PUBLIC);
                m.setLargeIcon(BitmapFactory.decodeResource(a.getResources(), R.mipmap.c));
                NotificationManager nmc = (NotificationManager) a.getSystemService(Context.NOTIFICATION_SERVICE);
                nmc.notify(Notifications.e, m.build());
            }
            if (sp.getBoolean("nCV", false)) {
                Intents.b(a, Clip.class);
            }
        }
    }
}
