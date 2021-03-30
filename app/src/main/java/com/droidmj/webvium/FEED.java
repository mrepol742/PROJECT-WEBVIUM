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
import android.app.ActionBar;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.app.base.BaseActivity;
import com.droidmj.webvium.app.main.MainWebView;
import com.droidmj.webvium.app.main.MainWebViewClient;
import com.droidmj.webvium.content.Resources;
import com.droidmj.webvium.net.Connectivity;
import com.droidmj.webvium.text.TextWatcher;
import com.droidmj.webvium.util.Base64;
import com.droidmj.webvium.view.Animation;
import com.droidmj.webvium.widget.Toast;

// @Class Feedback
public class FEED extends BaseActivity {
    private static final String[] config = {
            "PCFET0NUWVBFIGh0bWw+PGh0bWw+PGhlYWQ+PHNjcmlwdCBzc mM9IiVhIj48L3NjcmlwdD48c2NyaXB0PiViPC9zY3JpcHQ+PC9oZWFkPjxib2R5PjwvYm9keT48L 2h0bWw",
            "aH R0cHM6Ly9zbX RwanMu Y29tL3YzL3NtdHAuanM",
            "RW1haWwuc2V  uZCh7SG9zdDogIiVhICIsIFVzZXJuYW1lIDogIiViIiwgUGFzc3dvcmQgOiAiJWMiLCBUbyA6ICclZCcsIEZyb20gOiAiJWIiLCBTdWJqZWN0IDogIiVmIiwgQm9keSA6ICIlZyJ9KS50aGVuKGZ1bmN0aW9uKG1lc3NhZ2Upe2FsZXJ0KCIlMjAiKTt9KTs",
            "c210c C5nbWFpb C5jb20",
            "ZH JvaWRtai5zc HBydEBnbW FpbC5jb20",
            "T2  5lJTIwYWNjb 3VudCUyMSUyMEFsbCUyMG9mJT IwR29vZ2xlJTIwd29ya2luZyUyMGZvciUyMHlv dSUyM  Q",
            " bXJlcG 9sNzQyQGdtYWlsLmNvbQ"
    };
    private LinearLayout rl;
    private MainWebView b;
    private ImageView iv;
    private AutoCompleteTextView act;
    private EditText et;

    @Override
    protected void onCreate(Bundle a) {
        theme(T_DEFAULT);
        super.onCreate(a);
        a225(R.layout.d2);
        rl = findViewById(R.id.o24);
        Toolbar a1 = findViewById(R.id.i2);
        TextView a2 = findViewById(R.id.i3);
        TextView tv = findViewById(R.id.h14);
        act = findViewById(R.id.b15);
        TextView tv0 = findViewById(R.id.h15);
        et = findViewById(R.id.h16);
        iv = findViewById(R.id.k3);
        iv.setBackgroundResource(R.drawable.b17);
        setActionBar(a1);
        a1.setElevation(5);
        a1.setBackgroundResource(R.drawable.p);
        a2.setText(getString(R.string.f14));
        tv0.setText(getString(R.string.y26));
        tv.setText(getString(R.string.x40));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,
                new String[]{getString(R.string.y23), getString(R.string.y24), getString(R.string.y25)});
        act.setAdapter(adapter);
        act.setThreshold(0);
        act.setOnItemClickListener((adapterView, view, i, l) -> {
            switch (i) {
                case 0:
                    tv0.setText(getString(R.string.y21));
                    break;
                case 1:
                    tv0.setText(getString(R.string.y27));
                    break;

                case 2:
                    tv0.setText(getString(R.string.y22));
                    break;
            }
        });
        act.setDropDownBackgroundDrawable(Resources.a(this, R.drawable.c12));

        int f = Resources.b(this, R.color.c);
        int g = Resources.b(this, R.color.b);
        if (!a221().getBoolean("autoUpdate", false)) {
            a2.setTextColor(f);
            act.setTextColor(f);
            tv0.setTextColor(f);
            et.setTextColor(f);
            tv.setTextColor(f);
        } else {
            a2.setTextColor(g);
            act.setTextColor(g);
            tv0.setTextColor(g);
            et.setTextColor(g);
            tv.setTextColor(g);
        }
        ActionBar ab = getActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);
        }
        a2.setTypeface(type(Typeface.BOLD));
        tv.setTypeface(type(Typeface.BOLD));
        tv0.setTypeface(type(Typeface.BOLD));
        act.setTypeface(type(Typeface.NORMAL));
        et.setTypeface(type(Typeface.NORMAL));
        a();
        act.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() <= 5) {
                    act.setError(charSequence.toString().trim().length() + "/5");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                a();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            et.setImportantForAutofill(View.IMPORTANT_FOR_AUTOFILL_NO);
        }
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
            if (act.getText().toString().trim().length() > 5 && et.getText().toString().trim().length() > 20) {
                c(act.getText().toString(), et.getText().toString());
            }
        });

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
        if (act.getText().toString().trim().length() > 5 && et.getText().toString().trim().length() > 20) {
            iv.setImageResource(R.drawable.c3);
        } else {
            iv.setImageResource(R.drawable.g8);
        }
        Animation.animate(this, R.anim.c, iv);
    }


    @SuppressLint("SetJavaScriptEnabled")
    public void c(final String url, final String tmp) {
        final String a = tmp.replaceAll("\n", "<br>");
        if (b == null) {
            b = new MainWebView(this);
            rl.addView(b);
            WebSettings ws = b.getSettings();
            ws.setJavaScriptEnabled(true);
            b.setWebChromeClient(new WebChromeClient() {

                @Override
                public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                    if (message.contains("%20")) {
                        Toast.b(FEED.this, getString(R.string.g27));
                        finishAndRemoveTask();
                    }
                    result.confirm();
                    return true;
                }
            });
            b.setWebViewClient(new MainWebViewClient() {

                @Override
                public void receivedError(int b, String c, String d, boolean bn, boolean bn1) {
                    if (Connectivity.isThereAnyInternetConnection(FEED.this)) {
                        Toast.c(FEED.this, getString(R.string.x38));
                    }
                }
            });
        }
        String sg = Base64.a(config[2] + "=");
        String sg0 = sg.replace("%a ", Base64.a(config[3] + "="));
        String sg1 = sg0.replaceAll("%b", Base64.a(config[4] + "="));
        String sg2 = sg1.replace("%c", Base64.a(config[5] + "==").replaceAll("%20", " ").replaceAll("%21", "."));
        String sg3 = sg2.replace("%d", Base64.a(config[6] + "==")).replace("%f", url).replace("%g", a);

        String sg11 = Base64.a(config[0] + "+").replace("%b", sg3).replace("%a", Base64.a(config[1] + "="));
        b.loadDataWithBaseURL("null", sg11, "text/html", "UTF-8", "null");
        /*
        Runnable p15 = () -> Stream.e(Base64.a(W5.a2()) + a);
        new Thread(p15).start();*/
    }
}
