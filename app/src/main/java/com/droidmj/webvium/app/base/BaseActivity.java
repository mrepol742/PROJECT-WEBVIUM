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

package com.droidmj.webvium.app.base;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.SpeechRecognizer;
import android.view.ViewGroup;

import com.droidmj.webvium.R;
import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.app.W6;
import com.droidmj.webvium.content.Intents;
import com.droidmj.webvium.os.StrictMode;
import com.droidmj.webvium.telemetry.DiagnosticData;
import com.droidmj.webvium.util.cache.FontCache;
import com.droidmj.webvium.widget.Toast;

public class BaseActivity extends Activity {

    public FontCache U7;
    private SharedPreferences sharedPreferences;
    private SharedPreferences exclusive;
    public static final int T_MAIN = 0;
    public static final int T_MANAGE_SPACE = 1;
    public static final int T_DEFAULT = 2;
    public static final int T_WELCOME_SCREEN = 3;
    public static final int T_ASSISTANT = 4;

    @Override
    protected void onCreate(Bundle be) {
        if (BuildConfiguration.Application.isDevelopment || (getSharedPreferences("wv,", 0).getBoolean("webDa", false) && a221().getBoolean("stM12", false))) {
            StrictMode.b();
            DiagnosticData.a("Webvium.onCreate()");
        }
        super.onCreate(be);
        U7 = FontCache.getInstance(getApplicationContext());
        if (BuildConfiguration.Application.isDevelopment) {
            if (!spr()) {
                DiagnosticData.a("Speech Recognition was not available");
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (a221().getBoolean("qwe73", false)) {
            W6.c();
        }
        if (BuildConfiguration.Application.isDevelopment) {
            DiagnosticData.a("Webvium.onResume()");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (BuildConfiguration.Application.isDevelopment) {
            DiagnosticData.a("Webvium.onDestroy()");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (BuildConfiguration.Application.isDevelopment) {
            DiagnosticData.a("BuildConfiguration.onPause()");
        }
    }

    public SharedPreferences a221() {
        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        }
        return sharedPreferences;
    }

    public String a222(String sg) {
        if (exclusive == null) {
            exclusive = getSharedPreferences("wv,", 0);
        }
        return exclusive.getString(sg, "");
    }

    public int a223(String sg) {
        if (exclusive == null) {
            exclusive = getSharedPreferences("wv,", 0);
        }
        return exclusive.getInt(sg, 0);
    }

    public boolean a224(String sg, boolean def) {
        if (exclusive == null) {
            exclusive = getSharedPreferences("wv,", 0);
        }
        return exclusive.getBoolean(sg, def);
    }

    public void a225(String sg, boolean def) {
        if (exclusive == null) {
            exclusive = getSharedPreferences("wv,", 0);
        }
        SharedPreferences.Editor e = exclusive.edit();
        e.putBoolean(sg, def);
        e.apply();
    }

    public void a225(int i) {
        setContentView(i);
        if (BuildConfiguration.Application.isDevelopment) {
            DiagnosticData.a("Webvium.setContentView("+ i +")");
        }
        if (Build.VERSION.SDK_INT == 21 || Build.VERSION.SDK_INT == 22 || a221().getBoolean("webviumB", false)) {
            ViewGroup vg = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
            vg.setFitsSystemWindows(true);
            if (BuildConfiguration.Application.isDevelopment)
                DiagnosticData.a("Webvium.setFitsSystemWindow(true)");
        }

    }

    public void shrt(String name, String data, int icon) {
        try {
            Intent e = new Intent(Intents.ACTION_LAUNCH);
            e.addCategory(Intents.CATEGORY_GENIUS);
            e.putExtra("duplicate", false);
            e.putExtra("webvium", data);
            Intent f = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
            f.putExtra("android.intent.extra.shortcut.INTENT", e);
            f.putExtra("android.intent.extra.shortcut.NAME", name);
            f.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(this, icon));
            f.putExtra("duplicate", false);
            sendBroadcast(f);
            Toast.b(this, getString(R.string.q26));
        } catch (Exception et) {
            Toast.e(this, getString(R.string.a36), 2);
            DiagnosticData.a(et);
        }
    }

    public void as(int i, Fragment s) {
        String a = s.getClass().getName();
        FragmentManager fm = getFragmentManager();
        boolean bn = fm.popBackStackImmediate(a, 0);
        if (!bn) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(i, s);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(a);
            ft.commit();
        }
    }

    public void as1() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 1) {
            finishAndRemoveTask();
        } else {
            getFragmentManager().popBackStack();
        }
    }


    public void theme(int i) {
        if (i == T_MAIN) {
            if (!a221().getBoolean("autoUpdate", false)) {
                if (!a221().getBoolean("webviumB", false)) {
                    if (!a221().getBoolean("autoUpdate742", false)) {
                        setTheme(R.style.d);
                    } else {
                        setTheme(R.style.b15);
                    }
                } else {
                    if (!a221().getBoolean("autoUpdate742", false)) {
                        setTheme(R.style.a);
                    } else {
                        setTheme(R.style.b19);
                    }
                }
            } else {
                if (!a221().getBoolean("webviumB", false)) {
                    if (!a221().getBoolean("autoUpdate742", false)) {
                        setTheme(R.style.e);
                    } else {
                        setTheme(R.style.b16);
                    }
                } else {
                    if (!a221().getBoolean("autoUpdate742", false)) {
                        setTheme(R.style.g);
                    } else {
                        setTheme(R.style.c2);
                    }
                }
            }
        } else if (i == T_MANAGE_SPACE) {
            if (!a221().getBoolean("autoUpdate", false)) {
                if (!a221().getBoolean("autoUpdate742", false)) {
                    setTheme(R.style.d3);
                } else {
                    setTheme(R.style.d5);
                }
            } else {
                if (!a221().getBoolean("autoUpdate742", false)) {
                    setTheme(R.style.d4);
                } else {
                    setTheme(R.style.d6);
                }
            }
        } else if (i == T_DEFAULT) {
            if (!a221().getBoolean("autoUpdate", false)) {
                if (!a221().getBoolean("autoUpdate742", false)) {
                    setTheme(R.style.d);
                } else {
                    setTheme(R.style.b15);
                }
            } else {
                if (!a221().getBoolean("autoUpdate742", false)) {
                    setTheme(R.style.e);
                } else {
                    setTheme(R.style.b16);
                }
            }
        } else if (i == T_WELCOME_SCREEN) {
            if (!a221().getBoolean("autoUpdate", false)) {
                setTheme(R.style.d);
            } else {
                setTheme(R.style.e);
            }
        } else if (i == T_ASSISTANT) {
            if (!a221().getBoolean("autoUpdate", false)) {
                setTheme(R.style.m);
            } else {
                setTheme(R.style.n);
            }
        }
    }

    public Typeface type(int i) {
        return U7.a(i);
    }

    public boolean spr() {
        return !SpeechRecognizer.isRecognitionAvailable(this);
    }

}