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

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;

import com.mrepol742.webvium.app.Notifications;
import com.mrepol742.webvium.app.main.MainNotification;
import com.mrepol742.webvium.app.main.MainService;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.io.StorageDirectory;
import com.mrepol742.webvium.net.Connectivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;

// @Class SaveLinkService
public class SAVE extends MainService {

    @Override
    public int onStartCommand(final Intent b34, int c5, int fl) {
        if (!Connectivity.isThereAnyInternetConnection(this)) {
            Runnable runnable = () -> {
                try {
                    String a = b34.getStringExtra("a");
                    String b = b34.getStringExtra("b");
                    a1(a, b);
                    URL b5 = new URL(b);
                    File a2 = new File(StorageDirectory.getWebviumDir() + "/Downloads/" + a);
                    if (!a2.exists()) {
                        if (a2.createNewFile()) {
                            BufferedReader c = new BufferedReader(new InputStreamReader(b5.openStream()));
                            FileWriter fr = new FileWriter(a2, true);
                            BufferedWriter br = new BufferedWriter(fr);
                            String f;
                            while ((f = c.readLine()) != null) {
                                br.write(f);
                            }
                            br.close();
                            fr.close();
                            a2(StorageDirectory.getWebviumDir() + "/Downloads/" + a, a);
                        }
                    }
                } catch (IOException mu) {
                    mu.printStackTrace();
                }
            };
            new Thread(runnable).start();
        }
        s1();
        return super.onStartCommand(b34, c5, fl);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MainNotification.a(this, Notifications.f);
        s2();
    }


    private void a1(String a, String b) {
        MainNotification.c(this, getString(R.string.i4), getString(R.string.y16));
        android.app.Notification.Builder m = Notifications.a(this, getString(R.string.i4));
        m.setSmallIcon(R.drawable.c2);
        m.setContentTitle(a);
        if (!com.mrepol742.webvium.util.Inapproriate.isInapproriate(b)) {
            m.setContentText(b);
        }
        m.setOngoing(true);
        m.setColor(Resources.getColor(this, R.color.a));
        m.setAutoCancel(false);
        if (Build.VERSION.SDK_INT <= 26) {
            m.setPriority(android.app.Notification.PRIORITY_LOW);
        }
        m.setVisibility(android.app.Notification.VISIBILITY_PUBLIC);
        m.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.c2));
        startForeground(Notifications.f, m.build());

    }

    private void a2(String str, String jk) {
        MainNotification.b(this, getString(R.string.y19), getString(R.string.y99));
        android.app.Notification.Builder m = Notifications.a(this, getString(R.string.y19));
        m.setSmallIcon(R.drawable.c2);
        android.app.Notification.BigTextStyle bigText = new android.app.Notification.BigTextStyle();

        bigText.setSummaryText(getString(R.string.l4));
        if (com.mrepol742.webvium.util.Inapproriate.isInapproriate(jk)) {
            bigText.setBigContentTitle(getResources().getString(R.string.u20));
            m.setContentTitle(getResources().getString(R.string.u20));
            m.setContentText(jk);
            bigText.bigText(jk);
        } else {
            bigText.setBigContentTitle(getResources().getString(R.string.u20));
            m.setContentTitle(getResources().getString(R.string.u20));
        }
        m.setStyle(bigText);
        m.setColor(Resources.getColor(this, R.color.a));

        SharedPreferences sq = PreferenceManager.getDefaultSharedPreferences(this);
        m.setAutoCancel(sq.getBoolean("eac", true));
        m.setDefaults(android.app.Notification.DEFAULT_ALL);
        if (Build.VERSION.SDK_INT < 26) {
            if (sq.getString("py", "") == null) {
                m.setPriority(android.app.Notification.PRIORITY_DEFAULT);
            }
            if (Objects.requireNonNull(sq.getString("py", "")).equals("1x")) {
                m.setPriority(android.app.Notification.PRIORITY_DEFAULT);
            }
            if (Objects.requireNonNull(sq.getString("py", "")).equals("7x")) {
                m.setPriority(android.app.Notification.PRIORITY_HIGH);
            }
            if (Objects.requireNonNull(sq.getString("py", "")).equals("30x")) {
                m.setPriority(android.app.Notification.PRIORITY_LOW);
            }
            if (Objects.requireNonNull(sq.getString("py", "")).equals("60x")) {
                m.setPriority(android.app.Notification.PRIORITY_MAX);
            }
            if (Objects.requireNonNull(sq.getString("py", "")).equals("120x")) {
                m.setPriority(android.app.Notification.PRIORITY_MIN);
            }
        }
        if (sq.getString("vy", "") == null) {
            m.setVisibility(android.app.Notification.VISIBILITY_PUBLIC);
        }
        if (Objects.requireNonNull(sq.getString("vy", "")).equals("1y")) {

            m.setVisibility(android.app.Notification.VISIBILITY_PRIVATE);
        }
        if (Objects.requireNonNull(sq.getString("vy", "")).equals("7y")) {
            m.setVisibility(android.app.Notification.VISIBILITY_PUBLIC);
        }
        if (Objects.requireNonNull(sq.getString("vy", "")).equals("30y")) {
            m.setVisibility(android.app.Notification.VISIBILITY_SECRET);
        }


        m.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.c2));
        Intent j55 = new Intent(this, MAIN.class);
        j55.putExtra("webvium", "file:///" + str);
        PendingIntent pi = PendingIntent.getActivity(this, 2, j55,
                PendingIntent.FLAG_UPDATE_CURRENT);
        m.setContentIntent(pi);
        Intent j5555 = new Intent(Intent.ACTION_SEND);
        j5555.putExtra(Intent.EXTRA_STREAM, Uri.parse(str));
        PendingIntent pi23555 = PendingIntent.getActivity(this, 0, j5555, PendingIntent.FLAG_UPDATE_CURRENT);

        m.addAction(new android.app.Notification.Action(R.drawable.e, getString(R.string.a8), pi23555));

        PendingIntent pi235 = PendingIntent.getActivity(this, 1, j55, PendingIntent.FLAG_UPDATE_CURRENT);

        m.addAction(new android.app.Notification.Action(R.drawable.q, getString(R.string.h37), pi235));
        NotificationManager nmc = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nmc.notify(Notifications.getRandomizeNotificationId(Notifications.DEFAULT), m.build());
    }
}