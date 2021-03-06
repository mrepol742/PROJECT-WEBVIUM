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

package com.mrepol742.webvium.app.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mrepol742.webvium.Lock;
import com.mrepol742.webvium.Webv;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.app.Package;
import com.mrepol742.webvium.app.Sqlite;
import com.mrepol742.webvium.app.base.BasePreferenceFragment;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.util.FileUtil;
import com.mrepol742.webvium.util.AwesomeToast;
import com.mrepol742.webvium.util.JSON;

public class BackupFragment extends BasePreferenceFragment implements Preference.OnPreferenceClickListener {
    private final IntentFilter is = new IntentFilter();
    private R7 r7;
    private static final int SEARCH = 10;
    private static final int DOWNLOAD = 11;
    private static final int HISTORY = 12;
    private static final int BOOKMARK = 13;
    private static final int SETTINGS = 14;
    private static final int APK = 15;
    private Sqlite sql;

    @Override
    public boolean onPreferenceClick(Preference a123) {
        switch (a123.getKey()) {
            case "res":
                BackupFragment.this.a6();
                return true;
            case "ets":
                back("settings.json", SETTINGS);
                return true;
            case "se":
                Cursor resa = sql.getReadableDatabase().rawQuery("SELECT * FROM " + Sqlite.TABLE_SEARCH + " ORDER BY " + "_id" + " DESC", null);
                if (resa.getCount() == 0) {
                    g(BackupFragment.this.getString(R.string.v27));
                } else {
                    back(getName(4), SEARCH);
                }
                resa.close();
                return true;
            case "he":
                Cursor resb = sql.getReadableDatabase().rawQuery("SELECT * FROM " + Sqlite.TABLE_HISTORY + " ORDER BY " + "_id" + " DESC", null);
                if (resb.getCount() == 0) {
                    g(BackupFragment.this.getString(R.string.v27));
                } else {
                    back(getName(2), HISTORY);
                }
                resb.close();
                return true;
            case "be":
                Cursor resc = sql.getReadableDatabase().rawQuery("SELECT * FROM " + Sqlite.TABLE_BOOKMARK + " ORDER BY " + "_id" + " DESC", null);
                if (resc.getCount() == 0) {
                    g(BackupFragment.this.getString(R.string.v27));
                } else {
                    back(getName(0), BOOKMARK);
                }
                resc.close();
                return true;
            case "do":
                Cursor resd = sql.getReadableDatabase().rawQuery("SELECT * FROM " + Sqlite.TABLE_DOWNLOAD + " ORDER BY " + "_id" + " DESC", null);
                if (resd.getCount() == 0) {
                    g(BackupFragment.this.getString(R.string.v27));
                } else {
                    back(getName(1), DOWNLOAD);
                }
                resd.close();
                return true;
            case "bcP":
                try {
                    back(Package.c() + " " + Package.e(getActivity()) + ".apk", APK);
                } catch (Exception en) {
                    en.printStackTrace();
                }
                return true;
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 345 || resultCode != Activity.RESULT_OK) {
            getActivity().getFragmentManager().popBackStack();
        }
        if (data.getData() == null) {
            return;
        }
        switch (requestCode) {
            case APK:
                FileUtil.write(getActivity().getContentResolver(), data.getData());
                break;
            case SETTINGS:
                FileUtil.write(getActivity().getContentResolver(), data.getData(), JSON.toString(PreferenceManager.getDefaultSharedPreferences(BackupFragment.this.getActivity()).getAll()));
                break;
            case SEARCH:
                Cursor resa = sql.getWritableDatabase().rawQuery("SELECT * FROM " + Sqlite.TABLE_SEARCH + " ORDER BY " + "_id" + " DESC", null);
                FileUtil.write(getActivity().getContentResolver(), data.getData(), JSON.toString(resa, 4));
                resa.close();
                break;
            case HISTORY:
                Cursor resb = sql.getWritableDatabase().rawQuery("SELECT * FROM " + Sqlite.TABLE_HISTORY + " ORDER BY " + "_id" + " DESC", null);
                FileUtil.write(getActivity().getContentResolver(), data.getData(), JSON.toString(resb, 2));
                resb.close();
                break;
            case BOOKMARK:
                Cursor resc = sql.getWritableDatabase().rawQuery("SELECT * FROM " + Sqlite.TABLE_BOOKMARK + " ORDER BY " + "_id" + " DESC", null);
                FileUtil.write(getActivity().getContentResolver(), data.getData(), JSON.toString(resc, 0));
                resc.close();
                break;
            case DOWNLOAD:
                Cursor resd = sql.getWritableDatabase().rawQuery("SELECT * FROM " + Sqlite.TABLE_DOWNLOAD + " ORDER BY " + "_id" + " DESC", null);
                FileUtil.write(getActivity().getContentResolver(), data.getData(), JSON.toString(resd, 1));
                resd.close();
                break;
        }
    }

    @Override
    public void onCreate(Bundle b1) {
        super.onCreate(b1);
        if (a221().getBoolean("lockWn99", false) && a221().getBoolean("scrON", false)) {
            is.addAction(Intent.ACTION_SCREEN_ON);
            r7 = new R7();
            getActivity().registerReceiver(r7, is);
        }
        a5(R.xml.x);
        sql = Sqlite.getInstance(getActivity().getApplicationContext());
        Preference n = findPreference("res");
        n.setOnPreferenceClickListener(this);
        Preference l = findPreference("ets");
        l.setOnPreferenceClickListener(this);
        Preference l1 = findPreference("se");
        l1.setOnPreferenceClickListener(this);
        Preference l11 = findPreference("he");
        l11.setOnPreferenceClickListener(this);
        Preference l111 = findPreference("be");
        l111.setOnPreferenceClickListener(this);
        Preference l111444 = findPreference("do");
        l111444.setOnPreferenceClickListener(this);
        Preference asd = findPreference("bcP");
        asd.setOnPreferenceClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (r7 != null) {
            getActivity().unregisterReceiver(r7);
        }
    }

    private void a6() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.t6));
        a.setMessage(getString(R.string.b24));
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                SharedPreferences.Editor b = BackupFragment.this.a221().edit();
                b.clear();
                b.apply();
                BackupFragment.this.t();
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


    private void t() {
        AlertDialog.Builder c = new AlertDialog.Builder(getActivity());
        LayoutInflater d = LayoutInflater.from(getActivity());
        View e = d.inflate(R.layout.a12, null);
        c.setCancelable(false);
        c.setView(e);
        TextView f = e.findViewById(R.id.g1);
        f.setText(getString(R.string.o1));
        if (!a221().getBoolean("autoUpdate", false)) {
            f.setTextColor(Resources.getColor(getActivity(), R.color.c));
        } else {
            f.setTextColor(Resources.getColor(getActivity(), R.color.b));
        }
        AlertDialog j5 = c.create();
        O5 timer = new O5(2000, 2000, j5);
        timer.start();
        j5.show();
    }

    private void g(String a) {
        AwesomeToast.b(getActivity(), a);
    }

    private String getName(int id) {
        if (id == 0) {
            return "bookmark.json";
        } else if (id == 1) {
            return "download.json";
        } else if (id == 2) {
            return "history.json";
        }
        return "search.json";
    }

    private void back(String name, int id) {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/json");
        intent.putExtra(Intent.EXTRA_TITLE, name);
        startActivityForResult(intent, id);
    }

    private class R7 extends BroadcastReceiver {

        @Override
        public void onReceive(Context a, Intent b) {
            String sg = b.getAction();
            if (sg.equals(Intent.ACTION_SCREEN_ON)) {
                if (a221().getBoolean("lockWn99", false)) {
                    Intent it = new Intent(a, Lock.class);
                    startActivityForResult(it, 345);
                    getActivity().overridePendingTransition(R.anim.f, R.anim.b);
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
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            a5.dismiss();
            a5 = null;
            getActivity().finish();
            SharedPreferences c56 = getActivity().getSharedPreferences("wv", 0);
            Intent it = new Intent(getActivity(), Webv.class);
            it.putExtra("value", c56.getString("MyURL", ""));
            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(it);
        }
    }
}