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

import android.app.DownloadManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.webkit.CookieManager;

import com.mrepol742.webvium.app.Notifications;
import com.mrepol742.webvium.app.main.MainReceiver;
import com.mrepol742.webvium.app.main.MainNotification;
import com.mrepol742.webvium.app.main.MainService;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.content.IntentsFilter;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.download.DownloadServiceDataModel;
import com.mrepol742.webvium.io.Files;
import com.mrepol742.webvium.io.StorageDirectory;
import com.mrepol742.webvium.manifest.Permission;
import com.mrepol742.webvium.widget.Toast;

import java.util.Objects;

// @Class DownloadService
public class DOWN0 extends MainService {
    private final R37 br = new R37();
    private final R7 br1 = new R7();
    private CookieManager cm;
    private SharedPreferences sp;
    private DownloadManager dm;

    private void a() {
        MainNotification.c(this, "d", getString(R.string.y19));
        android.app.Notification.Builder m = Notifications.a(this, "d");
        m.setSmallIcon(R.drawable.c2);
        m.setContentTitle(getResources().getString(R.string.d38));
        m.setOngoing(true);
        m.setColor(Resources.getColor(this, R.color.a));
        m.setAutoCancel(false);
        if (Build.VERSION.SDK_INT <= 26) {
            m.setPriority(android.app.Notification.PRIORITY_LOW);
        }
        m.setVisibility(android.app.Notification.VISIBILITY_PUBLIC);
        m.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.c2));
        startForeground(Notifications.g, m.build());
    }

    private void b(String jk, String ghg, String name, String url) {
        android.app.Notification.Builder m = Notifications.a(this, "c");
        m.setSmallIcon(R.drawable.c2);
        android.app.Notification.BigTextStyle bigText = new android.app.Notification.BigTextStyle();
        bigText.setSummaryText(Package.c());
        bigText.setBigContentTitle(ghg);
        String text = String.format(getString(R.string.u22), name, url, jk);
        bigText.bigText(text);
        m.setContentTitle(ghg);
        m.setContentText(text);
        m.setColor(Resources.getColor(this, R.color.e));
        m.setStyle(bigText);
        m.setAutoCancel(sp.getBoolean("eac", true));
        m.setDefaults(android.app.Notification.DEFAULT_ALL);
        if (Build.VERSION.SDK_INT <= 26) {
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
        m.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.c2));
        Intent it = new Intent(this, DOWN.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pi = PendingIntent.getActivity(this, 2, it, PendingIntent.FLAG_UPDATE_CURRENT);
        m.setContentIntent(pi);
        NotificationManager nmc = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nmc.notify(Notifications.getRandomizeNotificationId(Notifications.DEFAULT), m.build());
    }

    private void c(DownloadServiceDataModel w9) {
        switch (w9.a) {
            case DownloadManager.ERROR_CANNOT_RESUME:
                g();
                b(getString(R.string.w30), getString(R.string.u21), w9.c, w9.e);
                s2();
                break;
            case DownloadManager.ERROR_DEVICE_NOT_FOUND:
                g();
                b(getString(R.string.x21), getString(R.string.u21), w9.c, w9.e);
                s2();
                break;
            case DownloadManager.ERROR_FILE_ALREADY_EXISTS:
                g();
                b(getString(R.string.x22), getString(R.string.u21), w9.c, w9.e);
                s2();
                break;
            case DownloadManager.ERROR_FILE_ERROR:
                g();
                b(getString(R.string.x23), getString(R.string.u21), w9.c, w9.e);
                s2();
                break;
            case DownloadManager.ERROR_HTTP_DATA_ERROR:
                g();
                b(getString(R.string.x24), getString(R.string.u21), w9.c, w9.e);
                s2();
                break;
            case DownloadManager.ERROR_INSUFFICIENT_SPACE:
                g();
                b(getString(R.string.x25), getString(R.string.u21), w9.c, w9.e);
                s2();
                break;
            case DownloadManager.ERROR_TOO_MANY_REDIRECTS:
                g();
                b(getString(R.string.x26), getString(R.string.u21), w9.c, w9.e);
                s2();
                break;
            case DownloadManager.ERROR_UNHANDLED_HTTP_CODE:
                g();
                b(getString(R.string.x27), getString(R.string.u21), w9.c, w9.e);
                s2();
                break;
            case DownloadManager.ERROR_UNKNOWN:
                g();
                b(getString(R.string.x28), getString(R.string.u21), w9.c, w9.e);
                s2();
                break;
            case DownloadManager.PAUSED_QUEUED_FOR_WIFI:
                h();
                b(getString(R.string.x29), getString(R.string.x35), w9.c, w9.e);
                break;
            case DownloadManager.PAUSED_UNKNOWN: // conflicting value 4 with paused
                h();
                b(getString(R.string.x30), getString(R.string.x35), w9.c, w9.e);
                break;
            case DownloadManager.PAUSED_WAITING_FOR_NETWORK: // conflicting value 1 with running
                h();
                b(getString(R.string.x31), getString(R.string.x35), w9.c, w9.e);
                break;
            case DownloadManager.PAUSED_WAITING_TO_RETRY: // conflicting value 2 with pending
                h();
                b(getString(R.string.x32), getString(R.string.x35), w9.c, w9.e);
                break;
            case DownloadManager.STATUS_FAILED:
                g();
                b(getString(R.string.x33), getString(R.string.u21), w9.c, w9.e);
                s2();
                break;
            case DownloadManager.STATUS_SUCCESSFUL:
                java.io.File file4 = new java.io.File(w9.d.replace("file://", ""));
                if (file4.renameTo(new java.io.File(w9.d.replace("file://", "").replace(".webvium", "")))) {
                    f(w9.c);
                    if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("wifi2", false)) {
                        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                        mediaScanIntent.setData(Uri.parse(w9.d.replace(".webvium", "")));
                        sendBroadcast(mediaScanIntent);
                    }
                }
                Toast.a(this, getString(R.string.x34));
                s2();
                break;

        }
    }

    private void e(Intent a) {
        Files.createNewFolder(StorageDirectory.getDownloadDir());
        String sg = a.getStringExtra("a"); // title
        String sg0 = a.getStringExtra("b"); // link
        boolean sg1 = a.getBooleanExtra("c", false); // visible
        boolean sg2 = a.getBooleanExtra("d", false); //notif
        String sg3 = a.getStringExtra("e"); // mimetyoe
        String sg4 = a.getStringExtra("f"); // cookie
        String sg5 = a.getStringExtra("g"); //us
        try {
            DownloadManager.Request f = new DownloadManager.Request(Uri.parse(sg0));
            //if (HDMS.b(sg)) {
            f.setTitle(sg);
            // } else {
            //    f.setTitle(getString(R.string.e26));
            //}
            if (Build.VERSION.SDK_INT < 29 && !sg1) {
                f.setVisibleInDownloadsUi(false);
            } else if (Build.VERSION.SDK_INT < 29) {
                f.setVisibleInDownloadsUi(true);
            }
            if (sg2) {
                f.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
            } else {
                f.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
            }
            if (sp.getBoolean("drh1", true)) {
                f.setMimeType(sg3);
            }
            f.setAllowedOverMetered(sp.getBoolean("drh2", true));
            f.setAllowedOverRoaming(sp.getBoolean("drh3", false));
            if (Build.VERSION.SDK_INT >= 24) {
                f.setRequiresCharging(sp.getBoolean("drh4", false));
                f.setRequiresDeviceIdle(sp.getBoolean("drh5", false));
            }
            f.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
            if (sp.getBoolean("drh", true)) {
                f.addRequestHeader("cookie", cm.getCookie(sg4));
            }
            if (sp.getBoolean("drh0", true)) {
                f.addRequestHeader("User-Agent", sg5);
            }
            if (Build.VERSION.SDK_INT >= 30) {
                f.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, sg + "." + Package.c().toLowerCase());
            } else if (Build.VERSION.SDK_INT >= 23 && Permission.checkOnly(this, Permission.STORAGE)) {
                f.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, sg + "." + Package.c().toLowerCase());
            } else {
                f.setDestinationInExternalPublicDir(Package.c() + "/Downloads", sg + "." + Package.c().toLowerCase());
            }
            DownloadManager.Query dmq = new DownloadManager.Query();
            dmq.setFilterById(dm.enqueue(f), 0);
            Cursor cursor = dm.query(dmq);
            if (cursor.moveToFirst()) {
                if (cursor.getCount() > 0) {
                    c(new DownloadServiceDataModel(cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)), cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE)), cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)), cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_URI))));
                }
            }
            cursor.close();
        } catch (Exception en) {
            en.printStackTrace();
        }
    }

    private void f(String jk) {
        android.app.Notification.Builder m = Notifications.a(this, "c");
        m.setSmallIcon(R.drawable.c2);
        android.app.Notification.BigTextStyle bigText = new android.app.Notification.BigTextStyle();
        bigText.setSummaryText(Package.c());
        //if (HDMS.b(jk)) {
        bigText.setBigContentTitle(getString(R.string.u20));
        m.setContentTitle(getString(R.string.u20));
        m.setContentText(jk);
        bigText.bigText(jk);
        /*} else {
            bigText.setBigContentTitle(ct.getResources().getString(R.string.u20));
            m.setContentTitle(ct.getResources().getString(R.string.u20));
        }*/
        m.setStyle(bigText);
        m.setColor(Resources.getColor(this, R.color.a));
        m.setAutoCancel(sp.getBoolean("eac", true));
        m.setDefaults(android.app.Notification.DEFAULT_ALL);
        if (Build.VERSION.SDK_INT <= 26) {
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

        m.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.c2));

        Intent it = new Intent(this, DOWN.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pi = PendingIntent.getActivity(this, 234, it,
                PendingIntent.FLAG_UPDATE_CURRENT);
        m.setContentIntent(pi);
        NotificationManager nmc = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nmc.notify(Notifications.getRandomizeNotificationId(Notifications.DEFAULT), m.build());
    }

    private void g() {
        Toast.a(this, getString(R.string.u21));
        s1();
    }

    private void h() {
        Toast.a(this, getString(R.string.x35));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        a();
        cm = CookieManager.getInstance();
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        registerReceiver(br, new IntentsFilter("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED"));
        registerReceiver(br1, new IntentsFilter("android.intent.action.DOWNLOAD_COMPLETE"));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        e(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(br);
        unregisterReceiver(br1);
        MainNotification.a(this, Notifications.g);
        s2();
    }

    static class R37 extends MainReceiver {

        @Override
        public void onReceive(Context a, Intent b) {
            super.onReceive(a, b);
            try {
                if (Objects.requireNonNull(b.getAction()).equals("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED")) {
                    Intents.a(a, DOWN.class);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class R7 extends MainReceiver {

        @Override
        public void onReceive(Context a, Intent b) {
            super.onReceive(a, b);
            if (Objects.requireNonNull(b.getAction()).equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                if (PreferenceManager.getDefaultSharedPreferences(a).getBoolean("launchF", false)) {
                    Intents.a(a, DOWN.class);
                }
                DownloadManager.Query dmq = new DownloadManager.Query();
                dmq.setFilterById(b.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0));
                Cursor cursor = dm.query(dmq);
                if (cursor.moveToFirst()) {
                    if (cursor.getCount() > 0) {
                        c(new DownloadServiceDataModel(cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)), cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE)), cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)), cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_URI))));
                    }
                }
                cursor.close();
            }

        }
    }

}