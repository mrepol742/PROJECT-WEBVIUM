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
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.app.Package;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.app.fragment.AboutFragment;
import com.mrepol742.webvium.app.fragment.BackupFragment;
import com.mrepol742.webvium.app.fragment.SettingFragment;
import com.mrepol742.webvium.security.Base64;
import com.mrepol742.webvium.util.Animation;

/*
 * SettingActivity
 */
public class Sett0 extends BaseActivity implements View.OnClickListener{
    private LinearLayout ll;
    private String id = "a";
    private ImageView iv;
    private ImageView iv0;
    private ImageView iv1;
    private PopupMenu mu;
    private TextView b;

    final MenuItem.OnMenuItemClickListener mio = new MenuItem.OnMenuItemClickListener() {

        @Override
        public boolean onMenuItemClick(MenuItem a1) {
            switch (a1.getItemId()) {
                case 0:
                    a(true);
                    return true;
                case 1:
                    a(false);
                    return true;
                default:
                    return false;
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.m17:
                b.setText(Sett0.this.getString(R.string.h3));
                Sett0.this.as(R.id.m10, new SettingFragment());
                id = "a";
                Sett0.this.b24(View.VISIBLE, View.GONE);
                Animation.animate(Sett0.this, R.anim.i, view);
                Animation.animate(Sett0.this, R.anim.c, b);
                break;
            case R.id.m20:
                b.setText(Sett0.this.getString(R.string.t21));
                Sett0.this.as(R.id.m10, new BackupFragment());
                id = "b";
                Sett0.this.b24(View.GONE, View.VISIBLE);
                Animation.animate(Sett0.this, R.anim.i, iv1);
                break;
            case R.id.m1:
                b.setText(Sett0.this.getString(R.string.l));
                Sett0.this.as(R.id.m10, new AboutFragment());
                id = "c";
                Sett0.this.b24(View.VISIBLE, View.GONE);
                Animation.animate(Sett0.this, R.anim.i, view);
                Animation.animate(Sett0.this, R.anim.c, b);
                break;
            case R.id.b7:
                Sett0.this.finishAndRemoveTask();
                break;
            case R.id.o25:
                Intent b = new Intent("android.intent.action.SEND");
                b.setType("text/plain");
                b.putExtra("android.intent.extra.TEXT", String.format(getString(R.string.f33), Base64.decode("aHR0cHM6Ly9tcmVwb2w3NDIuZ2l0aHViLmlvL1BST0pFQ1QtV0VCVklVTS8")));
                startActivity(Intent.createChooser(b, String.format(getString(R.string.l8), "\"" + Package.c() + "\"")));
                break;
            case R.id.o26:
                if (mu == null) {
                    mu = new PopupMenu(Sett0.this, view);
                    Menu menu = mu.getMenu();
                    menu.add(0, 0, 0, getString(R.string.f14)).setOnMenuItemClickListener(mio);
                    menu.add(0, 1, 0, getString(R.string.a41)).setOnMenuItemClickListener(mio);
                }
                mu.show();
                break;
            case R.id.o55:
            default:
                break;
        }
        Sett0.this.invalidateOptionsMenu();
    }

    @Override
    protected void onCreate(Bundle a) {
        theme(T_DEFAULT);
        super.onCreate(a);
        a225(R.layout.a);
        Toolbar tl = findViewById(R.id.b7);
        b = findViewById(R.id.b8);
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
        tl.setNavigationOnClickListener(this);
        b.setText(getString(R.string.h3));
        as(R.id.m10, new SettingFragment());
        ImageView tv = findViewById(R.id.m17);
        tv.setOnClickListener(this);
        ImageView tv1 = findViewById(R.id.m20);
            tv1.setOnClickListener(this);
        ImageView tv2 = findViewById(R.id.m1);
        tv2.setOnClickListener(this);
        if (!a221().getBoolean("autoUpdate", false)) {
            b.setTextColor(k);
        } else {
            b.setTextColor(l);
        }
        ll = findViewById(R.id.m16);
        ll.setBackgroundResource(R.drawable.f1);
        ll.setElevation(5);
        tv.setBackgroundResource(R.drawable.b17);
        tv1.setBackgroundResource(R.drawable.b17);
        tv2.setBackgroundResource(R.drawable.b17);
        tv.setImageResource(R.drawable.g9);
        tv1.setImageResource(R.drawable.g11);
        tv2.setImageResource(R.drawable.g12);
        iv = findViewById(R.id.o25);
        iv0 = findViewById(R.id.o26);
        iv1 = findViewById(R.id.o55);
        iv.setBackgroundResource(R.drawable.c6);
        iv0.setBackgroundResource(R.drawable.c6);
        iv1.setBackgroundResource(R.drawable.c6);
        iv.setOnClickListener(this);
        iv0.setOnClickListener(this);
        iv1.setOnClickListener(this);
        iv.setImageResource(R.drawable.c7);
        iv0.setImageResource(R.drawable.c8);
        iv1.setImageResource(R.drawable.b4);
        b24(View.VISIBLE, View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Animation.animate(this, R.anim.a, ll);
        if (id.equals("a") || id.equals("c")) {
            Animation.animate(this, R.anim.i, iv);
            Animation.animate(this, R.anim.i, iv0);
        } else if (id.equals("b")) {
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

    private void b24(int view, int views) {
        iv.setVisibility(view);
        iv0.setVisibility(view);
        iv1.setVisibility(views);
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