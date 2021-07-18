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

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.preference.PreferenceManager;

import com.mrepol742.webvium.app.Notifications;
import com.mrepol742.webvium.app.main.MainNotification;
import com.mrepol742.webvium.app.main.MainService;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.net.Connectivity;
import com.mrepol742.webvium.util.Stream;

import java.util.Calendar;
import java.util.Objects;

// @Class UpdateService
public class UPDA extends MainService {

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
                        int b = Integer.parseInt(Package.e(UPDA.this).replaceAll("\\.", ""));
                        int newUpdate = Stream.i("https://github.com/" + UPDA.this.getString(R.string.github_username) + "/" + UPDA.this.getString(R.string.github_repository) + "/blob/" + UPDA.this.getString(R.string.github_branch) + "/" + UPDA.this.getString(R.string.github_path) + "/newVersion.int?raw=true");
                        if (newUpdate == 0) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTimeInMillis(System.currentTimeMillis());
                            calendar.set(Calendar.HOUR_OF_DAY, 3);
                            AlarmManager alarmMgr = (AlarmManager) UPDA.this.getSystemService(Context.ALARM_SERVICE);
                            PendingIntent it = PendingIntent.getService(UPDA.this, 0, new Intent(UPDA.this, UPDA.class), PendingIntent.FLAG_UPDATE_CURRENT);
                            alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_HOUR, it);
                        } else if (newUpdate > b) {
                            MainNotification.b(UPDA.this, UPDA.this.getString(R.string.x11), UPDA.this.getString(R.string.z2));
                            android.app.Notification.Builder m = Notifications.a(UPDA.this, UPDA.this.getString(R.string.x11));
                            m.setSmallIcon(R.drawable.j);
                            android.app.Notification.BigTextStyle bigText = new android.app.Notification.BigTextStyle();
                            bigText.bigText(UPDA.this.getString(R.string.x12));
                            bigText.setBigContentTitle(UPDA.this.getString(R.string.x11));
                            bigText.setSummaryText(UPDA.this.getString(R.string.n21));
                            m.setContentTitle(UPDA.this.getString(R.string.x11));
                            m.setContentText(UPDA.this.getString(R.string.x12));
                            m.setStyle(bigText);
                            m.setColor(Resources.getColor(UPDA.this, R.color.a));
                            m.setAutoCancel(sp.getBoolean("eac", true));
                            m.setDefaults(android.app.Notification.DEFAULT_ALL);
                            if (Build.VERSION.SDK_INT < 26) {
                                if (Objects.requireNonNull(sp.getString("py", "1x")).equals("1x")) {
                                    m.setPriority(android.app.Notification.PRIORITY_DEFAULT);
                                }
                                if (Objects.requireNonNull(sp.getString("py", "1x")).equals("7x")) {
                                    m.setPriority(android.app.Notification.PRIORITY_HIGH);
                                }
                                if (Objects.requireNonNull(sp.getString("py", "1x")).equals("30x")) {
                                    m.setPriority(android.app.Notification.PRIORITY_LOW);
                                }
                                if (Objects.requireNonNull(sp.getString("py", "1x")).equals("60x")) {
                                    m.setPriority(android.app.Notification.PRIORITY_MAX);
                                }
                                if (Objects.requireNonNull(sp.getString("py", "1x")).equals("120x")) {
                                    m.setPriority(android.app.Notification.PRIORITY_MIN);
                                }
                            }
                            if (Objects.requireNonNull(sp.getString("vy", "7y")).equals("1y")) {

                                m.setVisibility(android.app.Notification.VISIBILITY_PRIVATE);
                            }
                            if (Objects.requireNonNull(sp.getString("vy", "7y")).equals("7y")) {
                                m.setVisibility(android.app.Notification.VISIBILITY_PUBLIC);
                            }
                            if (Objects.requireNonNull(sp.getString("vy", "7y")).equals("30y")) {
                                m.setVisibility(android.app.Notification.VISIBILITY_SECRET);
                            }
                            m.setLargeIcon(BitmapFactory.decodeResource(UPDA.this.getResources(), R.drawable.j));
                            Intent j = new Intent(UPDA.this, MAIN.class);
                            j.putExtra("value", "https://mrepol742.github.io/PROJECT-WEBVIUM");
                            PendingIntent k = PendingIntent.getActivity(UPDA.this, 1, j, PendingIntent.FLAG_UPDATE_CURRENT);
                            m.setContentIntent(k);

                            PendingIntent pi23 = PendingIntent.getActivity(UPDA.this, 0, j, PendingIntent.FLAG_UPDATE_CURRENT);
                            m.addAction(new android.app.Notification.Action(R.drawable.c2, UPDA.this.getString(R.string.b32), pi23));

                            NotificationManager nmc = (NotificationManager) UPDA.this.getSystemService(Context.NOTIFICATION_SERVICE);
                            nmc.notify(Notifications.c, m.build());
                        }
                    } catch (PackageManager.NameNotFoundException w) {
                        w.printStackTrace();
                    }
                }
            };
            new Thread(runnable).start();
        }
        s1();
        return super.onStartCommand(a, c, d);
    }
}