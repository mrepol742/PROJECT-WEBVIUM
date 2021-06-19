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

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.app.UiModeManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.preference.PreferenceManager;

import com.mrepol742.webvium.util.Hardware;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

// @class Application
public class APPL extends Application {
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
    private  SharedPreferences sp;

    @Override
    public void onCreate() {
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        if (sp.getBoolean("maUU", BuildConfig.DEBUG) && sp.getBoolean("stcMM", BuildConfig.DEBUG)) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
        }
        if (sp.getBoolean("maUU", BuildConfig.DEBUG) && sp.getBoolean("stcMM6", BuildConfig.DEBUG)) {
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
        }
        if (sp.getBoolean("thred", true)) {
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
        }
        super.onCreate();
        try {
            if (sp.getBoolean("autoUpdate55", false)) {
                boolean bn = Hardware.isNightMode(this) == Hardware.E1.Yes;
                if (!sp.getBoolean("autoUpdate", false) && bn) {
                    SharedPreferences.Editor se = sp.edit();
                    se.putBoolean("autoUpdate", true);
                    se.apply();
                }
            }
        } catch (Exception ex5) {
            ex5.printStackTrace();
        }
    }

    @Override
    public void onTrimMemory(int i) {
        if (sp.getBoolean("triMM", true) && (i == ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL || i == ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE || i == ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW)) {
            SQLiteDatabase.releaseMemory();
        }
        super.onTrimMemory(i);
    }

    private String getStackTrace(Throwable th){
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        Throwable cause = th;
        while (cause != null){
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        final String stacktraceAsString = result.toString();
        printWriter.close();
        return stacktraceAsString;
    }
}

