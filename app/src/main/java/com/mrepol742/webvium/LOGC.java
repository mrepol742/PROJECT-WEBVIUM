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
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mrepol742.webvium.annotation.Test;
import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.widget.Toast;

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
            int f = Resources.getColor(this, R.color.c);
            int g = Resources.getColor(this, R.color.b);
            a2.setTypeface(type(Typeface.BOLD));
            tv.setTypeface(type(Typeface.NORMAL));
            a2.setText(getString(R.string.f30));
            if (!a221().getBoolean("autoUpdate", false)) {
                a2.setTextColor(f);
                tv.setTextColor(f);
                tv.setBackgroundColor(Resources.getColor(this, R.color.p));
            } else {
                tv.setBackgroundColor(Resources.getColor(this, R.color.m));
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
            rx.printStackTrace();
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
               e1.printStackTrace();
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