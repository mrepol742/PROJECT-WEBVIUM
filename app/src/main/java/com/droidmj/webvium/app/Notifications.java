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

package com.droidmj.webvium.app;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.Random;

public class Notifications {
    public static final int DEFAULT = 10;
    public static final int FOREGROUND = 20;
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 5;
    public static final int f = 6;
    public static final int g = 7;
    public static final int h = 8;
    public static final int i = 9;

    public static Notification.Builder a(Context ct, String ch) {
        if (Build.VERSION.SDK_INT >= 26) {
            return new android.app.Notification.Builder(ct, ch);
        }
        return new android.app.Notification.Builder(ct);
    }

    public static void b(Context ct, Intent cn) {
        if (Build.VERSION.SDK_INT >= 26) {
            ct.startForegroundService(cn);
        } else {
            ct.startService(cn);
        }
    }

    public static int getRandomizeNotificationId(int a) {
        Random random = new Random();
        return a + random.nextInt(BuildConfiguration.Application.NOTIFICATION_DEFAULT_ID_MULTIPLIER);

    }

}