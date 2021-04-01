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
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.app.base.BaseActivity;
import com.droidmj.webvium.bookmark.BookmarkAdapter;
import com.droidmj.webvium.bookmark.BookmarkDatabase;
import com.droidmj.webvium.bookmark.BookmarkHelper;
import com.droidmj.webvium.content.Clipboard;
import com.droidmj.webvium.content.Intents;
import com.droidmj.webvium.content.Resources;
import com.droidmj.webvium.text.TextWatcher;
import com.droidmj.webvium.util.Domain;
import com.droidmj.webvium.util.Hardware;
import com.droidmj.webvium.util.Stream;
import com.droidmj.webvium.util.U3;
import com.droidmj.webvium.view.Animation;
import com.droidmj.webvium.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

// @Class Bookmarks
public class BOOK extends BaseActivity {
    private BookmarkHelper d3;
    private GridView e;
    private RelativeLayout f6;
    private BookmarkAdapter w15;
    private ArrayList<String> ls;
    private ArrayList<String> ls0;

    private ImageView iv;
    private TextView f8;
    private ImageView iv1;
    private PopupMenu pm;
    private int it;
    final MenuItem.OnMenuItemClickListener d = a1 -> {
        String a5 = ls.get(it);
        String a2 = ls0.get(it);
        switch (a1.getItemId()) {
            case 1:
                b(a2);
                return true;
            case 2:
                Clipboard.a(BOOK.this, a2);
                k5(getString(R.string.k9));
                return true;
            case 3:
                k2(a2, a5);
                return true;
            case 9:

                Clipboard.a(BOOK.this, a5);
                k5(getString(R.string.k9));
                return true;
            case 6:
                a(a2, a5);
                return true;

            case 5:
                l(a2, 7);
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
                o(a5, a2);
                return true;
        }
        return false;
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
        iv.setOnClickListener(view -> d());
        setActionBar(c);
        ActionBar ab = getActionBar();
        if (ab != null) {
            // ab.setDisplayHomeAsUpEnabled(true);
            // ab.setDisplayShowHomeEnabled(false);
            ab.setDisplayShowTitleEnabled(false);
        }
        int f = Resources.b(this, R.color.c);
        int g = Resources.b(this, R.color.b);
        if (a221().getBoolean("blockSV", true)) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
        c.setElevation(5);
        f8.setCompoundDrawablesWithIntrinsicBounds(null, Resources.a(this, R.drawable.a30), null, null);
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
        c.setNavigationOnClickListener(view -> finish());
        d3 = BookmarkHelper.getInstance(getApplicationContext());
        Cursor res = d3.getReadableDatabase().rawQuery("SELECT * FROM " +
                BookmarkDatabase.TABLE_BOOKMARK +
                " ORDER BY " +
                BuildConfiguration.DB_ID +
                " DESC", null);
        if (res.getCount() == 0) {
            f8.setVisibility(View.VISIBLE);
            e.setVisibility(View.GONE);
            f6.setClickable(true);
            iv.setVisibility(View.GONE);
        } else {
            ls = new ArrayList<>();
            ls0 = new ArrayList<>();
            while (res.moveToNext()) {
                ls.add(res.getString(1));
                ls0.add(res.getString(2));
            }
            f8.setVisibility(View.GONE);
            Animation.animate(BOOK.this, R.anim.i, iv);
        }
        res.close();
        if (ls != null) {
            w15 = new BookmarkAdapter(this, ls);
            e.setAdapter(w15);
            e.setOnItemClickListener((a1, b1, c1, d1) -> {
                Intents.d("value", ls0.get(c1), BOOK.this);
                finish();
            });
            e.setOnItemLongClickListener((adapterView, view, i, l) -> {
                n(view, i);
                return true;
            });
        }
        iv1 = findViewById(R.id.j3);
        iv1.setBackgroundResource(R.drawable.c6);
        iv1.setImageResource(R.drawable.c16);
        iv1.setOnClickListener(view -> c("", ""));
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
        final EDIT ed = c.findViewById(R.id.f10);
        final TextView ti1 = c.findViewById(R.id.f11);
        final EDIT ed1 = c.findViewById(R.id.f12);
        int e = Resources.b(this, R.color.c);
        int f = Resources.b(this, R.color.b);

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
        a.setPositiveButton(getString(R.string.i6), (a2, it) -> {
            //  m(ed.getText().toString() ,ed1.getText().toString());
            shrt(ed.getText().toString(), ed1.getText().toString(), R.mipmap.w);
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
        String c = getString(R.string.l8);
        String d = c.replaceAll("%a", "\"" + a + "\"");
        startActivity(Intent.createChooser(b, d));
    }

    public void c(String a23, String asd) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);


        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.z, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.u2));
        a.setView(c);
        final TextView ti = c.findViewById(R.id.f9);
        final EDIT ed = c.findViewById(R.id.f10);
        final TextView ti1 = c.findViewById(R.id.f11);
        final EDIT ed1 = c.findViewById(R.id.f12);
        int e = Resources.b(this, R.color.c);
        int f = Resources.b(this, R.color.b);

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
            d3.c(ed.getText().toString(), ed1.getText().toString());
            k5(getString(R.string.u1));
            f6.setClickable(false);
            this.e.setVisibility(View.VISIBLE);
            iv.setVisibility(View.VISIBLE);
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

    private void d() {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);


        a.setCancelable(true);
        a.setTitle(getString(R.string.h11));
        a.setMessage(getString(R.string.u4));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            d3.delete();
            k5(getString(R.string.u3));
            f6.setClickable(true);
            e.setVisibility(View.GONE);
            iv.setVisibility(View.GONE);
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    public void k2(final String b, final String c5) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);


        a.setCancelable(true);
        a.setTitle(getString(R.string.h11));
        String c = getString(R.string.l7);
        String dy = c.replaceAll("%a", "\"" + b + "\"");
        a.setMessage(dy);
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {

            d3.b(b, c5);


            String a56 = getString(R.string.h5).replaceAll("%a", b);

            k5(a56);
            k();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void k5(String a) {
        Toast.b(this, a);
    }

    private void k() {
        Runnable p15 = () -> {
            ArrayList<String> itemIdsh = new ArrayList<>();
            Cursor res = d3.getReadableDatabase().rawQuery("SELECT * FROM " +
                    BookmarkDatabase.TABLE_BOOKMARK +
                    " ORDER BY " +
                    BuildConfiguration.DB_ID +
                    " DESC", null);
            if (res.getCount() == 0) {
                runOnUiThread(() -> {
                    f8.setVisibility(View.VISIBLE);
                    f6.setClickable(true);
                    e.setVisibility(View.GONE);
                    iv.setVisibility(View.GONE);
                });
            } else {
                while (res.moveToNext()) {
                    itemIdsh.add(res.getString(1));
                }
                if (itemIdsh.size() == 0) {
                    runOnUiThread(() -> {
                        f8.setVisibility(View.VISIBLE);
                        f6.setClickable(true);
                        e.setVisibility(View.GONE);
                        iv.setVisibility(View.GONE);
                    });
                } else {
                    runOnUiThread(() -> {
                        w15.a(itemIdsh);
                        w15.notifyDataSetChanged();
                        f8.setVisibility(View.GONE);
                        e.setVisibility(View.VISIBLE);
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
        switch (type) {
            case 0:
                a.setTitle(getString(R.string.x9)); // LINKS
                break;
            case 1:
                a.setTitle(getString(R.string.x16)); // TRANCEROUT
                break;
            case 2:
                a.setTitle(getString(R.string.y11)); //NPing
                break;
            case 3:
                a.setTitle(getString(R.string.z4)); //Whois
                break;
            case 4:
                a.setTitle(getString(R.string.z15)); //Meta Tags
                break;
            case 5:
                a.setTitle(getString(R.string.y15)); // Headers
                break;
            case 6:
                a.setTitle(getString(R.string.f32)); // Robots
                break;
            case 7:
                a.setTitle(getString(R.string.j)); // Source Code
                break;
            case 8:
                a.setTitle(getString(R.string.z12)); // IP GeolocationDataModel
                break;
        }
        a.setView(c);
        final EDIT ed = c.findViewById(R.id.g8);
        final TextView ti = c.findViewById(R.id.e2);
        final Button bn = c.findViewById(R.id.k20);
        int e = Resources.b(this, R.color.c);
        int f = Resources.b(this, R.color.b);
        int e3 = Resources.b(this, R.color.j);
        int f3 = Resources.b(this, R.color.k);
        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(e);
            bn.setTextColor(e);
            ti.setTextColor(e3);
        } else {
            ed.setTextColor(f);
            ti.setTextColor(f3);
            bn.setTextColor(f);
        }
        if (type == 0 || type == 4 || type == 5 || type == 7 || type == 6) {
            ed.setText(url);
        } else if (type == 8) {
            ed.setText(Stream.c(url, getString(R.string.c33)));
        } else {
            ed.setText(Objects.requireNonNull(Uri.parse(url).getHost()).replaceAll("www.", ""));
        }
        bn.setText(getString(R.string.i6));

        ti.setText(getString(R.string.f31).replaceAll("%a", "\nhttps://example.com").replaceAll("%b", "http://example.com").replaceAll("%c", "example.com"));
        final AlertDialog g = a.create();
        bn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String a = ed.getText().toString();
                switch (type) {
                    case 0:
                        if (Domain.isValidDomain(a)) {
                            b(getString(R.string.x9) + " | " + a);
                        }
                        break;
                    case 1:
                        if (Domain.isValidDomain(a)) {
                            b(getString(R.string.x16) + " | " + a);
                        }
                        break;
                    case 2:
                        if (Domain.isValidDomain(a)) {
                            b(getString(R.string.y11) + " | " + a);
                        }
                        break;
                    case 3:
                        if (Domain.isValidDomain(a)) {
                            b(getString(R.string.z4) + " | " + a);
                        }
                        break;
                    case 4:
                        if (Domain.isValidDomain(a)) {
                            b(getString(R.string.z15) + " | " + a);
                        }
                        break;
                    case 5:
                        if (Domain.isValidDomain(a)) {
                            c(getString(R.string.y15) + " | " + a);
                        }
                        break;
                    case 6:
                        if (Domain.isValidDomain(a)) {
                            b(getString(R.string.f32) + " | " + a);
                        }
                        break;
                    case 7:
                        if (Domain.isValidDomain(a)) {
                            b(getString(R.string.j) + " | " + a);
                        }
                        break;
                    case 8:
                        b(getString(R.string.z12) + " | " + a);
                        break;
                }
            }

            public void b(String qr) {
                g.dismiss();
                Intent it = new Intent(BOOK.this, TOOL.class);
                it.putExtra("value", qr);
                startActivity(it);
            }

            public void c(String qr) {
                g.dismiss();
                Intent it = new Intent(BOOK.this, TOOL.class);
                it.putExtra("value0", qr);
                startActivity(it);
            }
        });
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String url = ed.getText().toString().trim();
                if (type == 6 || type == 7) {
                    if (!Domain.isValidDomain(url)) {
                        ed.setError(getString(R.string.c32));
                    }
                }
            }
        });
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
            sb1.add(0, 15, 0, getString(R.string.x9)).setOnMenuItemClickListener(d);
            sb1.add(0, 20, 0, getString(R.string.z15)).setOnMenuItemClickListener(d);
            sb1.add(0, 21, 0, getString(R.string.f32)).setOnMenuItemClickListener(d);
            sb1.add(0, 16, 0, getString(R.string.x16)).setOnMenuItemClickListener(d);
            sb1.add(0, 17, 0, getString(R.string.y11)).setOnMenuItemClickListener(d);
            sb1.add(0, 18, 0, getString(R.string.z4)).setOnMenuItemClickListener(d);
            sb1.add(0, 13, 0, getString(R.string.h6)).setOnMenuItemClickListener(d);
            sb1.add(0, 5, 0, getString(R.string.j)).setOnMenuItemClickListener(d);
            sb1.add(0, 22, 0, getString(R.string.z12)).setOnMenuItemClickListener(d);
            sb1.add(0, 12, 0, getString(R.string.i4)).setOnMenuItemClickListener(d);
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
        final EDIT ed = c.findViewById(R.id.f10);
        final TextView ti1 = c.findViewById(R.id.f11);
        final EDIT ed1 = c.findViewById(R.id.f12);
        int e = Resources.b(this, R.color.c);
        int f = Resources.b(this, R.color.b);

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
            d3.f(oldTitle, oldURl, ed.getText().toString(), ed1.getText().toString());
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

}
