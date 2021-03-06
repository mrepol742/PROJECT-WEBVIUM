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
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ClipboardManager.OnPrimaryClipChangedListener;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.webkit.URLUtil;

import com.mrepol742.webvium.app.Notifications;
import com.mrepol742.webvium.app.main.MainNotification;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.util.Domain;

import java.util.Objects;

/*
 * @ClipboardService
 */
public class Clip extends Service {
    static boolean c = false;
    ClipboardManager a;

    final OnPrimaryClipChangedListener b = new OnPrimaryClipChangedListener() {

        @Override
        public void onPrimaryClipChanged() {
            ClipData c56 = a.getPrimaryClip();
            ClipData.Item d34 = null;
            if (c56 != null) {
                d34 = c56.getItemAt(0);
            }
            if (d34 != null && (d34.getText() == null || d34.getText().toString().length() == 0)) {
                return;
            }
            String sg = null;
            if (d34 != null) {
                sg = d34.getText().toString();
            }
            if (sg != null) {
                if (URLUtil.isValidUrl(sg)) {
                    if (Domain.isValidDomain(sg)) {
                        a(sg);
                    }
                }

            }
            g();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        a = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    public int onStartCommand(Intent a1, int cint, int f) {
        e();
        return super.onStartCommand(a1, cint, f);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        f();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void a(String jk) {
        MainNotification.b(this, getString(R.string.x8), getString(R.string.z1));
        android.app.Notification.Builder m = Notifications.a(this, getString(R.string.x8));
        m.setSmallIcon(R.drawable.b16);
        m.setContentTitle(getResources().getString(R.string.x7));
        m.setContentText(jk);
        android.app.Notification.BigTextStyle bigText = new android.app.Notification.BigTextStyle();
        bigText.bigText(jk);
        bigText.setBigContentTitle(getResources().getString(R.string.x7));
        bigText.setSummaryText(getString(R.string.x8));
        m.setStyle(bigText);
        m.setColor(Resources.getColor(this, R.color.a));
        SharedPreferences sq = PreferenceManager.getDefaultSharedPreferences(this);
        m.setAutoCancel(sq.getBoolean("eac", true));
        m.setDefaults(android.app.Notification.DEFAULT_ALL);
        if (Build.VERSION.SDK_INT <= 26) {
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
        m.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.b16));
        Intent j = new Intent(this, Webv.class);
        j.putExtra("value", jk);
        PendingIntent k = PendingIntent.getActivity(this, 1, j, PendingIntent.FLAG_UPDATE_CURRENT);
        m.setContentIntent(k);
        Intent j55 = new Intent(Intent.ACTION_SEND);
        j55.setType("text/plain");
        j55.putExtra("android.intent.extra.TEXT", jk);
        PendingIntent pi235 = PendingIntent.getActivity(this, 0, j55, PendingIntent.FLAG_UPDATE_CURRENT);
        m.addAction(new android.app.Notification.Action(R.drawable.e, getString(R.string.a8), pi235));
        NotificationManager nmc = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nmc.notify(Notifications.getRandomizeNotificationId(Notifications.DEFAULT), m.build());
    }

    private void e() {
        if (!c) {
            a.addPrimaryClipChangedListener(b);
            c = true;
        }
    }

    private void f() {
        if (c) {
            a.removePrimaryClipChangedListener(b);
            c = false;
        }
    }

    private void g() {
        a.removePrimaryClipChangedListener(b);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                a.addPrimaryClipChangedListener(b);
            }
        }, 500);
    }

}