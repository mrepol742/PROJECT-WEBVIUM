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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.app.main.MainWebView;
import com.mrepol742.webvium.app.main.MainWebViewClient;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.io.StorageDirectory;
import com.mrepol742.webvium.net.Connectivity;
import com.mrepol742.webvium.net.Ping;
import com.mrepol742.webvium.security.Hash;
import com.mrepol742.webvium.text.Html;
import com.mrepol742.webvium.util.Stream;
import com.mrepol742.webvium.widget.AwesomeToast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;

// @Class Tools
public class TOOL extends BaseActivity {

    private TextView k;
    private TextView tt;
    private MainWebView m;
    public static final int TOOL_SOURCE_CODE = 0;
    public static final int TOOL_ROBOTS = 2;
    public static final int TOOL_SITEMAPS = 1;
    public static final int TOOL_ASSET_LINKS = 3;
    public static final int TOOL_HEADERS = 4;
    private FrameLayout fl;

    @Override
    protected void onCreate(Bundle a) {
        theme(T_DEFAULT);
        super.onCreate(a);
        a225(R.layout.d);
        k = findViewById(R.id.l);
        tt = findViewById(R.id.o29);
        fl = findViewById(R.id.c);
        Toolbar i = findViewById(R.id.k);
        k.setTypeface(type(Typeface.BOLD));
        m = new MainWebView(this);
        fl.addView(m);
        int o = Resources.getColor(this, R.color.c);
        int p = Resources.getColor(this, R.color.b);
        m.setBackgroundColor(Resources.getColor(this, R.color.p));
        if (!a221().getBoolean("autoUpdate", false)) {
            k.setTextColor(o);
            tt.setTextColor(o);
        } else {
            k.setTextColor(p);
            tt.setTextColor(p);
        }
        k.setTypeface(type(Typeface.NORMAL));
        tt.setTypeface(type(Typeface.BOLD));
        i.setBackgroundResource(R.drawable.p);
        setActionBar(i);
        i.setElevation(5);
        ActionBar ab = getActionBar();
        if (ab != null) {
            // ab.setDisplayHomeAsUpEnabled(true);
            // ab.setDisplayShowHomeEnabled(false);
            ab.setDisplayShowTitleEnabled(false);
        }
        i.setNavigationIcon(R.drawable.a2);
        i.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TOOL.this.finish();
            }
        });
        m.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onConsoleMessage(ConsoleMessage cm) {
                AlertDialog.Builder bld = new AlertDialog.Builder(TOOL.this);
                bld.setTitle(getString(R.string.y65));
                bld.setMessage(Html.b(String.format(getString(R.string.v18), cm.message(), cm.lineNumber(), cm.sourceId())));
                bld.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                bld.create().show();
                return true;
            }
        });
        m.setWebViewClient(new MainWebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                AwesomeToast.b(TOOL.this, getString(R.string.v13));
            }

            @Override
            public void receivedError(int b, String c, String d, boolean bn, boolean bn1) {
                if (Connectivity.isThereAnyInternetConnection(TOOL.this)) {
                    AwesomeToast.c(TOOL.this, getString(R.string.x38));
                    AwesomeToast.b(TOOL.this, getString(R.string.y72));
                }
                m.loadDataWithBaseURL("webvium", getString(R.string.c33), "html/text", "UTF-8", null);
            }
        });
        WebSettings ws = m.getSettings();
        if (Build.VERSION.SDK_INT >= 29) {
            ws.setForceDark(WebSettings.FORCE_DARK_OFF);
        }
        ws.setSupportZoom(true);
        if (Build.VERSION.SDK_INT < 30) {
            ws.setAllowFileAccess(true);
        }
        ws.setDisplayZoomControls(true);
        ws.setBuiltInZoomControls(true);
    }

    @Override
    public boolean onKeyDown(int a, KeyEvent b1) {
        if (b1.getAction() == 0 && a == 4) {
            finish();
            return true;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        onNewIntent(getIntent());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (m != null) {
            fl.removeView(m);
            m.removeAllViews();
            m.destroy();
        }
    }

    @Override
    protected void onNewIntent(Intent a) {
        try {
            int id = a.getIntExtra("id", 0);
            final String data = a.getStringExtra("dat");
            switch (id) {
                case TOOL_SOURCE_CODE:
                    tt.setText(getString(R.string.j));
                    k.setText(data);
                    if (data.startsWith("view-source:")) {
                        m.loadUrl(data);
                    } else {
                        m.loadUrl("view-source:"+data);
                    }
                    break;
                case TOOL_ROBOTS:
                    Runnable runnable = new Runnable() {

            @Override
                        public void run() {
                            try {
                                final URL url = new URL(data);
                                final boolean bn = Ping.isReachable(url.getProtocol() + "://" + url.getHost() + "/robots.txt");
                                TOOL.this.runOnUiThread(new Runnable() {

            @Override
                                    public void run() {
                                        if (bn) {
                                            m.loadUrl(url.getProtocol() + "://" + url.getHost() + "/robots.txt");
                                        } else {
                                            m.loadDataWithBaseURL(null, TOOL.this.getString(R.string.c33), "text", "UTF-8", null);
                                        }
                                    }
                                });
                            } catch (Exception en) {
                                en.printStackTrace();
                            }
                        }
                    };
                    new Thread(runnable).start();
                    tt.setText(getString(R.string.f32));
                    k.setText(Uri.parse(data).getHost());
                    break;
                case TOOL_ASSET_LINKS:
                    Runnable runnable1 = new Runnable() {

            @Override
                        public void run() {
                            try {
                                final URL url = new URL(data);
                                final boolean bn = Ping.isReachable(url.getProtocol() + "://" + url.getHost() + "/.well-known/assetlinks.json");
                                TOOL.this.runOnUiThread(new Runnable() {

            @Override
                                    public void run() {
                                        if (bn) {
                                            m.loadUrl(url.getProtocol() + "://" + url.getHost() + "/.well-known/assetlinks.json");
                                        } else {
                                            m.loadDataWithBaseURL(null, TOOL.this.getString(R.string.c33), "text", "UTF-8", null);
                                        }

                                    }
                                });
                            } catch (Exception en) {
                                en.printStackTrace();
                            }
                        }
                    };
                    new Thread(runnable1).start();
                    tt.setText(getString(R.string.y76));
                    k.setText(Uri.parse(data).getHost());
                    break;
                case TOOL_SITEMAPS:
                    Runnable runnable2 = new Runnable() {

            @Override
                        public void run() {
                            try {
                                final URL url = new URL(data);
                                final boolean bn = Ping.isReachable(url.getProtocol() + "://" + url.getHost() + "/sitemap.xml");
                                TOOL.this.runOnUiThread(new Runnable() {

            @Override
                                    public void run() {
                                        if (bn) {
                                            m.loadUrl(url.getProtocol() + "://" + url.getHost() + "/sitemap.xml");
                                        } else {
                                            m.loadDataWithBaseURL(null, TOOL.this.getString(R.string.c33), "text", "UTF-8", null);
                                        }
                                    }
                                });
                            } catch (Exception en) {
                                en.printStackTrace();
                            }
                        }
                    };
                    new Thread(runnable2).start();
                    tt.setText(getString(R.string.y77));
                    k.setText(Uri.parse(data).getHost());
                    break;
                case TOOL_HEADERS:
                    Runnable runnable3 = new Runnable() {

            @Override
                        public void run() {
                            try {
                                StringBuilder stringBuilder = new StringBuilder("<!DOCTYPE html><html><head></head><body>");
                                stringBuilder.append(Stream.d(data, TOOL.this.getString(R.string.c33)));
                                stringBuilder.append("\n</body></html");
                                File fe = new File(StorageDirectory.getFileDir(TOOL.this) + "/" + Hash.a("SHA-1", "Headers.html") + ".html");
                                if (fe.createNewFile()) {
                                    FileWriter fw = new FileWriter(fe, false);
                                    BufferedWriter br = new BufferedWriter(fw);
                                    br.write(stringBuilder.toString());
                                    br.close();
                                    fw.close();
                                    TOOL.this.runOnUiThread(new Runnable() {

            @Override
                                        public void run() {
                                            m.loadUrl("file://" + StorageDirectory.getFileDir(TOOL.this) + "/" + Hash.a("SHA-1", "Headers.html") + ".html");
                                        }
                                    });
                                } else {
                                    m.loadDataWithBaseURL(null, TOOL.this.getString(R.string.c33), "text", "UTF-8", null);
                                }
                            } catch (Exception en) {
                                en.printStackTrace();
                            }
                        }
                    };
                    new Thread(runnable3).start();
                    tt.setText(getString(R.string.y15));
                    k.setText(Uri.parse(data).getHost());
                    break;
                default:
                    // popup list


            }
            a.replaceExtras(new Bundle());
            a.setAction("");
            a.setData(null);
            a.setFlags(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    
}
 