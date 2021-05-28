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
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.app.main.MainWebView;
import com.mrepol742.webvium.app.main.MainWebViewClient;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.io.StorageDirectory;
import com.mrepol742.webvium.net.Connectivity;
import com.mrepol742.webvium.text.Html;
import com.mrepol742.webvium.text.TextWatcher;
import com.mrepol742.webvium.view.Animation;
import com.mrepol742.webvium.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

// @Class Feedback
public class FEED extends BaseActivity {
    private RelativeLayout rl;
    private MainWebView b;
    private Button iv;
    private EditText et;
    private String sg;


    @Override
    protected void onCreate(Bundle a) {
        theme(T_DEFAULT);
        super.onCreate(a);
        a225(R.layout.d2);
        rl = findViewById(R.id.o24);
        Toolbar a1 = findViewById(R.id.b7);
        TextView a2 = findViewById(R.id.b8);
        TextView tv0 = findViewById(R.id.h15);
        et = findViewById(R.id.h16);
        iv = findViewById(R.id.k3);
        setActionBar(a1);
        a1.setElevation(5);
        a1.setBackgroundResource(R.drawable.p);
        a2.setText(getString(R.string.f14));
        tv0.setText(getString(R.string.y27));
        int f = Resources.getColor(this, R.color.c);
        int g = Resources.getColor(this, R.color.b);
        if (!a221().getBoolean("autoUpdate", false)) {
            a2.setTextColor(f);
            tv0.setTextColor(f);
            et.setTextColor(f);
        } else {
            a2.setTextColor(g);
            tv0.setTextColor(g);
            et.setTextColor(g);
        }
        iv.setText(getString(R.string.x39));
        ActionBar ab = getActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);
        }
        a2.setTypeface(type(Typeface.BOLD));
        tv0.setTypeface(type(Typeface.BOLD));
        et.setTypeface(type(Typeface.NORMAL));
        a();
        et.setLines(5);
        et.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() <= 20) {
                    et.setError(charSequence.toString().trim().length() + "/20");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                a();
            }
        });
        iv.setOnClickListener(view -> {
            if (et.getText().toString().trim().length() > 20) {
                c(et.getText().toString());
            }
        });
        Animation.animate(this, R.anim.i, iv);
    }

    public boolean onKeyDown(int a5, KeyEvent b) {
        if (b.getAction() == KeyEvent.ACTION_DOWN && a5 == KeyEvent.KEYCODE_BACK) {
            finishAndRemoveTask();
            return true;
        }
        return false;
    }

    protected void onDestroy() {
        super.onDestroy();
        if (b != null) {
            rl.removeView(b);
            b.removeAllViews();
            b.destroy();
        }
    }

    private void a() {
        if (et.getText().toString().trim().length() > 20) {
            iv.setBackgroundResource(R.drawable.c10);
        } else {
            iv.setBackgroundResource(R.drawable.c11);
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void c(final String tmp) {
        if (b == null) {
            b = new MainWebView(this);
            rl.addView(b);
            WebSettings ws = b.getSettings();
            ws.setJavaScriptEnabled(true);
            ws.setDomStorageEnabled(true);
            ws.setDatabaseEnabled(true);
            ws.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            if (Build.VERSION.SDK_INT < 30) {
                ws.setAppCacheEnabled(true);
                ws.setAppCachePath(StorageDirectory.getCacheDir(this).toString());
            }
            b.setWebChromeClient(new WebChromeClient() {

                @Override
                public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                    if (message.contains("ara")) {
                        Toast.b(FEED.this, getString(R.string.g27));
                    } else {
                        Toast.b(FEED.this, getString(R.string.y68));
                    }
                    result.confirm();
                    return true;
                }

                @Override
                public boolean onConsoleMessage(ConsoleMessage cm) {
                    AlertDialog.Builder bld = new AlertDialog.Builder(FEED.this);
                    bld.setTitle(getString(R.string.y65));
                    bld.setMessage(Html.b(String.format(getString(R.string.v18), cm.message(), cm.lineNumber(), cm.sourceId())));
                    bld.setPositiveButton(getString(R.string.i6), (dialog, which) -> dialog.dismiss());
                    bld.create().show();
                    return true;
                }
            });
            b.setWebViewClient(new MainWebViewClient() {

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    Toast.b(FEED.this, getString(R.string.y71));
                }

                @Override
                public void receivedError(int b, String c, String d, boolean bn, boolean bn1) {
                    if (Connectivity.isThereAnyInternetConnection(FEED.this)) {
                        Toast.c(FEED.this, getString(R.string.x38));
                        Toast.b(FEED.this, getString(R.string.y72));
                    }
                }
            });
        }
        try {
            StringBuilder sg2 = new StringBuilder("https://mrepol742.github.io/PROJECT-WEBVIUM/Server/Feed.html" +
                    "?a=" + getString(R.string.firebase_apiKey) +
                    "&b=" + getString(R.string.firebase_authDomain) +
                    "&c=" + getString(R.string.firebase_projectId) +
                    "&d=" + getString(R.string.firebase_storageBucket) +
                    "&e=" + getString(R.string.firebase_messagingSenderId) +
                    "&f=" + getString(R.string.firebase_appId) +
                    "&g=" + getString(R.string.firebase_measurementId) +
                    "&h=" + URLEncoder.encode(Package.c() + " | v-" + Package.e(this) + " | c-" + Long.toString(Package.f(this)), "UTF-8") +
                    "&i=" + URLEncoder.encode(tmp, "UTF-8"));
            if (sg != null) {
                sg2.append("&j=").append(URLEncoder.encode(sg, "UTF-8"));
            }
            b.loadUrl(sg2.toString());

        } catch (UnsupportedEncodingException | PackageManager.NameNotFoundException e) {
           e.printStackTrace();
           Toast.b(FEED.this, getString(R.string.y68));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent a = getIntent();
        sg = a.getStringExtra("webvium");
        a.removeExtra("webvium");
        a.replaceExtras(new Bundle());
        a.setAction("");
        a.setData(null);
        a.setFlags(0);
    }
}
