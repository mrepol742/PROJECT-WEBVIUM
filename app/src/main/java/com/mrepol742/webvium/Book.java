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
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mrepol742.webvium.annotation.Keep;
import com.mrepol742.webvium.app.Sqlite;
import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.app.main.MainBaseAdapter;
import com.mrepol742.webvium.app.Clipboard;
import com.mrepol742.webvium.app.Intents;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.util.Html;
import com.mrepol742.webvium.util.Domain;
import com.mrepol742.webvium.util.Hardware;
import com.mrepol742.webvium.net.Stream;
import com.mrepol742.webvium.util.Animation;
import com.mrepol742.webvium.util.AwesomeToast;
import com.mrepol742.webvium.util.TextWatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * @BookmarkActivity
 */
public class Book extends BaseActivity {
    public static final int LINKS = 0;
    public static final int TRANCEROUTE = 1;
    public static final int NPING = 2;
    public static final int WHOIS = 3;
    public static final int META_TAGS = 4;
    public static final int HEADERS = 5;
    public static final int ROBOTS = 6;
    public static final int SOURCE_CODE = 7;
    public static final int IP_GEO = 8;
    public static final int ASSETLINKS = 9;
    public static final int SITEMAPS = 10;
    private GridView e;
    private RelativeLayout f6;
    private Adapter w15;
    private final List<String> ls = new ArrayList<>();
    private final List<String> ls0 = new ArrayList<>();
    private ImageView iv;
    private TextView f8;
    private ImageView iv1;
    private PopupMenu pm;
    private int it;
    private Sqlite sql;

    final MenuItem.OnMenuItemClickListener d = new MenuItem.OnMenuItemClickListener() {

        @Override
        public boolean onMenuItemClick(MenuItem a1) {
            String a5 = ls.get(it);
            String a2 = ls0.get(it);
            switch (a1.getItemId()) {
                case 1:
                    Book.this.b(a2);
                    return true;
                case 2:
                    Clipboard.a(Book.this, a2);
                    Book.this.k5(Book.this.getString(R.string.k9));
                    return true;
                case 3:
                    Book.this.k2(a2, a5);
                    return true;
                case 9:
                    Clipboard.a(Book.this, a5);
                    Book.this.k5(Book.this.getString(R.string.k9));
                    return true;
                case 6:
                    Book.this.a(a2, a5);
                    return true;
                case 5:
                    Book.this.l(a2, 7);
                    return true;
                case 13:
                    Book.this.c43(a2);
                    return true;
                case 15:
                    Book.this.l(a2, 0);
                    return true;
                case 16:
                    Book.this.l(a2, 1);
                    return true;
                case 17:
                    Book.this.l(a2, 2);
                    return true;
                case 18:
                    Book.this.l(a2, 3);
                    return true;
                case 19:
                    Book.this.l(a2, 5);
                    return true;
                case 20:
                    Book.this.l(a2, 4);
                    return true;
                case 21:
                    Book.this.l(a2, 6);
                    return true;
                case 22:
                    Book.this.l(a2, 8);
                    return true;
                case 23:
                    Book.this.b(a5);
                    return true;
                case 24:
                    Book.this.o(a5, a2);
                    return true;
                case 25:
                    Book.this.l(a2, ASSETLINKS);
                    return true;
                case 26:
                    Book.this.l(a2, SITEMAPS);
                    return true;
                case 27:
                    Book.this.c33(a2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle a) {
        theme(T_DEFAULT);
        super.onCreate(a);
        a225(R.layout.y);
        Toolbar c = findViewById(R.id.b7);
        TextView d = findViewById(R.id.b8);
        e = findViewById(R.id.c17);
        f6 = findViewById(R.id.f6);
        f6.setBackgroundResource(R.drawable.b17);
        f8 = findViewById(R.id.f8);
        iv = findViewById(R.id.j2);
        iv.setBackgroundResource(R.drawable.c6);
        iv.setImageResource(R.drawable.a23);
        iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Book.this.d();
            }
        });
        setActionBar(c);
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
        c.setElevation(5);
        f8.setCompoundDrawablesWithIntrinsicBounds(null, Resources.getDrawable(this, R.drawable.a30), null, null);
        f8.setCompoundDrawablePadding(40);
        d.setTypeface(type(Typeface.BOLD));
        f8.setTypeface(type(Typeface.BOLD));
        d.setText(getString(R.string.h11));
        f8.setText(getString(R.string.o6));
        if (!a221().getBoolean("autoUpdate", false)) {
            d.setTextColor(f);
            f8.setTextColor(f);
        } else {
            d.setTextColor(g);
            f8.setTextColor(g);
        }
        c.setBackgroundResource(R.drawable.p);
        c.setNavigationIcon(R.drawable.a2);
        c.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Book.this.finish();
            }
        });
        sql = Sqlite.getInstance(getApplicationContext());
        Cursor res = sql.getReadableDatabase().rawQuery("SELECT * FROM " +
                Sqlite.TABLE_BOOKMARK +
                " ORDER BY " +
                "_id" +
                " DESC", null);
        if (res.getCount() == 0) {
            f8.setVisibility(View.VISIBLE);
            e.setVisibility(View.GONE);
            f6.setClickable(true);
            iv.setVisibility(View.GONE);
        } else {
            while (res.moveToNext()) {
                ls.add(res.getString(1));
                ls0.add(res.getString(2));
            }
            f8.setVisibility(View.GONE);
            Animation.animate(Book.this, R.anim.i, iv);
        }
        res.close();
        w15 = new Adapter(this, ls);
        e.setAdapter(w15);
        e.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a1, View b1, int c1, long d1) {
                Intents.d("value", ls0.get(c1), Book.this);
                Book.this.finish();
            }
        });
        e.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book.this.n(view, i);
                return true;
            }
        });
        iv1 = findViewById(R.id.j3);
        iv1.setBackgroundResource(R.drawable.c6);
        iv1.setImageResource(R.drawable.c16);
        iv1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Book.this.c("", "");
            }
        });
        e.setNumColumns(p(getResources().getConfiguration().orientation));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Animation.animate(this, R.anim.i, iv1);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        e.setNumColumns(p(newConfig.orientation));
    }

    public void a(String a23, String asd) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.z, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.h12));
        a.setView(c);
        final TextView ti = c.findViewById(R.id.f9);
        final Edit ed = c.findViewById(R.id.f10);
        final TextView ti1 = c.findViewById(R.id.f11);
        final Edit ed1 = c.findViewById(R.id.f12);
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
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a2, int it) {
                Book.this.shrt(ed.getText().toString(), ed1.getText().toString(), R.mipmap.w);
                a2.dismiss();
            }
        });
        a.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a2, int i) {
                a2.dismiss();
            }
        });
        final AlertDialog g = a.create();
        g.show();
        final Button okButton = g.getButton(AlertDialog.BUTTON_POSITIVE);
        TextWatcher tw = new TextWatcher(ed, ed1, okButton);
        ed.addTextChangedListener(tw);
        ed1.addTextChangedListener(tw);
        okButton.setEnabled(!TextUtils.isEmpty(ed.getText().toString()) && !TextUtils.isEmpty(ed1.getText().toString()));
    }

    private void b(String a) {
        Intent b = new Intent("android.intent.action.SEND");
        b.setType("text/plain");
        b.putExtra("android.intent.extra.TEXT", a);
        startActivity(Intent.createChooser(b, String.format(getString(R.string.l8), "\"" + a + "\"")));
    }

    public void c(String a23, String asd) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.z, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.u2));
        a.setView(c);
        final TextView ti = c.findViewById(R.id.f9);
        final Edit ed = c.findViewById(R.id.f10);
        final TextView ti1 = c.findViewById(R.id.f11);
        final Edit ed1 = c.findViewById(R.id.f12);
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
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a2, int i) {
                ContentValues values = new ContentValues();
                values.put(Sqlite.COL1_BOOKMARK, ed.getText().toString());
                values.put(Sqlite.COL2_BOOKMARK, ed1.getText().toString());
                sql.getWritableDatabase().insert(Sqlite.TABLE_BOOKMARK, null, values);
                Book.this.k5(Book.this.getString(R.string.u1));
                Book.this.k();
                a2.dismiss();
            }
        });
        a.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a2, int i) {
                a2.dismiss();
            }
        });
        final AlertDialog g = a.create();
        g.show();
        final Button okButton = g.getButton(AlertDialog.BUTTON_POSITIVE);
        TextWatcher tw = new TextWatcher(ed, ed1, okButton);
        ed.addTextChangedListener(tw);
        ed1.addTextChangedListener(tw);
        okButton.setEnabled(!TextUtils.isEmpty(ed.getText().toString()) && !TextUtils.isEmpty(ed1.getText().toString()));
    }

    private void d() {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(getString(R.string.h11));
        a.setMessage(getString(R.string.u4));
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                sql.getWritableDatabase().delete(Sqlite.TABLE_BOOKMARK, null, null);
                Book.this.k5(Book.this.getString(R.string.u3));
                f6.setClickable(true);
                e.setVisibility(View.GONE);
                iv.setVisibility(View.GONE);
            }
        });
        a.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a1, int intetg) {
                a1.dismiss();
            }
        });
        a.create().show();
    }

    public void k2(final String b, final String c5) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(getString(R.string.h11));
        a.setMessage(String.format(getString(R.string.l7), "\"" + b + "\""));
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                sql.getWritableDatabase().delete(Sqlite.TABLE_BOOKMARK,
                        Sqlite.COL1_BOOKMARK +
                                "=? AND " +
                                Sqlite.COL2_BOOKMARK +
                                "=? ", new String[]{b, c5});
                Book.this.k5(String.format(Book.this.getString(R.string.h5), b));
                Book.this.k();
            }
        });
        a.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a1, int intetg) {
                a1.dismiss();
            }
        });
        a.create().show();
    }

    private void k5(String a) {
        AwesomeToast.b(this, a);
    }

    private void k() {
        final List<String> itemIdsh = new ArrayList<>();
        Cursor res = sql.getReadableDatabase().rawQuery("SELECT * FROM " +
                Sqlite.TABLE_BOOKMARK +
                " ORDER BY " +
                "_id" +
                " DESC", null);
        if (res.getCount() == 0) {
            f8.setVisibility(View.VISIBLE);
            f6.setClickable(true);
            e.setVisibility(View.GONE);
            iv.setVisibility(View.GONE);
        } else {
            while (res.moveToNext()) {
                itemIdsh.add(res.getString(1));
            }
            if (itemIdsh.size() == 0) {
                f8.setVisibility(View.VISIBLE);
                f6.setClickable(true);
                e.setVisibility(View.GONE);
                iv.setVisibility(View.GONE);
            } else {
                w15.a(itemIdsh);
                w15.notifyDataSetChanged();
                f8.setVisibility(View.GONE);
                e.setVisibility(View.VISIBLE);
            }
        }
        res.close();
    }

    public void l(String url, final int type) {
        if (!URLUtil.isValidUrl(url)) {
            AwesomeToast.c(this, getString(R.string.c32));
            return;
        } else if (!Domain.isValidDomain(url)) {
            AwesomeToast.c(this, getString(R.string.c32));
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
        final Edit ed = c.findViewById(R.id.g8);
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
        if ((url.startsWith("https://") || url.startsWith("http://")) && (!url.startsWith("file://") || !url.startsWith("content://")) && Domain.isValidDomain(url)) {
            bn.setBackgroundResource(R.drawable.c10);
        } else {
            bn.setBackgroundResource(R.drawable.c11);
        }
        bn.setText(getString(R.string.i6));
        ti.setText(String.format(getString(R.string.f31), "https://mrepol742.github.io", "http://mrepol742.github.io"));
        final AlertDialog g = a.create();
        bn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String a1 = ed.getText().toString();
                if (type == SOURCE_CODE) {
                    Intent it = new Intent(Book.this, Tool.class);
                    it.putExtra("dat", a1);
                    it.putExtra("id", Tool.TOOL_SOURCE_CODE);
                    Book.this.startActivity(it);
                } else if (type == HEADERS) {
                    Book.this.c126(a1);
                } else if (type == ROBOTS) {
                    Intent it = new Intent(Book.this, Tool.class);
                    it.putExtra("dat", a1);
                    it.putExtra("id", Tool.TOOL_ROBOTS);
                    Book.this.startActivity(it);
                } else if (type == ASSETLINKS) {
                    Intent it = new Intent(Book.this, Tool.class);
                    it.putExtra("dat", a1);
                    it.putExtra("id", Tool.TOOL_ASSET_LINKS);
                    Book.this.startActivity(it);
                } else if (type == SITEMAPS) {
                    Intent it = new Intent(Book.this, Tool.class);
                    it.putExtra("dat", a1);
                    it.putExtra("id", Tool.TOOL_SITEMAPS);
                    Book.this.startActivity(it);
                }
                g.dismiss();
            }
        });
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String url = ed.getText().toString().trim();
                if (url.startsWith("https://") || url.startsWith("http://")) {
                    if (!Domain.isValidDomain(url)) {
                        ed.setError(getString(R.string.y84));
                        bn.setBackgroundResource(R.drawable.c11);
                    } else {
                        bn.setBackgroundResource(R.drawable.c10);
                    }
                } else if (type != SOURCE_CODE && (url.startsWith("file://") || url.startsWith("content://"))) {
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

    public void c126(final String url) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.b8, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.y15));
        a.setView(c);
        final Edit ed = c.findViewById(R.id.g8);
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
        Runnable p15 = new Runnable() {

            @Override
            public void run() {
                final String sg = Stream.d(url, Book.this.getString(R.string.c33));
                Book.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        ti.setText(Html.b(sg));
                    }
                });
            }
        };
        new Thread(p15).start();
        if ((url.startsWith("https://") || url.startsWith("http://")) && (!url.startsWith("file://") || !url.startsWith("content://")) && Domain.isValidDomain(url)) {
            bn.setBackgroundResource(R.drawable.c10);
        } else {
            bn.setBackgroundResource(R.drawable.c11);
        }
        bn.setText(getString(R.string.i6));
        bn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String ab = ed.getText().toString();
                ti.setText(Book.this.getString(R.string.v13));
                if ((url.startsWith("https://") || url.startsWith("http://")) && (!url.startsWith("file://") || !url.startsWith("content://")) && Domain.isValidDomain(url)) {
                    Runnable p151 = new Runnable() {

                        @Override
                        public void run() {
                            final String sg = Stream.d(ab, Book.this.getString(R.string.c33));
                            Book.this.runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    ti.setText(Html.b(sg));
                                }
                            });
                        }
                    };
                    new Thread(p151).start();
                }
            }
        });
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String url = ed.getText().toString().trim();
                if (url.startsWith("https://") || url.startsWith("http://")) {
                    if (!Domain.isValidDomain(url)) {
                        ed.setError(getString(R.string.y84));
                        bn.setBackgroundResource(R.drawable.c11);
                    } else {
                        bn.setBackgroundResource(R.drawable.c10);
                    }
                } else if (url.startsWith("file://") || url.startsWith("content://")) {
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

    private void n(View w, int it) {
        if (pm == null) {
            pm = new PopupMenu(this, w);
            this.it = it;
            Menu a = pm.getMenu();
            SubMenu sb2 = a.addSubMenu(getString(R.string.a8));
            sb2.add(0, 1, 0, getString(R.string.t4)).setOnMenuItemClickListener(d);
            sb2.add(0, 23, 0, getString(R.string.s23)).setOnMenuItemClickListener(d);
            SubMenu sb = a.addSubMenu(getString(R.string.u));
            sb.add(0, 2, 0, getString(R.string.t4)).setOnMenuItemClickListener(d);
            sb.add(0, 9, 0, getString(R.string.s23)).setOnMenuItemClickListener(d);
            a.add(0, 6, 0, getString(R.string.s24)).setOnMenuItemClickListener(d);
            a.add(0, 3, 0, getString(R.string.k8)).setOnMenuItemClickListener(d);
            a.add(0, 24, 0, getString(R.string.u23)).setOnMenuItemClickListener(d);
            SubMenu sb1 = a.addSubMenu(getString(R.string.j36));
            sb1.add(0, 19, 0, getString(R.string.y15)).setOnMenuItemClickListener(d);
            sb1.add(0, 21, 0, getString(R.string.f32)).setOnMenuItemClickListener(d);
           sb1.add(0, 13, 0, getString(R.string.h6)).setOnMenuItemClickListener(d);
            sb1.add(0, 5, 0, getString(R.string.j)).setOnMenuItemClickListener(d);
            sb1.add(0, 25, 0, getString(R.string.y76)).setOnMenuItemClickListener(d);
            sb1.add(0, 26, 0, getString(R.string.y77)).setOnMenuItemClickListener(d);
            sb1.add(0, 27, 0, getString(R.string.y78)).setOnMenuItemClickListener(d);
            a.add(0, 12, 0, getString(R.string.i4)).setOnMenuItemClickListener(d);
        }
        pm.show();
    }

    private void o(final String oldTitle, final String oldURl) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.z, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.u23));
        a.setView(c);
        final TextView ti = c.findViewById(R.id.f9);
        final Edit ed = c.findViewById(R.id.f10);
        final TextView ti1 = c.findViewById(R.id.f11);
        final Edit ed1 = c.findViewById(R.id.f12);
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
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a2, int it) {
                ContentValues values = new ContentValues();
                values.put(Sqlite.COL1_BOOKMARK, ed.getText().toString());
                values.put(Sqlite.COL2_BOOKMARK, ed1.getText().toString());
                sql.getWritableDatabase().update(Sqlite.TABLE_BOOKMARK, values,
                        Sqlite.COL1_BOOKMARK +
                                " LIKE ? AND " +
                                Sqlite.COL2_BOOKMARK +
                                " LIKE ?", new String[]{oldTitle, oldURl});
                Book.this.k();
                a2.dismiss();
            }
        });
        a.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a2, int i) {
                a2.dismiss();
            }
        });
        final AlertDialog g = a.create();
        g.show();
        final Button okButton = g.getButton(AlertDialog.BUTTON_POSITIVE);
        TextWatcher tw = new TextWatcher(ed, ed1, okButton);
        ed.addTextChangedListener(tw);
        ed1.addTextChangedListener(tw);
        okButton.setEnabled(!TextUtils.isEmpty(ed.getText().toString()) && !TextUtils.isEmpty(ed1.getText().toString()));
    }

    private int p(int i) {
        if (Hardware.isTablet(this)) {
            if (i == Configuration.ORIENTATION_LANDSCAPE) {
                return 10;
            }
            return 5;
        } else {
            if (i == Configuration.ORIENTATION_LANDSCAPE) {
                return 5;
            }
            return 3;
        }
    }

    public void c43(final String url) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.b8, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.h6));
        a.setView(c);
        final Edit ed = c.findViewById(R.id.g8);
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
        Runnable p15 = new Runnable() {

            @Override
            public void run() {
                final String sg = Stream.a(url, Book.this.getString(R.string.c33), Book.this.getString(R.string.g25));
                Book.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        ti.setText(Html.b(sg));
                    }
                });
            }
        };
        new Thread(p15).start();
        bn.setText(getString(R.string.i6));
        if ((url.startsWith("https://") || url.startsWith("http://")) && (!url.startsWith("file://") || !url.startsWith("content://")) && Domain.isValidDomain(url)) {
            bn.setBackgroundResource(R.drawable.c11);
        } else {
            bn.setBackgroundResource(R.drawable.c10);
        }
        bn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String ab = ed.getText().toString();
                ti.setText(Book.this.getString(R.string.v13));

                if ((url.startsWith("https://") || url.startsWith("http://")) && (!url.startsWith("file://") || !url.startsWith("content://")) && Domain.isValidDomain(url)) {
                    Runnable p151 = new Runnable() {

                        @Override
                        public void run() {
                            final String sg = Stream.a(ab, Book.this.getString(R.string.c33), Book.this.getString(R.string.g25));
                            Book.this.runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    ti.setText(Html.b(sg));
                                }
                            });
                        }
                    };
                    new Thread(p151).start();
                }
            }
        });
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String url = ed.getText().toString().trim();
                if (url.startsWith("https://") || url.startsWith("http://")) {
                    if (!Domain.isValidDomain(url)) {
                        ed.setError(getString(R.string.y84));
                        bn.setBackgroundResource(R.drawable.c11);
                    } else {
                        bn.setBackgroundResource(R.drawable.c10);
                    }
                } else if (url.startsWith("file://") || url.startsWith("content://")) {
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

    private void c33(String url) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.b8, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.y78));
        a.setView(c);
        final Edit ed = c.findViewById(R.id.g8);
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
        pro(ti, url);
        ed.setText(url);
        bn.setText(getString(R.string.i6));
        bn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ti.setText(Book.this.getString(R.string.v13));
                pro(ti, ed.getText().toString());
            }
        });
        final AlertDialog g = a.create();
        g.show();
    }

    public static class Adapter extends MainBaseAdapter {
        private static final int[] background = {
                R.drawable.g3,
                R.drawable.g4,
                R.drawable.g5,
                R.drawable.g6
        };
        private final Context a;
        private final List<String> b;
        private final Random rm;


        public Adapter(Context ct, List<String> al) {
            super(ct);
            a = ct;
            b = al;
            rm = new Random();
        }

        public void a(List<String> a1) {
            synchronized (b) {
                b.clear();
                b.addAll(a1);
            }
        }

        @Override
        public int getCount() {
            return b.size();
        }

        @Override
        public Object getItem(int it) {
            return it;
        }

        @Override
        public long getItemId(int it) {
            return it;
        }

        @Override
        public View getView(int it, View e, ViewGroup vg) {
            try {
                Layout w20;
                if (e == null) {
                    LayoutInflater ll = (LayoutInflater) a.getSystemService(LAYOUT_INFLATER_SERVICE);
                    e = ll.inflate(R.layout.w, vg, false);
                    w20 = new Layout();
                    w20.tv = e.findViewById(R.id.f1);
                    w20.ll = e.findViewById(R.id.o23);
                    w20.tv.setCompoundDrawablePadding(20);
                    w20.tv.setTextColor(Resources.getColor(a, R.color.b));
                    w20.tv.setCompoundDrawablesWithIntrinsicBounds(null, Resources.getDrawable(a, R.drawable.a9), null, null);
                    w20.tv.setTypeface(type(Typeface.BOLD));
                    e.setTag(w20);
                } else {
                    w20 = (Layout) e.getTag();
                }
                w20.tv.setText(b.get(it));
                w20.ll.setBackgroundResource(background[rm.nextInt(background.length)]);
                w20.ll.setElevation(5);

            } catch (IndexOutOfBoundsException ignored) {

            }
            return e;
        }

        private static class Layout {
            TextView tv;
            LinearLayout ll;

            @Keep
            private Layout() {
            }
        }
    }
}
