/*
 *
 *
 *
 * DROID MJ Property || Confidential
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
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

import com.mrepol742.webvium.app.BuildConfiguration;
import com.mrepol742.webvium.app.Notifications;
import com.mrepol742.webvium.app.main.MainNotification;
import com.mrepol742.webvium.app.main.MainService;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.net.Connectivity;
import com.mrepol742.webvium.telemetry.DiagnosticData;
import com.mrepol742.webvium.util.Base64;
import com.mrepol742.webvium.util.Stream;

import java.util.Objects;

// @Class NotificationService
public class NOTI extends MainService {

    private SharedPreferences sp;

    @Override
    public void onCreate() {
        super.onCreate();
        sp = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    public int onStartCommand(Intent a, int c, int d) {
        if (Connectivity.isThereAnyInternetConnection(this) && Connectivity.isRestrictBackground(this)) {
            s1();
        }
        g();
        s1();
        return super.onStartCommand(a, c, d);
    }

    private void f(String title, String text, String url) {
        MainNotification.b(this, "h", getString(R.string.z3));
        android.app.Notification.Builder m = Notifications.a(this, "h");
        m.setSmallIcon(R.drawable.c);
        android.app.Notification.BigTextStyle bigText = new android.app.Notification.BigTextStyle();
        bigText.bigText(text);
        bigText.setBigContentTitle(title);
        bigText.setSummaryText(Package.c());
        m.setContentTitle(title);
        m.setContentText(text);
        m.setStyle(bigText);
        m.setColor(Resources.b(this, R.color.a));
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
        Intent j = new Intent(this, MAIN.class);
        j.putExtra("value", url);
        PendingIntent k = PendingIntent.getActivity(this, 1, j, PendingIntent.FLAG_UPDATE_CURRENT);
        m.setContentIntent(k);
        Intent j55 = new Intent(this, MAIN.class);
        j55.putExtra("webvium", url);
        PendingIntent pi235 = PendingIntent.getActivity(this, 2, j55, PendingIntent.FLAG_UPDATE_CURRENT);
        m.addAction(new android.app.Notification.Action(R.drawable.q, getString(R.string.g28)+ Objects.requireNonNull(Uri.parse(url).getHost()), pi235));
        NotificationManager nmc = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nmc.notify(Notifications.a, m.build());
    }

    public void g() {
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("b", 0);
            int notif = Stream.g(Base64.decode(sharedPreferences.getString(WELC.TEMP_NOTIFICATION_STATE, "")) + "?raw=true");
            if (notif > 0) {
                String neTf = Stream.f(Base64.decode(sharedPreferences.getString(WELC.TEMP_NOTIFICATION_DATA, "")) + "?raw=true", getString(R.string.c33));
                String[] sp = neTf.split(";");
                SharedPreferences j988 = getSharedPreferences("wv,", 0);
                if (!Objects.requireNonNull(j988.getString("notif1", "")).equals(sp[0]) && !Objects.requireNonNull(j988.getString("notif2", "")).equals(sp[1])) {
                    f(sp[0], sp[1], sp[2]);
                    if (BuildConfiguration.isDevelopment) {
                        DiagnosticData.a("Push Notifications =" + sp[0] + " " + sp[1] + " " + sp[2]);
                    }
                    SharedPreferences.Editor gujh = j988.edit();
                    gujh.putString("notif1", sp[0]);
                    gujh.putString("notif2", sp[1]);
                    gujh.apply();
                }
            }
        } catch (Exception en) {
            DiagnosticData.a(en);
        }
    }

}