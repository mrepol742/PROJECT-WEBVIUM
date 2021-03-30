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

import android.app.ActionBar;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.droidmj.webvium.annotation.Test;
import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.app.base.BaseActivity;
import com.droidmj.webvium.content.Resources;
import com.droidmj.webvium.telemetry.DiagnosticData;
import com.droidmj.webvium.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// @Class Logcat
@Test
public class LOGC extends BaseActivity {
    private TextView tv;
    private ScrollView sv;

    @Override
    protected void onCreate(Bundle a) {
        theme(T_DEFAULT);
        super.onCreate(a);
        try {
            a225(R.layout.n);
            Toolbar a1 = findViewById(R.id.b7);
            TextView a2 = findViewById(R.id.b8);
            tv = findViewById(R.id.a);
            ImageView iv = findViewById(R.id.b);
            sv = findViewById(R.id.m);
            iv.setImageResource(R.drawable.d13);
            iv.setBackgroundResource(R.drawable.c6);
            setActionBar(a1);
            ActionBar ab = getActionBar();
            if (ab != null) {
                // ab.setDisplayHomeAsUpEnabled(true);
                // ab.setDisplayShowHomeEnabled(false);
                ab.setDisplayShowTitleEnabled(false);
            }
            int f = Resources.b(this, R.color.c);
            int g = Resources.b(this, R.color.b);
            a2.setTypeface(type(Typeface.BOLD));
            tv.setTypeface(type(Typeface.NORMAL));
            a2.setText(getString(R.string.f30));
            if (!a221().getBoolean("autoUpdate", false)) {
                a2.setTextColor(f);
                tv.setTextColor(f);
                tv.setBackgroundColor(Resources.b(this, R.color.p));
            } else {
                tv.setBackgroundColor(Resources.b(this, R.color.m));
                a2.setTextColor(g);
                tv.setTextColor(g);
            }
            a1.setBackgroundResource(R.drawable.p);
            a1.setNavigationIcon(R.drawable.a2);
            a1.setNavigationOnClickListener(view -> finishAndRemoveTask());

            a1.setElevation(5);
            a5();
            iv.setOnClickListener(view -> {
                a5();
                Toast.b(LOGC.this, getString(R.string.t23));
            });

        } catch (Exception rx) {
            DiagnosticData.a(rx);
        }
    }


    public void a5() {
        Runnable e = () -> {
            try {
                Process pr = Runtime.getRuntime().exec("logcat -d");
                InputStream is = pr.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                while (br.readLine() != null) {
                    sb.append(br.readLine());
                    sb.append("\n\n");
                }
                pr.destroy();
                runOnUiThread(() -> {
                    if (sb.length() != 0) {
                        tv.setText(sb.toString());
                    } else {
                        tv.setText(LOGC.this.getString(R.string.t22));
                    }
                    sv.post(() -> sv.fullScroll(View.FOCUS_DOWN));
                });
            } catch (IOException e1) {
                DiagnosticData.a(e1);
            }
        };
        new Thread(e).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        a5();
    }


}