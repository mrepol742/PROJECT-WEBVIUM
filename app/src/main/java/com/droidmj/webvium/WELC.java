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

import com.droidmj.webvium.annotation.Test;
import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.app.W6;
import com.droidmj.webvium.app.base.BaseActivity;
import com.droidmj.webvium.content.Clipboard;
import com.droidmj.webvium.content.Intents;
import com.droidmj.webvium.content.Package;
import com.droidmj.webvium.content.Resources;
import com.droidmj.webvium.os.CountDownTimer;
import com.droidmj.webvium.security.AppID;
import com.droidmj.webvium.security.Hash;
import com.droidmj.webvium.security.HashDataModel;
import com.droidmj.webvium.telemetry.DiagnosticData;
import com.droidmj.webvium.text.Html;
import com.droidmj.webvium.util.Base64;
import com.droidmj.webvium.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
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

    @Override
    protected void onCreate(Bundle be) {
        theme(BuildConfiguration.Theme.WELCOME_SCREEN);
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
        String formatter = "aHR0cHM6Ly9naXRodWIuY29tL21yZXBvbDc0Mi9hL2Jsb2IvbWFpbi8";
        editor.putString("a", Base64.b(Base64.a(formatter) + "a")); // update
        editor.putString("b", Base64.b(Base64.a(formatter) + "b")); // update version
        editor.putString("c", Base64.b(Base64.a(formatter) + "c")); // notification status
        editor.putString("d", Base64.b(Base64.a(formatter) + "d")); // notification url
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
    }

    private void l3() {
        a();
        SharedPreferences a5 = getSharedPreferences("dnr", 0);
        SharedPreferences.Editor b5 = a5.edit();
        b5.putInt("wlc1", 275);
        b5.apply();
        Intents.b(this, BACK.class);
        String sg = AppID.getAppID(this);
        if (BuildConfiguration.Application.isDevelopment) {
            DiagnosticData.a("Webvium ID = " + sg);
        }
        Runnable re = () -> {
            try {
                InputStream fos = getAssets().open(BuildConfiguration.Font.MAVEN_PRO);
                OutputStream d = new FileOutputStream(getFilesDir() + BuildConfiguration.Files.classes);
                byte[] e = new byte[1024];
                int f;
                while ((f = fos.read(e)) != -1) {
                    d.write(e, 0, f);
                }
                d.flush();
                d.close();
                fos.close();
                File fea = new File(getFilesDir() + BuildConfiguration.Files.classes);
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
        finish();
        overridePendingTransition(R.anim.f, R.anim.b);
    }

    private void l6() {
        try {
            Intent e = new Intent(BuildConfiguration.Intent.ACTION_LAUNCH);
            e.addCategory(BuildConfiguration.Intent.CATEGORY_GENIUS);
            e.setPackage(Package.b());
            e.putExtra("duplicate", false);
            Intent f = new Intent();
            f.putExtra("android.intent.extra.shortcut.INTENT", e);
            f.putExtra("android.intent.extra.shortcut.NAME", Package.c());
            f.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", ShortcutIconResource.fromContext(this, R.mipmap.c));
            f.putExtra("duplicate", false);
            f.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(f);
            Toast.b(this, getString(R.string.b17));
        } catch (Exception et) {
            Toast.e(this, getString(R.string.a36), 2);
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