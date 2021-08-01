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
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.app.Package;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.setting.fragment.AboutFragment;
import com.mrepol742.webvium.setting.fragment.DatabaseFragment;
import com.mrepol742.webvium.setting.fragment.FeatureFragment;
import com.mrepol742.webvium.setting.fragment.SettingFragment;
import com.mrepol742.webvium.security.Base64;
import com.mrepol742.webvium.util.Hardware;
import com.mrepol742.webvium.util.Animation;

/*
 * SettingActivity
 */
public class Sett0 extends BaseActivity {
    private LinearLayout ll;
    private String id = "a";
    private ImageView iv, iv0, iv1;

    @Override
    protected void onCreate(Bundle a) {
        theme(T_DEFAULT);
        super.onCreate(a);
        a225(R.layout.a);
        Toolbar tl = findViewById(R.id.b7);
        final TextView b = findViewById(R.id.b8);
        setActionBar(tl);
        b.setTypeface(type(Typeface.BOLD));
        int k = Resources.getColor(this, R.color.c);
        int l = Resources.getColor(this, R.color.b);
        ActionBar ab = getActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);
        }
        tl.setBackgroundResource(R.drawable.p);
        tl.setNavigationIcon(R.drawable.a2);
        tl.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Sett0.this.finishAndRemoveTask();
            }
        });
        b.setText(getString(R.string.h3));
        as(R.id.m10, new SettingFragment());
        final TextView tv = findViewById(R.id.m17);
        tv.setText(getString(R.string.h3));
        Animation.animate(this, R.anim.c, b);
        Animation.animate(this, R.anim.i, tv);
        tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    Animation.animate(Sett0.this, R.anim.c, b);
                    b.setText(Sett0.this.getString(R.string.h3));
                } catch (Exception en) {
                    en.printStackTrace();
                }
                Animation.animate(Sett0.this, R.anim.i, tv);
                Sett0.this.as(R.id.m10, new SettingFragment());
                id = "a";
                Sett0.this.b24(true);
                Sett0.this.invalidateOptionsMenu();
            }
        });
        final TextView tv0 = findViewById(R.id.m18);
        if (BuildConfig.DEBUG) {
            tv0.setText(getString(R.string.y28));
            tv0.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    try {
                        Animation.animate(Sett0.this, R.anim.i, tv0);
                        Animation.animate(Sett0.this, R.anim.c, b);
                        b.setText(Sett0.this.getString(R.string.y28));
                    } catch (Exception en) {
                        en.printStackTrace();
                    }
                    Sett0.this.as(R.id.m10, new FeatureFragment());
                    id = "a3";
                    Sett0.this.b24(true);
                    Sett0.this.invalidateOptionsMenu();
                }
            });
        } else {
            tv0.setVisibility(View.GONE);
        }
        final TextView tv1 = findViewById(R.id.m20);
        if (Build.VERSION.SDK_INT < 29 && BuildConfig.DEBUG) {
            tv1.setText(getString(R.string.t21));
            tv1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    try {
                        Animation.animate(Sett0.this, R.anim.i, tv1);
                        Animation.animate(Sett0.this, R.anim.c, b);
                        b.setText(Sett0.this.getString(R.string.t21));

                    } catch (Exception en) {
                        en.printStackTrace();
                    }
                    Sett0.this.as(R.id.m10, new DatabaseFragment());
                    id = "a2";
                    Sett0.this.b24(false);
                    Sett0.this.invalidateOptionsMenu();
                }
            });
        } else {
            tv1.setVisibility(View.GONE);
        }
        final TextView tv2 = findViewById(R.id.m1);
        tv2.setText(getString(R.string.l));
        tv2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    Animation.animate(Sett0.this, R.anim.i, tv2);
                    Animation.animate(Sett0.this, R.anim.c, b);
                    b.setText(Sett0.this.getString(R.string.l));

                } catch (Exception en) {
                    en.printStackTrace();
                }
                Sett0.this.as(R.id.m10, new AboutFragment());
                id = "a1";
                Sett0.this.b24(true);
                Sett0.this.invalidateOptionsMenu();
            }
        });
        if (!a221().getBoolean("autoUpdate", false)) {
            b.setTextColor(k);
            tv.setTextColor(k);
            tv0.setTextColor(k);
            tv1.setTextColor(k);
            tv2.setTextColor(k);
        } else {
            b.setTextColor(l);
            tv.setTextColor(l);
            tv0.setTextColor(l);
            tv1.setTextColor(l);
            tv2.setTextColor(l);
        }
        tv.setTypeface(type(Typeface.NORMAL));
        tv0.setTypeface(type(Typeface.NORMAL));
        tv1.setTypeface(type(Typeface.NORMAL));
        tv2.setTypeface(type(Typeface.NORMAL));
        tv.setCompoundDrawablePadding(6);
        tv0.setCompoundDrawablePadding(6);
        tv1.setCompoundDrawablePadding(6);
        tv2.setCompoundDrawablePadding(6);
        ll = findViewById(R.id.m16);
        ll.setBackgroundResource(R.drawable.f1);
        ll.setElevation(5);
        tv.setBackgroundResource(R.drawable.b17);
        tv0.setBackgroundResource(R.drawable.b17);
        tv1.setBackgroundResource(R.drawable.b17);
        tv2.setBackgroundResource(R.drawable.b17);
        tv.setCompoundDrawablesRelativeWithIntrinsicBounds(null, Resources.getDrawable(this, R.drawable.g9), null, null);
        tv0.setCompoundDrawablesRelativeWithIntrinsicBounds(null, Resources.getDrawable(this, R.drawable.g10), null, null);
        tv1.setCompoundDrawablesRelativeWithIntrinsicBounds(null, Resources.getDrawable(this, R.drawable.g11), null, null);
        tv2.setCompoundDrawablesRelativeWithIntrinsicBounds(null, Resources.getDrawable(this, R.drawable.g12), null, null);
        iv = findViewById(R.id.o25);
        iv0 = findViewById(R.id.o26);
        iv1 = findViewById(R.id.a);
        iv.setBackgroundResource(R.drawable.c6);
        iv0.setBackgroundResource(R.drawable.c6);
        iv1.setBackgroundResource(R.drawable.c6);
        iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Sett0.this.b23();
            }
        });
        iv0.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                a(true);
            }
        });
        iv1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                a(false);
            }
        });
        iv.setImageResource(R.drawable.c7);
        iv0.setImageResource(R.drawable.c8);
        iv1.setImageResource(R.drawable.b18);
        b24(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Animation.animate(this, R.anim.a, ll);
        if (iv.getVisibility() == View.VISIBLE && iv0.getVisibility() == View.VISIBLE && id.equals("a2")) {
            Animation.animate(this, R.anim.i, iv);
            Animation.animate(this, R.anim.i, iv0);
            Animation.animate(this, R.anim.i, iv1);
        }
    }

    @Override
    public boolean onKeyDown(int a5, KeyEvent b) {
        if (b.getAction() == KeyEvent.ACTION_DOWN && a5 == KeyEvent.KEYCODE_BACK) {
            as1();
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu a) {
        if (Hardware.isTablet(this) && (id.equals("a") || id.equals("a1"))) {
            a.add(0, 0, 0, getString(R.string.a8));
            a.add(0, 1, 0, getString(R.string.f14));
            a.add(0, 2, 0, getString(R.string.z86));
        }
        return super.onCreateOptionsMenu(a);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem a) {
        switch (a.getItemId()) {
            case 0:
                b23();
                return true;
            case 1:
                a(true);
                return true;
            case 2:
                a(false);
                return true;
            default:
                return super.onOptionsItemSelected(a);
        }
    }

    private void b23() {
        Intent b = new Intent("android.intent.action.SEND");
        b.setType("text/plain");
        b.putExtra("android.intent.extra.TEXT", String.format(getString(R.string.f33), Base64.decode("aHR0cHM6Ly9tcmVwb2w3NDIuZ2l0aHViLmlvL1BST0pFQ1QtV0VCVklVTS8")));
        startActivity(Intent.createChooser(b, String.format(getString(R.string.l8), "\"" + Package.c() + "\"")));
    }

    private void b24(boolean bn) {
        if (bn) {
            iv.setVisibility(View.GONE);
            iv0.setVisibility(View.GONE);
            iv1.setVisibility(View.GONE);
        } else {
            iv.setVisibility(View.VISIBLE);
            iv.setVisibility(View.VISIBLE);
            iv.setVisibility(View.VISIBLE);
        }
    }

    private void a(boolean bn) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{getString(R.string.dev_mail)});
        if (bn) {
            intent.putExtra("android.intent.extra.SUBJECT", getString(R.string.z69));
            intent.putExtra("android.intent.extra.TEXT", getString(R.string.z66));
        } else {
            intent.putExtra("android.intent.extra.SUBJECT", getString(R.string.z86));
            intent.putExtra("android.intent.extra.TEXT", getString(R.string.z87));
        }
        intent.putExtra("android.intent.extra.CC", getString(R.string.dev_mail));
        intent.setType("text/html");
        intent.setPackage("com.google.android.gm");
        startActivity(Intent.createChooser(intent, getString(R.string.z65)));
    }
}