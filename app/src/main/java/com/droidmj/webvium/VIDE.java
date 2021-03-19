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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.app.base.BaseActivity;
import com.droidmj.webvium.app.main.MainWebView;
import com.droidmj.webvium.app.main.MainWebViewClient;
import com.droidmj.webvium.content.Resources;
import com.droidmj.webvium.util.Base64;
import com.droidmj.webvium.util.cache.BitmapCache;

import java.io.File;

// @Class VideoPoster
public class VIDE extends BaseActivity {

    private MainWebView w4;
    private FrameLayout fl;

    @Override
    protected void onCreate(Bundle a) {
        theme(BuildConfiguration.Theme.DEFAULT);
        super.onCreate(a);

        a225(R.layout.c9);

        Toolbar h17 = findViewById(R.id.c3);
        TextView h18 = findViewById(R.id.c4);
        TextView k8 = findViewById(R.id.c5);
        fl = findViewById(R.id.d);
        w4 = new MainWebView(this);
        fl.addView(w4);
        h18.setTypeface(type(Typeface.BOLD));
        k8.setTypeface(type(Typeface.BOLD));
        setActionBar(h17);
        h17.setElevation(5);
        h18.setText(getString(R.string.m10));
        k8.setText(getString(R.string.l13));
        ActionBar ab = getActionBar();
        if (ab != null) {
            // ab.setDisplayHomeAsUpEnabled(true);
            // ab.setDisplayShowHomeEnabled(false);
            ab.setDisplayShowTitleEnabled(false);
        }
        if (!a221().getBoolean("autoUpdate", false)) {
            h18.setTextColor(Resources.b(this, R.color.c));

            k8.setTextColor(Resources.b(this, R.color.c));
            w4.setBackgroundColor(Resources.b(this, R.color.p));
        } else {
            h18.setTextColor(Resources.b(this, R.color.b));
            k8.setTextColor(Resources.b(this, R.color.b));
            w4.setBackgroundColor(Resources.b(this, R.color.m));
        }
        h17.setBackgroundResource(R.drawable.p);
        h17.setNavigationIcon(R.drawable.a2);
        h17.setNavigationOnClickListener(view -> finish());

        SharedPreferences prefs = getSharedPreferences("Webvium", 0);
        boolean d78 = prefs.getBoolean("webDa", false);
        if (d78) {
            k8.setVisibility(View.VISIBLE);
        } else {
            k8.setVisibility(View.GONE);
        }
        w4.setWebViewClient(new MainWebViewClient());
        w4.setWebChromeClient(new WebChromeClient() {

            @Override
            public Bitmap getDefaultVideoPoster() {
                return a();
            }

            @Override
            public View getVideoLoadingProgressView() {
                return b();
            }
        });
        w4.loadDataWithBaseURL(null, Base64.a("PCFET0NUW VBFIGh0bWw+PGh0bWw+PGhlYWQ+PHRpdGxlPldlYnZpdW0gVmlkZW8gUG9zdGVyPC90aXRsZT48bWV0YSBuYW1lPVwidmlld3BvcnRcIiBjb250ZW50PVwid2lkdGg9ZGV2aWNlLXdpZHRoLCBpbml0aWFsLXNjYWxlPTEuMCwgbW F4aW11bS1zY2FsZT0xLjAsIG1pbmltdW0tc2NhbGU9MS4wLCB1c2VyLXNjYWxhYmxlPW5vXCIvPjwvaGVhZD48Ym9keT48Y2VudGVyPjx2aWRlbyBzdHlsZT1cIm1heC13aWR0a DogMTAwJTsgaGVpZ2h0OiBhdXRvO1wiLz48L2NlbnRlcj48L2Jv ZHk+PC9odG1sPg").replaceAll("\\\\", ""), "text/html", w4.getTextEncoding(), null);

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

    public Bitmap a() {
        if (new File(getFilesDir() + BuildConfiguration.Files.videoPoster).exists()) {
            return BitmapCache.getInstance().a(getFilesDir() + BuildConfiguration.Files.videoPoster);
        }
        return BitmapFactory.decodeResource(getResources(), R.drawable.e3);
    }

    public View b() {
        View v = View.inflate(this, R.layout.a21, null);
        ImageView v4 = v.findViewById(R.id.n28);
        v4.setImageResource(R.drawable.a21);
        return v;
    }

}