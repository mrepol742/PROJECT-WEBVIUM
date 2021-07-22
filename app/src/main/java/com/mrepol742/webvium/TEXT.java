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

import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.app.main.MainWebView;
import com.mrepol742.webvium.app.main.MainWebViewClient;
import com.mrepol742.webvium.content.Resources;

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
        Toolbar h17 = findViewById(R.id.b7);
        TextView h18 = findViewById(R.id.b8);
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
        a11.setTypeface(type(Typeface.BOLD));
        a9.setTypeface(type(Typeface.BOLD));
        setActionBar(h17);
        h17.setElevation(5);
        h18.setText(getString(R.string.g14));
        ActionBar ab = getActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);
        }
        w4.setWebViewClient(new MainWebViewClient() {

            @Override
            public boolean url(WebView webView, String str) {
                return false;
            }
        });
        if (!a221().getBoolean("autoUpdate", false)) {
            h18.setTextColor(Resources.getColor(this, R.color.c));
            a11.setTextColor(Resources.getColor(this, R.color.c));
            a9.setTextColor(Resources.getColor(this, R.color.c));
            w4.setBackgroundColor(Resources.getColor(this, R.color.p));
            w4.loadDataWithBaseURL(null, "<!DOCTYPE html><html><style name=\"text/css\">@font-face { font-family: classes; src: url(\"file:///android_asset/classes\"); } * { color: #212121; font-family: classes;} ::selection { background-color: #4285f4; color: #ffffff}  </style></html><body><h1>Heading 1</h1><h2>Heading 2</h2><h3>Heading 3</h3><h4>Heading 4</h4><h5>Heading 5</h5><h6>Heading 6</h6><p>Paragraph</p></body></html>", "text/html", w4.getTextEncoding(), null);
        } else {
            h18.setTextColor(Resources.getColor(this, R.color.b));
            a11.setTextColor(Resources.getColor(this, R.color.b));
            a9.setTextColor(Resources.getColor(this, R.color.b));
            w4.setBackgroundColor(Resources.getColor(this, R.color.m));
            w4.loadDataWithBaseURL(null, "<!DOCTYPE html><html><style name=\"text/css\">@font-face { font-family: classes; src: url(\"file:///android_asset/classes\"); } * { color: #ffffff; font-family: classes;} ::selection { background-color: #4285f4; color: #ffffff} </style></html><body><h1>Heading 1</h1><h2>Heading 2</h2><h3>Heading 3</h3><h4>Heading 4</h4><h5>Heading 5</h5><h6>Heading 6</h6><p>Paragraph</p></body></html>", "text/html", w4.getTextEncoding(), null);
        }
        h17.setBackgroundResource(R.drawable.p);
        h17.setNavigationIcon(R.drawable.a2);
        h17.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TEXT.this.finish();
            }
        });
        a11.setText(getString(R.string.o8));
        a9.setText(getString(R.string.f37));
        final WebSettings ws = w4.getSettings();
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
        final SharedPreferences a35 = getSharedPreferences("wv", 0);
        a10a.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ws.setTextZoom(i);
                w4.reload();
                SharedPreferences.Editor b5 = a35.edit();
                b5.putInt("dr", i);
                b5.apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        a1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ws.setDefaultFontSize(i);
                w4.reload();
                SharedPreferences.Editor b5 = a35.edit();
                b5.putInt("dr55", i);
                b5.apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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