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

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mrepol742.webvium.app.Sqlite;
import com.mrepol742.webvium.app.main.MainBaseActivity;
import com.mrepol742.webvium.bookmark.BookmarkHelper;
import com.mrepol742.webvium.content.Clipboard;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.history.HistoryHelper;
import com.mrepol742.webvium.io.StorageDirectory;
import com.mrepol742.webvium.search.SearchAdapter;
import com.mrepol742.webvium.search.SearchHelper;
import com.mrepol742.webvium.text.TextWatcher;
import com.mrepol742.webvium.util.Base64;
import com.mrepol742.webvium.util.U3;
import com.mrepol742.webvium.util.cache.BitmapCache;
import com.mrepol742.webvium.view.Animation;
import com.mrepol742.webvium.view.SoftKeyboard;
import com.mrepol742.webvium.widget.AwesomeToast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

// @Class SearchHistory
public class SEAR extends MainBaseActivity {
    private final String[] webvium = {
            "webvium-source:",
            "webvium://logcat",
            "webvium://calculator",
            "webvium://credits",
            "webvium://termsandcondition",
            "webvium://privacypolicy",
            "webvium://blank",
            "webvium://search",
            "webvium://history",
            "webvium://bookmarks",
            "webvium://rickroll"
    };
    private AutoCompleteTextView p;
    private ListView d;
    private RelativeLayout b19;
    private SearchAdapter aa;
    private SearchHelper d2;
    private ArrayList<String> ls;
    private ImageView iv1;
    private PopupMenu pm;
    private String query23;

    final MenuItem.OnMenuItemClickListener e = a1 -> {
        switch (a1.getItemId()) {
            case 0:
                if (SEAR.this.getCallingActivity() != null) {
                    Intents.d("result", query23, SEAR.this);
                } else {
                    Intents.e(SEAR.this, "value", query23, MAIN.class);
                }
                SEAR.this.finish();
                return true;
            case 1:
                SEAR.this.f4(query23);
                return true;
            case 2:
                Clipboard.a(SEAR.this, query23);
                SEAR.this.f6(SEAR.this.getString(R.string.k9));
                return true;
            case 3:
                SEAR.this.f5(query23);
                return true;
            case 4:

                q(query23);
                return true;

            case 5:
                s(query23);
                return true;
            case 6:
                w(query23);
                return true;

        }
        return false;
    };

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        a225(R.layout.e);

        Toolbar o = findViewById(R.id.o);
        p = findViewById(R.id.p);
        ImageView iv = findViewById(R.id.k19);
        iv1 = findViewById(R.id.m13);
        LinearLayout cd = findViewById(R.id.y);
        d = findViewById(R.id.a3);
        b19 = findViewById(R.id.b19);
        b19.setBackgroundResource(R.drawable.b17);
        b19.setClickable(true);
        TextView hau = findViewById(R.id.e7);
        TextView m11 = findViewById(R.id.m11);
        m11.setText(getString(R.string.e));
        hau.setText("|");
        if (a221().getBoolean("blocksv", false)) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
        iv1.setBackgroundResource(R.drawable.b17);
        d2 = SearchHelper.getInstance(getApplicationContext());
        Cursor res = d2.getReadableDatabase().rawQuery("SELECT * FROM " +
                Sqlite.TABLE_SEARCH +
                " ORDER BY " +
                "_id" +
                " DESC ", null);
        if (res.getCount() == 0) {
            runOnUiThread(() -> d.setVisibility(View.GONE));
        } else {
            if (ls == null) {
                ls = new ArrayList<>();
            }
            while (res.moveToNext()) {
                ls.add(res.getString(1));
            }
        }
        res.close();
        if (a221().getBoolean("showHTT", false)) {
            HistoryHelper d1 = HistoryHelper.getInstance(getApplicationContext());
            Cursor rest = d1.getReadableDatabase().rawQuery("SELECT * FROM " +
                    Sqlite.TABLE_HISTORY +
                    " ORDER BY " +
                    "_id" +
                    " DESC ", null);
            if (rest.getCount() != 0) {
                if (ls == null) {
                    ls = new ArrayList<>();
                }
                boolean bn = !a221().getBoolean("showLKS", false);
                while (rest.moveToNext()) {
                    if (bn) {
                        ls.add(rest.getString(1));
                    }
                    ls.add(rest.getString(2));
                }
            }
            rest.close();
            if (a221().getBoolean("showBKM", false)) {
                BookmarkHelper d3 = BookmarkHelper.getInstance(getApplicationContext());
                Cursor rest1 = d3.getReadableDatabase().rawQuery("SELECT * FROM " +
                        Sqlite.TABLE_BOOKMARK +
                        " ORDER BY " +
                        "_id" +
                        " DESC ", null);
                if (rest1.getCount() != 0) {
                    if (ls == null) {
                        ls = new ArrayList<>();
                    }
                    boolean bn = !a221().getBoolean("showLKS", false);
                    while (rest1.moveToNext()) {
                        if (bn) {
                            ls.add(rest1.getString(1));
                        }
                        ls.add(rest1.getString(2));
                    }
                }
                rest1.close();

            }
        }
        if (ls != null) {
            aa = new SearchAdapter(this, ls);
            if (Objects.equals(a221().getString("arrange", ""), "1z")) {
                Collections.sort(ls);
            }
            if (Objects.equals(a221().getString("arrange", ""), "7z")) {
                Collections.sort(ls, Collections.reverseOrder());
            }
            if (Objects.equals(a221().getString("arrange", ""), "60z")) {
                Collections.reverse(ls);
            }

            d.setOnItemClickListener((adapterView, view, c1, l) -> {
                search(aa.getItem(c1).toString());
                finish();
                SoftKeyboard.hide(SEAR.this, b19);
            });
            d.setOnItemLongClickListener((adapterView, view, i, l) -> {
                query23 = aa.getItem(i).toString();
                p(view);
                return true;
            });
            d.setAdapter(aa);
            p.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    aa.getFilter().filter(charSequence);
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    o();
                }

            });

        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        setActionBar(o);
        o.setElevation(5);
        if (a221().getBoolean("showS", true)) {
            d.setVisibility(View.VISIBLE);
        } else {
            d.setVisibility(View.GONE);
        }

        int d1 = Resources.getColor(this, R.color.c);
        int e = Resources.getColor(this, R.color.b);
        int f = Resources.getColor(this, R.color.j);
        int g = Resources.getColor(this, R.color.k);
        if (Objects.equals(a221().getString("hori", "30c"), "1c")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        if (Objects.equals(a221().getString("hori", "30c"), "7c")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        if (Objects.equals(a221().getString("hori", "30c"), "30c")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        }
        int flags = getWindow().getAttributes().flags;
        if (Objects.equals(a221().getString("hide", "1d"), "1d")) {
            if (flags == WindowManager.LayoutParams.FLAG_FULLSCREEN) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                m();
            }
        }
        if (Objects.equals(a221().getString("hide", "1d"), "7d")) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        p.setTypeface(type(Typeface.NORMAL));
        m11.setTypeface(type(Typeface.BOLD));
        final File fe = new File(StorageDirectory.getBackground(this));
        cd.setBackgroundResource(R.drawable.w);
        if (a221().getBoolean("webviumB", false) && fe.exists()) {
            o.setBackgroundColor(Resources.getColor(this, android.R.color.transparent));
            Runnable p155 = () -> {
                Bitmap bp = BitmapCache.getInstance().a(StorageDirectory.getBackground(this));
                runOnUiThread(() -> b19.setBackground(new BitmapDrawable(getResources(), bp)));
            };
            new Thread(p155).start();

        } else {
            o.setBackgroundResource(R.drawable.p);
        }
        if (!a221().getBoolean("autoUpdate", false)) {
            p.setTextColor(d1);
            p.setHintTextColor(f);
        } else {
            p.setTextColor(e);
            p.setHintTextColor(g);
        }

        cd.setOnClickListener(view -> SoftKeyboard.show(SEAR.this, p));

        p.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                SoftKeyboard.hide(SEAR.this, b19);
                String query = p.getText().toString();
                if (U3.b(query)) {
                    d2.c(query);
                    search(query);
                    finish();
                }
                return true;
            }
            return false;
        });
        o();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_dropdown_item_1line, webvium);
            p.setAdapter(adapter);
            p.setThreshold(4);
            p.setDropDownBackgroundDrawable(Resources.getDrawable(this, R.drawable.c12));
        if (a221().getBoolean("voice", true) && !spr()) {
            iv.setImageResource(R.drawable.c9);
            iv.setBackgroundResource(R.drawable.c6);
            iv.setVisibility(View.VISIBLE);
            iv.setOnClickListener(view -> f3());
            Animation.animate(this, R.anim.i, iv);
        } else {
            iv.setVisibility(View.GONE);
        }
        iv1.setOnClickListener(view -> {
            if (p.getText().toString().length() == 0) {
                finishAndRemoveTask();
            } else {
                p.getText().clear();
            }
        });
        ActionBar ab = getActionBar();
        if (ab != null) {
            // ab.setDisplayHomeAsUpEnabled(false);
            // ab.setDisplayShowHomeEnabled(false);
            ab.setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            if (resultCode == RESULT_OK && null != data) {
                String speechText = data.getStringExtra("a");
                d2.c(speechText);
                p.setText(speechText);
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        onNewIntent(getIntent());
    }

    @Override
    protected void onStop() {
        super.onStop();
        SoftKeyboard.hide(this, b19);
    }

    private void f3() {
        Intent a = new Intent(this, VOIC.class);
        if (a.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(a, 100);
        }
    }

    public void f4(String a) {
        Intent b = new Intent("android.intent.action.SEND");
        b.setType("text/plain");
        b.putExtra("android.intent.extra.TEXT", a);
        startActivity(Intent.createChooser(b, String.format(getString(R.string.l8), "\"" + a + "\"")));
    }

    public void f5(final String b) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(getString(R.string.e));
        a.setMessage(String.format(getString(R.string.l7), "\"" + b + "\""));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            d2.b(b);
            f6(String.format(getString(R.string.h5), b));
            k();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void f6(String a) {
        AwesomeToast.b(this, a);
    }

    private void k() {
        Runnable p15 = () -> {
            ArrayList<String> itemIdsh = new ArrayList<>();
            Cursor res = d2.getReadableDatabase().rawQuery("SELECT * FROM " +
                    Sqlite.TABLE_SEARCH +
                    " ORDER BY " +
                    "_id" +
                    " DESC ", null);
            if (res.getCount() == 0) {
                runOnUiThread(() -> d.setVisibility(View.GONE));
            } else {
                while (res.moveToNext()) {
                    itemIdsh.add(res.getString(1));
                }
                if (itemIdsh.size() == 0) {
                    runOnUiThread(() -> d.setVisibility(View.GONE));
                } else {
                    runOnUiThread(() -> {
                        aa.a(itemIdsh);
                        aa.notifyDataSetChanged();
                    });
                }
            }
            res.close();

        };
        new Thread(p15).start();
    }

    private void l() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void m() {
        View a = getWindow().getDecorView();
        a.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
    }

    private void search(String a) {
        if (a.startsWith("webvium-source:") || a.startsWith("view-source:") && (a.contains("https://") || a.contains("http://") || a.contains("file://") || a.contains("content://"))) {
            Intent it = new Intent(this, TOOL.class);
            it.putExtra("id", TOOL.TOOL_SOURCE_CODE);
            it.putExtra("dat", a);
            startActivity(it);
        } else {
            switch (a) {
                case "webvium://logcat":
                    Intents.a(this, LOGC.class);
                    break;
                case "webvium://calculator":
                    Intents.a(this, PRET.class);
                    break;
                case "webvium://credits":
                    Intents.a(this, CRED.class);
                    break;
                case "webvium://termsandcondition":
                    Intents.a(this, TERM.class);
                    break;
                case "webvium://managespace":
                    Intents.a(this, MANG.class);
                    break;
                case "webvium://privacypolicy":
                    Intents.a(this, PRIV.class);
                    break;
                case "webvium://blank":
                    send("about:blank");
                    break;
                case "webvium://rickroll":
                    send(Base64.decode("aHR0cHM6Ly93d3cueW91dHViZS5jb20vd2F0Y2g/dj1kUXc0dzlXZ1hjUQ"));
                    break;
                default:
                    send(a);
            }
        }
    }

    private void send(String sg) {
        if (getCallingActivity() != null) {
            Intents.d("value", sg, SEAR.this);
        } else {
            Intents.e(this, "value", sg, MAIN.class);
        }
    }

    private void o() {
        iv1.setImageResource(R.drawable.a14);
        Animation.animate(this, R.anim.c, iv1);
    }

    private void q(String a23) {
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
        ed.setText("");
        ed1.setText(a23);
        a.setPositiveButton(getString(R.string.i6), (a2, i) -> {
            u(ed.getText().toString(), ed1.getText().toString());
            t(getString(R.string.t2));
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

    private void p(View w) {
        if (pm == null) {
            pm = new PopupMenu(this, w);
            pm.setOnDismissListener(popupMenu -> {
                popupMenu.getMenu().clear();
                if (this.query23 != null) {
                    this.query23 = null;
                }
            });
        }
        Menu me = pm.getMenu();
        me.add(0, 0, 0, getString(R.string.o5)).setOnMenuItemClickListener(e);
        me.add(0, 1, 0, getString(R.string.a8)).setOnMenuItemClickListener(e);
        me.add(0, 2, 0, getString(R.string.u)).setOnMenuItemClickListener(e);
        if (!a221().getBoolean("showHTT", false) || !a221().getBoolean("showBKM", false)) {
            me.add(0, 3, 0, getString(R.string.k8)).setOnMenuItemClickListener(e);
            me.add(0, 6, 0, getString(R.string.u23)).setOnMenuItemClickListener(e);
        }
        SubMenu sb0 = me.addSubMenu(getString(R.string.h17));
        sb0.add(0, 4, 0, getString(R.string.h11)).setOnMenuItemClickListener(e);
        sb0.add(0, 5, 0, getString(R.string.h12)).setOnMenuItemClickListener(e);
        pm.show();
    }

    private void s(String a23) {
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
        ed.setText("");
        ed1.setText(a23);
        a.setPositiveButton(getString(R.string.i6), (a2, i) -> {
            shrt(ed.getText().toString(), ed1.getText().toString(), R.mipmap.b);
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

    private void t(String a) {
        AwesomeToast.b(this, a);

    }

    private void u(String a, String b) {
        BookmarkHelper d3 = BookmarkHelper.getInstance(getApplicationContext());
        d3.c(a, b);
    }

    private void w(final String oldTitle) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.x, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.u23));
        a.setView(c);
        final TextView ti = c.findViewById(R.id.e1);
        final EDIT ed = c.findViewById(R.id.e3);
        int e = Resources.getColor(this, R.color.c);
        int f = Resources.getColor(this, R.color.b);

        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(e);
            ti.setTextColor(e);
        } else {
            ed.setTextColor(f);
            ti.setTextColor(f);
        }
        ti.setText(getString(R.string.u24));
        ed.setText(oldTitle);

        a.setPositiveButton(getString(R.string.i6), (a2, it) -> {
            d2.f(oldTitle, ed.getText().toString());
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

                okButton.setEnabled(U3.a(ed));
            }

        });
        g.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(U3.a(ed));
    }

    @Override
    protected void onNewIntent(Intent a) {
        try {
            String sg = a.getAction();
            String sg1 = a.getStringExtra("value");
            if (sg1 != null && U3.b(sg1)) {
                p.setText(sg1);
            } else if (sg.equals(Intents.ACTION_PASTE)) {
                try {
                    String c = Clipboard.b(this);
                    if (U3.b(Objects.requireNonNull(c))) {
                        p.setText(c);
                    } else {
                        AwesomeToast.c(this, getString(R.string.t20));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    AwesomeToast.c(this, getString(R.string.t20));
                }
            }
            a.replaceExtras(new Bundle());
            a.setAction("");
            a.setData(null);
            a.setFlags(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (Objects.equals(a221().getString("hide", ""), "30d")) {
            if (hasFocus) {
                l();
            }
        }
    }
}