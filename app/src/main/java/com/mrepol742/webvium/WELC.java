/*
 *
 * Created by Melvin Jones Repol on 4/17/21 10:27 AM
 * Copyright (c) 2021 . All rights reserved. Melvin Jones Repol(mrepol742.github.io)
 * Last modified 4/17/21 10:26 AM
 *
 *  License under the GNU General Public License, Version 3.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *  https://www.gnu.org/licenses/gpl-3.0.en.html
 *  Unless required by the applicable law or agreed in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.mrepol742.webvium;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mrepol742.webvium.annotation.Test;
import com.mrepol742.webvium.app.BuildConfiguration;
import com.mrepol742.webvium.app.W6;
import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.io.StorageDirectory;
import com.mrepol742.webvium.os.CountDownTimer;
import com.mrepol742.webvium.security.AppID;
import com.mrepol742.webvium.telemetry.DiagnosticData;
import com.mrepol742.webvium.text.Html;
import com.mrepol742.webvium.util.Base64;
import com.mrepol742.webvium.util.cache.FontCache;
import com.mrepol742.webvium.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

// @Class WelcomeScreen
public class WELC extends BaseActivity {
    public RelativeLayout rl;
    public ImageView iv;
    public TextView tv;
    public FrameLayout fl;
    private A17a timer;
    private boolean bn = false;
    private Animation d;
    public static final String TEMP_UPDATE_URL = "a";
    public static final String TEMP_UPDATE_VERSION = "b";
    public static final String TEMP_NOTIFICATION_STATE = "c";
    public static final String TEMP_NOTIFICATION_DATA = "d";
    public static final String TEMP_B = "e";

    @Override
    protected void onCreate(Bundle be) {
        theme(T_WELCOME_SCREEN);
        super.onCreate(be);
        a225(R.layout.p);
        // Clipboard.a(this, Base64.b(Hash.a(new HashDataModel("SHA-512", Arrays.toString(Package.d(this, Package.b(), 0))))));
        // Toast.a(this, "Copied to clipboard");
        rl = findViewById(R.id.h9);
        iv = findViewById(R.id.b20);
        tv = findViewById(R.id.c6);
        fl = findViewById(R.id.h10);
        tv.setText(Package.c());
        rl.setBackgroundResource(R.drawable.a11);
        rl.setElevation(5);
        iv.setImageResource(R.drawable.b14);
        tv.setTypeface(type(Typeface.BOLD));
        RelativeLayout ll = findViewById(R.id.h11);
        if (!a221().getBoolean("autoUpdate", false)) {
            ll.setBackgroundColor(Resources.b(this, R.color.b));
            fl.setBackgroundColor(Resources.b(this, R.color.b));
        } else {
            ll.setBackgroundColor(Resources.b(this, R.color.n));
            fl.setBackgroundColor(Resources.b(this, R.color.n));
        }
        tv.setTextColor(Resources.b(this, R.color.b));
        d = AnimationUtils.loadAnimation(this, R.anim.h);
        iv.startAnimation(d);
        timer = new A17a(5000, 5000);
        timer.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timer == null && !bn) {
            timer = new A17a(3000, 3000);
            timer.start();
            iv.startAnimation(d);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (timer != null) {
            timer.cancel();
            timer = null;
            d.cancel();
        }
    }

    @Test
    private void a() {
        SharedPreferences sp3 = getSharedPreferences("b", 0);
        SharedPreferences.Editor editor = sp3.edit();
        String formatter = "aHR0cHM6Ly9naXRodWIuY29tL21yZXBvbDc0Mi9TZXJ2ZXIvYmxvYi9tYXN0ZXIv";
        editor.putString(TEMP_UPDATE_URL, Base64.encode(Base64.decode(formatter) + "update_url.txt")); // update
        editor.putString(TEMP_UPDATE_VERSION, Base64.encode(Base64.decode(formatter) + "update_version.txt")); // update version
        editor.putString(TEMP_NOTIFICATION_STATE, Base64.encode(Base64.decode(formatter) + "notification_state.txt")); // notification status
        editor.putString(TEMP_NOTIFICATION_DATA, Base64.encode(Base64.decode(formatter) + "notification_data.txt")); // notification url
        editor.putString(TEMP_B, Base64.encode(Base64.decode(formatter) + "b.txt")); // B
        editor.apply();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void a2() {
        View c = View.inflate(this, R.layout.a3, null);
        TextView ed = c.findViewById(R.id.n23);
        final Button bn = c.findViewById(R.id.n24);
        bn.setText(getString(R.string.n25));
        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(Resources.b(this, R.color.c));
        } else {
            ed.setTextColor(Resources.b(this, R.color.b));
        }
        ScrollView sv = c.findViewById(R.id.j);
        bn.setTypeface(type(Typeface.BOLD));
        bn.setAllCaps(true);
        sv.getViewTreeObserver()
                .addOnScrollChangedListener(() -> {
                    if (sv.getChildAt(0).getBottom()
                            <= (sv.getHeight() + sv.getScrollY())) {
                        bn.setOnClickListener(view -> {
                            b();
                            fl.removeView(c);
                        });

                    }
                });
        sv.setOnTouchListener((v, event) -> (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0);
        ed.setTypeface(type(Typeface.NORMAL));
        Html.a(ed, getString(R.string.n23));
        fl.addView(c);
        Toast.a(this, getString(R.string.y62));
    }

    @SuppressLint("ClickableViewAccessibility")
    public void b() {
        View c = View.inflate(this, R.layout.a3, null);
        TextView ed = c.findViewById(R.id.n23);
        final Button bn = c.findViewById(R.id.n24);
        ScrollView sv = c.findViewById(R.id.j);

        sv.getViewTreeObserver()
                .addOnScrollChangedListener(() -> {
                    if (sv.getChildAt(0).getBottom()
                            <= (sv.getHeight() + sv.getScrollY())) {
                        bn.setOnClickListener(view -> {

                            fl.removeView(c);
                            l3();
                            l6();
                        });
                    }
                });
        sv.setOnTouchListener((v, event) -> (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0);
        bn.setText(getString(R.string.n25));
        bn.setAllCaps(true);
        bn.setTypeface(type(Typeface.BOLD));
        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(Resources.b(this, R.color.c));
        } else {
            ed.setTextColor(Resources.b(this, R.color.b));
        }
        Html.a(ed, getString(R.string.n24));
        ed.setTypeface(type(Typeface.NORMAL));
        fl.addView(c);
        Toast.a(this, getString(R.string.y62));
    }

    private void l3() {
        a();
        SharedPreferences a5 = getSharedPreferences("dnr", 0);
        SharedPreferences.Editor b5 = a5.edit();
        b5.putInt("wlc1", 275);
        b5.apply();
        Intents.b(this, BACK.class);
        String sg = AppID.getAppID(this);
        if (BuildConfiguration.isDevelopment) {
            DiagnosticData.a("Webvium ID = " + sg);
        }
        Runnable re = () -> {
            try {
                InputStream fos = getAssets().open(FontCache.MAVEN_PRO);
                OutputStream d = new FileOutputStream(StorageDirectory.getClasses(this));
                byte[] e = new byte[1024];
                int f;
                while ((f = fos.read(e)) != -1) {
                    d.write(e, 0, f);
                }
                d.flush();
                d.close();
                fos.close();
                File fea = new File(StorageDirectory.getClasses(this));
                fea.setReadOnly();
            } catch (Exception en) {
                DiagnosticData.a(en);
            }
        };
        new Thread(re).start();
        SharedPreferences a52 = getSharedPreferences("di", 0);
        SharedPreferences.Editor b52 = a52.edit();
        b52.putString("di", sg);
        b52.apply();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        PendingIntent it = PendingIntent.getService(this, 0, new Intent(this, UPDA.class), PendingIntent.FLAG_UPDATE_CURRENT);
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, it);
        PendingIntent it1 = PendingIntent.getService(this, 0, new Intent(this, NOTI.class), PendingIntent.FLAG_UPDATE_CURRENT);
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, it1);
        PendingIntent it2 = PendingIntent.getService(this, 0, new Intent(this, UPDA0.class), PendingIntent.FLAG_UPDATE_CURRENT);
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, it2);
        finish();
        Intent intent = new Intent(this, UPDA.class);
        intent.putExtra("sta", "non");
        startService(intent);
        Intent intent2 = new Intent(this, NOTI.class);
        intent2.putExtra("sta", "non");
        startService(intent2);
        Intent intent3 = new Intent(this, UPDA0.class);
        intent3.putExtra("sta", "non");
        startService(intent3);
        overridePendingTransition(R.anim.f, R.anim.b);
    }

    private void l6() {
        try {
            Intent e = new Intent(Intents.ACTION_LAUNCH);
            e.addCategory(Intents.CATEGORY_GENIUS);
            e.setPackage(Package.b());
            e.putExtra("duplicate", false);
            Intent f = new Intent();
            f.putExtra("android.intent.extra.shortcut.INTENT", e);
            f.putExtra("android.intent.extra.shortcut.NAME", Package.c());
            f.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", ShortcutIconResource.fromContext(this, R.mipmap.c));
            f.putExtra("duplicate", false);
            f.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(f);
        } catch (Exception et) {
            DiagnosticData.a(et);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
            W6.a();
            return true;
        }
        return false;
    }

    class A17a extends CountDownTimer {

        public A17a(long a, long b) {
            super(a, b);

        }

        @Override
        public void onFinish() {
            WELC.this.rl.setVisibility(View.GONE);
            WELC.this.a2();
            WELC.this.bn = true;
        }
    }
}