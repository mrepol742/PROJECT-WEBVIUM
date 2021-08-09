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

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mrepol742.webvium.app.Clipboard;
import com.mrepol742.webvium.app.Intents;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.app.SoftKeyboard;
import com.mrepol742.webvium.app.Sqlite;
import com.mrepol742.webvium.app.StorageDirectory;
import com.mrepol742.webvium.app.main.MainBaseActivity;
import com.mrepol742.webvium.bookmark.BookmarkHelper;
import com.mrepol742.webvium.download.DownloadHelper;
import com.mrepol742.webvium.history.HistoryHelper;
import com.mrepol742.webvium.search.SearchAdapter;
import com.mrepol742.webvium.search.SearchDataModel;
import com.mrepol742.webvium.search.SearchHelper;
import com.mrepol742.webvium.security.Base64;
import com.mrepol742.webvium.util.Animation;
import com.mrepol742.webvium.util.AwesomeToast;
import com.mrepol742.webvium.util.BitmapCache;
import com.mrepol742.webvium.util.TextWatcher;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/*
 * @SearchActivity
 */
public class Sear extends MainBaseActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, TextView.OnEditorActionListener, PopupMenu.OnDismissListener {
    private EditText p;
    private ListView d;
    private RelativeLayout b19;
    private SearchAdapter aa;
    private SearchHelper d2;
    private final List<SearchDataModel> ls = new ArrayList<>();
    private ImageView iv1;
    private PopupMenu pm;
    private String query23;
    public static final int SEARCH = 1;
    public static final int HISTORY = 2;
    public static final int DOWNLOAD = 3;
    public static final int BOOKMARK = 4;

    final MenuItem.OnMenuItemClickListener e = new MenuItem.OnMenuItemClickListener() {

        @Override
        public boolean onMenuItemClick(MenuItem a1) {
            switch (a1.getItemId()) {
                case 0:
                    if (Sear.this.getCallingActivity() != null) {
                        Intents.d("result", query23, Sear.this);
                    } else {
                        Intents.e(Sear.this, "value", query23, Webv.class);
                    }
                    Sear.this.finish();
                    return true;
                case 1:
                    Sear.this.f4(query23);
                    return true;
                case 2:
                    Clipboard.a(Sear.this, query23);
                    Sear.this.f6(Sear.this.getString(R.string.k9));
                    return true;
                case 3:
                    Sear.this.f5(query23);
                    return true;
                case 4:
                    Sear.this.q(query23);
                    return true;
                case 5:
                    Sear.this.s(query23);
                    return true;
                case 6:
                    Sear.this.w(query23);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int c1, long l) {
        Sear.this.search(aa.c(c1).data);
        Sear.this.finish();
        SoftKeyboard.hide(Sear.this, b19);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l){
        query23=aa.c(i).data;
        Sear.this.p(view);
        return true;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            String query = p.getText().toString();
            if (!TextUtils.isEmpty(query)) {
                Sear.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                SoftKeyboard.hide(Sear.this, b19);
                d2.c(query);
                Sear.this.search(query);
                Sear.this.finish();
                return true;
            }
        }
        return false;
    }

    @Override
    public void onDismiss(PopupMenu popupMenu) {
        popupMenu.getMenu().clear();
        if (Sear.this.query23 != null) {
            Sear.this.query23 = null;
        }
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        a225(R.layout.e);
        final Toolbar o = findViewById(R.id.o);
        p = findViewById(R.id.p);
        ImageView iv = findViewById(R.id.k19);
        iv1 = findViewById(R.id.m13);
        LinearLayout cd = findViewById(R.id.y);
        d = findViewById(R.id.a3);
        b19 = findViewById(R.id.b19);
        b19.setBackgroundResource(R.drawable.b17);
        b19.setClickable(true);
        TextView m11 = findViewById(R.id.m11);
        m11.setText(getString(R.string.e));
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
            d.setVisibility(View.GONE);
        } else {
            while (res.moveToNext()) {
                ls.add(new SearchDataModel(res.getString(1), SEARCH));
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
                boolean bn = !a221().getBoolean("showLKS", false);
                while (rest.moveToNext()) {
                    if (bn) {
                        ls.add(new SearchDataModel(rest.getString(1), HISTORY));
                    }
                    ls.add(new SearchDataModel(rest.getString(2), HISTORY));
                }
            }
            rest.close();
        }
        if (a221().getBoolean("showBKM", false)) {
            BookmarkHelper d3 = BookmarkHelper.getInstance(getApplicationContext());
            Cursor rest1 = d3.getReadableDatabase().rawQuery("SELECT * FROM " +
                    Sqlite.TABLE_BOOKMARK +
                    " ORDER BY " +
                    "_id" +
                    " DESC ", null);
            if (rest1.getCount() != 0) {
                boolean bn = !a221().getBoolean("showLKS", false);
                while (rest1.moveToNext()) {
                    if (bn) {
                        ls.add(new SearchDataModel(rest1.getString(1), BOOKMARK));
                    }
                    ls.add(new SearchDataModel(rest1.getString(2), BOOKMARK));
                }
            }
            rest1.close();
        }
        if (a221().getBoolean("showDLM", false)) {
            DownloadHelper d31 = DownloadHelper.getInstance(getApplicationContext());
            Cursor rest2 = d31.getReadableDatabase().rawQuery("SELECT * FROM " +
                    Sqlite.TABLE_DOWNLOAD +
                    " ORDER BY " +
                    "_id" +
                    " DESC ", null);
            if (rest2.getCount() != 0) {
                boolean bn12 = !a221().getBoolean("showLKS", false);
                while (rest2.moveToNext()) {
                    if (bn12) {
                        ls.add(new SearchDataModel(rest2.getString(1), DOWNLOAD));
                    }
                    ls.add(new SearchDataModel(rest2.getString(2), DOWNLOAD));
                }
            }
            rest2.close();
        }
        aa = new SearchAdapter(this, ls);
        // TODO:failed
        /*if (Objects.equals(a221().getString("arrange", ""), "1z")) {
            Collections.sort(ls);
        }

         */
        if (Objects.equals(a221().getString("arrange", "30z"), "7z")) {
            Collections.sort(ls, Collections.reverseOrder());
        }
        if (Objects.equals(a221().getString("arrange", "30z"), "60z")) {
            Collections.reverse(ls);
        }
        d.setOnItemClickListener(this);
        d.setOnItemLongClickListener(this);
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
        cd.setBackgroundResource(R.drawable.w);
        Runnable re = new Runnable() {

            @Override
            public void run() {
                final File fe = new File(StorageDirectory.getBackground(Sear.this));
                if (a221().getBoolean("webviumB", false) && fe.exists()) {
                    final Bitmap bp = BitmapCache.getInstance().a(StorageDirectory.getBackground(Sear.this));
                    Sear.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            o.setBackgroundColor(Resources.getColor(Sear.this, android.R.color.transparent));
                            b19.setBackground(new BitmapDrawable(Sear.this.getResources(), bp));
                        }
                    });
                } else {
                    Sear.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            o.setBackgroundResource(R.drawable.p);
                        }
                    });
                }
            }
        };
        new Thread(re).start();
        if (!a221().getBoolean("autoUpdate", false)) {
            p.setTextColor(d1);
            p.setHintTextColor(f);
        } else {
            p.setTextColor(e);
            p.setHintTextColor(g);
        }
        cd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SoftKeyboard.show(Sear.this, p);
            }
        });
        p.setOnEditorActionListener(this);
        o();
        if (a221().getBoolean("voice", true) && !spr()) {
            iv.setImageResource(R.drawable.c9);
            iv.setBackgroundResource(R.drawable.c6);
            iv.setVisibility(View.VISIBLE);
            iv.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent a = new Intent(Sear.this, Voic.class);
                    startActivityForResult(a, 100);
                }
            });
            Animation.animate(this, R.anim.i, iv);
        } else {
            iv.setVisibility(View.GONE);
        }
        iv1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (p.getText().toString().length() == 0) {
                    Sear.this.finishAndRemoveTask();
                } else {
                    p.getText().clear();
                }
            }
        });
        ActionBar ab = getActionBar();
        if (ab != null) {
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
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                d2.b(b);
                Sear.this.f6(String.format(Sear.this.getString(R.string.h5), b));
                Sear.this.k();
                a12.dismiss();
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

    private void f6(String a) {
        AwesomeToast.b(this, a);
    }

    // TODO: needs update
    private void k() {
        List<SearchDataModel> itemIdsh = new ArrayList<>();
        Cursor res = d2.getReadableDatabase().rawQuery("SELECT * FROM " +
                Sqlite.TABLE_SEARCH +
                " ORDER BY " +
                "_id" +
                " DESC ", null);
        if (res.getCount() == 0) {
            d.setVisibility(View.GONE);
        } else {
            while (res.moveToNext()) {
                itemIdsh.add(new SearchDataModel(res.getString(1), SEARCH));
            }
            if (itemIdsh.size() == 0) {
                d.setVisibility(View.GONE);

            } else {
                aa.a(itemIdsh);
                aa.notifyDataSetChanged();
            }
        }
        res.close();
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
            Intent it = new Intent(this, Tool.class);
            it.putExtra("id", Tool.TOOL_SOURCE_CODE);
            it.putExtra("dat", a);
            startActivity(it);
        } else {
            switch (a) {
                case "webvium://calculator":
                    Intents.a(this, Pret.class);
                    break;
                case "webvium://managespace":
                    Intents.a(this, Mana.class);
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
            Intents.d("value", sg, Sear.this);
        } else {
            Intents.e(this, "value", sg, Webv.class);
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
        ed.setText("");
        ed1.setText(a23);
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a2, int i) {
                Sear.this.u(ed.getText().toString(), ed1.getText().toString());
                Sear.this.t(Sear.this.getString(R.string.t2));
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
        okButton.setEnabled(!TextUtils.isEmpty(ed.getText().toString()) && !TextUtils.isEmpty(ed.getText().toString()));
    }

    private void p(View w) {
        if (pm == null) {
            pm = new PopupMenu(this, w);
            pm.setOnDismissListener(this);
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
        ed.setText("");
        ed1.setText(a23);
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a2, int i) {
                Sear.this.shrt(ed.getText().toString(), ed1.getText().toString(), R.mipmap.b);
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
        final Edit ed = c.findViewById(R.id.e3);
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

        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a2, int it) {
                d2.f(oldTitle, ed.getText().toString());
                Sear.this.k();
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
        TextWatcher tw = new TextWatcher(ed, okButton);
        ed.addTextChangedListener(tw);
        okButton.setEnabled(!TextUtils.isEmpty(ed.getText().toString()));
    }

    @Override
    protected void onNewIntent(Intent a) {
        try {
            String sg = a.getAction();
            String sg1 = a.getStringExtra("value");
            if (sg1 != null) {
                p.setText(sg1);
            } else if (sg.equals(Intents.ACTION_PASTE)) {
                try {
                    String c = Clipboard.b(this);
                    if (c != null) {
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
