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
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toolbar;

import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.app.base.BaseActivity;
import com.droidmj.webvium.app.main.MainWebView;
import com.droidmj.webvium.app.main.MainWebViewClient;
import com.droidmj.webvium.content.Resources;
import com.droidmj.webvium.widget.W11;

// @Class TextSize
public class TEXT extends BaseActivity {
    private MainWebView w4;
    private FrameLayout fl;

    @Override
    protected void onCreate(Bundle a) {
        theme(T_DEFAULT);
        super.onCreate(a);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            a225(R.layout.d1);
        } else {
            a225(R.layout.l);
        }
        Toolbar h17 = findViewById(R.id.c3);
        TextView h18 = findViewById(R.id.c4);
        TextView k8 = findViewById(R.id.c5);
        TextView a11 = findViewById(R.id.b16);
        SeekBar a1 = findViewById(R.id.a1);
        TextView a9 = findViewById(R.id.b17);
        SeekBar a10a = findViewById(R.id.b18);
        fl = findViewById(R.id.t);
        w4 = new MainWebView(this);
        fl.addView(w4);
        int ae = w4.getTextZoom();
        int as = w4.getFontSize();
        a1.setMax(75);
        a10a.setMax(200);
        h18.setTypeface(type(Typeface.BOLD));
        k8.setTypeface(type(Typeface.BOLD));
        a11.setTypeface(type(Typeface.BOLD));
        a9.setTypeface(type(Typeface.BOLD));
        setActionBar(h17);
        h17.setElevation(5);
        h18.setText(getString(R.string.g14));
        k8.setText(getString(R.string.l13));

        ActionBar ab = getActionBar();
        if (ab != null) {
            // ab.setDisplayHomeAsUpEnabled(true);
            // ab.setDisplayShowHomeEnabled(false);
            ab.setDisplayShowTitleEnabled(false);
        }
        w4.setWebViewClient(new MainWebViewClient() {

            @Override
            public boolean url(WebView webView, String str) {
                return false;
            }
        });
        if (!a221().getBoolean("autoUpdate", false)) {
            h18.setTextColor(Resources.b(this, R.color.c));
            a11.setTextColor(Resources.b(this, R.color.c));
            a9.setTextColor(Resources.b(this, R.color.c));
            k8.setTextColor(Resources.b(this, R.color.c));
            w4.setBackgroundColor(Resources.b(this, R.color.p));
            w4.loadDataWithBaseURL(null, "<!DOCTYPE html><html><style name=\"text/css\">@font-face { font-family: classes; src: url(\"file:///android_asset/classes\"); } * { color: #212121; font-family: classes;} ::selection { background-color: #4285f4; color: #ffffff}  </style></html><body><h1>Heading 1</h1><h2>Heading 2</h2><h3>Heading 3</h3><h4>Heading 4</h4><h5>Heading 5</h5><h6>Heading 6</h6><p>Paragraph</p></body></html>", "text/html", w4.getTextEncoding(), null);

        } else {
            h18.setTextColor(Resources.b(this, R.color.b));
            k8.setTextColor(Resources.b(this, R.color.b));
            a11.setTextColor(Resources.b(this, R.color.b));
            a9.setTextColor(Resources.b(this, R.color.b));
            w4.setBackgroundColor(Resources.b(this, R.color.m));
            w4.loadDataWithBaseURL(null, "<!DOCTYPE html><html><style name=\"text/css\">@font-face { font-family: classes; src: url(\"file:///android_asset/classes\"); } * { color: #ffffff; font-family: classes;} ::selection { background-color: #4285f4; color: #ffffff} </style></html><body><h1>Heading 1</h1><h2>Heading 2</h2><h3>Heading 3</h3><h4>Heading 4</h4><h5>Heading 5</h5><h6>Heading 6</h6><p>Paragraph</p></body></html>", "text/html", w4.getTextEncoding(), null);

        }
        h17.setBackgroundResource(R.drawable.p);
        h17.setNavigationIcon(R.drawable.a2);
        h17.setNavigationOnClickListener(view -> finish());
        if (a224("webDa", false)) {
            k8.setVisibility(View.VISIBLE);
        } else {
            k8.setVisibility(View.GONE);
        }

        a11.setText(getString(R.string.o8));
        a9.setText(getString(R.string.f37));
        WebSettings ws = w4.getSettings();
        if (a223("dr") == 0) {
            ws.setTextZoom(ae);
            a10a.setProgress(ae);
        } else {
            ws.setTextZoom(a223("dr"));
            a10a.setProgress(a223("dr"));
        }

        if (a223("dr55") == 0) {
            ws.setDefaultFontSize(as);
            a1.setProgress(as);
        } else {
            ws.setDefaultFontSize(a223("dr55"));
            a1.setProgress(a223("dr55"));
        }
        SharedPreferences a35 = getSharedPreferences("wv,", 0);

        a10a.setOnSeekBarChangeListener(new W11() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ws.setTextZoom(i);
                w4.reload();
                SharedPreferences.Editor b5 = a35.edit();
                b5.putInt("dr", i);
                b5.apply();
            }
        });
        a1.setOnSeekBarChangeListener(new W11() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ws.setDefaultFontSize(i);
                w4.reload();
                SharedPreferences.Editor b5 = a35.edit();
                b5.putInt("dr55", i);
                b5.apply();
            }

        });


    }

    @Override
    protected void onDestroy() {
        if (w4 != null) {
            fl.removeView(w4);
            w4.removeAllViews();
            w4.destroy();
        }
        super.onDestroy();

    }

}