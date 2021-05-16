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
import android.content.Intent;
import android.graphics.Typeface;
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
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.setting.AboutFragment;
import com.mrepol742.webvium.setting.DatabaseFragment;
import com.mrepol742.webvium.setting.FeatureFragment;
import com.mrepol742.webvium.setting.SettingFragment;
import com.mrepol742.webvium.util.Base64;
import com.mrepol742.webvium.util.Hardware;
import com.mrepol742.webvium.view.Animation;

// Settings
public class SETT0 extends BaseActivity {
    private LinearLayout ll;
    private String id = "a";
    private ImageView iv, iv0;

    @Override
    protected void onCreate(Bundle a) {
        theme(T_DEFAULT);
        super.onCreate(a);
        a225(R.layout.a);
        Toolbar tl = findViewById(R.id.c3);
        TextView b = findViewById(R.id.c4);
        setActionBar(tl);
        TextView c1 = findViewById(R.id.c5);
        b.setTypeface(type(Typeface.BOLD));
        c1.setTypeface(type(Typeface.BOLD));
        int k = Resources.getColor(this, R.color.c);
        int l = Resources.getColor(this, R.color.b);
        ActionBar ab = getActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);
        }
        tl.setBackgroundResource(R.drawable.p);
        tl.setNavigationIcon(R.drawable.a2);
        tl.setNavigationOnClickListener(view -> finishAndRemoveTask());
        b.setText(getString(R.string.h3));
        c1.setText(getString(R.string.l13));
        boolean d78 = a224("webDa", false);
        if (d78) {
            c1.setVisibility(View.VISIBLE);
        } else {
            c1.setVisibility(View.GONE);
        }
        as(R.id.m10, new SettingFragment());
        TextView tv = findViewById(R.id.m17);
        tv.setText(getString(R.string.h3));
        Animation.animate(this, R.anim.c, b);
        Animation.animate(this, R.anim.i, tv);
        tv.setOnClickListener(view -> {
            try {
                Animation.animate(this, R.anim.c, b);
                b.setText(getString(R.string.h3));

            } catch (Exception en) {
                en.printStackTrace();
            }
            Animation.animate(this, R.anim.i, tv);
            as(R.id.m10, new SettingFragment());
            id = "a";
            b24(true);
            invalidateOptionsMenu();
        });
        TextView tv0 = findViewById(R.id.m18);
        tv0.setText(getString(R.string.y28));
        tv0.setOnClickListener(view -> {
            try {
                Animation.animate(this, R.anim.i, tv0);
                Animation.animate(this, R.anim.c, b);
                b.setText(getString(R.string.y28));

            } catch (Exception en) {
                en.printStackTrace();
            }
            as(R.id.m10, new FeatureFragment());
            id = "a3";
            b24(true);
            invalidateOptionsMenu();
        });
        TextView tv1 = findViewById(R.id.m20);
        tv1.setText(getString(R.string.t21));
        tv1.setOnClickListener(view -> {
            try {
                Animation.animate(this, R.anim.i, tv1);
                Animation.animate(this, R.anim.c, b);
                b.setText(getString(R.string.t21));

            } catch (Exception en) {
                en.printStackTrace();
            }
            as(R.id.m10, new DatabaseFragment());
            id = "a2";
            b24(false);
            invalidateOptionsMenu();
        });
        TextView tv2 = findViewById(R.id.m1);
        tv2.setText(getString(R.string.l));
        tv2.setOnClickListener(view -> {
            try {
                Animation.animate(this, R.anim.i, tv2);
                Animation.animate(this, R.anim.c, b);
                b.setText(getString(R.string.l));

            } catch (Exception en) {
                en.printStackTrace();
            }
            as(R.id.m10, new AboutFragment());
            id = "a1";
            b24(true);
            invalidateOptionsMenu();
        });
        if (!a221().getBoolean("autoUpdate", false)) {
            b.setTextColor(k);
            c1.setTextColor(k);

            tv.setTextColor(k);
            tv0.setTextColor(k);
            tv1.setTextColor(k);
            tv2.setTextColor(k);
        } else {
            b.setTextColor(l);
            c1.setTextColor(l);
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
        iv.setBackgroundResource(R.drawable.c6);
        iv0.setBackgroundResource(R.drawable.c6);
        iv.setOnClickListener(view -> b23());
        iv0.setOnClickListener(view -> Intents.a(this, FEED.class));
        iv.setImageResource(R.drawable.c7);
        iv0.setImageResource(R.drawable.c8);
        b24(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Animation.animate(this, R.anim.a, ll);
        if (iv.getVisibility() == View.VISIBLE && iv0.getVisibility() == View.VISIBLE && id.equals("a2")) {
            Animation.animate(this, R.anim.i, iv);
            Animation.animate(this, R.anim.i, iv0);
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
                Intents.a(this, FEED.class);
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
        } else {
            iv.setVisibility(View.VISIBLE);
            iv.setVisibility(View.VISIBLE);
        }
    }
}