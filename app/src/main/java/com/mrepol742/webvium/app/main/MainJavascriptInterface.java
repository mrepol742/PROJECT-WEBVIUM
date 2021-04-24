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

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.preference.PreferenceManager;

import com.mrepol742.webvium.MAIN;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.app.Notifications;
import com.mrepol742.webvium.app.W6;
import com.mrepol742.webvium.content.Clipboard;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.os.Vibrate;
import com.mrepol742.webvium.telemetry.DiagnosticData;
import com.mrepol742.webvium.widget.Toast;

import java.util.Objects;

@SuppressWarnings("ALL")
public interface MainJavascriptInterface {


    default void mainShowToast(Context a, String b, int c, int d) {
        try {
            if (c == 0) {
                Toast.a(a, b, d);
            } else if (c == 1) {
                Toast.c(a, b);
            } else if (c == 2) {
                Toast.b(a, b);
            }
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

    default void mainCopyToClipboard(Context a, String b) {
        Clipboard.a(a, b);
    }

    default void mainVibrate(Context a, int b) {
        Vibrate.a(a, b);
    }

    default void mainEnableWifi(Context a, boolean b) {
        WifiManager wifiManager = (WifiManager) a.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(b);
    }

    default void mainExit() {
        W6.a();
    }

    default void mainShowNotification(Context a, String b, String c, String d) {
        MainNotification.b(a, Uri.parse(d).getHost(), a.getResources().getString(R.string.y20));
        android.app.Notification.Builder m =
                Notifications.a(a, Uri.parse(d).getHost());
        m.setSmallIcon(R.drawable.c);
        android.app.Notification.BigTextStyle bigText = new android.app.Notification.BigTextStyle();
        if (d.length() != 0) {
            bigText.setSummaryText(Uri.parse(d).getHost());
        } else {
            bigText.setSummaryText(a.getResources().getString(R.string.g31));
        }
        // if (HDMS.b(c)) {
        m.setContentText(c);
        bigText.bigText(c);
     /*   } else {
            m.setContentText(a.getResources().getString(R.string.g29));
bigText.bigText(a.getResources().getString(R.string.g29));
        }*/
        // if (HDMS.b(b)) {
        m.setContentTitle(b);
        bigText.setBigContentTitle(b);
       /* } else {
            m.setContentTitle(a.getResources().getString(R.string.g30));
            bigText.setBigContentTitle(a.getResources().getString(R.string.g30));
        }*/
        m.setStyle(bigText);

        m.setColor(Resources.b(a, R.color.a));

        SharedPreferences sq = PreferenceManager.getDefaultSharedPreferences(a);

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

        m.setLargeIcon(BitmapFactory.decodeResource(a.getResources(), R.drawable.c));
        if (d.length() != 0) {

            Intent it = new Intent(a, MAIN.class);
            it.putExtra("value", d);
            PendingIntent contentIntent = PendingIntent.getActivity(a, 0, it, PendingIntent.FLAG_UPDATE_CURRENT);
            m.setContentIntent(contentIntent);
            Intent j = new Intent(a, MAIN.class);
            j.putExtra("webvium", d);
            PendingIntent pi23 = PendingIntent.getActivity(a, 1, j, PendingIntent.FLAG_UPDATE_CURRENT);
            m.addAction(new android.app.Notification.Action(R.drawable.q, String.format(a.getResources().getString(R.string.g28), Objects.requireNonNull(Uri.parse(d).getHost())), pi23));
        }

        NotificationManager nmc = (NotificationManager) a.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        nmc.notify(Notifications.getRandomizeNotificationId(Notifications.DEFAULT), m.build());
    }

    default void mainEnableFlashlight(Context a, boolean b) {
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                CameraManager cm = (CameraManager) a.getSystemService(Context.CAMERA_SERVICE);
                cm.setTorchMode(cm.getCameraIdList()[0], b);
            } catch (CameraAccessException c5a) {
                DiagnosticData.a(c5a);
            }
        } else {
            try {
                if (a.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
                    Camera ca = Camera.open();
                    if (b) {
                        Camera.Parameters p = ca.getParameters();
                        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        ca.setParameters(p);
                        ca.startPreview();
                    } else {
                        ca.stopPreview();
                        ca.release();
                    }
                }
            } catch (Exception e) {
                DiagnosticData.a(e);
            }
        }
    }

}