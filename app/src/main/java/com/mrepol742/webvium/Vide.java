/*
 *
 * Copyright (c) 2021 Melvin Jones Repol (mrepol742.github.io). All rights reserved.
 *
 * License under the GNU General Public License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain changedTo copy of the License at
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
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.app.main.MainWebView;
import com.mrepol742.webvium.app.main.MainWebViewClient;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.app.StorageDirectory;
import com.mrepol742.webvium.util.BitmapCache;

import java.io.File;

/*
 * @VideoPosterActivity
 */
public class Vide extends BaseActivity {

    private MainWebView w4;
    private FrameLayout fl;

    @Override
    protected void onCreate(Bundle a) {
        theme(T_DEFAULT);
        super.onCreate(a);
        a225(R.layout.c9);
        Toolbar h17 = findViewById(R.id.b7);
        TextView h18 = findViewById(R.id.b8);
        fl = findViewById(R.id.d);
        w4 = new MainWebView(this);
        fl.addView(w4);
        h18.setTypeface(type(Typeface.BOLD));
        setActionBar(h17);
        h17.setElevation(5);
        h18.setText(getString(R.string.m10));
        ActionBar ab = getActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);
        }
        if (!a221().getBoolean("autoUpdate", false)) {
            h18.setTextColor(Resources.getColor(this, R.color.c));
            w4.setBackgroundColor(Resources.getColor(this, R.color.p));
        } else {
            h18.setTextColor(Resources.getColor(this, R.color.b));
            w4.setBackgroundColor(Resources.getColor(this, R.color.m));
        }
        h17.setBackgroundResource(R.drawable.p);
        h17.setNavigationIcon(R.drawable.a2);
        h17.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Vide.this.finish();
            }
        });
        w4.setWebViewClient(new MainWebViewClient());
        w4.setWebChromeClient(new WebChromeClient() {

            @Override
            public Bitmap getDefaultVideoPoster() {
                if (new File(StorageDirectory.getVideoPoster(Vide.this)).exists()) {
                    return BitmapCache.getInstance().a(StorageDirectory.getVideoPoster(Vide.this));
                }
                return null;
            }

            @Override
            public View getVideoLoadingProgressView() {
                View v = View.inflate(Vide.this, R.layout.a21, null);
                ImageView v4 = v.findViewById(R.id.n28);
                v4.setImageResource(R.drawable.a21);
                return v;
            }
        });
        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>Webvium Video Poster</title>\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no\"/>\n" +
                "</head>\n" +
                "<body>\n" +
                "<center>\n" +
                "<video style=\"max-width: 100%; height: auto;\"/>\n" +
                "</center>\n" +
                "</body>\n" +
                "</html>";
        w4.loadDataWithBaseURL(null, html, "text/html", w4.getTextEncoding(), null);
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