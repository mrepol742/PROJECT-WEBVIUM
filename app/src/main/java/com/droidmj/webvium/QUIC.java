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
import android.graphics.BitmapFactory;
import android.os.Build;

import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.app.Notifications;
import com.droidmj.webvium.app.main.MainNotification;
import com.droidmj.webvium.app.main.MainService;
import com.droidmj.webvium.content.Resources;

// @Class QuickSearch
public class QUIC extends MainService {

    @Override
    public int onStartCommand(Intent a1, int c555, int c) {
        MainNotification.b(this, "c", getString(R.string.y18));
        android.app.Notification.Builder m = Notifications.a(this, "c");
        m.setSmallIcon(R.drawable.a18);
        m.setContentTitle(getString(R.string.l33));
        m.setContentText(getString(R.string.n39));
        m.setOngoing(true);
        m.setColor(Resources.b(this, R.color.a));
        m.setAutoCancel(false);
        Intent j11 = new Intent(this, SEAR.class);

        PendingIntent k567 = PendingIntent.getActivity(this, 0, j11, PendingIntent.FLAG_UPDATE_CURRENT);
        m.setContentIntent(k567);
        if (Build.VERSION.SDK_INT <= 26) {
            m.setPriority(android.app.Notification.PRIORITY_LOW);
        }
        m.setVisibility(android.app.Notification.VISIBILITY_PUBLIC);
        m.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.b));
        NotificationManager nmc = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nmc.notify(BuildConfiguration.Notification.d, m.build());
        s1();
        return super.onStartCommand(a1, c555, c);
    }

}