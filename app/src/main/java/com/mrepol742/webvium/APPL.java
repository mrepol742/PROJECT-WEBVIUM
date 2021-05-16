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
// WEBVIEW

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;

import com.mrepol742.webvium.util.Hardware;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

// @class Application
// NOTE: This class must be instantiated in fraction of a second so this won't cause any slow loading of
// activities, broadcast receivers, and services

public class APPL extends Application {
    private SharedPreferences sp;
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;


    @Override
    public void onCreate() {
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        this.uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler((thread, ex) -> {
            Intent intent = new Intent(getApplicationContext(), EXCE.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("error", getStackTrace(ex));
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 11111, intent, PendingIntent.FLAG_ONE_SHOT);
            AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 1000, pendingIntent);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(2);
            uncaughtExceptionHandler.uncaughtException(thread, ex);
        });
        super.onCreate();
        try {
            if (sp.getBoolean("autoUpdate55", false)) {
                boolean bn = Hardware.isNightMode(this) == Hardware.E1.Yes;
                if (bn) {
                    if (!sp.getBoolean("autoUpdate", false)) {
                        g(true);
                    }
                } else {
                    if (!sp.getBoolean("autoUpdate", false)) {
                        g(false);
                    }
                }
            }
        } catch (Exception ex5) {
            ex5.printStackTrace();
        }
    }

    private void g(boolean bn) {
        SharedPreferences.Editor se = sp.edit();
        se.putBoolean("autoUpdate", bn);
        se.apply();
    }

    @Override
    public void onTrimMemory(int i) {
        if (i == ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL || i == ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE || i == ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW) {
            SQLiteDatabase.releaseMemory();
            System.gc();
        }
        super.onTrimMemory(i);
    }


    private String getStackTrace(Throwable th){
        final Writer result = new StringWriter();

        final PrintWriter printWriter = new PrintWriter(result);
        Throwable cause = th;

        while(cause != null){
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        final String stacktraceAsString = result.toString();
        printWriter.close();

        return stacktraceAsString;
    }
}

