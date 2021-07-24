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
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.preference.PreferenceManager;

import com.mrepol742.webvium.app.Notifications;
import com.mrepol742.webvium.app.main.MainNotification;
import com.mrepol742.webvium.app.Package;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.util.FileUtil;
import com.mrepol742.webvium.app.StorageDirectory;
import com.mrepol742.webvium.app.Permission;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Objects;

/*
 * @BackupService
 */
public class Back extends Service {

    @Override
    public int onStartCommand(Intent a, int flag, int c) {
        if (Build.VERSION.SDK_INT >= 29) {
            stopSelf();
        }
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                try {
                    String sg1 = Back.this.dt();
                    if (new java.io.File(sg1).exists()) {
                        stopSelf();
                    }
                    try {
                        int t = 10;
                        String sg = Back.this.getPackageName();
                        String sg0 = StorageDirectory.getBaseApk();
                        String pc = sg + "-";
                        for (int i = 0; i < t; i++) {
                            String sg2 = pc + i;
                            java.io.File fe = new java.io.File(String.format(sg0, sg2));
                            if (fe.exists()) {
                                FileInputStream fos = new FileInputStream(fe.toString());
                                OutputStream d = new FileOutputStream(sg1);
                                byte[] e = new byte[1024];
                                int f;
                                while ((f = fos.read(e)) != -1) {
                                    d.write(e, 0, f);
                                }
                                d.flush();
                                d.close();
                                fos.close();
                                Back.this.d1(sg1);
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
        stopSelf();
        return super.onStartCommand(a, flag, c);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void d1(String loc) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        MainNotification.b(this, getString(R.string.n22), getString(R.string.z56));
        android.app.Notification.Builder m = Notifications.a(this, getString(R.string.n22));
        m.setSmallIcon(R.drawable.a20);
        m.setContentTitle(getString(R.string.z57));
        m.setContentText(loc);
        m.setColor(Resources.getColor(this, R.color.a));
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
        m.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.a20));
        NotificationManager nmc = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nmc.notify(Notifications.a, m.build());
    }

    private String dt() throws PackageManager.NameNotFoundException {
        if (!Permission.checkOnly(this, Permission.STORAGE)) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
            MainNotification.b(this, getString(R.string.n22), getString(R.string.z42));
            android.app.Notification.Builder m = Notifications.a(this, getString(R.string.n22));
            m.setSmallIcon(R.drawable.r);
            android.app.Notification.BigTextStyle bigText = new android.app.Notification.BigTextStyle();
            bigText.bigText(getString(R.string.z44));
            bigText.setBigContentTitle(getString(R.string.z43));
            bigText.setSummaryText(getString(R.string.n22));
            m.setContentTitle(getString(R.string.z43));
            m.setContentText(getString(R.string.z44));
            m.setStyle(bigText);
            m.setColor(Resources.getColor(this, R.color.e));
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
            m.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.r));
            Intent j = new Intent(this, Back1.class);
            PendingIntent k = PendingIntent.getActivity(this, 1, j, PendingIntent.FLAG_UPDATE_CURRENT);
            m.setContentIntent(k);
            Intent j55 = new Intent(this, Back1.class);
            PendingIntent pi235 = PendingIntent.getActivity(this, 2, j55, PendingIntent.FLAG_UPDATE_CURRENT);
            m.addAction(new android.app.Notification.Action(R.drawable.a20, getString(R.string.z45), pi235));
            NotificationManager nmc = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            nmc.notify(Notifications.a, m.build());
        }
        FileUtil.createNewFolder(StorageDirectory.getWebviumDir() + "/Backup");
        FileUtil.createNewFolder(StorageDirectory.getWebviumDir() + "/Backup/Application");
        return StorageDirectory.getWebviumDir() + "/Backup/Application/" + Package.c() + " v" + Package.e(this) + ".apk";
    }
}