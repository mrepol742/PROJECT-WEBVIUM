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

package com.droidmj.webvium.app.main;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import com.droidmj.webvium.telemetry.DiagnosticData;

import java.util.Objects;

public class MainNotification {
    public static void a(Context ctx, int notifyId) {
        NotificationManager nMgr = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        nMgr.cancel(notifyId);
    }

    public static void a(Context ctx) {
        NotificationManager nMgr = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        nMgr.cancelAll();
    }

    @TargetApi(Build.VERSION_CODES.O)
    public static void b(Context ctx, String a, String b) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return;
        }
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ctx);
        NotificationChannel nc = new NotificationChannel(a, a, NotificationManager.IMPORTANCE_DEFAULT);
        try {
            if (Objects.equals(sp.getString("py", ""), "7x")) {
                nc = new NotificationChannel(a, a, NotificationManager.IMPORTANCE_HIGH);
            }
            if (Objects.equals(sp.getString("py", ""), "30x")) {
                nc = new NotificationChannel(a, a, NotificationManager.IMPORTANCE_LOW);
            }
            if (Objects.equals(sp.getString("py", ""), "60x")) {
                nc = new NotificationChannel(a, a, NotificationManager.IMPORTANCE_MAX);
            }
            if (Objects.equals(sp.getString("py", ""), "120x")) {
                nc = new NotificationChannel(a, a, NotificationManager.IMPORTANCE_MIN);
            }
            if (Objects.equals(sp.getString("py", ""), "240x")) {
                nc = new NotificationChannel(a, a, NotificationManager.IMPORTANCE_NONE);
            }
            if (Objects.equals(sp.getString("py", ""), "480x")) {
                nc = new NotificationChannel(a, a, NotificationManager.IMPORTANCE_UNSPECIFIED);
            }
            nc.setDescription(b);
            NotificationManager nm = ctx.getSystemService(NotificationManager.class);
            nm.createNotificationChannel(nc);
        } catch (Exception ec) {
            DiagnosticData.a(ec);
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    public static void c(Context c, String a, String b) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return;
        }
        try {
            NotificationChannel nc = new NotificationChannel(a, a, NotificationManager.IMPORTANCE_MIN);
            nc.setDescription(b);
            NotificationManager nm = c.getSystemService(NotificationManager.class);
            nm.createNotificationChannel(nc);
        } catch (Exception ec) {
            DiagnosticData.a(ec);
        }
    }

    public static void d(Context c) {

    }

}