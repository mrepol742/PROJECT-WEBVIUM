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
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.WindowManager;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mrepol742.webvium.app.NoSuchItemToGet;
import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.bookmark.BookmarkHelper;
import com.mrepol742.webvium.content.Clipboard;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.history.HistoryAdapter;
import com.mrepol742.webvium.history.HistoryDataModel;
import com.mrepol742.webvium.history.HistoryDatabase;
import com.mrepol742.webvium.history.HistoryHelper;
import com.mrepol742.webvium.net.IPAddress;
import com.mrepol742.webvium.text.Html;
import com.mrepol742.webvium.text.TextWatcher;
import com.mrepol742.webvium.util.Domain;
import com.mrepol742.webvium.util.Stream;
import com.mrepol742.webvium.util.U3;
import com.mrepol742.webvium.view.Animation;
import com.mrepol742.webvium.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

// @Class History
public class HIST extends BaseActivity {
    private HistoryHelper d1;
    private HistoryAdapter w15;
    private ListView a3;
    private ArrayList<HistoryDataModel> al;
    private RelativeLayout f2;
    private ImageView o21;
    private ImageView o22;
    private TextView f4;
    private PopupMenu pm;
    private int b;
    public static int LINKS = 0;
    public static int TRANCEROUTE = 1;
    public static int NPING = 2;
    public static int WHOIS = 3;
    public static int META_TAGS = 4;
    public static int HEADERS = 5;
    public static int ROBOTS = 6;
    public static int SOURCE_CODE = 7;
    public static int IP_GEO = 8;
    public static int ASSETLINKS = 9;
    public static int SITEMAPS = 10;

    final MenuItem.OnMenuItemClickListener d = a1 -> {
        try {
            String a5 = w15.c(b).ls;
            String a2 = w15.c(b).ls0;
            String a4 = w15.c(b).ls2;
            switch (a1.getItemId()) {
                case 1:
                    b(a2);
                    return true;
                case 2:
                    Clipboard.a(HIST.this, a2);
                    f(getString(R.string.k9));
                    return true;
                case 3:
                    c(a2, a5, a4);
                    return true;
                case 4:

                    h(a5, a2);
                    return true;
                case 9:

                    Clipboard.a(HIST.this, a5);
                    f(getString(R.string.k9));
                    return true;
                case 6:
                    a(a2, a5);
                    return true;

                case 5:
                    l(a2, 7);
                    return true;
                case 13:
                    c43(a2);
                    return true;
                case 15:

                    l(a2, 0);

                    return true;
                case 16:

                    l(a2, 1);

                    return true;
                case 17:

                    l(a2, 2);

                    return true;
                case 18:

                    l(a2, 3);

                    return true;
                case 19:

                    l(a2, 5);

                    return true;
                case 20:

                    l(a2, 4);

                    return true;
                case 21:

                    l(a2, 6);

                    return true;
                case 22:

                    l(a2, 8);

                    return true;
                case 23:
                    b(a5);

                    return true;
                case 24:
                    o(a5, a2, a4);
                    break;
                case 25:
                    l(a2, ASSETLINKS);
                    return true;
                case 26:
                    l(a2, SITEMAPS);
                    return true;
            }
        } catch (NoSuchItemToGet l4) {
            l4.printStackTrace();
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle a) {
        theme(T_DEFAULT);
        super.onCreate(a);
        a225(R.layout.i);
        Toolbar a1 = findViewById(R.id.b7);
        TextView a2 = findViewById(R.id.b8);
        f2 = findViewById(R.id.f2);
        o21 = findViewById(R.id.o21);
        o22 = findViewById(R.id.o22);
        a3 = findViewById(R.id.a3);
        f4 = findViewById(R.id.f4);
        f2.setBackgroundResource(R.drawable.b17);
        setActionBar(a1);
        d1 = HistoryHelper.getInstance(getApplicationContext());
        a1.setElevation(5);
        Cursor res = d1.getReadableDatabase().rawQuery("SELECT * FROM " +
                HistoryDatabase.TABLE_HISTORY +
                " ORDER BY " +
                "_id" +
                " DESC", null);
        if (res.getCount() == 0) {
            runOnUiThread(() -> {
                f4.setVisibility(View.VISIBLE);
                a3.setVisibility(View.GONE);
                f2.setClickable(true);
            });
        } else {
            al = new ArrayList<>();
            while (res.moveToNext()) {
                al.add(new HistoryDataModel(res.getString(1),
                        res.getString(2),
                        res.getString(3)));
            }
            runOnUiThread(() -> {
                f4.setVisibility(View.GONE);
                a3.setVisibility(View.VISIBLE);
                o22.setVisibility(View.VISIBLE);
                Animation.animate(this, R.anim.i, o22);
            });
        }
        res.close();
        ActionBar ab = getActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);
        }
        int f = Resources.getColor(this, R.color.c);
        int g = Resources.getColor(this, R.color.b);
        if (a221().getBoolean("blocksv", false)) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
        f4.setCompoundDrawablesWithIntrinsicBounds(null,
                Resources.getDrawable(this, R.drawable.a28),
                null,
                null);
        f4.setCompoundDrawablePadding(40);
        f4.setText(getString(R.string.k5));
        a2.setTypeface(type(Typeface.BOLD));
        f4.setTypeface(type(Typeface.BOLD));
        a2.setText(getString(R.string.h18));
        a1.setBackgroundResource(R.drawable.p);
        if (!a221().getBoolean("autoUpdate", false)) {

            a2.setTextColor(f);
            f4.setTextColor(f);
        } else {
            a2.setTextColor(g);
            f4.setTextColor(g);
        }
        a1.setNavigationIcon(R.drawable.a2);
        a1.setNavigationOnClickListener(view -> finish());
        if (al != null) {
            w15 = new HistoryAdapter(this, al);
            a3.setAdapter(w15);
            a3.setOnItemClickListener((a4, b, c, d) -> {
                try {
                    Intents.d("value", w15.c(c).ls0, HIST.this);
                } catch (NoSuchItemToGet a34) {
                    a34.printStackTrace();
                }
                finish();
            });
            a3.setOnItemLongClickListener((adapterView, view, i, l) -> {
                n(view, i);
                return true;
            });
        }
        o22.setBackgroundResource(R.drawable.c6);
        o22.setImageResource(R.drawable.a23);
        o22.setOnClickListener(view -> i());
        o22.setVisibility(View.GONE);
        o21.setBackgroundResource(R.drawable.c6);
        o21.setImageResource(R.drawable.a24);
        o21.setOnClickListener(view -> finish());

    }

    @Override
    protected void onResume() {
        super.onResume();
        Animation.animate(this, R.anim.i, o21);
    }

    public void a(String a23, String asd) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);


        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.z, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.h12));
        a.setView(c);
        final TextView ti = c.findViewById(R.id.f9);
        final EDIT ed = c.findViewById(R.id.f10);
        final TextView ti1 = c.findViewById(R.id.f11);
        final EDIT ed1 = c.findViewById(R.id.f12);
        int e = Resources.getColor(this, R.color.c);
        int f = Resources.getColor(this, R.color.b);

        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(e);
            ed1.setTextColor(e);
            ti.setTextColor(e);
            ti1.setTextColor(e);
        } else {
            ed.setTextColor(f);
            ed1.setTextColor(f);
            ti.setTextColor(f);
            ti1.setTextColor(f);
        }
        ti.setText(getString(R.string.t3));
        ti1.setText(getString(R.string.t4));
        if (asd != null) {
            ed.setText(asd);
        } else {
            Uri hl = Uri.parse(a23);
            ed.setText(hl.getHost());
        }
        ed1.setText(a23);
        a.setPositiveButton(getString(R.string.i6), (a2, i) -> {
            shrt(ed.getText().toString(), ed1.getText().toString(), R.mipmap.x);
            a2.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a2, i) -> a2.dismiss());
        final AlertDialog g = a.create();
        g.show();
        final Button okButton = g.getButton(AlertDialog.BUTTON_POSITIVE);
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (U3.a(ed)) {
                    okButton.setEnabled(U3.a(ed1));
                } else {
                    okButton.setEnabled(false);
                }
            }


        });
        ed1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (U3.a(ed)) {
                    okButton.setEnabled(U3.a(ed1));
                } else {
                    okButton.setEnabled(false);
                }
            }


        });
        g.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(U3.a(ed) && U3.a(ed1));
    }

    private void b(String a) {
        Intent b = new Intent("android.intent.action.SEND");
        b.setType("text/plain");
        b.putExtra("android.intent.extra.TEXT", a);
        startActivity(Intent.createChooser(b, String.format(getString(R.string.l8), "\"" + a + "\"")));
    }

    private void c(final String b, final String kl, final String b1) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(getString(R.string.h18));
        a.setMessage(String.format(getString(R.string.l7), "\"" + b + "\""));
        a.setPositiveButton(getString(R.string.i6), (a1, intetg) -> {
            d1.b(b, kl, b1);
            f(String.format(getString(R.string.h5), b));
            k();
            a1.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a12, intetg) -> a12.dismiss());
        a.create().show();
    }

    private void f(String a) {
        Toast.b(this, a);

    }

    private void g(String a, String b) {
        BookmarkHelper d3 = BookmarkHelper.getInstance(getApplicationContext());
        d3.c(a, b);
    }

    public void h(String a23, String asd) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.z, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.h11));
        a.setView(c);
        final TextView ti = c.findViewById(R.id.f9);
        final EDIT ed = c.findViewById(R.id.f10);
        final TextView ti1 = c.findViewById(R.id.f11);
        final EDIT ed1 = c.findViewById(R.id.f12);
        int e = Resources.getColor(this, R.color.c);
        int f = Resources.getColor(this, R.color.b);

        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(e);
            ed1.setTextColor(e);
            ti.setTextColor(e);
            ti1.setTextColor(e);
        } else {
            ed.setTextColor(f);
            ed1.setTextColor(f);
            ti.setTextColor(f);
            ti1.setTextColor(f);
        }
        ti.setText(getString(R.string.t3));
        ti1.setText(getString(R.string.t4));
        if (a23 != null) {
            ed.setText(a23);
        } else {
            Uri hl = Uri.parse(asd);
            ed.setText(hl.getHost());
        }
        ed1.setText(asd);
        a.setPositiveButton(getString(R.string.i6), (a2, i) -> {
            g(ed.getText().toString(), ed1.getText().toString());
            f(getString(R.string.t2));
            a2.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a2, i) -> a2.dismiss());
        final AlertDialog g = a.create();
        g.show();
        final Button okButton = g.getButton(AlertDialog.BUTTON_POSITIVE);
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (U3.a(ed)) {
                    okButton.setEnabled(U3.a(ed1));
                } else {
                    okButton.setEnabled(false);
                }
            }


        });
        ed1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (U3.a(ed)) {
                    okButton.setEnabled(U3.a(ed1));
                } else {
                    okButton.setEnabled(false);
                }
            }


        });
        g.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(U3.a(ed) && U3.a(ed1));
    }

    private void i() {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(getString(R.string.h18));
        a.setMessage(getString(R.string.t5));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            d1.delete();
            f(getString(R.string.t1));
            f4.setVisibility(View.VISIBLE);
            a3.setVisibility(View.GONE);

            o22.setVisibility(View.GONE);
            f2.setClickable(true);

        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void k() {
        Runnable p15 = () -> {
            ArrayList<HistoryDataModel> al = new ArrayList<>();
            Cursor res = d1.getReadableDatabase().rawQuery("SELECT * FROM " +
                    HistoryDatabase.TABLE_HISTORY +
                    " ORDER BY " +
                    "_id" +
                    " DESC", null);
            if (res.getCount() == 0) {
                runOnUiThread(() -> {
                    f4.setVisibility(View.VISIBLE);
                    a3.setVisibility(View.GONE);
                    o22.setVisibility(View.GONE);
                    f2.setClickable(true);
                });
            } else {
                while (res.moveToNext()) {
                    al.add(new HistoryDataModel(res.getString(1),
                            res.getString(2),
                            res.getString(3)));
                }
                if (al.size() == 0) {
                    runOnUiThread(() -> {
                        f4.setVisibility(View.VISIBLE);
                        a3.setVisibility(View.GONE);
                        o22.setVisibility(View.GONE);
                        f2.setClickable(true);
                    });
                } else {
                    runOnUiThread(() -> {
                        w15.a(al);
                        w15.notifyDataSetChanged();
                        f4.setVisibility(View.GONE);
                        a3.setVisibility(View.VISIBLE);
                    });
                }
            }
            res.close();
        };
        new Thread(p15).start();
    }

    public void l(String url, final int type) {
        if (!URLUtil.isValidUrl(url)) {
            Toast.c(this, getString(R.string.c32));
            return;
        } else if (!Domain.isValidDomain(url)) {
            Toast.c(this, getString(R.string.c32));
            return;
        }
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.b8, null);
        a.setCancelable(true);
        if (type == LINKS) {
            a.setTitle(getString(R.string.x9)); // LINKS
        } else if (type == TRANCEROUTE) {
            a.setTitle(getString(R.string.x16)); // TRANCEROUT
        } else if (type == NPING) {
            a.setTitle(getString(R.string.y11)); //NPing
        } else if (type == WHOIS) {
            a.setTitle(getString(R.string.z4)); //Whois
        } else if (type == META_TAGS) {
            a.setTitle(getString(R.string.z15)); //Meta Tags
        } else if (type == HEADERS) {
            a.setTitle(getString(R.string.y15)); // Headers
        } else if (type == ROBOTS) {
            a.setTitle(getString(R.string.f32)); // Robots
        } else if (type == SOURCE_CODE) {
            a.setTitle(getString(R.string.j)); // Source Code
        } else if (type == IP_GEO) {
            a.setTitle(getString(R.string.z12)); // IP GeolocationDataModel
        } else if (type == ASSETLINKS) {
            a.setTitle(getString(R.string.y76)); // assetslinks
        } else if (type == SITEMAPS) {
            a.setTitle(getString(R.string.y77)); // sitemap
        }
        a.setView(c);
        final EDIT ed = c.findViewById(R.id.g8);
        final TextView ti = c.findViewById(R.id.e2);
        final Button bn = c.findViewById(R.id.k20);
        int e = Resources.getColor(this, R.color.c);
        int f = Resources.getColor(this, R.color.b);
        int e3 = Resources.getColor(this, R.color.j);
        int f3 = Resources.getColor(this, R.color.k);
        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(e);
            ti.setTextColor(e3);
        } else {
            ed.setTextColor(f);
            ti.setTextColor(f3);
        }
        ed.setText(url);
        if ((url.startsWith("https://") || url.startsWith("http://")) && (!url.startsWith("file://") || !url.startsWith("content://")) && Domain.isValidDomain(url)){
            bn.setBackgroundResource(R.drawable.c10);
        } else {
            bn.setBackgroundResource(R.drawable.c11);
        }
        bn.setText(getString(R.string.i6));
        ti.setText(String.format(getString(R.string.f31), "https://mrepol742.github.io", "http://mrepol742.github.io"));
        final AlertDialog g = a.create();
        bn.setOnClickListener(view -> {
            String a1 = ed.getText().toString();
            if (type == SOURCE_CODE) {
                Intent it = new Intent(HIST.this, TOOL.class);
                it.putExtra("dat", a1);
                it.putExtra("id", TOOL.TOOL_SOURCE_CODE);
                startActivity(it);
            } else if (type == HEADERS) {
                c126(a1);
            } else if (type == ROBOTS) {
                Intent it = new Intent(HIST.this, TOOL.class);
                it.putExtra("dat", a1);
                it.putExtra("id", TOOL.TOOL_ROBOTS);
                startActivity(it);
            } else if (type == ASSETLINKS) {
                Intent it = new Intent(HIST.this, TOOL.class);
                it.putExtra("dat", a1);
                it.putExtra("id", TOOL.TOOL_ASSET_LINKS);
                startActivity(it);
            } else if (type == SITEMAPS) {
                Intent it = new Intent(HIST.this, TOOL.class);
                it.putExtra("dat", a1);
                it.putExtra("id", TOOL.TOOL_SITEMAPS);
                startActivity(it);
            }
            g.dismiss();
        });
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String url = ed.getText().toString().trim();
                if (url.startsWith("https://") || url.startsWith("http://")) {
                    if (!Domain.isValidDomain(url)){
                        ed.setError(getString(R.string.y84));
                        bn.setBackgroundResource(R.drawable.c11);
                    } else {
                        bn.setBackgroundResource(R.drawable.c10);
                    }
                }  else if (type != SOURCE_CODE && (url.startsWith("file://") || url.startsWith("content://"))){
                    ed.setError(getString(R.string.y83));
                    bn.setBackgroundResource(R.drawable.c11);
                } else {
                    ed.setError(getString(R.string.y82));
                    bn.setBackgroundResource(R.drawable.c11);
                }
            }
        });
        g.show();
    }

    public void c126(String url) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.b8, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.y15));
        a.setView(c);
        final EDIT ed = c.findViewById(R.id.g8);
        final TextView ti = c.findViewById(R.id.e2);
        final Button bn = c.findViewById(R.id.k20);
        int e = Resources.getColor(this, R.color.c);
        int f = Resources.getColor(this, R.color.b);
        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(e);
            ti.setTextColor(e);
        } else {
            ed.setTextColor(f);
            ti.setTextColor(f);
        }
        ti.setText(getString(R.string.v13));
        ed.setText(url);
        Runnable p15 = () -> {
            final String sg = Stream.d(url, getString(R.string.c33));
            runOnUiThread(() -> ti.setText(Html.b(sg)));
        };
        new Thread(p15).start();
        if ((url.startsWith("https://") || url.startsWith("http://")) && (!url.startsWith("file://") || !url.startsWith("content://")) && Domain.isValidDomain(url)){
            bn.setBackgroundResource(R.drawable.c10);
        } else {
            bn.setBackgroundResource(R.drawable.c11);
        }
        bn.setText(getString(R.string.i6));
        bn.setOnClickListener(view -> {
            String ab = ed.getText().toString();
            ti.setText(getString(R.string.v13));
            if ((url.startsWith("https://") || url.startsWith("http://")) && (!url.startsWith("file://") || !url.startsWith("content://")) && Domain.isValidDomain(url)){
                Runnable p151 = () -> {
                    final String sg = Stream.d(ab, getString(R.string.c33));
                    runOnUiThread(() -> ti.setText(Html.b(sg)));
                };
                new Thread(p151).start();
            }
        });
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String url = ed.getText().toString().trim();
                if (url.startsWith("https://") || url.startsWith("http://")) {
                    if (!Domain.isValidDomain(url)){
                        ed.setError(getString(R.string.y84));
                        bn.setBackgroundResource(R.drawable.c11);
                    } else {
                        bn.setBackgroundResource(R.drawable.c10);
                    }
                }  else if (url.startsWith("file://") || url.startsWith("content://")){
                    ed.setError(getString(R.string.y83));
                    bn.setBackgroundResource(R.drawable.c11);
                } else {
                    ed.setError(getString(R.string.y82));
                    bn.setBackgroundResource(R.drawable.c11);
                }
            }


        });
        final AlertDialog g = a.create();
        g.show();
    }

    private void n(View w, int b) {
        if (pm == null) {
            pm = new PopupMenu(this, w);
            this.b = b;
            Menu a = pm.getMenu();
            SubMenu sb2 = a.addSubMenu(getString(R.string.a8));
            sb2.add(0, 1, 0, getString(R.string.t4)).setOnMenuItemClickListener(d);
            sb2.add(0, 23, 0, getString(R.string.s23)).setOnMenuItemClickListener(d);
            SubMenu sb = a.addSubMenu(getString(R.string.u));
            sb.add(0, 2, 0, getString(R.string.t4)).setOnMenuItemClickListener(d);
            sb.add(0, 9, 0, getString(R.string.s23)).setOnMenuItemClickListener(d);
            SubMenu sb0 = a.addSubMenu(getString(R.string.h17));
            sb0.add(0, 4, 0, getString(R.string.h11)).setOnMenuItemClickListener(d);
            sb0.add(0, 6, 0, getString(R.string.h12)).setOnMenuItemClickListener(d);
            a.add(0, 3, 0, getString(R.string.k8)).setOnMenuItemClickListener(d);
            a.add(0, 24, 0, getString(R.string.u23)).setOnMenuItemClickListener(d);
            SubMenu sb1 = a.addSubMenu(getString(R.string.j36));
            sb1.add(0, 19, 0, getString(R.string.y15)).setOnMenuItemClickListener(d);
            //sb1.add(0, 15, 0, getString(R.string.x9)).setOnMenuItemClickListener(d);
            //sb1.add(0, 20, 0, getString(R.string.z15)).setOnMenuItemClickListener(d);
            sb1.add(0, 21, 0, getString(R.string.f32)).setOnMenuItemClickListener(d);
            //sb1.add(0, 16, 0, getString(R.string.x16)).setOnMenuItemClickListener(d);
            //sb1.add(0, 17, 0, getString(R.string.y11)).setOnMenuItemClickListener(d);
            //sb1.add(0, 18, 0, getString(R.string.z4)).setOnMenuItemClickListener(d);
            sb1.add(0, 13, 0, getString(R.string.h6)).setOnMenuItemClickListener(d);
            sb1.add(0, 5, 0, getString(R.string.j)).setOnMenuItemClickListener(d);
            sb1.add(0, 25, 0, getString(R.string.y76)).setOnMenuItemClickListener(d);
            sb1.add(0, 26, 0, getString(R.string.y77)).setOnMenuItemClickListener(d);
            //sb1.add(0, 22, 0, getString(R.string.z12)).setOnMenuItemClickListener(d);
            a.add(0, 12, 0, getString(R.string.i4)).setOnMenuItemClickListener(d);
        }
        pm.show();
    }

    private void o(final String oldTitle, final String oldURl, final String oldTIme) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.z, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.u23));
        a.setView(c);
        final TextView ti = c.findViewById(R.id.f9);
        final EDIT ed = c.findViewById(R.id.f10);
        final TextView ti1 = c.findViewById(R.id.f11);
        final EDIT ed1 = c.findViewById(R.id.f12);
        int e = Resources.getColor(this, R.color.c);
        int f = Resources.getColor(this, R.color.b);

        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(e);
            ed1.setTextColor(e);
            ti.setTextColor(e);
            ti1.setTextColor(e);
        } else {
            ed.setTextColor(f);
            ed1.setTextColor(f);
            ti.setTextColor(f);
            ti1.setTextColor(f);
        }
        ti.setText(getString(R.string.t3));
        ti1.setText(getString(R.string.t4));
        ed.setText(oldTitle);

        ed1.setText(oldURl);
        a.setPositiveButton(getString(R.string.i6), (a2, it) -> {
            d1.i(oldTitle, oldURl, oldTIme, ed.getText().toString(), ed1.getText().toString());
            k();
            a2.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a2, i) -> a2.dismiss());
        final AlertDialog g = a.create();
        g.show();
        final Button okButton = g.getButton(AlertDialog.BUTTON_POSITIVE);
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (U3.a(ed)) {
                    okButton.setEnabled(U3.a(ed1));
                } else {
                    okButton.setEnabled(false);
                }
            }


        });
        ed1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (U3.a(ed)) {
                    okButton.setEnabled(U3.a(ed1));
                } else {
                    okButton.setEnabled(false);
                }
            }


        });

        g.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(U3.a(ed) && U3.a(ed1));
    }

    public void c43(String url) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.b8, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.h6));
        a.setView(c);
        final EDIT ed = c.findViewById(R.id.g8);
        final TextView ti = c.findViewById(R.id.e2);
        final Button bn = c.findViewById(R.id.k20);
        int e = Resources.getColor(this, R.color.c);
        int f = Resources.getColor(this, R.color.b);
        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(e);
            ti.setTextColor(e);
        } else {
            ed.setTextColor(f);
            ti.setTextColor(f);
        }
        ti.setText(getString(R.string.v13));
        ed.setText(url);
        Runnable p15 = () -> {
            final String sg = Stream.a(url, getString(R.string.c33), getString(R.string.g25));
            runOnUiThread(() -> ti.setText(Html.b(sg)));
        };
        new Thread(p15).start();
        bn.setText(getString(R.string.i6));
        if ((url.startsWith("https://") || url.startsWith("http://")) && (!url.startsWith("file://") || !url.startsWith("content://")) && Domain.isValidDomain(url)){
            bn.setBackgroundResource(R.drawable.c10);
        } else {
            bn.setBackgroundResource(R.drawable.c11);
        }
        bn.setOnClickListener(view -> {
            String ab = ed.getText().toString();
            ti.setText(getString(R.string.v13));

            if ((url.startsWith("https://") || url.startsWith("http://")) && (!url.startsWith("file://") || !url.startsWith("content://")) && Domain.isValidDomain(url)){
                Runnable p151 = () -> {
                    final String sg = Stream.a(ab, getString(R.string.c33), getString(R.string.g25));
                    runOnUiThread(() -> ti.setText(Html.b(sg)));
                };
                new Thread(p151).start();
            }
        });
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String url = ed.getText().toString().trim();
                if (url.startsWith("https://") || url.startsWith("http://")) {
                    if (!Domain.isValidDomain(url)){
                        ed.setError(getString(R.string.y84));
                        bn.setBackgroundResource(R.drawable.c11);
                    } else {
                        bn.setBackgroundResource(R.drawable.c10);
                    }
                }  else if (url.startsWith("file://") || url.startsWith("content://")){
                    ed.setError(getString(R.string.y83));
                    bn.setBackgroundResource(R.drawable.c11);
                } else {
                    ed.setError(getString(R.string.y82));
                    bn.setBackgroundResource(R.drawable.c11);
                }
            }


        });
        final AlertDialog g = a.create();
        g.show();
    }
}