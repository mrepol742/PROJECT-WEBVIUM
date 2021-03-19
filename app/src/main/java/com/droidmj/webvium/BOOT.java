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

import android.app.AlarmManager;
import android.app.DownloadManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;

import com.droidmj.webvium.app.Notifications;
import com.droidmj.webvium.app.main.MainReceiver;
import com.droidmj.webvium.content.Intents;
import com.droidmj.webvium.telemetry.DiagnosticData;

import java.util.Calendar;
import java.util.Objects;

// @Class BootCompleted
public class BOOT extends MainReceiver {

    @Override
    public void onReceive(Context a, Intent b) {
        super.onReceive(a, b);
        try {
            if (Objects.requireNonNull(b.getAction()).equals("android.intent.action.BOOT_COMPLETED")) {
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(a);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, 6);
                AlarmManager alarmMgr = (AlarmManager) a.getSystemService(Context.ALARM_SERVICE);
                try {
                    DownloadManager dm = (DownloadManager) a.getSystemService(Context.DOWNLOAD_SERVICE);
                    Cursor cursor = dm.query(new DownloadManager.Query());
                    if (cursor.moveToFirst()) {
                        if (cursor.getCount() > 0) {
                            int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                            if (status == DownloadManager.STATUS_RUNNING) {
                                Notifications.b(a, new Intent(a, DOWN0.class));
                            }
                        }
                    }
                    cursor.close();
                } catch (Exception n) {
                    DiagnosticData.a(n);
                }
                if (sp.getBoolean("qckS", false)) {
                    Intents.b(a, QUIC.class);
                }
                if (sp.getBoolean("acu", true)) {
                    PendingIntent it = PendingIntent.getService(a, 0, new Intent(a, UPDA.class), PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, it);
                }
                if (sp.getBoolean("pnd", true)) {
                    PendingIntent it = PendingIntent.getService(a, 0, new Intent(a, NOTI.class), PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, it);
                }
                if (sp.getBoolean("fltWeb", false)) {
                    Intents.b(a, FLOA.class);
                }
                if (sp.getBoolean("nCV", false)) {
                    Intents.b(a, CLIP.class);
                }
            }
        } catch (Exception en) {
            DiagnosticData.a(en);
        }
    }
}
