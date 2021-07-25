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
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.preference.PreferenceManager;

import com.mrepol742.webvium.app.Notifications;
import com.mrepol742.webvium.app.main.MainNotification;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.net.Connectivity;
import com.mrepol742.webvium.security.SHA;
import com.mrepol742.webvium.net.Stream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Objects;

import org.json.*;
/*
 * @NotificationService
 */
public class Noti extends Service {
    private SharedPreferences sp;

    @Override
    public void onCreate() {
        super.onCreate();
        sp = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    public int onStartCommand(Intent a, int c, int d) {
        if (!Connectivity.isThereAnyInternetConnection(this)) {
            Runnable runnable = new Runnable() {

                @Override
                public void run() {
                    try {
                        String[] neTf = a(Stream.f("https://github.com/" + Noti.this.getString(R.string.github_username) + "/" + Noti.this.getString(R.string.github_repository) + "/blob/" + Noti.this.getString(R.string.github_branch) + "/" + Noti.this.getString(R.string.github_path) + "/Noti.json?raw=true", "742"));
                        if (neTf[0].equals("742")) {
                           b();
                        } else {
                            SharedPreferences j988 = Noti.this.getSharedPreferences("wv", 0);
                            if (!Objects.requireNonNull(j988.getString("notif1", "")).equals(SHA.a("SHA-1", neTf[0])) && !Objects.requireNonNull(j988.getString("notif2", "")).equals(SHA.a("SHA-1", neTf[1]))) {
                                Noti.this.f(neTf[0], neTf[1], neTf[2]);
                                SharedPreferences.Editor gujh = j988.edit();
                                gujh.putString("notif1", SHA.a("SHA-1", neTf[0]));
                                gujh.putString("notif2", SHA.a("SHA-1", neTf[1]));
                                gujh.apply();
                            }
                        }
                    } catch (Exception en) {
                        en.printStackTrace();
                    }
                }
            };
            new Thread(runnable).start();
        } else {
            b();
        }
        stopSelf();
        return super.onStartCommand(a, c, d);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void f(String title, String text, String url) {
        MainNotification.b(this, getString(R.string.n13), getString(R.string.z3));
        android.app.Notification.Builder m = Notifications.a(this, getString(R.string.n13));
        m.setSmallIcon(R.drawable.c);
        android.app.Notification.BigTextStyle bigText = new android.app.Notification.BigTextStyle();
        bigText.bigText(text);
        bigText.setBigContentTitle(title);
        bigText.setSummaryText(getString(R.string.m6));
        m.setContentTitle(title);
        m.setContentText(text);
        m.setStyle(bigText);
        m.setColor(Resources.getColor(this, R.color.a));
        m.setAutoCancel(sp.getBoolean("eac", true));
        m.setDefaults(android.app.Notification.DEFAULT_ALL);
        if (Build.VERSION.SDK_INT < 26) {
            if (sp.getString("py", "") == null) {
                m.setPriority(android.app.Notification.PRIORITY_DEFAULT);
            }
            if (Objects.requireNonNull(sp.getString("py", "")).equals("1x")) {
                m.setPriority(android.app.Notification.PRIORITY_DEFAULT);
            }
            if (Objects.requireNonNull(sp.getString("py", "")).equals("7x")) {
                m.setPriority(android.app.Notification.PRIORITY_HIGH);
            }
            if (Objects.requireNonNull(sp.getString("py", "")).equals("30x")) {
                m.setPriority(android.app.Notification.PRIORITY_LOW);
            }
            if (Objects.requireNonNull(sp.getString("py", "")).equals("60x")) {
                m.setPriority(android.app.Notification.PRIORITY_MAX);
            }
            if (Objects.requireNonNull(sp.getString("py", "")).equals("120x")) {
                m.setPriority(android.app.Notification.PRIORITY_MIN);
            }
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
        m.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.c));
        Intent j = new Intent(this, Webv.class);
        j.putExtra("value", url);
        PendingIntent k = PendingIntent.getActivity(this, 1, j, PendingIntent.FLAG_UPDATE_CURRENT);
        m.setContentIntent(k);
        Intent j55 = new Intent(this, Webv.class);
        j55.putExtra("webvium", url);
        PendingIntent pi235 = PendingIntent.getActivity(this, 2, j55, PendingIntent.FLAG_UPDATE_CURRENT);
        m.addAction(new android.app.Notification.Action(R.drawable.q, String.format(getString(R.string.g28), Objects.requireNonNull(Uri.parse(url).getHost())), pi235));
        NotificationManager nmc = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nmc.notify(Notifications.a, m.build());
    }

    private String[] a(String sg) {
        try {
            JSONObject root = new JSONObject(sg);
            return new String[]{root.getString("title"),
                    root.getString("content"),
                    root.getString("url")};
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new String[]{"742", null, null};
    }

    private void b() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 3);
        AlarmManager alarmMgr = (AlarmManager) Noti.this.getSystemService(Context.ALARM_SERVICE);
        PendingIntent it = PendingIntent.getService(Noti.this, 0, new Intent(Noti.this, Noti.class), PendingIntent.FLAG_UPDATE_CURRENT);
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_HOUR, it);
    }

}