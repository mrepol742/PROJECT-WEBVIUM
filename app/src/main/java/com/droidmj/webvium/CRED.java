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

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.droidmj.webvium.app.main.MainReceiver;
import com.droidmj.webvium.app.base.BaseActivity;
import com.droidmj.webvium.content.IntentsFilter;
import com.droidmj.webvium.content.Package;
import com.droidmj.webvium.content.Resources;
import com.droidmj.webvium.telemetry.DiagnosticData;
import com.droidmj.webvium.util.Base64;
import com.droidmj.webvium.util.Hardware;
import com.droidmj.webvium.view.Animation;

// @Class Credits
public class CRED extends BaseActivity {

    private static final String[] accounts = {
            "a HR0cH M6Ly9mYWNlYm9vay5jb20vbXJlcG9 sNzQyLmRyb2lkbWpsbGM",
            "aH R0cHM6Ly9mYi5tZS9tcmVwb2w3NDI",
            "aH R0cHM6Ly9mYWNlYm9vay5jb20 vbXJlcG9sNzQyLndlYnZpdW0",
            "aHR 0cHM6Ly9pbnN0YWdyYW0u Y29tL21yZXBvbDc0Mg",
            "aHR0cHM6Ly9naXRo dWIuY29tL21yZXBvbDc0Mg",
            "aHR0cHM6Ly90d2l0dGV yLmNvbS9tcmVwb2w3NDI",
            "aHR0 cHM6Ly9t cmVwb2w3NDIuZ2l0aHViLmlv",
            "bXJlcG9sNzQyQGdtYWlsLmNvbQ",
            "aHR0 cHM6Ly93d3cueW91dHViZS5jb20vY2hhbm5lbC9VQzg0RzR1ZjBYczhPYkRMNTJrajRIcEE",
            "aHR0cHM6Ly93d3cucGF0cmVvbi5jb20 vbXJlcG9sNzQy",
            "aHR  0cHM6Ly9mYWNlYm9v ay5jb2 0vU2FtaXVuTmFmaXMw",
            "aHR0 cHM6Ly9naXRodWI uY29tL1NhbWl1bk5hZmlz",
            "aHR 0cHM6Ly9pbnN0YWdyYW0uY  29tL1NhbWl1bk5hZmlz",
            "aHR0cHM6Ly90 d2l0dGVyLmNvb S9TYW1pdW5OYWZpcw",
            "aHR0 cHM6Ly9mYi5tZS9hbW9zLmZyb3NoLjk0",
            "aHR0cH M6Ly93d3cueW91dH ViZS5jb20vY2hhbm5lbC9 VQ3dVaHdwY0ZENm ItOUZieFJjYS1obmc "
    };
    private final R36 br = new R36();
    private ImageView iv;
    private LinearLayout fl;
    private TextView tv2, tv4;
    private PopupMenu pm0, pm1, pm2;

    @Override
    protected void onCreate(Bundle a) {
        theme(T_DEFAULT);
        super.onCreate(a);
        a225(R.layout.c20);
        TextView tv = findViewById(R.id.f13);
        RelativeLayout rl = findViewById(R.id.c3);
        if (!a221().getBoolean("autoUpdate", false)) {
            tv.setTextColor(Resources.b(this, R.color.c));
            rl.setBackgroundColor(Resources.b(this, R.color.b));
        } else {
            tv.setTextColor(Resources.b(this, R.color.b));
            rl.setBackgroundColor(Resources.b(this, R.color.n));
        }
        tv.setText(getString(R.string.u26));
        tv.setTypeface(type(Typeface.NORMAL));
        TextView tv0 = findViewById(R.id.c15);
        tv0.setText(getString(R.string.u28));
        tv0.setTextColor(Resources.b(this, R.color.b));
        tv0.setTypeface(type(Typeface.NORMAL));
        TextView tv1 = findViewById(R.id.c10);
        tv1.setTextColor(Resources.b(this, R.color.b));
        tv1.setTypeface(type(Typeface.BOLD));
        tv1.setText(getString(R.string.u29));
        tv1.setCompoundDrawablesWithIntrinsicBounds(null, Resources.a(this, R.drawable.e9), null, null);
        tv1.setCompoundDrawablePadding(10);
        iv = findViewById(R.id.c16);
        iv.setImageResource(R.drawable.a2);
        iv.setBackgroundResource(R.drawable.f2);
        iv.setElevation(5);
        iv.setOnClickListener(v -> finish());

        tv2 = findViewById(R.id.d18);

        tv2.setText(Base64.decode("U2FtaXVuI E5hZmlz "));
        tv2.setTypeface(type(Typeface.BOLD));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tv2.setCompoundDrawablePadding(10);

        fl = findViewById(R.id.c9);

        fl.setElevation(5);
        tv2.setOnClickListener(this::e);
        fl.setOnClickListener(v -> d(tv1));

        tv4 = findViewById(R.id.o27);

        tv4.setText(Base64.decode("QW1vcyBBeW9taWRl"));
        tv4.setTypeface(type(Typeface.BOLD));
        tv4.setCompoundDrawablePadding(10);
        tv4.setOnClickListener(this::g);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Animation.animate(this, R.anim.i, iv);
        registerReceiver(br, new IntentsFilter(Intent.ACTION_BATTERY_CHANGED));
        f();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(br);
    }

    private void a(String a) {
        Intent it = new Intent(this, MAIN.class);
        it.putExtra("value", a);
        startActivity(it);
    }

    private void c(String c) {
        Intent a = new Intent(Intent.ACTION_SENDTO, Uri.parse(c));
        a.putExtra(Intent.EXTRA_SUBJECT, Package.c());
        a.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(Intent.createChooser(a, getString(R.string.a26)));
    }

    private void d(View w) {
        if (pm0 == null) {
            pm0 = new PopupMenu(this, w);
            MenuItem.OnMenuItemClickListener e = a1 -> {
                switch (a1.getItemId()) {
                    case 0:
                        a(Base64.decode(accounts[0] + "="));
                        return true;
                    case 1:
                        a(Base64.decode(accounts[1] + "="));
                        return true;
                    case 2:
                        a(Base64.decode(accounts[2] + "="));
                        return true;
                    case 3:
                        a(Base64.decode(accounts[3] + "=="));
                        return true;
                    case 4:
                        a(Base64.decode(accounts[4] + "=="));
                        return true;
                    case 5:
                        a(Base64.decode(accounts[5] + "="));
                        return true;
                    case 6:
                        a(Base64.decode(accounts[6]));
                        return true;
                    case 7:
                        c(Base64.decode(accounts[7] + "=="));
                        return true;
                    case 8:
                        c(Base64.decode(accounts[8] + "="));
                        return true;
                    case 9:
                        c(Base64.decode(accounts[9]));
                        return true;

                }
                return false;
            };
            Menu me = pm0.getMenu();
            me.add(0, 0, 0, getString(R.string.f20)).setOnMenuItemClickListener(e);
            me.add(0, 1, 0, getString(R.string.f19)).setOnMenuItemClickListener(e);
            me.add(0, 2, 0, getString(R.string.z9)).setOnMenuItemClickListener(e);
            me.add(0, 3, 0, getString(R.string.g1)).setOnMenuItemClickListener(e);
            me.add(0, 4, 0, getString(R.string.z14)).setOnMenuItemClickListener(e);
            me.add(0, 5, 0, getString(R.string.n4)).setOnMenuItemClickListener(e);
            me.add(0, 6, 0, getString(R.string.n16)).setOnMenuItemClickListener(e);
            me.add(0, 7, 0, getString(R.string.g2)).setOnMenuItemClickListener(e);
            me.add(0, 8, 0, getString(R.string.x41)).setOnMenuItemClickListener(e);
            me.add(0, 9, 0, getString(R.string.x44)).setOnMenuItemClickListener(e);
        }
        pm0.show();
    }

    // samiun
    private void e(View w) {
        if (pm1 == null) {
            pm1 = new PopupMenu(this, w);
            MenuItem.OnMenuItemClickListener e = a1 -> {
                switch (a1.getItemId()) {
                    case 0:
                        a(Base64.decode(accounts[10]));
                        return true;
                    case 2:
                        a(Base64.decode(accounts[11]));
                        return true;
                    case 3:
                        a(Base64.decode(accounts[12]));
                        return true;
                    case 4:
                        a(Base64.decode(accounts[13]));
                        return true;

                }
                return false;
            };
            Menu me = pm1.getMenu();
            me.add(0, 0, 0, getString(R.string.v21)).setOnMenuItemClickListener(e);
            me.add(0, 2, 0, getString(R.string.z14)).setOnMenuItemClickListener(e);
            me.add(0, 3, 0, getString(R.string.g1)).setOnMenuItemClickListener(e);
            me.add(0, 4, 0, getString(R.string.n4)).setOnMenuItemClickListener(e);
        }
        pm1.show();
    }

    private void f() {
        boolean bn = Hardware.isBatterySaveMode(this);
        if (!bn) {
            getWindow().setStatusBarColor(Resources.b(this, R.color.a));
        }
        if (bn) {
            tv2.setTextColor(Resources.b(this, R.color.g));
            fl.setBackgroundResource(R.drawable.f30);
            tv2.setCompoundDrawablesWithIntrinsicBounds(null, Resources.a(this, R.drawable.g1), null, null);
            tv2.setBackgroundResource(R.drawable.g2);
            tv4.setTextColor(Resources.b(this, R.color.g));
            tv4.setCompoundDrawablesWithIntrinsicBounds(null, Resources.a(this, R.drawable.g1), null, null);
            tv4.setBackgroundResource(R.drawable.g2);
        } else {
            tv2.setTextColor(Resources.b(this, R.color.a));
            fl.setBackgroundResource(R.drawable.e7);
            tv2.setCompoundDrawablesWithIntrinsicBounds(null, Resources.a(this, R.drawable.z), null, null);
            tv2.setBackgroundResource(R.drawable.e8);
            tv4.setTextColor(Resources.b(this, R.color.a));
            tv4.setCompoundDrawablesWithIntrinsicBounds(null, Resources.a(this, R.drawable.z), null, null);
            tv4.setBackgroundResource(R.drawable.e8);
        }
    }

    private void g(View w) {
        if (pm2 == null) {
            pm2 = new PopupMenu(this, w);
            MenuItem.OnMenuItemClickListener e = a1 -> {
                switch (a1.getItemId()) {
                    case 0:
                        a(Base64.decode(accounts[14]));
                        return true;
                    case 2:
                        a(Base64.decode(accounts[15] + "="));
                        return true;
                }
                return false;
            };
            Menu me = pm2.getMenu();
            me.add(0, 0, 0, getString(R.string.x43)).setOnMenuItemClickListener(e);
            me.add(0, 2, 0, getString(R.string.x41)).setOnMenuItemClickListener(e);

        }
        pm2.show();
    }

    private class R36 extends MainReceiver {

        @Override
        public void onReceive(Context a, Intent b) {
            super.onReceive(a, b);
            try {
                String sg = b.getAction();
                if (sg.equals("android.intent.action.BATTERY_CHANGED")) {
                    f();
                }
            } catch (Exception ex) {
                DiagnosticData.a(ex);
            }
        }
    }
}