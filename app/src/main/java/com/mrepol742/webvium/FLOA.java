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
import android.graphics.BitmapFactory;
import android.os.Build;

import com.mrepol742.webvium.app.Notifications;
import com.mrepol742.webvium.app.main.MainNotification;
import com.mrepol742.webvium.app.main.MainService;
import com.mrepol742.webvium.content.Resources;

// @Class FloatingService
public class FLOA extends MainService {

    @Override
    public int onStartCommand(Intent a, int c, int f) {
     /*   WindowManager.LayoutParams wl = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, PixelFormat.TRANSLUCENT);

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams")
        View vw = li.inflate(R.layout.test, null);
        vw.setOnTouchListener(new OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View Views, MotionEvent event) {
                Toast.a(FLW.this,"Removing view");
                wm.removeView(vw);
                return true;
            }
        });
        wm.addView(vw, wl);
*/
        MainNotification.c(this, "j", getString(R.string.l34));
        android.app.Notification.Builder m = Notifications.a(this, "j");
        m.setSmallIcon(R.drawable.a18);
        m.setContentTitle(getString(R.string.l32));
        m.setContentText(getString(R.string.n40));
        m.setOngoing(true);
        m.setColor(Resources.b(this, R.color.a));
        m.setAutoCancel(false);
        Intent j11 = new Intent(this, MAIN.class);

        PendingIntent k567 = PendingIntent.getActivity(this, 0, j11, PendingIntent.FLAG_UPDATE_CURRENT);
        m.setContentIntent(k567);
        if (Build.VERSION.SDK_INT <= 26) {
            m.setPriority(android.app.Notification.PRIORITY_MIN);
        }
        m.setVisibility(android.app.Notification.VISIBILITY_PUBLIC);
        m.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.c));
        NotificationManager nmc = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nmc.notify(Notifications.e, m.build());
        s1();
        return super.onStartCommand(a, c, f);
    }

}