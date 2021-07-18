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

package com.mrepol742.webvium.app;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.webkit.JavascriptInterface;

import com.mrepol742.webvium.MAIN;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.annotation.Keep;
import com.mrepol742.webvium.app.main.MainNotification;
import com.mrepol742.webvium.content.Clipboard;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.os.Vibrate;
import com.mrepol742.webvium.widget.AwesomeToast;

import java.util.Objects;

@SuppressWarnings("ALL")
public class WebviumJSI {
    private Context a;
    private Camera camera;
    private Camera.Parameters params;

    public WebviumJSI(Context c) {
        a = c;
    }

    @Keep
    private WebviumJSI() {
    }

    @JavascriptInterface
    public void showToast(String c) {
        mainShowToast(a, c, 0, 0);
    }

    @JavascriptInterface
    public void showToastError(String c) {
        mainShowToast(a, c, 1, 0);
    }

    @JavascriptInterface
    public void showToastSuccess(String c) {
        mainShowToast(a, c, 2, 0);
    }

    @JavascriptInterface
    public void showToast(String c, int b) {
        mainShowToast(a, c, 0, b);
    }

    @JavascriptInterface
    public void showToastError(String c, int b) {
        mainShowToast(a, c, 1, b);
    }

    @JavascriptInterface
    public void showToastSuccess(String c, int b) {
        mainShowToast(a, c, 2, b);
    }

    @JavascriptInterface
    public void copyToClipboard(String c) {
        Clipboard.a(a, c);
    }

    @JavascriptInterface
    public void vibrate(int c) {
        Vibrate.a(a, c);
    }

    @JavascriptInterface
    public void enableWifi(boolean c) {
        WifiManager wifiManager = (WifiManager) a.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(c);
    }

    @JavascriptInterface
    public int currentVersion() throws PackageManager.NameNotFoundException {
        return Integer.parseInt(Package.e(a).replaceAll("\\.", ""));
    }

    @JavascriptInterface
    public void print() {

    }

    @JavascriptInterface
    public void showNotification(String b, String c, String d) {
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

        m.setColor(Resources.getColor(a, R.color.a));

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

    @JavascriptInterface
    public void enableFlashlight(boolean c) {
        try {
            if (Build.VERSION.SDK_INT > 23) {
                CameraManager cm = (CameraManager) a.getSystemService(Context.CAMERA_SERVICE);
                cm.setTorchMode(cm.getCameraIdList()[0], c);
            } else {
                if (c) {
                    camera = Camera.open();
                    params = camera.getParameters();
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(params);
                    camera.startPreview();
                } else {
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    camera.setParameters(params);
                    camera.stopPreview();
                    camera.release();
                    camera = null;
                    params = null;
                }
            }
        } catch (Exception c5a) {
            c5a.printStackTrace();
        }
    }

    private void mainShowToast(Context a, String b, int c, int d) {
        switch (c) {
            default:
            case 0:
                AwesomeToast.a(a, b, d);
                break;
            case 1:
                AwesomeToast.c(a, b);
                break;
            case 2:
                AwesomeToast.b(a, b);
                break;
        }
    }
}