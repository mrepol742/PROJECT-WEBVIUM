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

package com.droidmj.webvium;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.preference.PreferenceManager;

import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.app.Notifications;
import com.droidmj.webvium.app.main.MainNotification;
import com.droidmj.webvium.app.main.MainService;
import com.droidmj.webvium.content.Package;
import com.droidmj.webvium.content.Resources;
import com.droidmj.webvium.net.Connectivity;
import com.droidmj.webvium.telemetry.DiagnosticData;
import com.droidmj.webvium.util.Base64;
import com.droidmj.webvium.util.Stream;

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
        if (Connectivity.isThereAnyInternetConnection(this) && Connectivity.isRestrictBackground(this)) {
            s1();
        }
        e();
        s1();
        return super.onStartCommand(a, c, d);
    }

    private void e() {
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("b", 0);
            int b = Integer.parseInt(Package.e(this).replaceAll("\\.", ""));
            int newUpdate = Stream.i(Base64.decode(sharedPreferences.getString(WELC.TEMP_UPDATE_VERSION, "")) + "?raw=true");
            if (newUpdate > b) {
                f(getString(R.string.x11), getString(R.string.x12), Base64.decode(sharedPreferences.getString(WELC.TEMP_UPDATE_URL, "")) + "?raw=true");
                if (BuildConfiguration.isDevelopment)
                    DiagnosticData.a("Package Update =" + newUpdate);
            }
        } catch (PackageManager.NameNotFoundException w) {
            DiagnosticData.a(w);
        }

    }

    private void f(String title, String text, String url) {
        MainNotification.b(this, "g", getString(R.string.z2));
        android.app.Notification.Builder m = Notifications.a(this, "g");
        m.setSmallIcon(R.drawable.j);
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
        m.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.j));
        Intent j = new Intent(this, MAIN.class);
        j.putExtra("value", url);
        PendingIntent k = PendingIntent.getActivity(this, 1, j, PendingIntent.FLAG_UPDATE_CURRENT);
        m.setContentIntent(k);

        PendingIntent pi23 = PendingIntent.getActivity(this, 0, j, PendingIntent.FLAG_UPDATE_CURRENT);
        m.addAction(new android.app.Notification.Action(R.drawable.c2, getString(R.string.b32), pi23));

        NotificationManager nmc = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nmc.notify(Notifications.c, m.build());
    }


}