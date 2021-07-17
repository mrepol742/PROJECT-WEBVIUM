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

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.app.main.MainReceiver;
import com.mrepol742.webvium.bookmark.BookmarkHelper;
import com.mrepol742.webvium.content.C10;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.content.IntentsFilter;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.download.DownloadHelper;
import com.mrepol742.webvium.history.HistoryHelper;
import com.mrepol742.webvium.io.Files;
import com.mrepol742.webvium.io.StorageDirectory;
import com.mrepol742.webvium.manage.space.ManageSpaceAdapter;
import com.mrepol742.webvium.manage.space.ManageSpaceDataModel;
import com.mrepol742.webvium.manifest.Permission;
import com.mrepol742.webvium.os.CountDownTimer;
import com.mrepol742.webvium.permission.PermissionHelper;
import com.mrepol742.webvium.search.SearchHelper;
import com.mrepol742.webvium.text.Html;
import com.mrepol742.webvium.util.Base64;
import com.mrepol742.webvium.view.Animation;
import com.mrepol742.webvium.widget.AwesomeToast;

import java.util.ArrayList;
import java.util.Objects;

// @Class ManageSpace
public class MANG extends BaseActivity {

    private static final int[] drawables = {
            R.drawable.d15,
            R.drawable.d16,
            R.drawable.d17,
            R.drawable.d18,
            R.drawable.d19,
            R.drawable.e14,
            R.drawable.e15
    };
    private static final int[] strings = {
            R.string.i13,
            R.string.e10,
            R.string.d12,
            R.string.d14,
            R.string.c35,
            R.string.p12,
            R.string.c36
    };
    private static final int[] drawables1 = {
            R.drawable.d15,
            R.drawable.d16,
            R.drawable.d17,
            R.drawable.d18,
            R.drawable.d19
    };
    private static final int[] strings1 = {
            R.string.i13,
            R.string.e10,
            R.string.d12,
            R.string.d14,
            R.string.c35
    };
    private final ArrayList<String> a = new ArrayList<>();
    private final ArrayList<String> b = new ArrayList<>();
    private final ArrayList<Integer> c = new ArrayList<>();
    private final ArrayList<String> d = new ArrayList<>();
    private final IntentsFilter ee = new IntentsFilter();
    private ManageSpaceAdapter w19;
    private ImageView iv1;
    private R7 r7;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 345) {
            if (resultCode == Activity.RESULT_OK) {
                Permission.check(this, Permission.STORAGE, 1);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                finishAndRemoveTask();
            }
        }
    }

    @Override
    protected void onCreate(Bundle a22) {
        theme(T_MANAGE_SPACE);
        super.onCreate(a22);
        if (a221().getBoolean("lockWn99", false)) {
            Intent it12 = new Intent(this, LOCK.class);
            startActivityForResult(it12, 345);
        } else {
            Permission.check(this, Permission.STORAGE, 1);
        }
        a2();
        a225(R.layout.a4);

        Toolbar g5 = findViewById(R.id.b7);
        TextView g6 = findViewById(R.id.b8);
        ListView a3 = findViewById(R.id.a3);
        setActionBar(g5);
        ActionBar ab = getActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);
        }
        int it = Resources.getColor(this, R.color.c);
        int it2 = Resources.getColor(this, R.color.b);
        if (!a221().getBoolean("autoUpdate", false)) {
            g6.setTextColor(it);
        } else {
            g6.setTextColor(it2);
        }
        g5.setBackgroundResource(R.drawable.p);
        g5.setNavigationIcon(R.drawable.a2);
        g6.setTypeface(type(Typeface.BOLD));
        g6.setText(getString(R.string.l14));
        g5.setNavigationOnClickListener(view -> finishAndRemoveTask());
        g5.setElevation(5);
        w19 = new ManageSpaceAdapter(this, new ManageSpaceDataModel(a, b, c, d));
        a3.setAdapter(w19);
        a3.setOnItemClickListener((a, b, c, d) -> i(c));
        iv1 = findViewById(R.id.c8);
        iv1.setBackgroundResource(R.drawable.e16);
        iv1.setImageResource(R.drawable.a23);
        iv1.setOnClickListener(view -> {
            if (Permission.check(this, Permission.STORAGE, 5)) {
                y(getString(R.string.l14), getString(R.string.u11));
            }
        });
        if (a221().getBoolean("lockWn99", false) && a221().getBoolean("scrON", false)) {
            ee.act(Intent.ACTION_SCREEN_ON);
            r7 = new R7();
            registerReceiver(r7, ee);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        a1();
        Animation.animate(this, R.anim.i, iv1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (r7 != null) {
            unregisterReceiver(r7);
        }
    }

    public long b(String as) {
        java.io.File a = new java.io.File(as);
        return f(a);
    }

    private String c(long a) {
        return Formatter.formatFileSize(this, a);
    }

    private int d(String as) {
        int f = 0;
        java.io.File a = new java.io.File(as);
        if (a.exists()) {
            String[] b = a.list();
            if (b != null) {
                f = b.length;
            }
        }
        return f;
    }

    private long e(java.io.File a) {
        return f(a);
    }

    private long e(String a) {
        return f(new java.io.File(a));
    }

    public long f(java.io.File a) {
        long b = 0;
        if (a.exists()) {
            java.io.File[] c = a.listFiles();
            if (c != null) {
                for (java.io.File file : c) {
                    if (file.isFile()) {
                        b += file.length();
                    } else {
                        b += f(file);
                    }
                }
            }
        }
        return b;
    }

    private String g(String as) {
        long h = 0;
        java.io.File a = new java.io.File(as);
        if (a.exists()) {
            h = a.length();
        }
        return Long.toString(h);
    }

    private long h(String as) {
        long h = 0;
        java.io.File a = new java.io.File(as);
        if (a.exists()) {
            h = a.length();
        }
        return h;
    }

    private void i(int a) {
        if (a == 0) {
            Runnable re = () -> {
                String sb5 = "SELECT * FROM " + "A" +
                        " ORDER BY " +
                        "_id" +
                        " DESC";
                Cursor rest1 = BookmarkHelper.getInstance(getApplicationContext()).getReadableDatabase().rawQuery(sb5, null);
                if (rest1.getCount() != 0) {
                    runOnUiThread(() -> k(getString(R.string.l14), getString(R.string.u4)));
                } else {
                    runOnUiThread(() -> r(getString(R.string.v27)));
                }
                rest1.close();
            };
            new Thread(re).start();
        } else if (a == 1) {
            m(getString(R.string.l14), getString(R.string.u5));
        } else if (a == 2) {
            Runnable re = () -> {
                String sb12 = "SELECT * FROM " + "A" +
                        " ORDER BY " +
                        "_id" +
                        " DESC";
                Cursor rest = HistoryHelper.getInstance(getApplicationContext()).getReadableDatabase().rawQuery(sb12, null);
                if (rest.getCount() != 0) {
                    runOnUiThread(() -> n(getString(R.string.l14), getString(R.string.t5)));
                } else {
                    runOnUiThread(() -> r(getString(R.string.v27)));
                }
                rest.close();
            };
            new Thread(re).start();
        } else if (a == 3) {
            Runnable re = () -> {
                String sb55 = "SELECT * FROM " + "A" +
                        " ORDER BY " +
                        "_id" +
                        " DESC";
                Cursor res = SearchHelper.getInstance(getApplicationContext()).getReadableDatabase().rawQuery(sb55, null);
                if (res.getCount() != 0) {
                    runOnUiThread(() -> o(getString(R.string.l14), getString(R.string.u6)));
                } else {
                    runOnUiThread(() -> r(getString(R.string.v27)));
                }
                res.close();
            };
            new Thread(re).start();
        } else if (a == 4) {
            x(getString(R.string.l14), getString(R.string.u7));
        } else if (a == 5) {
            if (Permission.check(this, Permission.STORAGE, 3)) {
                java.io.File fe = new java.io.File(StorageDirectory.getWebviumDir() + "/Screenshot");
                int i = 0;
                if (fe.exists() && fe.isDirectory()) {
                    i = Objects.requireNonNull(fe.list()).length;
                }
                if (i > 0) {
                    u(getString(R.string.l14), getString(R.string.u9));
                } else {
                    r(getString(R.string.v29));
                }
            }
        } else if (a == 6) {
            if (Permission.check(this, Permission.STORAGE, 4)) {
                java.io.File fe = new java.io.File(StorageDirectory.getDownloadDir());
                int i = 0;
                if (fe.exists() && fe.isDirectory()) {
                    i = Objects.requireNonNull(fe.list()).length;
                }
                if (i > 0) {
                    v(getString(R.string.l14), getString(R.string.u10));
                } else {
                    r(getString(R.string.v29));
                }
            }
        }

    }

    private void j(String a) {
        Files.delete(a);
    }

    private void k(String a1, String b1) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(a1);
        a.setMessage(b1);
        a.setPositiveButton(getString(R.string.i6), (a22, intetg) -> {
            BookmarkHelper d3 = BookmarkHelper.getInstance(getApplicationContext());
            d3.delete();
            a22.dismiss();
            a1();
        });
        a.setNegativeButton(getString(R.string.i7), (a2, intetg) -> a2.dismiss());
        a.create().show();
    }

    private void l(String a) {
        java.io.File b = new java.io.File(a);
        if (b.exists()) {
            Files.deleteAll(b);
        }
    }

    private void l(java.io.File b) {
        if (b.exists()) {
            Files.deleteAll(b);
        }
    }

    private void m(String a1, String b1) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(a1);
        a.setMessage(b1);
        a.setPositiveButton(getString(R.string.i6), (a22, intetg) -> {
            Runnable run = () -> l(StorageDirectory.getCacheDir(this));
            new Thread(run).start();
            a22.dismiss();
            a1();
        });
        a.setNegativeButton(getString(R.string.i7), (a2, intetg) -> a2.dismiss());
        a.create().show();
    }

    private void n(String a1, String b1) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(a1);
        a.setMessage(b1);
        a.setPositiveButton(getString(R.string.i6), (a22, intetg) -> {
            HistoryHelper d1 = HistoryHelper.getInstance(getApplicationContext());
            d1.delete();
            if (MAIN.bl) {
                MAIN.c63();
            }
            a22.dismiss();
            a1();
        });
        a.setNegativeButton(getString(R.string.i7), (a2, intetg) -> a2.dismiss());
        a.create().show();
    }

    private void o(String a1, String b1) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(a1);
        a.setMessage(b1);
        a.setPositiveButton(getString(R.string.i6), (a22, intetg) -> {
            SearchHelper d2 = SearchHelper.getInstance(getApplicationContext());
            d2.delete();
            a22.dismiss();
            a1();
        });
        a.setNegativeButton(getString(R.string.i7), (a2, intetg) -> a2.dismiss());
        a.create().show();
    }

    private void q(String jk) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(getString(R.string.l14));
        a.setMessage(Html.b(jk));
        a.setPositiveButton(getString(R.string.u14), (a12, intetg) -> {
            Intents.l(this, Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", Package.b(), null));
            a12.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    public void r(String a) {
        AwesomeToast.c(this, a);
    }

    public void s(String a) {
        AwesomeToast.b(this, a);
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void onRequestPermissionsResult(int a, String[] b, int[] c) {
        super.onRequestPermissionsResult(a, b, c);
        switch (a) {
            case 1:
                if (c.length > 0 && c[0] == PackageManager.PERMISSION_GRANTED) {
                    a1();
                } else {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        r(getString(R.string.u17));
                    } else {
                        q(getString(R.string.u18));
                    }
                }
                break;
            case 3:
                if (c.length > 0 && c[0] == PackageManager.PERMISSION_GRANTED) {
                    a1();
                    java.io.File fe = new java.io.File(StorageDirectory.getWebviumDir() + "/Screenshot");
                    int i = 0;
                    if (fe.exists() && fe.isDirectory()) {
                        i = Objects.requireNonNull(fe.list()).length;
                    }
                    if (i > 0) {
                        u(getString(R.string.l14), getString(R.string.u9));
                    } else {
                        r(getString(R.string.v29));
                    }
                } else {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        r(getString(R.string.u15));
                    } else {
                        q(getString(R.string.u16));
                    }
                }
                break;
            case 4:
                if (c.length > 0 && c[0] == PackageManager.PERMISSION_GRANTED) {
                    a1();
                    java.io.File fe = new java.io.File(StorageDirectory.getDownloadDir());
                    int i = 0;
                    if (fe.exists() && fe.isDirectory()) {
                        i = Objects.requireNonNull(fe.list()).length;
                    }
                    if (i > 0) {
                        v(getString(R.string.l14), getString(R.string.u10));
                    } else {
                        r(getString(R.string.v29));
                    }
                } else {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        r(getString(R.string.u15));
                    } else {
                        q(getString(R.string.u16));
                    }
                }
                break;
            case 5:
                if (c.length > 0 && c[0] == PackageManager.PERMISSION_GRANTED) {
                    a1();
                    y(getString(R.string.l14), getString(R.string.u11));
                } else {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        r(getString(R.string.u15));
                    } else {
                        q(getString(R.string.u16));
                    }
                }
                break;
        }
    }

    private void u(String a1, String b1) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(a1);
        a.setMessage(b1);
        a.setPositiveButton(getString(R.string.i6), (a22, intetg) -> {
            l(StorageDirectory.getWebviumDir() + "/Screenshot/");
            a22.dismiss();
            a1();
        });
        a.setNegativeButton(getString(R.string.i7), (a2, intetg) -> a2.dismiss());
        a.create().show();
    }

    private void v(String a1, String b1) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(a1);
        a.setMessage(b1);
        a.setPositiveButton(getString(R.string.i6), (a22, intetg) -> {
            l(StorageDirectory.getWebviumDir() + "/Downloads/");
            a22.dismiss();
            a1();
        });
        a.setNegativeButton(getString(R.string.i7), (a2, intetg) -> a2.dismiss());
        a.create().show();
    }

    private void x(String a1, String b1) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(a1);
        a.setMessage(b1);
        a.setPositiveButton(getString(R.string.i6), (a22, intetg) -> {
            C10.a(this, "com.mrepol742.webvium.activity.alias.PRE", PackageManager.COMPONENT_ENABLED_STATE_DISABLED);
            C10.a(this, "com.mrepol742.webvium.activity.alias.MAY", PackageManager.COMPONENT_ENABLED_STATE_ENABLED);
            getSharedPreferences("a", 0).edit().clear().apply();
            a221().edit().clear().apply();
            getSharedPreferences("wv", 0).edit().clear().apply();
            a22.dismiss();
            System.exit(0);

        });
        a.setNegativeButton(getString(R.string.i7), (a2, intetg) -> a2.dismiss());
        a.create().show();
    }

    private void y(String a1, String b1) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(a1);
        a.setMessage(b1);
        a.setPositiveButton(getString(R.string.i6), (a22, intetg) -> {
            C10.a(this, "com.mrepol742.webvium.activity.alias.PRE", PackageManager.COMPONENT_ENABLED_STATE_DISABLED);
            C10.a(this, "com.mrepol742.webvium.activity.alias.MAY", PackageManager.COMPONENT_ENABLED_STATE_ENABLED);
            getSharedPreferences("wv", 0).edit().clear().apply();
            a221().edit().clear().apply();
            getSharedPreferences("a", 0).edit().clear().apply();
            HistoryHelper d1 = HistoryHelper.getInstance(getApplicationContext());
            d1.delete();
            SearchHelper d2 = SearchHelper.getInstance(getApplicationContext());
            d2.delete();
            BookmarkHelper d3 = BookmarkHelper.getInstance(getApplicationContext());
            d3.delete();
            DownloadHelper d9 = DownloadHelper.getInstance(getApplicationContext());
            d9.delete();
            PermissionHelper d91 = PermissionHelper.getInstance(getApplicationContext());
            d91.delete();
            if (Build.VERSION.SDK_INT < 29) {
                l(StorageDirectory.getWebviumDir());
            }
            if (MAIN.bl2) {
                MAIN.c63();
            }
            a22.dismiss();
            System.exit(0);
        });
        a.setNegativeButton(getString(R.string.i7), (a2, inetg) -> a2.dismiss());
        a.create().show();
    }

    private void a1() {
            final ArrayList<String> a = new ArrayList<>();
            final ArrayList<String> b = new ArrayList<>();
            final ArrayList<Integer> c = new ArrayList<>();
            final ArrayList<String> d = new ArrayList<>();
            for (int i : getStrings()) {
                a.add(getString(i));
            }
            for (int i5 : getDrawables()) {
                c.add(i5);
            }
            for (int i5 = 0; i5 < 5; i5++) {
                d.add("");
            }
            String sb5 = "SELECT * FROM " + "A" +
                    " ORDER BY " +
                    "_id" +
                    " DESC";
            Cursor rest1 = BookmarkHelper.getInstance(getApplicationContext()).getReadableDatabase().rawQuery(sb5, null);
            String sb55 = "SELECT * FROM " + "A" +
                    " ORDER BY " +
                    "_id" +
                    " DESC";
            Cursor res = SearchHelper.getInstance(getApplicationContext()).getReadableDatabase().rawQuery(sb55, null);
            String sb12 = "SELECT * FROM " + "A" +
                    " ORDER BY " +
                    "_id" +
                    " DESC";
            Cursor rest = HistoryHelper.getInstance(getApplicationContext()).getReadableDatabase().rawQuery(sb12, null);
            int cont = rest1.getCount();
            if (cont == 0) {
                b.add(getString(R.string.v27));
            } else {
                b.add(String.format(getResources().getQuantityString(R.plurals.v25, cont), cont));
            }
            b.add(getString(R.string.c38) + c(e(StorageDirectory.getCacheDir(this).toString())));
            int cont1 = rest.getCount();
            if (cont1 == 0) {
                b.add(getString(R.string.v27));
            } else {
                b.add(String.format(getResources().getQuantityString(R.plurals.v25, cont1), cont1));
            }
            int cont11 = res.getCount();
            if (cont11 == 0) {
                b.add(getString(R.string.v27));
            } else {
                b.add(String.format(getResources().getQuantityString(R.plurals.v25, cont11), cont11));
            }
            b.add(getString(R.string.c38) + c(b(StorageDirectory.getSharedPref(this))));
            rest1.close();
            res.close();
            rest.close();
            if (Build.VERSION.SDK_INT < 29) {
                boolean bn = Permission.checkOnly(this, Permission.STORAGE);
                if (bn) {
                    int lg = a7(Package.c() + "/Screenshot/");
                    int lg1 = a7(Package.c() + "/Downloads/");
                    if (lg == 0) {
                        b.add(getString(R.string.v29));
                    } else{
                        b.add(String.format(getResources().getQuantityString(R.plurals.v27, lg), lg));
                        b.add(String.format(getResources().getQuantityString(R.plurals.v28, lg),
                                c(b(StorageDirectory.getWebviumDir() + "/Screenshot/"))));
                    }
                    if (lg1 == 0) {
                        b.add(getString(R.string.v29));
                    } else {
                        b.add(String.format(getResources().getQuantityString(R.plurals.v27, lg1), lg1));
                        b.add(String.format(getResources().getQuantityString(R.plurals.v28, lg1),
                                c(b(StorageDirectory.getDownloadDir()))));
                    }
                } else {
                    b.add("Storage permission is required");
                    b.add("Storage permission is required");
                }
            }
                w19.a(new ManageSpaceDataModel(a, b, c, d));
                w19.notifyDataSetChanged();
    }

    private void a2() {
            for (int i5 : getDrawables()) {
                c.add(i5);
            }
            for (int i5 = 0; i5 < 5; i5++) {
                d.add("");
            }
            for (int i : getStrings()) {
                a.add(getString(i));
            }
            String sb5 = "SELECT * FROM " + "A" +
                    " ORDER BY " +
                    "_id" +
                    " DESC";
            Cursor rest1 = BookmarkHelper.getInstance(getApplicationContext()).getReadableDatabase().rawQuery(sb5, null);
            String sb55 = "SELECT * FROM " + "A" +
                    " ORDER BY " +
                    "_id" +
                    " DESC";
            Cursor res = SearchHelper.getInstance(getApplicationContext()).getReadableDatabase().rawQuery(sb55, null);
            String sb12 = "SELECT * FROM " + "A" +
                    " ORDER BY " +
                    "_id" +
                    " DESC";
            Cursor rest = HistoryHelper.getInstance(getApplicationContext()).getReadableDatabase().rawQuery(sb12, null);
            int cont = rest1.getCount();
            if (cont == 0) {
                b.add(getString(R.string.v27));
            } else {
                b.add(String.format(getResources().getQuantityString(R.plurals.v25, cont), cont));
            }
            b.add(getString(R.string.c38) + c(e(StorageDirectory.getCacheDir(this).toString())));
            int cont1 = rest.getCount();
            if (cont1 == 0) {
                b.add(getString(R.string.v27));
            } else {
                b.add(String.format(getResources().getQuantityString(R.plurals.v25, cont1), cont1));
            }
            int cont11 = res.getCount();
            if (cont11 == 0) {
                b.add(getString(R.string.v27));
            } else {
                b.add(String.format(getResources().getQuantityString(R.plurals.v25, cont11), cont11));
            }
            b.add(getString(R.string.c38) + c(b(StorageDirectory.getSharedPref(this))));
            rest1.close();
            res.close();
            rest.close();
             if (Build.VERSION.SDK_INT < 29) {
                boolean bn = Permission.checkOnly(this, Permission.STORAGE);
                if (bn) {
                    int lg = a7(Package.c() + "/Screenshot/");
                    int lg1 = a7(Package.c() + "/Downloads/");
                    if (lg == 0) {
                        b.add(getString(R.string.v29));
                    } else{
                        b.add(String.format(getResources().getQuantityString(R.plurals.v27, lg), lg));
                        b.add(String.format(getResources().getQuantityString(R.plurals.v28, lg),
                                c(b(StorageDirectory.getWebviumDir() + "/Screenshot/"))));
                    }
                    if (lg1 == 0) {
                        b.add(getString(R.string.v29));
                    } else {
                        b.add(String.format(getResources().getQuantityString(R.plurals.v27, lg1), lg1));
                        b.add(String.format(getResources().getQuantityString(R.plurals.v28, lg1),
                                c(b(StorageDirectory.getDownloadDir()))));
                    }
                } else {
                    b.add("Storage permission is required");
                    b.add("Storage permission is required");
                }
            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu a) {
        a.add(0, 0, 0, getString(R.string.a8));
        a.add(0, 1, 0, getString(R.string.f14));
        return super.onCreateOptionsMenu(a);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem a) {
        switch (a.getItemId()) {
            case 0:
                a3();
                return true;
            case 1:
                // TODO
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, "Test");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
                startActivity(Intent.createChooser(intent, "Send Email"));
                return true;
            default:
                return super.onOptionsItemSelected(a);
        }
    }

    private void a3() {
        Intent b = new Intent("android.intent.action.SEND");
        b.setType("text/plain");
        b.putExtra("android.intent.extra.TEXT", String.format(getString(R.string.f33), Base64.decode("aHR0cHM6Ly9tcmVwb2w3NDIuZ2l0aHViLmlvL1BST0pFQ1QtV0VCVklVTS8")));
        startActivity(Intent.createChooser(b, String.format(getString(R.string.l8), "\"" + Package.c() + "\"")));
    }

    public void a6(int vr) {
        AlertDialog.Builder c = new AlertDialog.Builder(this);
        LayoutInflater d = getLayoutInflater();
        View e = d.inflate(R.layout.a12, null);
        c.setCancelable(false);
        c.setView(e);
        TextView f = e.findViewById(R.id.g1);
        f.setText(getString(R.string.o1));
        if (!a221().getBoolean("autoUpdate", false)) {
            if (vr == 1) {
                f.setTextColor(Resources.getColor(this, R.color.b));
            } else {
                f.setTextColor(Resources.getColor(this, R.color.c));
            }
        } else {
            if (vr == 1) {
                f.setTextColor(Resources.getColor(this, R.color.c));
            } else {
                f.setTextColor(Resources.getColor(this, R.color.b));
            }
        }
        AlertDialog j5 = c.create();
        O5 timer = new O5(2000, 2000, j5);
        timer.start();
        j5.show();
    }

    private void a55() {
        finish();
        SharedPreferences c56 = getSharedPreferences("wv", 0);
        Intent it = new Intent(this, MAIN.class);
        it.putExtra("value", c56.getString("MyURL", ""));
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(it);
    }

    private int a7(String sg) {
        return d(StorageDirectory.a() + "/" + sg);
    }

    private int[] getDrawables() {
        if (Build.VERSION.SDK_INT < 29) {
            return drawables;
        }
        return drawables1;
    }

    private int[] getStrings() {
        if (Build.VERSION.SDK_INT < 29) {
            return strings;
        }
        return strings1;
    }

    private class R7 extends MainReceiver {

        @Override
        public void onReceive(Context a, Intent b) {
            super.onReceive(a, b);
            String sg = b.getAction();
            if (sg.equals(Intent.ACTION_SCREEN_ON)) {
                if (a221().getBoolean("lockWn99", false)) {
                    Intent it = new Intent(a, LOCK.class);
                    startActivityForResult(it, 34);
                    overridePendingTransition(R.anim.f, R.anim.b);
                }
            }
        }
    }

    class O5 extends CountDownTimer {
        AlertDialog a5;

        public O5(long a, long b, AlertDialog gj) {
            super(a, b);
            a5 = gj;
        }

        @Override
        public void onTick(long l) {

        }

        @Override
        public void onFinish() {
            a5.dismiss();
            a5 = null;
            a55();

        }
    }
}