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

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
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

import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.io.StorageDirectory;
import com.mrepol742.webvium.os.CountDownTimer;
import com.mrepol742.webvium.security.Hash;
import com.mrepol742.webvium.text.Html;
import com.mrepol742.webvium.util.AppID;
import com.mrepol742.webvium.util.cache.FontCache;
import com.mrepol742.webvium.widget.Toast;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

// @Class WelcomeScreen
public class WELC extends BaseActivity {
    public RelativeLayout rl;
    public ImageView iv;
    public TextView tv;
    public FrameLayout fl;
    private A17a timer;
    private Animation d;
    public static final String INIT = "init_12";
    private RelativeLayout ll;

    @Override
    protected void onCreate(Bundle be) {
        theme(T_WELCOME_SCREEN);
        super.onCreate(be);
        a225(R.layout.p);
        rl = findViewById(R.id.h9);
        iv = findViewById(R.id.b20);
        tv = findViewById(R.id.c6);
        fl = findViewById(R.id.h10);
        tv.setText(getString(R.string.y63));
        rl.setBackgroundResource(R.drawable.a11);
        rl.setElevation(5);
        iv.setImageResource(R.drawable.b14);
        tv.setTypeface(type(Typeface.BOLD));
        ll = findViewById(R.id.h11);
        tv.setTextColor(Resources.getColor(this, R.color.b));
        d = AnimationUtils.loadAnimation(this, R.anim.h);
        iv.startAnimation(d);
        timer = new A17a(5000, 5000);
        timer.start();
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
            } catch (Exception en) {
                en.printStackTrace();
            }
        };
        new Thread(re).start();
        try {
            ll.setBackgroundResource(R.drawable.f11);
        } catch (Exception en) {
            Toast.a(this, en.getMessage());
            en.printStackTrace();
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timer == null) {
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

    @SuppressLint("ClickableViewAccessibility")
    public void a2() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!a221().getBoolean("autoUpdate", false)) {
                if (!a221().getBoolean("webviumB", false)) {
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                }
            }
        }
        View c = View.inflate(this, R.layout.a3, null);
        TextView ed = c.findViewById(R.id.n23);
        final Button bn = c.findViewById(R.id.n24);
        TextView ed1 = c.findViewById(R.id.o28);
        ed1.setText(getString(R.string.f12).toUpperCase());
        bn.setBackgroundResource(R.drawable.c11);
        com.mrepol742.webvium.view.Animation.animate(this, R.anim.i, bn);
        bn.setText(getString(R.string.y62));
        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(Resources.getColor(this, R.color.c));
            ed1.setTextColor(Resources.getColor(this, R.color.c));
            fl.setBackgroundColor(Resources.getColor(this, R.color.b));
        } else {
            ed.setTextColor(Resources.getColor(this, R.color.b));
            ed1.setTextColor(Resources.getColor(this, R.color.b));
            fl.setBackgroundColor(Resources.getColor(this, R.color.n));
        }
        ScrollView sv = c.findViewById(R.id.j);
        bn.setTypeface(type(Typeface.BOLD));
        bn.setAllCaps(true);
        sv.getViewTreeObserver()
                .addOnScrollChangedListener(() -> {
                    if (sv.getChildAt(0).getBottom()
                            <= (sv.getHeight() + sv.getScrollY())) {
                        if (!bn.getText().toString().equals(getString(R.string.n25))) {
                            com.mrepol742.webvium.view.Animation.animate(this, R.anim.i, bn);
                            bn.setText(getString(R.string.n25));
                            bn.setBackgroundResource(R.drawable.c10);
                            bn.setOnClickListener(view -> {
                                fl.removeView(c);
                                a3();
                                SharedPreferences sp = getSharedPreferences("ag233", 0);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putLong("ag233", new Date().getTime());
                                editor.apply();
                            });
                        }
                    }
                });
        sv.setOnTouchListener((v, event) -> (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0);
        ed.setTypeface(type(Typeface.NORMAL));
        ed1.setTypeface(type(Typeface.BOLD));
        Html.a(ed, getString(R.string.n23));
        fl.addView(c);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void a3() {
        View c = View.inflate(this, R.layout.a3, null);
        TextView ed = c.findViewById(R.id.n23);
        final Button bn = c.findViewById(R.id.n24);
        TextView ed1 = c.findViewById(R.id.o28);
        ed1.setText(getString(R.string.x44).toUpperCase());
        bn.setBackgroundResource(R.drawable.c11);
        com.mrepol742.webvium.view.Animation.animate(this, R.anim.i, bn);
        bn.setText(getString(R.string.y62));
        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(Resources.getColor(this, R.color.c));
            ed1.setTextColor(Resources.getColor(this, R.color.c));
        } else {
            ed.setTextColor(Resources.getColor(this, R.color.b));
            ed1.setTextColor(Resources.getColor(this, R.color.b));
        }
        ScrollView sv = c.findViewById(R.id.j);
        bn.setTypeface(type(Typeface.BOLD));
        bn.setAllCaps(true);
        sv.getViewTreeObserver()
                .addOnScrollChangedListener(() -> {
                    if (sv.getChildAt(0).getBottom()
                            <= (sv.getHeight() + sv.getScrollY())) {
                        if (!bn.getText().toString().equals(getString(R.string.n25))) {
                            com.mrepol742.webvium.view.Animation.animate(this, R.anim.i, bn);
                            bn.setText(getString(R.string.n25));
                            bn.setBackgroundResource(R.drawable.c10);
                            bn.setOnClickListener(view -> {
                                fl.removeView(c);
                                l3();
                                l6();
                                SharedPreferences sp = getSharedPreferences("ag233", 0);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putLong("ag466", new Date().getTime());
                                editor.apply();
                            });
                        }
                    }
                });
        sv.setOnTouchListener((v, event) -> (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0);
        ed.setTypeface(type(Typeface.NORMAL));
        ed1.setTypeface(type(Typeface.BOLD));
        Html.a(ed, getString(R.string.n24));
        fl.addView(c);
    }

    private void l3() {
        SharedPreferences a5 = getSharedPreferences(WELC.INIT, 0);
        SharedPreferences.Editor b5 = a5.edit();
        b5.putInt("noid", 275);
        b5.apply();
        Intents.b(this, BACK.class);
        SharedPreferences a52 = getSharedPreferences("di", 0);
        SharedPreferences.Editor b52 = a52.edit();
        b52.putString("di", Hash.a("SHA-1", String.valueOf(System.currentTimeMillis())));
        b52.putString("di1", AppID.getAppID(this));
        b52.apply();


        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        PendingIntent it = PendingIntent.getService(this, 0, new Intent(this, UPDA.class), PendingIntent.FLAG_UPDATE_CURRENT);
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, it);
        PendingIntent it1 = PendingIntent.getService(this, 0, new Intent(this, NOTI.class), PendingIntent.FLAG_UPDATE_CURRENT);
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, it1);
        finish();
        Intents.b(this, UPDA.class);
        Intents.b(this, NOTI.class);
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
            et.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
            System.exit(0);
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
            SharedPreferences sp = getSharedPreferences("ag233", 0);
            if (sp.getLong("ag233", 0) != 0 && sp.getLong("ag466", 0) != 0) {
                l3();
            } else {
                WELC.this.a2();
            }
            if (!a221().getBoolean("autoUpdate", false)) {
                ll.setBackgroundColor(Resources.getColor(WELC.this, R.color.b));
            } else {
                ll.setBackgroundColor(Resources.getColor(WELC.this, R.color.n));
            }
        }
    }
}