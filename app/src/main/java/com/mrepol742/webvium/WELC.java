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
import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.io.StorageDirectory;
import com.mrepol742.webvium.os.CountDownTimer;
import com.mrepol742.webvium.security.Hash;
import com.mrepol742.webvium.util.Log;
import com.mrepol742.webvium.text.Html;
import com.mrepol742.webvium.util.Base64;
import com.mrepol742.webvium.util.cache.FontCache;

import java.io.File;
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
    private boolean bn = false;
    private Animation d;
    public static final String TEMP_UPDATE_URL = "a";
    public static final String TEMP_UPDATE_VERSION = "b";
    public static final String TEMP_NOTIFICATION_STATE = "c";
    public static final String TEMP_NOTIFICATION_DATA = "d";
    public static final String TEMP_WEBVIUM_URLS = "e";
    public static final String TEMP_HEADERS = "f";
    public static final String TEMP_HEADERS_1 = "g";
    public static final String TEMP_IP_ADDRESS = "h";
    public static final String TEMP_LINKS = "i";
    public static final String TEMP_META_TAGS = "j";
    public static final String TEMP_USER_AGENT = "k";
    public static final String TEMP_SEARCH = "l";
    public static boolean bn1 = false;

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
        tv.setText(getString(R.string.y63));
        rl.setBackgroundResource(R.drawable.a11);
        rl.setElevation(5);
        iv.setImageResource(R.drawable.b14);
        tv.setTypeface(type(Typeface.BOLD));
        RelativeLayout ll = findViewById(R.id.h11);
        if (!a221().getBoolean("autoUpdate", false)) {
            ll.setBackgroundColor(Resources.getColor(this, R.color.b));
            fl.setBackgroundColor(Resources.getColor(this, R.color.b));
        } else {
            ll.setBackgroundColor(Resources.getColor(this, R.color.n));
            fl.setBackgroundColor(Resources.getColor(this, R.color.n));
        }
        tv.setTextColor(Resources.getColor(this, R.color.b));
        d = AnimationUtils.loadAnimation(this, R.anim.h);
        iv.startAnimation(d);
        timer = new A17a(5000, 5000);
        timer.start();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        try {
            bn1 = intent.getBooleanExtra("welc", false);
            intent.replaceExtras(new Bundle());
            intent.setAction("");
            intent.setData(null);
            intent.setFlags(0);
        } catch (Exception en) {
            Log.a(en);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timer == null && !bn) {
            timer = new A17a(3000, 3000);
            timer.start();
            iv.startAnimation(d);
        }
        onNewIntent(getIntent());
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
        String formatter1 = "aHR0cHM6Ly9sdWVhLjAwMHdlYmhvc3RhcHAuY29tL21qLw";
        editor.putString(TEMP_UPDATE_URL, Base64.encode(Base64.decode(formatter) + "update_url.txt")); // update
        editor.putString(TEMP_UPDATE_VERSION, Base64.encode(Base64.decode(formatter) + "update_version>1.2.txt")); // update version
        editor.putString(TEMP_NOTIFICATION_STATE, Base64.encode(Base64.decode(formatter) + "notification_state.txt")); // notification status
        editor.putString(TEMP_NOTIFICATION_DATA, Base64.encode(Base64.decode(formatter) + "notification_data.txt")); // notification url
        editor.putString(TEMP_WEBVIUM_URLS, Base64.encode(Base64.decode(formatter) + "webvium_urls.txt")); // Webvium urls
        editor.putString(TEMP_HEADERS, Base64.encode(Base64.decode(formatter1) + "headers.php")); // Headers
        editor.putString(TEMP_HEADERS_1, Base64.encode(Base64.decode(formatter1) + "headers1.php")); // Headers version 2
        editor.putString(TEMP_IP_ADDRESS, Base64.encode(Base64.decode(formatter1) + "ipaddress.php")); // IP Address
        editor.putString(TEMP_LINKS, Base64.encode(Base64.decode(formatter1) + "links.php")); //LINKS
        editor.putString(TEMP_META_TAGS, Base64.encode(Base64.decode(formatter1) + "metatag.php")); //Meta tags
        editor.putString(TEMP_USER_AGENT, Base64.encode(Base64.decode(formatter1) + "useragent.php")); //useragent
        editor.putString(TEMP_SEARCH, "aHR0cHM6Ly9tcmVwb2w3NDIuZ2l0aHViLmlvL1BST0pFQ1QtV0VCVklVTS9TZWFyY2gvaW5kZXguaHRtbA"); // search
        editor.apply();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void a2() {
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
                        com.mrepol742.webvium.view.Animation.animate(this, R.anim.i, bn);
                        bn.setText(getString(R.string.n25));
                        bn.setBackgroundResource(R.drawable.c10);
                        bn.setOnClickListener(view -> {
                            fl.removeView(c);
                            l3();
                            l6();
                            SharedPreferences sp = getSharedPreferences("ag233", 0);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putLong("ag233", new Date().getTime());
                            editor.putBoolean("aa", true);
                            editor.apply();
                        });

                    }
                });
        sv.setOnTouchListener((v, event) -> (event.getFlags() & MotionEvent.FLAG_WINDOW_IS_OBSCURED) != 0);
        ed.setTypeface(type(Typeface.NORMAL));
        ed1.setTypeface(type(Typeface.BOLD));
        Html.a(ed, getString(R.string.n23));
        fl.addView(c);
    }

    private void l3() {
        a();
        SharedPreferences a5 = getSharedPreferences("ddnrr", 0);
        SharedPreferences.Editor b5 = a5.edit();
        b5.putInt("noid", 275);
        b5.apply();
        Intents.b(this, BACK.class);
        String sg = Hash.a("SHA-1", String.valueOf(System.currentTimeMillis()));
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
                Log.a(en);
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
        if (bn1) {
            Intent intent44 = new Intent(this, EXPI.class);
            startActivity(intent44);
        }
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
            Log.a(et);
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
            if ( !sp.getBoolean("aa", false)) {
                WELC.this.a2();
            } else {
                l3();
                l6();
            }
            WELC.this.bn = true;
        }
    }
}